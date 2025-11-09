/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Entity.Assets;
//import client.AssetClient;
//import ejb.AssetsBeanLocal;
//import ejb.GoalBeanLocal;
//
//import ejb.TransactionBeanLocal;
//
//import ejb.OrderBeanLocal;
//import ejb.PaymentBeanLocal;
//import ejb.PayoutBeanLocal;
//import ejb.PortfolioBeanLocal;
//import ejb.RedemptionBeanLocal;
//import ejb.WatchlistBeanLocal;
//import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.ws.rs.core.GenericType;
//import jakarta.ws.rs.core.Response;
//import java.util.ArrayList;
//import java.util.Collection;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AssetsServlet", urlPatterns = {"/AssetsServlet"})
public class AssetsServlet extends HttpServlet {
    
//    @EJB TransactionBeanLocal tbl;
//    AssetClient acl;
//    Response rs;
//    Collection<Assets> assets;
//    GenericType<Collection<Assets>> gassets;
//    @EJB AssetsBeanLocal abl;
//    @EJB OrderBeanLocal obl;
//    @EJB PaymentBeanLocal pbl;
//    @EJB PortfolioBeanLocal pobl;
//    @EJB RedemptionBeanLocal rbl;
//    @EJB PayoutBeanLocal pabl;
//    @EJB WatchlistBeanLocal wbl;
//    @EJB GoalBeanLocal gbl;
 
    
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
            
//            acl = new AssetClient();
//            assets = new ArrayList<>();
//            gassets = new GenericType<Collection<Assets>>(){};
            
//            tbl.addTransaction(3, 4, "debit",15005,"success");
//            tbl.addTransaction(2, 2, "debit",17003.0,"success");
//            tbl.addTransaction(4, 3, "debit",9306,"success");
           
//              abl.addAsset("AAPL", "Apple Inc.", "Stock", 229.35, "Active", "Medium");
//              abl.addAsset("GOOGL", "Alphabet Inc.", "Stock", 162.28, "Active", "Medium");
//              abl.addAsset("BTC", "Bitcoin", "Cryptocurrency", 66000.00, "Active", "High");

//            pbl.addPayment(1, 1,"online","TXN12345", "Completed");
//            pbl.addPayment(2, 3,"UPI","TXN12325", "Completed");
//            pbl.addPayment(3, 4,"Credit Card","TXN98345", "Completed");
//            pbl.addPayment(4, 6,"NetBanking","TXN12045", "Completed");
//            pbl.addPayment(5, 7,"Wallet","TXN12365", "Completed");

              
//              rbl.addRedemption(1, 1, 4, "Processed", 10000);
//              rbl.addRedemption(2, 3, 4, "Processed", 10000);
//              rbl.addRedemption(3, 4, 4, "Pending", 10000);
//              rbl.addRedemption(4, 6, 4, "Pending", 10000);
//              rbl.addRedemption(5, 7, 4, "Processed", 10000);


//               pobl.updatePortfolio(1,2, 15, 620.80);

//              pobl.updatePortfolio(2,7, 10, 1500.50);
//              pobl.updatePortfolio(3,1, 5, 3500.75);
//              pobl.updatePortfolio(4,3, 8, 1700.20);
//                pobl.updatePortfolio(5,6, 15, 620.80);
//              pobl.updatePortfolio(6,4, 6, 2850.90);

//               pabl.addPayout(1, 1, 14500, "REF123","Paid");
//               pabl.addPayout(2, 2, 16500, "REF423","Paid");
//               pabl.addPayout(3, 3, 13540, "REF193","Pending");
//               pabl.addPayout(4, 4, 16800, "REF122","Paid");
//               pabl.addPayout(5, 5, 16500, "REF121","Pending");

//                wbl.addWatchlist(1, 1);
//                wbl.addWatchlist(2, 6);
//                wbl.addWatchlist(3, 7);
//                wbl.addWatchlist(4, 4);
//                wbl.addWatchlist(5, 3);
//                wbl.addWatchlist(6, 1);

                
//                java.sql.Date d1= java.sql.Date.valueOf(java.time.LocalDate.of(2026, 5, 11));
//                java.sql.Date d2= java.sql.Date.valueOf(java.time.LocalDate.of(2027, 3, 13));
//                java.sql.Date d3= java.sql.Date.valueOf(java.time.LocalDate.of(2026, 4, 29));
//                java.sql.Date d4= java.sql.Date.valueOf(java.time.LocalDate.of(2028, 9, 15));
//                java.sql.Date d5= java.sql.Date.valueOf(java.time.LocalDate.of(2029, 6, 21));
//
//                gbl.addGoal(2,"Child Education", 800000, 250000, d1);
//                gbl.addGoal(3,"Child Education", 800000, 250000, d2);
//                gbl.addGoal(4,"Foreign Trip", 300000, 60000, d3);
//                gbl.addGoal(5,"Retirement Fund", 1000000, 450000, d4);
//                gbl.addGoal(6,"Home Renovation", 400000, 100000, d5);

//            tbl.updateTransaction(1, 1, 2, 3000, "offline", "Active");
//              tbl.deleteTransaction(1);
            
//            obl.addOrder(1, 1, "buy", 5, 15005,"Completed");
//            obl.addOrder(2, 3, "buy", 15, 17003.75,"Completed");
//            obl.addOrder(3, 4, "buy", 18, 13601.6,"Completed");
//            obl.addOrder(4, 6, "buy", 5, 9306,"Completed");
//            obl.addOrder(5, 7, "buy", 6, 17101.8,"Completed");

            
            
//            tbl.addTransaction(1, 1, "online",1000,"success");
//            tbl.addTransaction(2, 1, "online",1000,"success");
//            pbl.addPayment(1, 1,"online","3876589", "active");
            
//              rbl.addRedemption(1, 2, 4, "active", 10000);
//              pobl.updatePortfolio(1,1, 200, 3000);
//               pabl.addPayout(1, 1, 100, "900922","active");
//                wbl.addWatchlist(1, 1);
            
                java.sql.Date d= java.sql.Date.valueOf(java.time.LocalDate.of(2026, 2, 10));
//                gbl.addGoal(1,"Car", 20000, 1500, d);
//            tbl.updateTransaction(1, 1, 2, 3000, "offline", "Active");
//              tbl.deleteTransaction(1);
            
//            obl.addOrder(1, 1, "buy", 5, 100,"Completed");
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
