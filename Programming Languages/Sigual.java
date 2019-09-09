

public class Sigual extends Instrucao
{
	String identificador;
	
	public Sigual(String s)
	{
		this.identificador = s;
	}

	public String toString() 
	{
		return "Sigual" + " " + this.identificador;
	}	
}
