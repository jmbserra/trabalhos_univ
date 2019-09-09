package p2jogo;
import java.util.Random;

public class Tabuleiro {

	private	int[][] tabuleiro ;
	private int tamanho;

	public Tabuleiro(int tamanho){

		this.tamanho = tamanho;
		this.tabuleiro = new int[tamanho][tamanho];
	}

	public void criaTabuleiro(int raiz, int cor){ 
		Random cores = new Random(raiz);  // RANDOM(S)

		for(int i=0; i < this.tamanho; i++){

			for (int j = 0; j < this.tamanho; j++){
				this.tabuleiro[i][j] = cores.nextInt(cor)+1;  // De maneira a não ter 0's no tabuleiro, zero = vazio.
			}
		}
	}

	public void mostraTabuleiro(){

		for(int i=0; i < this.tamanho; i++){
			for (int j = 0; j < this.tamanho; j++){
				System.out.print(this.tabuleiro[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public void jogada(int x,int y){  
		if( horizontal(x,y) == true & vertical(x,y) == false){ // Operador "&" de maneira a verificar sempre ambas as funções
			groundZero(x,y);
		}
		empurraColuna();
	}

	public boolean horizontal(int x,int y){
		// PRIMEIRA PARTE VÊ LADO DIREITO

		boolean estado = false;
		int escolha = this.tabuleiro[x][y];  // escolha da posição do utilizador
		for(int j = y; j < this.tabuleiro.length-1; j++){
			if (this.tabuleiro[x][j+1] == escolha){
				estado = true;
				for(int i = x; i > 0; i--){
					this.tabuleiro[i][j+1] = this.tabuleiro[i-1][j+1];   // se este estado = true, significa que encontrou pelo menos uma cor igual adjacente.
				}
				this.tabuleiro[0][j+1] = 0;
			}
			else{
				break;
			}
		}
		// SEGUNDA PARTE VÊ LADO ESQUERDO
		for(int j = y; j > 0; j--){
			if (this.tabuleiro[x][j-1] == escolha){
				for(int i = x; i > 0; i--){
					this.tabuleiro[i][j-1] = this.tabuleiro[i-1][j-1];  
					estado = true; // se este estado ficar true, significa que encontrou pelo menos uma cor igual adjacente.
				}
				this.tabuleiro[0][j-1] = 0;
			}
			else{
				break;
			}
		}

		return estado;
	}

	public boolean vertical(int x,int y){
		int contador = 0;
		boolean estado = false;  
		int escolha = this.tabuleiro[x][y];
		for (int h = x; h < this.tamanho-1; h++){// Correr linhas para baixo
			if(this.tabuleiro[h+1][y] == escolha){ // se o que está a baixo de escolha for igual a esc...
				contador++;  //obtem o X do valor igual à escolha mais a baixo
			}
			else{
				break;
			}
		}
		// aqui acaba para baixo, contou quantas peças iguais havia
		// aqui começa para cima
		int contadorCima = 0;
		int novaPosicao = x + contador;

		// o seguinte for incrementa o contador para saber quantas peças há iguais em cima.
		for (int h = novaPosicao; h > 0; h--){
			if(this.tabuleiro[h][y] == this.tabuleiro[h-1][y]){
				contadorCima++;
			}
			else{
				break;
			}
		}
		if (contadorCima > 0){
			estado = true; // se chegou aqui significa que encontrou pelo menos uma para cima ou baixo
			for (int h = novaPosicao; h > 0; h--){// Correr linhas (h)
				if(contadorCima >= 0){
					for( int j = novaPosicao; j >= 0; j-- ){ 

						if(j > 0)
							this.tabuleiro[j][y] = this.tabuleiro[j - 1][y];
						else
							this.tabuleiro[0][y] = 0;
					}
					contadorCima--;
				}
				else
					break;
			}
			if(novaPosicao==1){  //caso particular posição linha1.
				this.tabuleiro[1][y]=0;
				System.out.println("METI UM ZERO");
			}
			if ((this.tabuleiro[this.tamanho-1][y] != 0) && (this.tabuleiro[this.tamanho-2][y] == 0)){ //caso particular linhat-1
				this.tabuleiro[this.tamanho-1][y] = 0;
			}

		}
		return estado;
	}

	public void groundZero(int x,int y){ //Troca a própria coluna quando a função horizontal == true && cima == false
		for( int j = x; j >= 0; j-- ){
			if (j>0){//começa aqui a substituição
				this.tabuleiro[j][y] = this.tabuleiro[j - 1][y];// count  = 1
			}
			else{
				this.tabuleiro[0][y] = 0;
			}
		}
	}

	public void empurraColuna(){ //função que vai empurrar a coluna vazia para a direita
		int countZeros = 0; // serve para encontrar colunas cheias de zeros
		int corre = 100; // Assim temos a certeza que corre todas as linhas de maneira a empurrar para a esquerda
		while(corre > 0){
			for(int j = 1; j <= this.tamanho-1; j++ ){        //ciclo que percorre linhas e colunas I/J
				for(int i = 0; i <= this.tamanho-1; i++ ){
					if(this.tabuleiro[i][j] == 0){
						countZeros++;
					}
					if(countZeros == this.tamanho){          // Se entrar no IF significa que encontrou uma Col cheia de 0's
						for( int x = 0; x < tamanho; x++ ){//começa aqui a substituição
							this.tabuleiro[x][j] = this.tabuleiro[x][j-1]; //valor em J fica igual ao valor J-1
							this.tabuleiro[x][j-1] = 0; //coluna anterior a J fica toda == 0;
						}
					}
				}
				corre --; // decrementação do Ciclo Principal de maneira a correr todas as linhas e colunas
				countZeros = 0; // reinicia o valor do contador de maneira a poder encontrar mais colunas cheias de 0's
			}
		}
	}

	// Aqui Começam verificações para saber se é possivel jogar, ou se o jogo Termina.
	public boolean verificaHorizontal(int x, int y){
		int escolha = this.tabuleiro[x][y];

		if (escolha == 0)
			return false;

		if (y != 0){
			if (this.tabuleiro[x][y-1] == escolha)  // se a Coluna = 0 e X = 0, não verifica a esquerda, senao verifica.
				return true;
			else
				return false;
		}
		if (y != tabuleiro.length - 1) {  // Nao esta na primeira posicao, verifica sempre para a direita, senao, nao verifica
			if (this.tabuleiro[x][y+1] == escolha)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean verificaVertical(int x, int y){
		int escolha = this.tabuleiro[x][y];

		if (escolha == 0)
			return false;

		if (x != 0){
			if (this.tabuleiro[x-1][y] == escolha)
				return true;
			else
				return false;
		}
		if (x != tabuleiro.length - 1) {
			if (this.tabuleiro[x+1][y] == escolha)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean verificaFimJogo(){
		for(int i = 0; i < this.tamanho ; i++){
			for( int j = 0; j < this.tamanho ; j++){
				if (verificaHorizontal(i, j) || verificaVertical(i, j))
					return true; // Se isto acontecer, significa que há pelo menos uma jogada possivel
			}
		}
		return false;
	}

	public void pontuacao(){
		int notZeroCounter = 0;
		int pontuacao;
		for(int i = 0; i < this.tamanho ; i++){
			for( int j = 0; j < this.tamanho ; j++){
				if(this.tabuleiro[i][j]!=0){
					notZeroCounter++;
				}
			}
		}
		pontuacao = (this.tamanho * this.tamanho - notZeroCounter);

		System.out.println("O JOGO CHEGOU AO FIM");
		System.out.println("Atingiu uma pontuação de "+ pontuacao +" em "+ this.tamanho*this.tamanho +" pontos possiveis!\n");
		System.out.println("Caso queira repetir o tabuleiro, insira o mesmo tamanho e valor de Raiz inseridos no tabuleiro anterior");
	}
}