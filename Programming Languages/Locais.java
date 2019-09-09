
public class Locais extends Instrucao
{
	int valor1;
	int valor2;
	
	public Locais (int v1, int v2)
	{
		this.valor1 = v1;
		this.valor2 = v2;
	}

	public String toString() 
	{
		return "Locais " + this.valor1 + " " + this.valor2;
	}
}
