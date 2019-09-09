package so2;

public class Server
{
    
    public static void main(String args[]) 
    {
	int regPort= 9009; 
        
	try 
        { 
	    QuestionarioPergunta obj= new QuestPergImpl(); //objeto
	    
            java.rmi.registry.LocateRegistry.createRegistry(regPort);            
                        
	    java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);
            
	    registry.rebind("QuestionarioPergunta", obj);

	    System.out.println("Bound RMI object in registry");

            System.out.println("Server Ready");
	} 
	catch (Exception ex) 
        {
	    ex.printStackTrace();
	}
    }  
}
