#include "programa.h"

/////////////////////////////////////////////////////////////////
/*
Esta função utiliza um algoritmo de insert_sort para ordenar um array de strings. 
*/
/////////////////////////////////////////////////////////////////

void inserir_por_ordem(struct seguir lista_de_seguidos[], int num_seguidos)
{	
	int i, j;
	struct seguir temporario;

	for (i = 1; i < num_seguidos; i++)
	{
		temporario = lista_de_seguidos[i];
		j = i-1;

		while((strcmp(temporario.nick, lista_de_seguidos[j].nick) < 0) && (j >= 0))
		{
			lista_de_seguidos[j+1] = lista_de_seguidos[j];
			--j;
		}
		lista_de_seguidos[j+1] = temporario;
	}
}

/////////////////////////////////////////////////////////////////
/*
Algoritmo de pesquisa binaria.
*/
/////////////////////////////////////////////////////////////////

int pesquisa_binaria(char nick[T_NICK], struct seguir lista_de_seguidos[], int i, int j)
{
    if (i > j)
        return -1;      

    int base = (i + j) / 2; 

    int aux = strcmp(nick, lista_de_seguidos[base].nick);
    
    if (aux < 0)
        return pesquisa_binaria(nick, lista_de_seguidos, i, base - 1);
    
    if (aux > 0)
        return pesquisa_binaria(nick, lista_de_seguidos, base + 1, j);
    
    return base;
}

int procurar_seguido(char nick[T_NICK], int tamanho, struct seguir lista_de_seguidos[])
{
    return pesquisa_binaria(nick, lista_de_seguidos, 0, tamanho - 1);
}

/////////////////////////////////////////////////////////////////
/* 
Esta função cria um utilizador, chama a função inserir_utilizador_no_ficheiro 
e imprime uma frase se o utilizador foi criado corretamente e outra caso nao tenha. 
*/
/////////////////////////////////////////////////////////////////

void criar_utilizador(char nick[T_NICK], char nome[T_NOME], struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro)
{	
	struct Utilizador utilizador;

	strcpy(utilizador.nome, nome);
	utilizador.num_seguidos = 0;
	utilizador.num_enviadas = 0;
	utilizador.num_seguidores = 0;

	int posicao = inserir_utilizador_no_ficheiro(nick, utilizador, memoria_nicks, numero_de_utilizadores, ficheiro);

	if (posicao != -1 && posicao != -2)
		printf("+ utilizador %s criado\n", nick);		
	else
		printf("+ nick %s usado previamente\n", nick);
}

/////////////////////////////////////////////////////////////////
/* 
Esta função chama a função pesquisar_posicao_do_utilizador para tentar encontrar o utilizador.
Caso encontre chama a função remover_utilizador_no_ficheiro e impreme uma frase respectiva.
Caso nao encontre imprime uma outra frase.
*/
/////////////////////////////////////////////////////////////////

void remover_utilizador(char nick[T_NICK], struct info_nicks * memoria_nicks)
{
	int posicao = pesquisar_posicao_do_utilizador(nick, memoria_nicks);

	if (posicao != -1 && posicao != -2)
	{
		remover_utilizador_no_ficheiro(nick, memoria_nicks);
		printf("+ utilizador %s removido\n" , nick);
	}
	else
		printf("+ utilizador %s inexistente\n", nick);
}

/////////////////////////////////////////////////////////////////
/* 
Esta função chama a função pesquisar_posicao_do_utilizador para tentar encontrar o utilizador.
Caso encontre chama a função ler_do_ficheiro para aceder ao utilizador. Adiciona 1 ao numero de mensagens e adiciona ao array de mensagens a nova mensagem.
Caso nao encontre imprime uma frase respectiva.
*/
/////////////////////////////////////////////////////////////////

