

public class Salta extends Instrucao
{
	String identificador;
	
	public Salta(String s)
	{
		this.identificador = s;
	}

	public String toString() 
	{
		return "Salta" + " " + this.identificador;
	}	
}
