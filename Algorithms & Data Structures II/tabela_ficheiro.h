#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

#define NOME_DO_FICHEIRO "Tabela_de_Utilizadores"
#define NOME_DO_FICHEIRO_NICKS "Tabela_de_Nicks"
#define T_HASH_UTILIZADORES 3000017
#define T_MEM_UTILIZADORES 1100000
#define T_NICK 6
#define T_NOME 26
#define T_MSG 63
#define T_SEGUIDOS 100
#define T_TAB_MSG 8

struct seguir 
{
	char nick[T_NICK];
	int num_msg_lidas;
};

struct info_nicks
{
	char nick[T_NICK];
    int posicao;
	int activo;
};

struct Utilizador
{
    char nome[T_NOME];
    int num_enviadas;
    int num_seguidores;
    int num_seguidos;
    char msg_enviadas[T_TAB_MSG][T_MSG];
    struct seguir lista_de_seguidos[T_SEGUIDOS];
};

unsigned int hashing(char *string);
int criar_ficheiro_principal();
struct info_nicks * criar_mem_nicks();
void levantar_numero_de_utilizadores(int * numero_de_utilizadores , int ficheiro_nicks);
void carregar_numero_de_utilizadores(int * numero_de_utilizadores, int ficheiro_nicks);
void levantar_mem_nicks(struct info_nicks * memoria_nicks, int ficheiro_nicks);
void carregar_mem_nicks(struct info_nicks * memoria_nicks, int ficheiro_nicks);
int criar_ficheiro_nicks(struct info_nicks * memoria_nicks);
void fechar_ficheiro(struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro, int ficheiro_nicks);
void escrever_no_ficheiro(struct Utilizador utilizador, int posicao, int ficheiro);
struct Utilizador ler_do_ficheiro(int posicao, int ficheiro);
int pesquisar_posicao_do_utilizador(char nick[], struct info_nicks * memoria_nicks);
int inserir_utilizador_no_ficheiro(char nick[], struct Utilizador utilizador, struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro);
int remover_utilizador_no_ficheiro(char nick[], struct info_nicks * memoria_nicks);