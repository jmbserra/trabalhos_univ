/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Registos;
import beans.Utilizador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database implements businessLogic
{
    public Connection con; //Conecta à bd
    public Statement stmt; // Executa statement sql e retorna resultados
    
    public Database(String PG_HOST, String PG_DB, String USER, String PWD)
    {
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
    
     
      public void createUser(String nome, String password, String email)
    {
        try
        {
            String query = "INSERT INTO users VALUES('"+nome+"', '"+password+"','"+email+"');";
            stmt.executeUpdate(query);
            
            System.out.println("Criado o user com identificação '"
                    + nome + "' com password de " +password + 
                    " com email "+ email+".");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Erro durante a criação do user");
        }    
    }
      
      public boolean verifyUsername(String nome)
    {        
        try 
        {
            ResultSet rs = stmt.executeQuery("Select * FROM users WHERE username='"+nome+"';");
            if (rs.next()==false)
            {
                return false;
            }
            
            else
            {
                return true;
            }
        } 
        catch (Exception e) 
        {          
            return false;             
        }       
    }
      
      public Utilizador getUser(String nome)
      {
          Utilizador user = null;
        
        String query = "select * from users where username = '"+nome+"';";

        try
        {         
            ResultSet rs = stmt.executeQuery(query);
            
            rs.next();
           
            user = new Utilizador(rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("email"));              

            rs.close();        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Problema ao obter utilizador.");
        }
        
        
        return user;
      }
      
      public void editAlergias(String nome, ArrayList alergias)
      {
        try
        {
            String query = "DELETE FROM useral WHERE username ='"+nome+"';";
            stmt.executeUpdate(query);
            
           
            for(int i = 0; i < alergias.size(); i++)
            {
                String query1 = "INSERT INTO useral VALUES('"+nome+"', '"+alergias.get(i)+"');";
                stmt.executeUpdate(query1);
                
                System.out.println("adicionada alergias com user '"
                    + nome + "' com alergias '" +alergias.get(i)+"'.");
            }
   
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Erro durante a edição de alergias");
        }    
      }
      
       public ArrayList queryGetAlergias(String nome)
        {
         ArrayList listaAlergias = new ArrayList();

        try 
        {
            ResultSet rs = stmt.executeQuery("select * FROM useral WHERE username ='"+nome+"';");
            while (rs.next()) 
            {
                String alergia = rs.getString("id");

                listaAlergias.add(alergia);
            }
            rs.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println("Problemas na operação para obter alergias.\n");
        } 
        
        return listaAlergias;
        }
       
       public void queryIntroduzRegisto(String nome, String alergia, String data,String codigo, String latitude, String longitude)
        {
         

        try
        {           
                String query1 = "INSERT INTO registos VALUES('"+nome+"', '"+alergia+"','"+data+"','"+codigo+"','"+latitude+"','"+longitude+"');";
                stmt.executeUpdate(query1);
                
                System.out.println("Adicionado registo");
        }
            catch(Exception e)
            {
                e.printStackTrace();
                System.err.println("Erro durante a edição de alergias");
            }    
        }
       
       public ArrayList queryGetRegistos(String nome)  
       {
           ArrayList<Registos> listaRegistos = new ArrayList();
           Registos registo = null;
           
           try
           { 
           ResultSet rs = stmt.executeQuery("Select * from registos where username = '"+nome+"';");
           
          
            while(rs.next())
            {
                 registo = new Registos(rs.getString("username"),
                                rs.getString("id"),
                                rs.getString("data"),
                                rs.getString("codigo"),
                                rs.getString("latitude"),
                                rs.getString("longitude"));              

                listaRegistos.add(registo);
                
            }
            
     
        rs.close();
  
           }
           catch(Exception e)
           {
               e.printStackTrace();
                System.err.println("Problema ao obter registos.");
           }
           return listaRegistos;
       }  
       
       public ArrayList queryGetAllRegistos()  
       {
           ArrayList<Registos> listaAllRegistos = new ArrayList();
           Registos registo = null;
           
           try
           { 
           ResultSet rs = stmt.executeQuery("Select * from registos;");
           
          
            while(rs.next())
            {
                 registo = new Registos(rs.getString("username"),
                                rs.getString("id"),
                                rs.getString("data"),
                                rs.getString("codigo"),
                                rs.getString("latitude"),
                                rs.getString("longitude"));              

                listaAllRegistos.add(registo);
                
            }

        rs.close();
  
           }
           catch(Exception e)
           {
               e.printStackTrace();
                System.err.println("Erro");
           }
           return listaAllRegistos;
       }
       
       public void queryRemoveRegisto(String codigo)
       {

        try
        {           
                String query1 = ("delete from registos where codigo ='"+codigo+"';");
                stmt.executeUpdate(query1);
                
                System.out.println("Adicionado registo");
        }
            catch(Exception e)
            {
                e.printStackTrace();
                System.err.println("Erro durante a edição de alergias");
            }    
        }
       
        public boolean getEmailByUser(String email)
      {
          Utilizador user = null;
        
        String query = "select * from users where email = '"+email+"';";

        try
        {         
            ResultSet rs = stmt.executeQuery(query);
            
            rs.next();
           
            user = new Utilizador(rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("email"));              

            rs.close();        
        }
        catch(Exception e)
        {
            System.out.println("Problema ao obter email de utilizador.");
            
            return false;
        }
        return true;
      }
        
        public boolean getUsernameByUser(String nome)
      {
          Utilizador user = null;
        
        String query = "select * from users where username = '"+nome+"';";

        try
        {         
            ResultSet rs = stmt.executeQuery(query);
            
            rs.next();
           
            user = new Utilizador(rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("email"));              

            rs.close();        
        }
        catch(Exception e)
        {
            System.out.println("Problema ao obter username de utilizador.");
            
            return false;
        }
        return true;
      }
      
}
    