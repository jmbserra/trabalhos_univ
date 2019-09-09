package so2;

//Objeto Remoto
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

public class QuestPergImpl extends UnicastRemoteObject implements QuestionarioPergunta, java.io.Serializable
{  
    String port ="localhost"; //getProperties("port");
    String bdname = "quest";//getProperties("bdname");
    String user = "serra";//getProperties("user");
    String pw = "umaPass";//getProperties("pw");
    
    public QuestPergImpl() throws java.rmi.RemoteException{}; // Construtor vazio

    Database databaseConnection = new Database(port,bdname,user,pw);  //conexao base de dados
    
    public String getProperties(String name) 
    {
       String value = null;
       InputStream inputStream;

       try 
       {
           Properties prop = new Properties();
           String propFileName = "svlocal.properties";

           inputStream = getClass().getResourceAsStream(propFileName);

           if (inputStream != null) 
           {
               prop.load(inputStream);
           } 
           
           else 
           {
               throw new FileNotFoundException("file '" + propFileName + "' not found");
           }
           value = prop.getProperty(name);
       } 
       
       catch (Exception e) 
       {
           System.out.println("Exception: " + e);
       }
       return value;
    }
    
    public boolean setup() throws RemoteException
    {
        return databaseConnection.querySetup();
    }
    
    public ArrayList listarQuestionarios() throws RemoteException 
    {
        return databaseConnection.queryListarQuestionarios();
    }
    
    public void criarQuestionario(String id,int n) throws RemoteException
    {
       	databaseConnection.queryCriarQuestionario(id, n);
    }
    
    public void apagarQuestionario(String id) throws RemoteException
    {
       	databaseConnection.queryApagarQuestionario(id);
    }
    
    public void adicionarPergunta(String id,String pergunta,int num) throws RemoteException
    {
       	databaseConnection.queryAdicionarPergunta(id,pergunta,num);
    }
    public int existeQuestionario(String id) throws RemoteException
    {
       	return databaseConnection.queryExisteQuestionario(id);
    }
    public void adicionarResposta(String id,int resposta) throws RemoteException
    {
        databaseConnection.queryAdicionarResposta(id,resposta);
    }
    
    public void incrementaVezesRespondido(String id )throws RemoteException
    {
        databaseConnection.queryIncrementaVezesRespondido(id);
    }

    public Questionario vezesRespondido(String id) throws RemoteException
    {
       	return databaseConnection.queryDevolveVezesRespondido(id);
    }
    
    public ArrayList listarPerguntas(String id) throws SQLException , RemoteException
    {
        return databaseConnection.queryDevolvePerguntas(id);
    }

    public ArrayList mediaDoValorRespondido(String id) throws SQLException, RemoteException 
    {
        return databaseConnection.queryDevolveRespostas(id);
    }
    
}
