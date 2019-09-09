#include "tabela_ficheiro.h"

void inserir_por_ordem(struct seguir lista_de_seguidos[], int num_seguidos);
int pesquisa_binaria(char nick[T_NICK], struct seguir lista_de_seguidos[], int i, int j);
int procurar_seguido(char nick[T_NICK], int tamanho, struct seguir lista_de_seguidos[]);
void criar_utilizador(char nick[T_NICK], char nome[T_NOME], struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro);
void remover_utilizador(char nick[T_NICK], struct info_nicks * memoria_nicks);
void enviar_mensagem_utilizador(char nick[T_NICK], char msg[T_MSG], struct info_nicks * memoria_nicks, int ficheiro);
void informacao_utilizador(char nick[T_NICK], struct info_nicks * memoria_nicks, int ficheiro);
void seguir_utilizador(char nick1[T_NICK], char nick2[T_NICK], struct info_nicks * memoria_nicks, int ficheiro);
void deixar_de_seguir_utilizador(char nick1[T_NICK], char nick2[T_NICK], struct info_nicks * memoria_nicks, int ficheiro);
void ler_mensagens_utilizador(char nick[T_NICK], struct info_nicks * memoria_nicks, int ficheiro);
void gestor_de_inputs(struct info_nicks * memoria_nicks, int * numero_de_utilizadores, int ficheiro);