void enviar_mensagem_utilizador(char nick[T_NICK], char msg[T_MSG], struct info_nicks * memoria_nicks, int ficheiro)
{
	int posicao = pesquisar_posicao_do_utilizador(nick, memoria_nicks);

	if (posicao != -1 && posicao != -2)
	{	
		struct Utilizador temporario = ler_do_ficheiro(memoria_nicks[posicao].posicao, ficheiro);
		strcpy(temporario.msg_enviadas[temporario.num_enviadas % T_TAB_MSG], msg);
		temporario.num_enviadas++;
		
		escrever_no_ficheiro(temporario, memoria_nicks[posicao].posicao, ficheiro);
	}
	else
		printf("+ utilizador %s inexistente\n", nick);
}

/////////////////////////////////////////////////////////////////
/* 
Esta função chama a função pesquisar_posicao_do_utilizador para tentar encontrar o utilizador.
Caso encontre chama a função ler_do_ficheiro para aceder ao utilizador. Imprime alguns dos seus dados. 
Caso nao encontre imprime uma frase respectiva.
*/
/////////////////////////////////////////////////////////////////

void informacao_utilizador(char nick[T_NICK], struct info_nicks * memoria_nicks, int ficheiro)
{
	int posicao = pesquisar_posicao_do_utilizador(nick, memoria_nicks);

	if (posicao != -1 && posicao != -2)
	{
		struct Utilizador temporario = ler_do_ficheiro(memoria_nicks[posicao].posicao, ficheiro);

		printf("utilizador %s (%s)\n%d mensagens, %d seguidores, segue %d utilizadores\n", nick, temporario.nome, temporario.num_enviadas, temporario.num_seguidores, temporario.num_seguidos);
	
		for (int i = 0; i < temporario.num_seguidos; ++i)
        	printf("%s (%d lidas)\n", temporario.lista_de_seguidos[i].nick, temporario.lista_de_seguidos[i].num_msg_lidas);
	}
	else
		printf("+ utilizador %s inexistente\n", nick);
}

/////////////////////////////////////////////////////////////////
/*
Esta função verifica se é possivel nick1 seguir nick2.
Caso nao seja possivel, impreme as mensagens respeticas.
Caso seja possivel adiciona nick2 ao array de seguidos de nick1, aumenta o numero de seguidos de nick1 e o numero de seguidores de nick2 
e imprime as mensagens respectivas.
*/
/////////////////////////////////////////////////////////////////

void seguir_utilizador(char nick1[T_NICK], char nick2[T_NICK], struct info_nicks * memoria_nicks, int ficheiro)
{
	int posicao_nick1 = pesquisar_posicao_do_utilizador(nick1, memoria_nicks);
	int posicao_nick2 = pesquisar_posicao_do_utilizador(nick2, memoria_nicks);
	
	if (posicao_nick1 == -1 || posicao_nick1 == -2)
		printf("+ utilizador %s inexistente\n", nick1);

	else if (posicao_nick2 == -1 || posicao_nick2 == -2)
		printf("+ utilizador %s inexistente\n", nick2);

	else
	{
		struct Utilizador temporario1 = ler_do_ficheiro(memoria_nicks[posicao_nick1].posicao, ficheiro);
		struct Utilizador temporario2 = ler_do_ficheiro(memoria_nicks[posicao_nick2].posicao, ficheiro);

		if (procurar_seguido(nick2, temporario1.num_seguidos, temporario1.lista_de_seguidos) != -1)
			printf("+ utilizador %s segue %s\n", nick1, nick2);
																// qual a ordem destes 2 if ver isto com os inputs
		else if (temporario1.num_seguidos == T_SEGUIDOS)
			printf("+ utilizador %s segue o limite\n", nick1);

		else
		{
			temporario1.num_seguidos++;
			strcpy(temporario1.lista_de_seguidos[temporario1.num_seguidos-1].nick, nick2);
			temporario1.lista_de_seguidos[temporario1.num_seguidos-1].num_msg_lidas = temporario2.num_enviadas;

			inserir_por_ordem(temporario1.lista_de_seguidos, temporario1.num_seguidos);
					
			if (strcmp(nick1, nick2) == 0)
				temporario1.num_seguidores++;

			else
			{
				temporario2.num_seguidores++;
				escrever_no_ficheiro(temporario2, memoria_nicks[posicao_nick2].posicao, ficheiro);
			}

			escrever_no_ficheiro(temporario1, memoria_nicks[posicao_nick1].posicao, ficheiro);
			printf("+ %s passou a seguir %s\n", nick1, nick2);
		}
	}
}

