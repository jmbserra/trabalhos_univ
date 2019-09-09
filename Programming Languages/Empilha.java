

public class Empilha extends Instrucao
{
	int valor;
	
	public Empilha(int v)
	{
		this.valor = v;
	}

	public String toString()
	{
		return "Empilha" + " " + this.valor;
	}
}
