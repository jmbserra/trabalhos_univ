/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Registos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataManager;

/**
 *
 * @author serra
 */
public class mapServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            String base = "/jsp/";
            String url = base + "mapa.jsp";

            RequestDispatcher requestDispatcher
                    = getServletContext().getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
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

        DataManager db = (DataManager) getServletContext().getAttribute("db");

        ArrayList<Registos> regs = new ArrayList();
        regs = db.queryGetAllRegistos();

        String tudo = "";
        for (int i = 0; i < regs.size(); i++) {
            tudo = tudo + regs.get(i).getNome() + " ";
            tudo = tudo + regs.get(i).getAlergia() + " ";
            tudo = tudo + regs.get(i).getData() + " ";
            tudo = tudo + regs.get(i).getCodigo() + " ";
            tudo = tudo + regs.get(i).getLatitude() + " ";
            tudo = tudo + regs.get(i).getLongitude() + " ";
        }

        request.setAttribute("tudo", tudo);
        processRequest(request, response);
        /*
        PrintWriter writer = response.getWriter();

            // build HTML code
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your username is: " + tudo + "\n" + "</h2>";
            htmlRespone += "</html>";

            // return response
            writer.println(htmlRespone);
         */

        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {

        return "Short description";
    }// </editor-fold>

}
