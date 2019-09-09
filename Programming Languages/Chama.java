
public class Chama extends Instrucao
{
	int valor;
	String identificador;
	
	public Chama(int d, String p)
	{
		this.valor = d;
		this.identificador = p;
	}

	public String toString() 
	{
		return "Chama" + " " + this.identificador;
	}
}