/////////////////////////////////////////////////////////////////
/*
Esta função verifica se é possivel nick1 deixar de seguir nick2.
Caso nao seja possivel, impreme as mensagens respeticas.
Caso seja possivel remove nick2 ao array de seguidos de nick1, diminui o numero de seguidos de nick1 e o numero de seguidores de nick2 
e imprime as mensagens respectivas.
*/
/////////////////////////////////////////////////////////////////

void deixar_de_seguir_utilizador(char nick1[T_NICK], char nick2[T_NICK], struct info_nicks * memoria_nicks, int ficheiro)
{
	int posicao_nick1 = pesquisar_posicao_do_utilizador(nick1, memoria_nicks);
	int posicao_nick2 = pesquisar_posicao_do_utilizador(nick2, memoria_nicks);
	
	if (posicao_nick1 == -1 || posicao_nick1 == -2)
		printf("+ utilizador %s inexistente\n", nick1);

	else if (posicao_nick2 == -1 || posicao_nick2 == -2)
		printf("+ utilizador %s inexistente\n", nick2);

	else
	{
		struct Utilizador temporario1 = ler_do_ficheiro(memoria_nicks[posicao_nick1].posicao, ficheiro);
		struct Utilizador temporario2 = ler_do_ficheiro(memoria_nicks[posicao_nick2].posicao, ficheiro);

		int posicao_na_lista_de_seguido_de_temp1 = procurar_seguido(nick2, temporario1.num_seguidos, temporario1.lista_de_seguidos);

		if (posicao_na_lista_de_seguido_de_temp1 == -1)
			printf("+ utilizador %s nao segue %s\n", nick1, nick2);
																// qual a ordem destes 2 if ver isto com os inputs
		else
		{
			strcpy(temporario1.lista_de_seguidos[posicao_na_lista_de_seguido_de_temp1].nick, "~~~~~");
			inserir_por_ordem(temporario1.lista_de_seguidos, temporario1.num_seguidos);
			temporario1.num_seguidos--;

			if (strcmp(nick1, nick2) == 0)
				temporario1.num_seguidores--;

			else
			{
				temporario2.num_seguidores--;
				escrever_no_ficheiro(temporario2, memoria_nicks[posicao_nick2].posicao, ficheiro);
			}

			escrever_no_ficheiro(temporario1, memoria_nicks[posicao_nick1].posicao, ficheiro);
			printf("+ %s deixou de seguir %s\n", nick1, nick2);
		}
	}
}

/////////////////////////////////////////////////////////////////
/*
Acede a todas as mensagens dos seguidos de nick.
Se não tiver seguidos, imprime o respetivo output.
Caso tenha seguidos, percorre todo os array de seguidos de nick e verifica se estes estão ativos.
Caso não estejam ativos imprime o respetivo output.
Caso estejam ativos, imprime o respetivo output que pode incluir as (tamanho = T_TAB_MSG) ultimas mensagens
*/
/////////////////////////////////////////////////////////////////

