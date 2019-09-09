package so2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class Database 
{
    public Connection con; //Conecta à bd
    public Statement stmt; // Executa statement sql e retorna resultados
    
    public Database(String PG_HOST, String PG_DB, String USER, String PWD)
    {
        this.con = null;
        this.stmt = null;
        
        try 
        {
            Class.forName ("org.postgresql.Driver");        
            // url = "jdbc:postgresql://host:port/database",
            con = DriverManager.getConnection("jdbc:postgresql://"+PG_HOST+":5432/"+PG_DB,
                                              USER,
                                              PWD);
            
            stmt = con.createStatement();            
        }
        
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println("Problems setting the connection");
        }
    }
    
    public void closeDatabase() //Encerra a ligação com a bdados
    {
        try 
        {
            stmt.close();
            con.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public boolean querySetup() throws java.rmi.RemoteException 
    {
        String s;
        StringBuilder sb = new StringBuilder();

        try 
        {
            FileReader fr = new FileReader(new File("command.sql"));

            BufferedReader br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) 
            {
                sb.append(s);
            }
            br.close();

            String[] inst = sb.toString().split(";");

            for (int i = 0; i < inst.length; i++) 
            {
                if (!inst[i].trim().equals("")) 
                {
                    stmt.executeUpdate(inst[i]);
                    //System.out.println(">>" + inst[i]); //Mostra no terminal as instruções SQL executadas 

                }
            }
            System.out.println("Base de Dados criada/reposta.");
        } 
        
        catch (Exception e) 
        {
            System.out.println("*** Erro : " + e.toString());
            e.printStackTrace();
            System.out.println(sb.toString());
        }
        return true;
    }
    
    public int queryExisteQuestionario(String id)
    {        
        try 
        {
            ResultSet rs = stmt.executeQuery("Select * FROM questionarios WHERE id='"+id+"';");
            if (rs.next()==false)
            {
                return 0;
            }
            
            else
            {
                return 1;
            }
        } 
        catch (Exception e) 
        {          
            return 0;             
        }       
    }
    
     public ArrayList queryListarQuestionarios() throws RemoteException 
     {
         ArrayList listaQuestionarios = new ArrayList();

        try 
        {
            ResultSet rs = stmt.executeQuery("Select id FROM questionarios;");
            while (rs.next()) 
            {
                String nome = rs.getString("id");

                listaQuestionarios.add(nome);
            }
            System.out.println("Questionarios listados ao Cliente"); // ENVIA ESTA MSG PARA O SERVER
            rs.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println("Problemas na operação para obter a lista de Questionarios.\n");
        } 
        
        return listaQuestionarios;
     }
     
    public void queryCriarQuestionario(String id,int numPerguntas) throws RemoteException
    {   
        
        int vezesRespondido = 0;
        try
        {
            String query = "INSERT INTO questionarios VALUES('"+id+"\', "+numPerguntas+","+vezesRespondido+");";
            stmt.executeUpdate(query);
            
            System.out.println("Criado o questionario com identificação '"
                    + id + "' com o numero inicial de " + numPerguntas + 
                    " perguntas.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Erro durante a criação do questionário");
        }    
    }
    
    public void queryApagarQuestionario(String id) throws RemoteException
    {
        try
        {
            stmt.executeUpdate("DELETE FROM resposta WHERE id = '"+id+"';");
            stmt.executeUpdate("DELETE FROM pergunta WHERE id = '"+id+"';");
            stmt.executeUpdate("DELETE FROM questionarios WHERE id = '"+id+"';");
            
            System.out.println("O Questionario de identificação '"+id+"' foi apagado.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Erro ao apagar questionário de identificação = "+id+".");
        }
    }
    
    public void queryAdicionarPergunta(String id,String pergunta,int num) throws RemoteException
    {
        try
        {
            stmt.executeUpdate("INSERT INTO pergunta VALUES('"+id+"\','"+pergunta+"',"+num+");");
            System.out.println("[Client] Adicionada pergunta ao questionario"+id+".");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Erro de adição de pergunta.");
        }
    }

        public void queryAdicionarResposta(String id,int resposta) throws RemoteException
    {
        try
        {
            stmt.executeUpdate("INSERT INTO resposta VALUES ("
                                + "DEFAULT" + ", '" + id + "', " + resposta + ");");
            //stmt.executeUpdate("INSERT INTO resposta VALUES('DEFAULT',"+""+id+"\','"+resposta+"',"+num+");");
            System.out.println("Adicionada resposta ao questionario "+id+".");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Erro de adição de resposta.");
        }
    }
        
    public void queryIncrementaVezesRespondido(String id)
    {
        try
        {
            stmt.executeUpdate("UPDATE questionarios SET vezesrespondido = vezesrespondido + 1"
                            +  "where id = '"+id+"';");
            //stmt.executeUpdate("INSERT INTO resposta VALUES('DEFAULT',"+""+id+"\','"+resposta+"',"+num+");");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Erro de adição de resposta.");
        }
    }
    
    public Questionario queryDevolveVezesRespondido(String id)
    {
        Questionario questionario = null;
        
        String query = "SELECT * FROM questionarios WHERE id='"+id+"'";

        try
        {         
            ResultSet rs = stmt.executeQuery(query);
            
            rs.next();
           
            questionario = new Questionario(rs.getString("id"),
                                rs.getInt("numPerguntas"),
                                rs.getInt("vezesRespondido"));              

            rs.close();        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Problema ao obter o numero de respostas de um questionário.");
        }

        return questionario;
    }
    
    public ArrayList queryDevolvePerguntas(String id) throws SQLException, RemoteException
    {
        ArrayList listaPerguntas = new ArrayList();
        
        ResultSet rs = stmt.executeQuery("Select * from pergunta where id = '"+id+"';");
        
        while (rs.next()) 
            {
                String nome = rs.getString("pergunta");

                listaPerguntas.add(nome);
            }
        
            System.out.println("Perguntas do Questionário "+id+" listados ao Cliente"); // ENVIA ESTA MSG PARA O SERVER
            rs.close();
        return listaPerguntas;
    }
    
    public ArrayList queryDevolveRespostas(String id) throws SQLException, RemoteException
    {
        ArrayList listaRespostas = new ArrayList();
        ResultSet rs = stmt.executeQuery("Select * from resposta where id = '"+id+"';");
        
        while(rs.next())
            {
                String nome = rs.getString("resposta");
                
                listaRespostas.add(nome);
           
            }
        
        rs.close();
        
        System.out.println("Efetuado calculo da média no Questionário '"+id+"'.");
        
        return listaRespostas;
        
    }
    
    }

  
    
    
    
    
    