

public class Empilha_var extends Instrucao
{
	int valor1;
	int valor2;
	
	public Empilha_var(int v1, int v2)
	{
		this.valor1 = v1;
		this.valor2 = v2;
	}

	public String toString() 
	{
		return "Empilha_var" + " " + this.valor1 + " " + this.valor2;
	}
}
