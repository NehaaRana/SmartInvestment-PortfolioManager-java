/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Payments;
import Entity.Transactions;
import Entity.Users;
import ejb.PaymentBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("Payment")
@RequestScoped
public class PaymentResource {

    @Context
    private UriInfo context;
    @EJB PaymentBeanLocal pbl;
    /**
     * Creates a new instance of PaymentResource
     */
    public PaymentResource() {
    }

    @POST
    @Path("addPayment/{uid}/{tid}/{method}/{reference_no}/{status}")
    public void addPayment(@PathParam("uid") Integer uid,
            @PathParam("tid") Integer tid,
            @PathParam("method") String method,
            @PathParam("refernce_no") String reference_no,
            @PathParam("status") String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        pbl.addPayment(uid, tid, method, reference_no, status);
    }

    @PUT
    @Path("updatePayment/{pid}/{tid}/{method}/{reference_no}/{status}")
    public void updatePayment(@PathParam("pid") Integer pid,
            @PathParam("uid") Integer uid,
            @PathParam("tid") Integer tid,
            @PathParam("method") String method,
            @PathParam("refernce_no")String reference_no, 
            @PathParam("status") String status) {
            
        pbl.updatePayment(pid, uid, tid, method, reference_no, status);
    }

    @DELETE
    @Path("deletePayment/{pid}")
    public void deletePayment(Integer pid) {
        pbl.deletePayment(pid);
    }
    @GET
    @Path("findPaymentById/{pid}")
    @Produces("application/json")
    public Payments findPaymentById(Integer pid) {
        return pbl.findPaymentById(pid);
    }

    @GET
    @Produces("application/json")
    public Collection<Payments> getAllPayments() {
        return pbl.getAllPayments();
    }

    @GET
    @Path("getPaymentByUser/{uid}")
    @Produces("application/json")
    public Collection<Payments> getPaymentByUser(Integer uid) {
         return pbl.getPaymentByUser(uid);
    }
    @GET
    @Path("getPaymentByStatus/{status}")
    @Produces("application/json")
    public Collection<Payments> getPaymentByStatus(String status) {
        return pbl.getPaymentByStatus(status);
    }

    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.PaymentResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of PaymentResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
}





//package com.portfolio.portfoliomanger.resources;
//
//import Entity.Payments;
//import ejb.PaymentsBeanLocal;
//import jakarta.ejb.EJB;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import java.util.Collection;
//
//@Path("payments")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class PaymentsResource {
//
//    @EJB
//    PaymentsBeanLocal paymentsBean;
//
//    // 1️⃣ Add new payment
//    @POST
//    public void addPayment(Payments payment) {
//        paymentsBean.addPayment(
//            payment.getUid(),
//            payment.getTid(),
//            payment.getMethod(),
//            payment.getReferenceNo(),
//            payment.getStatus()
//        );
//    }
//
//    // 2️⃣ Get all payments
//    @GET
//    public Collection<Payments> getAllPayments() {
//        return paymentsBean.getAllPayments();
//    }
//
//    // 3️⃣ Get payment by ID
//    @GET
//    @Path("{pid}")
//    public Payments getPayment(@PathParam("pid") int pid) {
//        return paymentsBean.findPaymentById(pid);
//    }
//
//    // 4️⃣ Get payments by user
//    @GET
//    @Path("user/{uid}")
//    public Collection<Payments> getPaymentsByUser(@PathParam("uid") int uid) {
//        return paymentsBean.getPaymentsByUser(uid);
//    }
//
//    // 5️⃣ Get payments by status
//    @GET
//    @Path("status/{status}")
//    public Collection<Payments> getPaymentsByStatus(@PathParam("status") String status) {
//        return paymentsBean.getPaymentsByStatus(status);
//    }
//
//    // 6️⃣ Update payment status
//    @PUT
//    @Path("{pid}")
//    public void updatePaymentStatus(@PathParam("pid") int pid, Payments payment) {
//        paymentsBean.updatePaymentStatus(pid, payment.getStatus());
//    }
//
//    // 7️⃣ Delete payment
//    @DELETE
//    @Path("{pid}")
//    public void deletePayment(@PathParam("pid") int pid) {
//        paymentsBean.deletePayment(pid);
//    }
//}
