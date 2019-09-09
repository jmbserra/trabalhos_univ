/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Utilizador;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataManager;

/**
 * zz
 *
 * @author serra
 */
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "/jsp/login.jsp";

        DataManager db = (DataManager) getServletContext().getAttribute("db");

        //OBJETO BASE DE DADOS
        RequestDispatcher rd;

        //OBJETO UTILIZADOR DA SESSAO
        Utilizador user = new Utilizador();

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("submeter")) {
                int offset = 5;//Inteiro para Decrypt

                String name = request.getParameter("username");
                String password = request.getParameter("pass");

                //Pega na password e faz decrypt para comparar a pw inserida originalmente
                if (db.verifyUsername(name)) {
                    
                    String decrypt = user.decrypt(db.getUser(name).getPw(), offset);
                    //Pega na password e faz decrypt para comparar a pw inserida originalmente
                    if (!password.equals(decrypt)) {
                        
                        System.out.println("O utilizador existe mas introduziu uma PASSWORD ERRADA");
                        rd = getServletContext().getRequestDispatcher("/jsp/login.jsp");
                        rd.forward(request, response);
                        //System.out.println("O USER EXISTE MAS A PW ESTA ERRADA");
                    } else {
                        
                        user.setNome(name);
                        user.setPassword(password);
                        //user.setEmail(db.queryGetEmail(name));
                        user.setAlergias(db.queryGetAlergias(name));
                        user.setRegs(db.queryGetRegistos(name));
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);

                        rd = getServletContext().getRequestDispatcher("/jsp/welcome.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    //SE USERNAME NAO EXISTIR

                    rd = getServletContext().getRequestDispatcher("/jsp/login.jsp");
                    rd.forward(request, response);
                }

            } else {
            }
        } else {
            rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
