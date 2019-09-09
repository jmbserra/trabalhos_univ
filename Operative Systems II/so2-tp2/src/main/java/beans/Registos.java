/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;



public class Registos 
{
    public String nome;
    public String alergia;
    public String data;
    public String codigo;
    public String latitude;
    public String longitude;
    
    public Registos(){
        
    }
               

    public Registos(String nome, String alergia, String data, String codigo, String latitude, String longitude) {
        this.nome = nome;
        this.alergia = alergia;
        this.data = data;
        this.codigo = codigo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public String getAlergia() {
        return alergia;
    }

    public String getData() {
        return data;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    public String totheString()
    {
       
        
        String str = "Registou em "+this.data+": / Alergia: "+ this.alergia +" / código submissão: "+this.codigo + "/  Lat: "+this.latitude +"\n"
                + "/ Long: "+ this.longitude;
        return str;
    }    
    
}
