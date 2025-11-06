/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Entity.Assets;
import client.AssetClient;
import ejb.TransactionBeanLocal;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AssetsServlet", urlPatterns = {"/AssetsServlet"})
public class AssetsServlet extends HttpServlet {
    
    @EJB TransactionBeanLocal tbl;
    AssetClient acl;
    Response rs;
    Collection<Assets> assets;
    GenericType<Collection<Assets>> gassets;
    
    
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AssetsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            
//          Add Data
            
            acl = new AssetClient();
            assets = new ArrayList<>();
            gassets = new GenericType<Collection<Assets>>(){};
            
            tbl.addTransaction(1, 1, "online",1000,"success");
//            tbl.updateTransaction(1, 1, 2, 3000, "offline", "Active");
              tbl.deleteTransaction(1);
//            acl.addAsset("Symbole_1",  "Asset_1","type_1" ,"1000.10", "success", "low");
//            acl.addAsset("Symbole_2",  "Asset_2","type_2" ,"2000.10", "pending", "medium");
//            acl.addAsset("Symbole_3",  "Asset_3","type_3" ,"4000.10", "success", "high");
 

//          Update Assets

//             acl.updateAsset("1" , "SYMBOL", "ASSET", "TYPE", "60000.0", "SUCCESS", "low");
            
//            out.println("<h2>Add Assets,Update Assets</h2>");
            

//          Delete Asset
//            acl.removeAsset("5");
//            out.println("<h2>Remove Asset</h2>");
//            Display Assets

//            rs=acl.getAllAssets(Response.class);
//            assets = rs.readEntity(gassets);
//            for(Assets a:assets){
//                out.println("<br/>Id :" + a.getAid() + " Symbol:"+ a.getSymbol() + " Name:" + a.getName() + " Type:" + a.getType() + " Current_Price:" + a.getCurrentPrice() + " Status:" + a.getStatus() + " Risk:" + a.getRisk());
//                
//            }
            out.println("<h1>Servlet AssetsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
