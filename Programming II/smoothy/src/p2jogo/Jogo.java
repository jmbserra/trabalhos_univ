package p2jogo;
import java.util.Scanner;

public class Jogo {



	public static void main(String args[]){  // Função principal
		Scanner input = new Scanner(System.in); 

		System.out.println();

		String tamanho = "";   //variavel tamanho
		String raiz = "";	// variavel raiz
		String cor = "";	// variavel cor

		int t; 	//Variavel que armazena o valor de tamanho depois de tratada
		int r;	//Variavel que armazena o valor de raiz depois de tratada
		int c;	//Variavel que armazena o valor de cor depois de tratada


		//variaveis para jogadas

		int x; // coord x
		int y;	// coord y
		boolean validade = true; // variavel que verifica erros nos pedidos

		//VALIDADE TAMANHO
		while (validade){ 		//Verifica a validade do tamanho do tabuleiro
			validade = false;
			System.out.println("Escolha o tamanho do tabuleiro pretendido: \n");
			tamanho = input.nextLine();
			if	(tamanho.length() > 0 && tamanho.length() < 3){
				for(int i = 0; i < tamanho.length(); i++){
					if(!(Character.isDigit(tamanho.charAt(i)))){
						validade = true;  // true, indica que o while pode chegar ao fim, ou pq esta tudo bem, ou porque ocorreram erros
					}
				}
			}
			else{
				validade = true;
			}
			if(validade == true){
				System.out.println("ERRO! Valor errado para o tamanho do tabuleiro! \n");
			}
		}
		t = Integer.parseInt(tamanho);

		validade = true;

		//VALIDADE NUMERO DE CORES
		while (validade){
			validade = false;
			System.out.println("Escolha o numero de cores diferentes pretendidas: \n");
			cor = input.nextLine();
			if	(cor.length() == 1){
				if(!(Character.isDigit(cor.charAt(0)))){
					validade = true;
				}
			}
			else{
				validade = true;
			}

			if(validade == true){
				System.out.println("ERRO! Valor errado para o numero de cores pretendidas! \n");
			}
		}
		c = Integer.parseInt(cor);

		validade = true;


		//VALIDADE NUMERO RAIZ
		while (validade){
			validade = false;
			System.out.println("Escolha o numero de Raiz(S) do tabuleiro \n");
			raiz = input.nextLine();
			if	(tamanho.length() > 0 && raiz.length() <= 3){
				for(int i = 0; i < raiz.length()-1; i++){
					if(!(Character.isDigit(tamanho.charAt(i)))){
						validade = true;
					}
				}
			}
			else{
				validade = true;
			}
			if(validade == true){
				System.out.println("ERRO! Valor errado para o tamanho do tabuleiro! \n");
			}
		}
		r = Integer.parseInt(raiz);

		Tabuleiro novo = new Tabuleiro(t); //alterar

		novo.criaTabuleiro(r,c);

		//LOOP DO JOGO, VERIFICOU A VALIDADE DE TUDO
		while (novo.verificaFimJogo()){
			novo.mostraTabuleiro();
			System.out.println("Introduza a coordenada para a Linha: \n");
			x = input.nextInt();
			System.out.println("Introduza a coordenada para a Coluna: \n");
			y = input.nextInt();

			novo.jogada(x, y);
			System.out.println("\n\n");
		}
		System.out.println("\n");
		novo.mostraTabuleiro();
		novo.pontuacao();
		input.close();
	}
}

