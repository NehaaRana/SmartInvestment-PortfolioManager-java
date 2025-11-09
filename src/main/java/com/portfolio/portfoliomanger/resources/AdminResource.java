/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import Entity.GroupMaster;
import Entity.Orders;
import Entity.Payments;
import Entity.Payouts;
import Entity.Portfolio;
import Entity.Redemptions;
import Entity.Transactions;
import Entity.Users;
import ejb.AdminBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("Admin")
@RequestScoped
public class AdminResource {

    @Context
    private UriInfo context;
    @EJB AdminBeanLocal abl;

    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
    }

    @POST
    @Path("addUser/{fullName}/{email}/{password}/{phone}/{gid}")
    public void addUser(@PathParam("fullName") String fullName,
            @PathParam("email") String email,
            @PathParam("password") String password,
            @PathParam("phone") String phone, 
            @PathParam("gid") int gid) {
        abl.addUser(fullName, email, password, phone, gid);
    }

//    @PUT
//    @Path("updateUser")
//    public void updateUser(int uid, String fullName, String phone) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @DELETE
    @Path("deleteUser/{uid}")
    public void deleteUser(@PathParam("uid") int uid) {
        abl.deleteUser(uid);
    }

    @GET
    @Produces("application/json")
    public Collection<Users> getAllUsers() {
        return abl.getAllUsers();
    }

    @GET
    @Produces("application/json")
    @Path("findUserById/{uid}")
    public Users findUserById(@PathParam("uid") int uid) {
        return abl.findUserById(uid);
    }

    @POST
    @Path("addAsset/{symbol}/{name}/{type}/{currentPrice}/{status}/{risk}")
    public void addAsset(@PathParam("symbol") String symbol,
            @PathParam("name") String name, 
            @PathParam("type") String type,
            @PathParam("currentPrice") BigDecimal currentPrice,
            @PathParam("status") String status, 
            @PathParam("risk") String risk) {
        abl.addAsset(symbol, name, type, currentPrice, status, risk);
    }

    @PUT
    @Path("updateAssetPrice/{aid}/{newPrice}")
    public void updateAssetPrice(@PathParam("aid") int aid, 
            @PathParam("newPrice") BigDecimal newPrice) {
        abl.updateAssetPrice(aid, newPrice);
    }
    
    @PUT
    @Path("updateAssetStatus/{aid}/{status}")
    public void updateAssetStatus(@PathParam("aid") int aid, 
            @PathParam("status") String status) {
        abl.updateAssetStatus(aid, status);
    }

    @GET
    @Path("getAllAssets")
    @Produces("application/json")
    public Collection<Assets> getAllAssets() {
        return abl.getAllAssets();
    }

    @GET
    @Path("findAssetById/{aid}")
    @Produces("application/json")
    public Assets findAssetById(@PathParam("aid") int aid) {
        return abl.findAssetById(aid);
    }

    @GET
    @Path("getAllOrders")
    @Produces("application/json")
    public Collection<Orders> getAllOrders() {
        return abl.getAllOrders();
    }

    @GET
    @Path("getAllTransactions")
    @Produces("application/json")
    public Collection<Transactions> getAllTransactions() {
        return abl.getAllTransactions();
    }

    @PUT
    @Path("updateOrderStatus/{oid}/{status}")
    public void updateOrderStatus(@PathParam("oid") int oid,
            @PathParam("status") String status) {
        abl.updateOrderStatus(oid, status);
    }

    @PUT
    @Path("updateTransactionStatus/{tid}/{status}")
    public void updateTransactionStatus(@PathParam("tid") int tid,
            @PathParam("status") String status) {
        abl.updateTransactionStatus(tid, status);
    }

    @GET
    @Path("getAllPayments")
    @Produces("application/json")
    public Collection<Payments> getAllPayments() {
        return abl.getAllPayments();
    }

    @PUT
    @Path("updatePaymentStatus/{pid}/{status}")
    public void updatePaymentStatus(@PathParam("pid") int pid,
            @PathParam("status") String status) {
        abl.updatePaymentStatus(pid, status);
    }

    @GET
    @Path("getAllRedemptions")
    @Produces("application/json")
    public Collection<Redemptions> getAllRedemptions() {
        return abl.getAllRedemptions();
    }

    @PUT
    @Path("approveRedemption/{rid}")
    public void approveRedemption(@PathParam("rid") int rid) {
        abl.approveRedemption(rid);
    }

    @PUT
    @Path("rejectRedemption/{rid}")
    public void rejectRedemption(@PathParam("rid") int rid) {
        abl.rejectRedemption(rid);
    }

    @POST
    @Path("addPayout/{rid}/{uid}/{amount}/{referenceNo}/{status}/")
    public void addPayout(@PathParam("rid") int rid, 
            @PathParam("uid") int uid,
            @PathParam("amount") BigDecimal amount,
            @PathParam("referenceNo") String referenceNo,
            @PathParam("status") String status) {
        abl.addPayout(rid, uid, amount, referenceNo, status);
    }

    @GET
    @Path("getAllPayouts")
    @Produces("application/json")
    public Collection<Payouts> getAllPayouts() {
        return abl.getAllPayouts();
    }

    @PUT
    @Path("updatePayoutStatus/{payId}/{status}")
    public void updatePayoutStatus(@PathParam("payId") int payId,
            @PathParam("status") String status) {
        abl.updatePayoutStatus(payId, status);
    }

    @GET
    @Path("getTotalInvestedAmount")
    public BigDecimal getTotalInvestedAmount() {
        return abl.getTotalInvestedAmount();
    }

    @GET
    @Path("getTotalPayouts")
    public BigDecimal getTotalPayouts() {
        return abl.getTotalPayouts();
    }

    @GET
    @Path("getTotalUsers")
    public int getTotalUsers() {
        return abl.getTotalUsers();
    }

    @GET
    @Path("getTotalAssets")
    public int getTotalAssets() {
        return abl.getTotalAssets();
    }

}
