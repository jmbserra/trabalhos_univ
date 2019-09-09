package so2;

import java.io.IOException;
import java.util.Scanner;
import java.text.ParseException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client 
{     
    //Variaveis que limitam o numeros de perguntas por questionario
    public static int MINPERG = 3;
    public static int MAXPERG = 5;
    
    public static void main(String args[]) throws RemoteException, SQLException, IOException 
    {      
        String regHost = "localhost";
        String regPort = "9009";  // porto do binder
        try 
        {        
            menu(regHost,regPort);          
        } 
        
        catch (ParseException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
  
    public static void menu(String regHost,String regPort) throws ParseException, RemoteException, SQLException, IOException 
    {
        Registry registry;
        QuestionarioPergunta stub = null;
        
        //VARIAVEIS PARA LEITURA DE USER INPUTS.
        int opcao = -1;

        Scanner scanner = new Scanner(System.in);
        BufferedReader br ;
        
        try
        {
            registry = LocateRegistry.getRegistry(regHost);

            stub = (QuestionarioPergunta)  java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/QuestionarioPergunta"); 
        }
        catch(Exception e)
        {
              e.printStackTrace();
        }
        
        while(opcao != 0)
        {
            System.out.println("***-------Serviço Questionarios-------***\n"
                                +"0 para abandonar o serviço:\n"
                                +"1 para Restabelecer os Questionários ao seu estado inicial:\n"
                                +"2 Consultar a lista de Questionarios:\n"
                                +"3 Para criar um novo questionário:\n"
                                +"4 Para apagar um questionário:\n"
                                +"5 Obter a lista de perguntas de um Questionário:\n"
                                +"6 Submeter respostas ás questões de um Questionário:\n"
                                +"7 Consultar o numero de vezes que um Questionário foi respondido:\n"
                                +"8 Obter a media do valor das respostas de um Questionario:\n"
                                +"Insira a sua opção: ");
            br = new BufferedReader(new InputStreamReader(System.in));
            opcao = Integer.parseInt(br.readLine());
            switch (opcao)
            {
                case 0:  System.out.println("\nO programa terminou.");
                         System.exit(0);
                         break;    
                         
                case 1:  //REPOR BD
                        
                        if (stub.setup()) 
                        {
                            System.out.println("Base de dados criada/reposta!\n");
                        } 
                        else 
                        {
                            System.out.println("Erro ao criar/repor base de dados!\n");
                        }
                        
                        break;
                                        
                case 2: //LISTAR QUESTIONARIOS
                        ArrayList obj2 = stub.listarQuestionarios();

                        System.out.println("Questionarios existentes:");
                        for(int i = 0; i < obj2.size(); i++)
                        {
                           System.out.println("- " + obj2.get(i));
                        }              
                        System.out.println("\n");

                         break;
                case 3: //CRIAR NOVO QUESTIONARIO  
                        
                        System.out.println("inserir nome do Questionario: ");
			
                        br = new BufferedReader(new InputStreamReader(System.in));
                        
			String id1 = br.readLine();
                        
                        int existe = stub.existeQuestionario(id1); //verifica se existe id1 na tabela questionarios
                        
                        if(existe == 0)
                        {
                        
                            System.out.println("inserir numero de perguntas: ");
                            br = new BufferedReader(new InputStreamReader(System.in));

                            int totalPerguntas = Integer.parseInt(br.readLine());

                            if(totalPerguntas < MINPERG || totalPerguntas > MAXPERG) //se o numero inserido estiver fora dos limites
                            {
                                System.out.println("Numero Inválido de perguntas!\nPor favor recomece.\n");
                            }

                            else//se tudo correr bem adiciona as perguntas no else
                            {
                                stub.criarQuestionario(id1, totalPerguntas);
                                System.out.println("Adicione as perguntas.");

                                for(int i = 1; i <= totalPerguntas; i++)
                                {
                                    System.out.print("Pergunta --> +"+i+": ");
                                    br = new BufferedReader(new InputStreamReader(System.in));
                                    String pergunta = br.readLine();
                                    stub.adicionarPergunta(id1, pergunta, i);
                                }
                            }
                        }
                        
                        else 
                        {
                            System.out.println("Utilizador já existente.");
                        }
                        
                        System.out.print("\n");
                          
                        break;
                            
                case 4:  //APAGAR QUESTIONARIO
                         System.out.println("insira a identificação do Questionário a apagar: ");
                         br = new BufferedReader(new InputStreamReader(System.in));
                         String id4 = br.readLine();

                         stub.apagarQuestionario(id4);
                         System.out.println("Caso exista, o Questionario foi apagado.\n");

                         break;
                         
                case 5: //OBTER LISTA DE PERGUNTAS DE UM QUEST
                        System.out.println("insira a identificação do Questionario: ");
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String id5 = br.readLine();
                       
                        ArrayList obj5 = stub.listarPerguntas(id5);
                        if(obj5.isEmpty())
                        {
                            System.out.println("O questionário que introduziu não existe.\n");
                        }
                        else
                        {
                            System.out.println("Lista de Perguntas:");
                            for(int i = 0; i < obj5.size(); i++)
                            {
                               System.out.println("- " + obj5.get(i));
                            }              
                            System.out.println("\n");
                        }
                        break;
                    
                case 6: //RESPONDER AS PERG DOS QUEST
                    System.out.print("Introduza o id do Questionário a que pretende responder: ");
                    
                    br = new BufferedReader(new InputStreamReader(System.in));
                    String id6 = br.readLine();

                    ArrayList obj6= stub.listarPerguntas(id6);
                    
                    int numPerguntas= obj6.size();
                    
                    
                     if(numPerguntas == 0)
                    {
                        System.out.println("Questionario Inexistente.\n");
                    }
                     else
                     {
                        

                        Scanner scResposta= new Scanner(System.in);
                        boolean valid = false;
                        
                        for(int i = 0 ; i < numPerguntas ; i++)
                        {
                            System.out.println("Pergunta "+ (i+1)+ ": " + obj6.get(i));

                            int resposta = 0;
                            int num = 0;
                            
                            while(!valid) 
                            {
                               System.out.print("Insira um número entre 1 e 10: ");
                               
                               try 
                               {
                                  num = scResposta.nextInt();
                                  if(num >= 1 && num <= 10)
                                  {
                                     valid = true;
                                  } 
                                  
                                  else 
                                  {
                                     System.out.println("Fora do intervalo de números .");
                                  }
                               } 
                               catch (InputMismatchException e) 
                               {
                                  System.out.println("Caracteres inválidos.");
                                  scResposta.next();
                               }
                            }
                            valid=false;
                                                                
                            stub.adicionarResposta(id6 ,num);
                            
                         }
                        
                        String uniqueID = UUID.randomUUID().toString();
                        
                        System.out.println("\nRespostas submetidas. Código de submissão "+uniqueID+"\n");
                        
                        stub.incrementaVezesRespondido(id6);
                        }

                    break;
                    
                case 7://NUMERO DE VEZES RESPONDIDO
                    System.out.println("Introduza o id do Questionario que pretende analisar: \n");
                    br = new BufferedReader(new InputStreamReader(System.in));
                    String id7 = br.readLine();
                    
                    Questionario obj7 = stub.vezesRespondido(id7);
                    
                     if(obj7 == null)
                         {
                            System.out.println("Questionario Inexistente.\n");           
                         }
                         else
                         {
                            System.out.println("O Questionario foi respondido "+obj7.getVezesRespondido()+" vezes.\n");
                         }
                    
                    break;
                    
                case 8: //MEDIA DO VALOR DAS RESPOSTAS
                    
                    System.out.println("Introduza o id do Questionário cuja média de resposta pretende verificar: ");
                    
                    br = new BufferedReader(new InputStreamReader(System.in));
                    String id8 = br.readLine();
                    
                    ArrayList obj8= stub.mediaDoValorRespondido(id8);
                    
                    int numRespostas=obj8.size();
                    
                    if(numRespostas == 0)
                    {
                        System.out.println("Questionario não existe ou não tem respostas ás suas perguntas.\n");
                    }
                    
                    else
                    {
                        int sumRespostas = 0;
                        
                        for(int i=0 ; i < numRespostas ;i++)
                        {
                           Integer intResposta = Integer.parseInt((String) obj8.get(i));
                           sumRespostas = sumRespostas + intResposta;
                        }
                        float media = sumRespostas / (float)numRespostas;

                        System.out.println("O valor da média é de: "+media+" !\n");                                               
                    }
                    break;
                    
                default: System.out.println("Opção inválida! Insira uma opção: \n");
                
                         break;
            }
        }
   }
}
