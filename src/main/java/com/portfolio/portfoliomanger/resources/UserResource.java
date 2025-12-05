/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import Entity.Goals;
import Entity.Orders;
import Entity.Payments;
import Entity.Payouts;
import Entity.Portfolio;
import Entity.Redemptions;
import Entity.Transactions;
import Entity.Users;
import Entity.Watchlist;
import ejb.UserBeanLocal;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author Admin
 */
@DeclareRoles({"ADMIN","USER"})
@Path("User")
@RequestScoped
public class UserResource {

    @Context
    private UriInfo context;
    
    @EJB UserBeanLocal ubl;
    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }
    
//    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getProfile/{uid}")
    public Users getProfile(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return ubl.getProfile(uid);
    
    }
    
//    @RolesAllowed("USER")
//    @PermitAll
    @POST
    @Path("register/{fullName}/{email}/{rawPassword}/{phone}")
    public void register(
            @PathParam("fullName") String fullName, 
            @PathParam("email") String email, 
            @PathParam("rawPassword") String rawPassword, 
            @PathParam("phone") String phone) {
            
        ubl.register(fullName, email, rawPassword, phone);
        
    }
    
//    @RolesAllowed("USER")
//    @PermitAll
    @GET
    @Produces("application/json")
    @Path("findByEmail/{email}")
    public Users findByEmail(@PathParam("email") String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return ubl.findByEmail(email);
    }
    
