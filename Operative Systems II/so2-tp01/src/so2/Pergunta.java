package so2;

import java.util.Vector;
import java.io.Serializable;

public class Pergunta implements Serializable
{
    String pergunta;
    private static int MAX_RESPOSTAS = 9999;
    Vector<Integer> resposta ;
    
    public Pergunta(String p)
    {
        this.pergunta = p;
        resposta = new Vector<Integer>(MAX_RESPOSTAS);
    }
    
    public void responder(int x)
    {
        if(x>=1 && x<= 10)
        {
            resposta.add(x);
        }
        else
        {
            
        }
    }
    
    public float media()
    {
        int soma = 0;
        for(int i = 0; i<resposta.size();i++)
        {
            soma += resposta.get(i);
        }
        
        return soma/resposta.size();       
    }
}

