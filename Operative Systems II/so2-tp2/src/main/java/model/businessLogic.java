/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Utilizador;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author serra
 */
public interface businessLogic {

    public abstract void createUser(String nome, String password, String email);

    public boolean verifyUsername(String nome);

    public Utilizador getUser(String nome);

    public void editAlergias(String nome, ArrayList alergias);

    public ArrayList queryGetAlergias(String nome);

    public void queryIntroduzRegisto(String nome, String alergia, String data, String codigo, String latitude, String longitude);

    public ArrayList queryGetRegistos(String nome);

    public ArrayList queryGetAllRegistos();

    public void queryRemoveRegisto(String codigo);

    public boolean getEmailByUser(String email);
    
     public boolean getUsernameByUser(String nome);

}
