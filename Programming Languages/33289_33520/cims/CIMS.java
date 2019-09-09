

import java.util.ArrayList;

// Conjunto de Instrucoes Maquina Simples

public class CIMS 
{
	ArrayList<Instrucao> listaMemoria = new ArrayList<>();
  
	public void printMemoria()
	{
		for (Instrucao z : listaMemoria) 
		{
			System.out.println("   " + z.toString());
		}
	}


  public void executa()
  {
  }
}
