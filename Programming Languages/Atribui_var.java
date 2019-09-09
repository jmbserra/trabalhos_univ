
public class Atribui_var extends Instrucao
{
	int valor1;
	int valor2;
	
	public Atribui_var(int v1, int v2)
	{
		this.valor1 = v1;
		this.valor2 = v2;
	}
	
	public String toString() 
	{
		return "Atribui_var " + valor1 + " " + valor2;
	}

}
