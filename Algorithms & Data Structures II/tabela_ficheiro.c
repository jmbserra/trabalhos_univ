#include "tabela_ficheiro.h"

/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////

/*
	Algoritmo de hashing usado -> djb2
  	http://www.cse.yorku.ca/~oz/hash.html
*/

unsigned int hashing(char *string)
{  
    unsigned int hash = 5381;
    int c;
 
    while ((c = *string++))
        hash = ((hash << 5) + hash) + c;
       
    hash %= T_HASH_UTILIZADORES;
   
    return hash;
}

/////////////////////////////////////////////////////////////////
/*
Se não existir, cria um ficheiro (Tabela_de_Utilizadores) em disco com tamanho = 1,100,000
onde estarão guardados o nome, numero msgs enviadas, numero de seguidores,
numero de seguidos, mensagens enviadas e os users que segue(nick e numero de mensagens lidas respetivamente) de cada utilizador.

Retorna um inteiro do ficheiro(Tabela_de_Utilizadores).
*/
/////////////////////////////////////////////////////////////////

int criar_ficheiro_principal() 
{
    int ficheiro = open(NOME_DO_FICHEIRO, O_RDWR, S_IRUSR | S_IWUSR);

    if (ficheiro == -1) 
    {
    	ficheiro = open(NOME_DO_FICHEIRO, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    	return  ficheiro;
    }
    return ficheiro;
}

/////////////////////////////////////////////////////////////////
/*
Aloca espaço em memória ram para uma hashtable que contém as seguintes informações dos users:
	-nick, posição e ativo/inativo

Retorna um apontador da hashtable em memória.
*/
/////////////////////////////////////////////////////////////////

struct info_nicks * criar_mem_nicks()
{
	struct info_nicks * memoria_nicks = malloc(T_HASH_UTILIZADORES * sizeof(struct info_nicks));
	return memoria_nicks;
}

/////////////////////////////////////////////////////////////////
/*
Acede à posição do ficheiro (Tabela_de_Nicks) em disco que se encontra a seguir da hashtable (info_nicks), que contém um inteiro
que representa o número de utilizadores que a hashtable contém no momento.
Faz read desse inteiro para numero_de_utilizadores.
*/
/////////////////////////////////////////////////////////////////

void levantar_numero_de_utilizadores(int * numero_de_utilizadores, int ficheiro_nicks)
{
    lseek(ficheiro_nicks, (T_HASH_UTILIZADORES + 1) * sizeof(struct info_nicks), SEEK_SET);
    read(ficheiro_nicks, numero_de_utilizadores, sizeof(int));
}

/////////////////////////////////////////////////////////////////
/*
Acede à posição do ficheiro (Tabela_de_Nicks) em disco que se encontra a seguir da hashtable (info_nicks), que contém um inteiro
que representa o número de utilizadores que a hashtable contém no momento.
Faz write de numero_de_utilizadores para essa posição.
*/
/////////////////////////////////////////////////////////////////

void carregar_numero_de_utilizadores(int * numero_de_utilizadores, int ficheiro_nicks)
{
    lseek(ficheiro_nicks, (T_HASH_UTILIZADORES + 1) * sizeof(struct info_nicks), SEEK_SET);
    write(ficheiro_nicks, numero_de_utilizadores, sizeof(int));
}

/////////////////////////////////////////////////////////////////
/*
Faz read da hashtable (info_nicks) do ficheiro (Tabela_de_Nicks) para o argumento memoria_nicks (hashtable).
*/
/////////////////////////////////////////////////////////////////

void levantar_mem_nicks(struct info_nicks * memoria_nicks, int ficheiro_nicks)
{
	lseek(ficheiro_nicks, 0 * sizeof(struct info_nicks), SEEK_SET);
    read(ficheiro_nicks, memoria_nicks, T_HASH_UTILIZADORES * sizeof(struct info_nicks));
}

/////////////////////////////////////////////////////////////////
/*
Faz write do argumento memoria_nicks (hashtable) para o ficheiro (Tabela_de_Nicks) em disco .
*/
/////////////////////////////////////////////////////////////////

void carregar_mem_nicks(struct info_nicks * memoria_nicks, int ficheiro_nicks)
{
	lseek(ficheiro_nicks, 0 * sizeof(struct info_nicks), SEEK_SET);
    write(ficheiro_nicks, memoria_nicks, T_HASH_UTILIZADORES * sizeof(struct info_nicks));
}

/////////////////////////////////////////////////////////////////
/*
Se não existir, cria um ficheiro (Tabela_de_Nicks) em disco com tamanho = 3,000,017
onde estarão guardados o nick, a posicao do resto dos dados no ficheiro (Tabela_de_Utilizadores) e ativo/inativo de cada utilizador.
Também chama a funçao levantar_mem_nicks.

Retorna um inteiro do ficheiro (Tabela_de_Nicks)
*/
/////////////////////////////////////////////////////////////////

int criar_ficheiro_nicks(struct info_nicks * memoria_nicks) 
{
    int ficheiro_nicks = open(NOME_DO_FICHEIRO_NICKS, O_RDWR, S_IRUSR | S_IWUSR);

    if (ficheiro_nicks == -1) 
    {
    	ficheiro_nicks = open(NOME_DO_FICHEIRO_NICKS, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    	return ficheiro_nicks;
    }
    levantar_mem_nicks(memoria_nicks, ficheiro_nicks);
    return ficheiro_nicks;
}

/////////////////////////////////////////////////////////////////
/*
Chama as funções carregar_mem_nicks e carregar_numero_de_utilizadores.
Liberta a memoria ocupada pela hastable (info_nicks) e fecha os 2 ficheiros (Tabela_de_Nicks e Tabela_de_Utilizadores).
*/
/////////////////////////////////////////////////////////////////

void fechar_ficheiro(struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro, int ficheiro_nicks)
{
	carregar_mem_nicks(memoria_nicks, ficheiro_nicks);
	carregar_numero_de_utilizadores(numero_de_utilizadores, ficheiro_nicks);
	free(memoria_nicks);
	close(ficheiro);
	close(ficheiro_nicks);
}

/////////////////////////////////////////////////////////////////
/*
Escreve, na posicao do ficheiro (Tabela_de_Utilizadores), o argumento utilizador. 
*/
/////////////////////////////////////////////////////////////////

void escrever_no_ficheiro(struct Utilizador utilizador, int posicao, int ficheiro)
{
	lseek(ficheiro, posicao * sizeof(struct Utilizador), SEEK_SET);
    write(ficheiro, &utilizador, sizeof(struct Utilizador));
}

/////////////////////////////////////////////////////////////////
/*
Lê, da posicao do ficheiro (Tabela_de_Utilizadores), para a variavel utilizador.

Retorna a variavel utilizador.
*/
/////////////////////////////////////////////////////////////////

struct Utilizador ler_do_ficheiro(int posicao, int ficheiro)
{
	struct Utilizador utilizador;

	lseek(ficheiro, posicao * sizeof(struct Utilizador), SEEK_SET);
    read(ficheiro, &utilizador, sizeof(struct Utilizador));

    return utilizador;
}

/////////////////////////////////////////////////////////////////
/*
Percorre, por ordem crecente, cada posição de memoria_nicks, a partir de posicao (valor de hash do nick):
	- se encontrar o nick na respectiva posicao, retorna a posicao;
	- se encontrar o nick na respectiva posicao, mas tiver sido removido, retorna -1;
	- se nao encontrar o nick, retorna -2. 

A posicao incrementa caso não entre em nenhum destes casos. 
*/
/////////////////////////////////////////////////////////////////

int pesquisar_posicao_do_utilizador(char nick[], struct info_nicks * memoria_nicks)
{
	int posicao = hashing(nick);

	int contador = 0;

	struct info_nicks temporario;

	while(1)
	{
		temporario = memoria_nicks[posicao];

		if (strcmp(temporario.nick, nick) == 0 && temporario.activo == 1) // acha
            return posicao;

        else if (strcmp(temporario.nick, nick) == 0 && temporario.activo == -1) // acha mas está removido
            return -1;

        else if ((temporario.activo != 1 && temporario.activo != -1) || (contador == T_HASH_UTILIZADORES - 1)) // nao acha
        	return -2;

       	else if (posicao == T_HASH_UTILIZADORES - 1) // dar a volta
       		posicao = 0;

       	posicao ++;
       	contador ++;
	}
}

/////////////////////////////////////////////////////////////////
/*
Percorre, por ordem crecente, cada posição de memoria_nicks, a partir de posicao (valor de hash do nick):
	- se existir um nick igual na respectiva posicao, retorna -1;
	- se encontrar uma posicao desocupada, escreve na hashtable (memoria_nicks), na posicao respectiva, o nick, a posicao onde vai ficar no disco e o activo, e chama a função escrever_no_ficheiro, 
	onde o argumento posicao é o numero de utilizadores existentes na hashtable memoria_nicks. Retorna essa posicao.

A posicao incrementa caso não entre em nenhum destes casos. 
*/
/////////////////////////////////////////////////////////////////

int inserir_utilizador_no_ficheiro(char nick[], struct Utilizador utilizador, struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro)
{
	int posicao = hashing(nick);
	struct info_nicks temporario;

	while(1)
	{
		temporario = memoria_nicks[posicao];

		if (strcmp(temporario.nick, nick) == 0) // existe um igual
			return -1;

		else if (temporario.activo != 1 && temporario.activo != -1) // posicao vazia
		{
			strcpy(memoria_nicks[posicao].nick, nick);
			memoria_nicks[posicao].activo = 1;
			memoria_nicks[posicao].posicao = *numero_de_utilizadores;

			escrever_no_ficheiro(utilizador, *numero_de_utilizadores, ficheiro);
			*numero_de_utilizadores = *numero_de_utilizadores + 1;
			
			return posicao;
		}

		else if (posicao == T_HASH_UTILIZADORES - 1)
			posicao = 0;

		posicao ++;
	}
	return -2;
}

/////////////////////////////////////////////////////////////////
/*
Chama função pesquisar_posicao_de_utilizador, acha a posicao onde se encontra, acede-a na memerio_nicks (hashtable) e altera o activo para -1, retorna a respectiva posição
Caso não encontre retorna -1 ou -2.
*/
/////////////////////////////////////////////////////////////////

int remover_utilizador_no_ficheiro(char nick[], struct info_nicks * memoria_nicks)
{
	int posicao = pesquisar_posicao_do_utilizador(nick, memoria_nicks);

	if (posicao != -1 && posicao != -2)
	{
		memoria_nicks[posicao].activo = -1;
		return posicao;
	}
	return posicao;
}