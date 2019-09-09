/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Utilizador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataManager;

/**
 *
 * @author serra
 */
public class editServlet extends HttpServlet {

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

        String url = "/jsp/editprofile.jsp";

        DataManager db = (DataManager) getServletContext().getAttribute("db");

        RequestDispatcher rd;

        //VAI BUSCAR TODOS OS ATRIBUTOS DO USER EM SESSAO
        Utilizador user = new Utilizador();

        HttpSession session = request.getSession(false);
        if (session != null) {
            user = (Utilizador) session.getAttribute("user");
        }
        //VERIFICA AS ACCOES
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("submeter")) {

                String name = user.getNome();//NOME DA SESSAO PARA ENVIAR PARA OS METODOS DA BD

                ArrayList<String> alergias = new ArrayList<>();

                //ALERGIAS QUE O UTILIZADOR SUBMETE
                String al1 = request.getParameter("1");
                if (al1 != null) {
                    alergias.add(al1);
                }

                String al2 = request.getParameter("2");
                if (al2 != null) {
                    alergias.add(al2);
                }

                String al3 = request.getParameter("3");
                if (al3 != null) {
                    alergias.add(al3);
                }

                String al4 = request.getParameter("4");
                if (al4 != null) {
                    alergias.add(al4);
                }

                //ADICIONA AS ALERGIAS                   
                db.editAlergias(name, alergias);

                //FAZ REFRESH NOS ATRIBUTOS DO USER EM SESSÃO
                user.setAlergias(alergias);

                rd = getServletContext().getRequestDispatcher("/jsp/profile.jsp");
                rd.forward(request, response);
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