//    @PUT
//    @Path("updateProfile/{uid}")
//    @Consumes("application/json")
//    public void updateProfile(
//            @PathParam("uid") int uid, Users updatedUser) {
//            ubl.updateProfile(uid, updatedUser.getFullName(),
//                               updatedUser.getPhone(),
//                               updatedUser.getPassword());
//    }

    
//    @RolesAllowed("USER")
//    @PUT
//    @Path("updateProfile/{uid}/{fullName}/{phone}/{password}")
//    public void updateProfile(
//            @PathParam("uid") int uid, 
//            @PathParam("fullName") String fullName, 
//            @PathParam("phone") String phone, 
//            @PathParam("password") String password) {
////      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        ubl.updateProfile(uid, fullName, phone, password);
//    
//    }
    
    
    @RolesAllowed("USER")
    @PUT
    @Path("updateProfile/{uid}/{fullName}/{phone}/{password}")
    public void updateProfile(
            @PathParam("uid") int uid, 
            @PathParam("fullName") String fullName, 
            @PathParam("phone") String phone, 
            @PathParam("password") String password) {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        // If password is blank → treat as NO CHANGE
        if (password == null || password.trim().isEmpty() || password.equals("null")) {
            password = null; // signal to BL: do not update password
        }

        ubl.updateProfile(uid, fullName, phone, password);
    
    }

    
    @RolesAllowed({"ADMIN","USER"})
    @GET
    @Produces("application/json")
    @Path("getAllAssets")
    public Collection<Assets> getAllAssets() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return ubl.getAllAssets();
    }

    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getAssetById/{aid}")
    public Assets getAssetById(@PathParam("aid") int aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         return ubl.getAssetById(aid);
    }

    @RolesAllowed("USER")
    @POST
    @Path("addToWatchlist/{uid}/{aid}")
    public void addToWatchlist(@PathParam("uid") int uid,@PathParam("aid") int aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
       ubl.addToWatchlist(uid, aid);
    }

    @RolesAllowed("USER")
    @DELETE
    @Path("removeFromWatchlist/{wid}")
    public void removeFromWatchlist(@PathParam("wid") int wid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         ubl.removeFromWatchlist(wid);
          
    
    }

    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserWatchlist/{uid}")
    public Collection<Watchlist> getUserWatchlist(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return ubl.getUserWatchlist(uid);
    }

    @RolesAllowed("USER")
    @POST
    @Path("placeOrder/{uid}/{aid}/{orderType}/{units}/{amount}")
    public void placeOrder(
            @PathParam("uid") int uid, 
            @PathParam("aid") int aid,
            @PathParam("orderType") String orderType,
            @PathParam("units") BigDecimal units, 
            @PathParam("amount") BigDecimal amount) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         
            ubl.placeOrder(uid, aid, orderType, units, amount);
    }

    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserOrders/{uid}")
    public Collection<Orders> getUserOrders(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       return ubl.getUserOrders(uid);
    }

    @RolesAllowed("USER")
    @POST
    @Path("recordTransaction/{oid}/{uid}/{amount}/{txnType}/{status}")
    public void recordTransaction(
            @PathParam("oid") int oid, 
            @PathParam("uid") int uid, 
            @PathParam("amount") BigDecimal amount, 
            @PathParam("txnType") String txnType, 
            @PathParam("status") String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         ubl.recordTransaction(oid, uid, amount, txnType, status);
    }

    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserTransactions/{uid}")
    public Collection<Transactions> getUserTransactions(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       
        return ubl.getUserTransactions(uid);
    }

    @RolesAllowed("USER")
    @POST
    @Path("makePayment/{uid}/{tid}/{method}/{referenceNo}/{status}")
    public void makePayment(
            @PathParam("uid") int uid, 
            @PathParam("tid") int tid,
            @PathParam("method") String method,
            @PathParam("referenceNo") String referenceNo,
            @PathParam("status")  String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ubl.makePayment(uid, tid, method, referenceNo, status);
    }

    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserPayments/{uid}")
    public Collection<Payments> getUserPayments(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return ubl.getUserPayments(uid);
        
    
    }

    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserPortfolio/{uid}")
    public Collection<Portfolio> getUserPortfolio(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return ubl.getUserPortfolio(uid);
    }

    
    @RolesAllowed("USER")
    @PUT
    @Path("updatePortfolioValue/{uid}/{aid}/{currentValue}")
    public void updatePortfolioValue(
            @PathParam("uid") int uid, 
            @PathParam("aid") int aid, 
            @PathParam("currentValue") BigDecimal currentValue) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          ubl.updatePortfolioValue(uid, aid, currentValue);
    }

    
    @RolesAllowed("USER")
    @POST
    @Path("createGoal/{uid}/{goalName}/{targetAmt}/{targetDate}")
    public void createGoal(
            @PathParam("uid") int uid, 
            @PathParam("goalName") String goalName,
            @PathParam("targetAmt") BigDecimal targetAmt, 
            @PathParam("targetDate") String  targetDateStr ) {
//          throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
//            Date targetDate = Date.valueOf(targetDateStr); // yyyy-MM-dd format
            java.sql.Date targetDate = java.sql.Date.valueOf(targetDateStr); // yyyy-MM-dd format

            ubl.createGoal(uid, goalName, targetAmt, targetDate);
    
    }
    
    @RolesAllowed("USER")
    @PUT
    @Path("updateGoalProgress/{goalId}/{currentAmt}")
    public void updateGoalProgress(@PathParam("goalId") int goalId,@PathParam("currentAmt") BigDecimal currentAmt) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            ubl.updateGoalProgress(goalId, currentAmt);
    }

    
    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserGoals/{uid}")
    public Collection<Goals> getUserGoals(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         return ubl.getUserGoals(uid);
    }

    @RolesAllowed("USER")
    @POST
    @Path("requestRedemption/{uid}/{aid}/{units}/{status}")
    public void requestRedemption(
            @PathParam("uid") int uid, 
            @PathParam("aid") int aid, 
            @PathParam("units") BigDecimal units,
            @PathParam("status") String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ubl.requestRedemption(uid, aid, units, status);
    }

    
    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserRedemptions/{uid}")
    public Collection<Redemptions> getUserRedemptions(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       return ubl.getUserRedemptions(uid);
    }

    @RolesAllowed("USER")
    @GET
    @Produces("application/json")
    @Path("getUserPayouts/{uid}")
    public Collection<Payouts> getUserPayouts(@PathParam("uid") int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       return ubl.getUserPayouts(uid);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    @POST
//    @Path("registerUser/{fullName}/{email}/{password}/{phone}")
//    public void registerUser(
//             @PathParam("fullName") String fullName,
//             @PathParam("email")  String email,
//             @PathParam("password")  String password,
//             @PathParam("phone") String phone) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        
//          ubl.registerUser(fullName, email, password, phone);
//    }
//    
//    
//    @GET
//    @Produces("application/json")
//    @Path("findByEmail/{email}")
//    public Users findByEmail(@PathParam("email")  String email) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//          return ubl.findByEmail(email);
//    }
//    
//    
//    @PUT
//    @Path("updateUser/{uid}/{fullName}/{email}/{password}/{phone}")
//    public void updateUser(
//           @PathParam("uid")  Integer uid, 
//            @PathParam("fullName") String fullName,
//            @PathParam("email") String email, 
//            @PathParam("password") String password,
//            @PathParam("phone") String phone) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//           ubl.updateUser(uid, fullName, email, password, phone);
//    }
//    
//    @DELETE
//    @Path("deleteUser/{uid}")
//    public void deleteUser(@PathParam("uid") Integer uid) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//           ubl.deleteUser(uid);
//     
//    }
//    
//    @GET
//    @Produces("application/json")
//    public Collection<Users> getAllUsers() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        return ubl.getAllUsers();
//    }
    
    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.UserResource
     * @return an instance of java.lang.String
     */
   
}
