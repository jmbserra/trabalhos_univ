

public class Empilha_arg  extends Instrucao
{

	int valor1;
	int valor2;
	
	public Empilha_arg(int v1, int v2)
	{
		this.valor1 = v1;
		this.valor2 = v2;
	}

	public String toString() 
	{
		return "Empilha_arg" + " " + this.valor1 + " " + this.valor2;
	}	
}
