
public class Smenor extends Instrucao
{
	String identificador;
	
	public Smenor(String s)
	{
		this.identificador = s;
	}

	public String toString() 
	{
		return "Smenor" + " " + this.identificador;
	}	
}
