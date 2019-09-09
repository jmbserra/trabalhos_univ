package so2;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface QuestionarioPergunta extends java.rmi.Remote
{
    public boolean setup() throws RemoteException;
    public ArrayList listarQuestionarios() throws RemoteException;
    public void criarQuestionario(String id,int n) throws RemoteException;
    public void apagarQuestionario(String id) throws RemoteException;
    public void adicionarPergunta(String id,String pergunta,int num) throws RemoteException;
    public int existeQuestionario(String id)throws RemoteException;
    public void adicionarResposta(String id,int resposta) throws RemoteException;
    public void incrementaVezesRespondido(String id )throws RemoteException;
    public Questionario vezesRespondido(String id) throws RemoteException;
    public ArrayList listarPerguntas(String id) throws SQLException, RemoteException;
    public ArrayList mediaDoValorRespondido(String id) throws SQLException, RemoteException;
}