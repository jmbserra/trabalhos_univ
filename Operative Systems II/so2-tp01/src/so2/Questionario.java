package so2;

import java.util.Vector;

public class Questionario implements java.io.Serializable
{ 
    public Vector<Pergunta> questionario;//conjunto de perguntas de um questionário
    private String id; //identificação do questionário
    public int numPerguntas;
    private int vezesRespondido;
    
    boolean temResposta; 
    
    //Construtor de Questionario
     public Questionario(String nome, int numPerguntas, int vezesRespondido)
     {       
            this.id = nome;
            this.numPerguntas = numPerguntas;
            this.questionario = new Vector<Pergunta>(numPerguntas);
            this.temResposta = false;
            this.vezesRespondido = vezesRespondido;
    }
    
    //GETS E SETS 
     
    public String getId() 
    {
        return id;
    }
    
    public boolean getTemResposta()
    {
        return temResposta;
    }
    
    public void getPerguntas()
    {
            for(int i = 0; i < questionario.size();i++)
            {
                System.out.println(questionario.get(i).pergunta);
            }
    }
    
    public int getVezesRespondido()
    {
        return vezesRespondido;
    }
            
    public void setPergunta(String texto)
    {
        if(questionario.size() < Client.MAXPERG)
        {
            questionario.add(new Pergunta(texto));
        }
        else
        {
            System.out.println("Este questionário tem "+numPerguntas+" e só "
                    + "pode ter no máximo "+Client.MAXPERG);
        }
    }
    
    public void setTemResposta()
    {
        temResposta = true;
    }
    
    public void getMedia()
    {
        float media;
        
        for(int i = 0; i<questionario.size();i++)
        {
            System.out.println(questionario.get(i).pergunta);
            media = questionario.get(i).media();
            System.out.println("\n media: "+ media + "\n");
        }
    }
    
    public void responderPerguntas(int resposta, int i)
    {
        this.questionario.get(i).responder(resposta);
    }
}
