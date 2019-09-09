#include "programa.h"

int main(int argc, char const *argv[])
{
	int aux = 0;
	int * numero_de_utilizadores = &aux; // vai sendo incrementado e define as posicoes na memoria principal

	struct info_nicks * memoria_nicks = criar_mem_nicks();

	int ficheiro = criar_ficheiro_principal();
	int ficheiro_nicks = criar_ficheiro_nicks(memoria_nicks);
	levantar_numero_de_utilizadores(numero_de_utilizadores, ficheiro_nicks);

	gestor_de_inputs(memoria_nicks, numero_de_utilizadores, ficheiro);

	fechar_ficheiro(memoria_nicks, numero_de_utilizadores, ficheiro, ficheiro_nicks);
	return 0;
}