void ler_mensagens_utilizador(char nick[T_NICK], struct info_nicks * memoria_nicks, int ficheiro)
{
	int posicao = pesquisar_posicao_do_utilizador(nick, memoria_nicks);
	
	struct Utilizador utilizador;

	if (posicao == -1 || posicao == -2)
		printf("+ utilizador %s inexistente\n", nick);
	
	else
	{
		utilizador = ler_do_ficheiro(memoria_nicks[posicao].posicao, ficheiro);

		if (utilizador.num_seguidos == 0)
			printf("+ utilizador %s sem seguidos\n", memoria_nicks[posicao].nick);

		else
		{

			for (int i = 0; i < utilizador.num_seguidos; ++i)
			{
				int posicao_temporario = pesquisar_posicao_do_utilizador(utilizador.lista_de_seguidos[i].nick, memoria_nicks);
				
				if (posicao_temporario == -1)
				{
					printf("utilizador %s desactivado\n", utilizador.lista_de_seguidos[i].nick);
					
					strcpy(utilizador.lista_de_seguidos[i].nick, "~~~~~");
					inserir_por_ordem(utilizador.lista_de_seguidos, utilizador.num_seguidos);
					utilizador.num_seguidos--;
					i--;
				}
				
				else
				{
					struct Utilizador temporario = ler_do_ficheiro(memoria_nicks[posicao_temporario].posicao, ficheiro);
					
					if (utilizador.lista_de_seguidos[i].num_msg_lidas == temporario.num_enviadas)
						printf("sem mensagens novas de %s (%s)\n", memoria_nicks[posicao_temporario].nick, temporario.nome);
					else
					{
						for (int j = utilizador.lista_de_seguidos[i].num_msg_lidas; j < temporario.num_enviadas; ++j)
						{
							if (temporario.num_enviadas - j - T_TAB_MSG > 0)
								printf("+ mensagem %d de %s (%s) expirada\n", j+1, memoria_nicks[posicao_temporario].nick, temporario.nome);

							else
								printf("+ mensagem %d de %s (%s)\n%s\n", j+1, memoria_nicks[posicao_temporario].nick, temporario.nome, temporario.msg_enviadas[j % T_TAB_MSG]);
						}

						utilizador.lista_de_seguidos[i].num_msg_lidas = temporario.num_enviadas;
					}
				}
			}
		}
		escrever_no_ficheiro(utilizador, memoria_nicks[posicao].posicao, ficheiro);
	}	
}

/////////////////////////////////////////////////////////////////
/* 
Esta função de acordo com os inputs inseridos chama as respetivas funções.
*/
/////////////////////////////////////////////////////////////////

void gestor_de_inputs(struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro)
{
	char codigo;
    char nick1[T_NICK], nick2[T_NICK], nome[T_NOME], msg[T_MSG];
    
    while (scanf (" %c", &codigo) != EOF) // EOF --> CTRL-D
    {
        if (codigo == 'X')
            return;
        
        else if (codigo == 'U')
        { 
            scanf(" %s %[^\n]s", nick1, nome);
            criar_utilizador(nick1, nome, memoria_nicks, numero_de_utilizadores, ficheiro);
        }

        else if (codigo == 'R')
        {
            scanf(" %s", nick1);
            remover_utilizador(nick1, memoria_nicks);
        }

        else if (codigo == 'P')
        {
            scanf(" %s %[^\n]s", nick1, msg);
            enviar_mensagem_utilizador(nick1, msg, memoria_nicks, ficheiro);
        }

        else if (codigo == 'I')
        {
            scanf(" %s", nick1);
            informacao_utilizador(nick1, memoria_nicks, ficheiro);
        }

        else if (codigo == 'S')
        {
            scanf(" %s %s", nick1, nick2);
            seguir_utilizador(nick1, nick2, memoria_nicks, ficheiro);
        }

        else if (codigo == 'D')
        {
            scanf(" %s %s", nick1, nick2);
            deixar_de_seguir_utilizador(nick1, nick2, memoria_nicks, ficheiro);
        }

        else if (codigo == 'L')
        {
            scanf("%s", nick1);
            ler_mensagens_utilizador(nick1, memoria_nicks, ficheiro);
        }
    }
}

/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
