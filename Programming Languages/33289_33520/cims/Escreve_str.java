
public class Escreve_str extends Instrucao
{
	String identificador;
	
	public Escreve_str(String s)
	{
		this.identificador = s;
	}

	public String toString() 
	{
		return "Escreve_str" + " " + this.identificador;
	}	
}
