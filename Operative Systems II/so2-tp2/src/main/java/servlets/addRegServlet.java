/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Utilizador;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataManager;
import org.glassfish.grizzly.http.util.FastDateFormat;


public class addRegServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String url = "/jsp/addregisto.jsp";

            DataManager db = (DataManager) getServletContext().getAttribute("db");

            RequestDispatcher rd;

            //VAI BUSCAR A SESSAO
            Utilizador user = new Utilizador();

            HttpSession session = request.getSession(false);
            if (session != null) {
                user = (Utilizador) session.getAttribute("user");
            }
            //VERIFICA AS ACCOES
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("submeter")) {

                    //ATRIBUTOS DO REGISTO
                    String name = user.getNome();//NOME DA SESSAO PARA ENVIAR PARA OS METODOS DA BD  
                    String lat = request.getParameter("lat");
                    String longi = request.getParameter("long");

                    Date todaysDate = new Date();
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String data = df.format(todaysDate);
                    String codigo = name + UUID.randomUUID().toString().substring(1, 8) + data;

                    String alergia = null;
                    if (request.getParameter("1") != null) {
                        alergia = request.getParameter("1");
                    }

                    db.queryIntroduzRegisto(name, alergia, data, codigo, lat, longi);
                    //ADICIONA O REGISTO NA BASE DE DADOS                  

                    user.setRegs(db.queryGetRegistos(name));

                    rd = getServletContext().getRequestDispatcher("/jsp/profile.jsp");
                    rd.forward(request, response);
                } else{
                }
            } else {
                rd = getServletContext().getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
