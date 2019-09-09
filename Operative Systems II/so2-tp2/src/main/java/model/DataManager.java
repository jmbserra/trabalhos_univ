/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Utilizador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 *
 * @author serra
 */
public class DataManager implements businessLogic {

    String port = getProperties("port");
    String bdname = getProperties("bdname");
    String user = getProperties("user");
    String pw = getProperties("pw");

    private static businessLogic dbManager;
    

    public DataManager() {
    } // Construtor vazio

    public DataManager(ServletContext context) {
        dbManager = new Database(port, bdname, user, pw);  //conexao base de dados    
    }
    
    public String getProperties(String propriedade){
        String parametro = null;
        
        try (InputStream input = new FileInputStream("svlocal.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            parametro = prop.getProperty(propriedade);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return parametro;
    }

    public void createUser(String nome, String password, String email) {
        dbManager.createUser(nome, password, email);
    }

    public boolean verifyUsername(String nome) {
        return dbManager.verifyUsername(nome);
    }

    public Utilizador getUser(String nome) {
        return dbManager.getUser(nome);
    }

    public void editAlergias(String nome, ArrayList alergias) {
        dbManager.editAlergias(nome, alergias);
    }

    public ArrayList queryGetAlergias(String nome) {
        return dbManager.queryGetAlergias(nome);
    }

    public void queryIntroduzRegisto(String nome, String alergia, String data, String codigo, String latitude, String longitude) {
        dbManager.queryIntroduzRegisto(nome, alergia, data, codigo, latitude, longitude);
    }

    public ArrayList queryGetRegistos(String nome) {
        return dbManager.queryGetRegistos(nome);
    }

    public ArrayList queryGetAllRegistos() {
        return dbManager.queryGetAllRegistos();
    }

    public void queryRemoveRegisto(String codigo) {
        dbManager.queryRemoveRegisto(codigo);
    }
    
    public boolean getEmailByUser(String email)
    {
      return dbManager.getEmailByUser(email);
    }
    
     public boolean getUsernameByUser(String nome){
         return dbManager.getUsernameByUser(nome);
     }

}
