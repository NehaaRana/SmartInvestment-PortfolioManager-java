/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Payments;
import Entity.Transactions;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.Collection;

/**
 *
 * @author User
 */
@Stateless
public class PaymentBean implements PaymentBeanLocal {

    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    @Override
    public void addPayment(Integer uid, Integer tid, String method, String reference_no, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Payments p = new Payments();
        Users u = em.find(Users.class, uid);
        Transactions t = em.find(Transactions.class, tid);
        p.setUid(u);
        p.setTid(t);
        p.setMethod(method);
        p.setReferenceNo(reference_no);
        p.setStatus(status);
        em.persist(p);
    }

    @Override
    public void updatePayment(Integer pid, Integer uid, Integer tid, String method, String reference_no, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Payments p = (Payments) em.find(Payments.class, pid);
        Users u = (Users) em.find(Users.class, uid);
        Transactions t = (Transactions) em.find(Transactions.class, tid);
        p.setUid(u);
        p.setTid(t);
        p.setMethod(method);
        p.setReferenceNo(reference_no);
        p.setStatus(status);
        em.merge(p);
    }

    @Override
    public void deletePayment(Integer pid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Payments p = (Payments) em.find(Payments.class, pid);
          if(p != null){
              em.remove(p);
          }
    }

    @Override
    public Payments findPaymentById(Integer pid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          try{
              return em.createNamedQuery("Payments.findByPid",Payments.class)
                      .setParameter("pid",pid)
                      .getSingleResult();
          }catch(NoResultException ex){
              return null;
          }
    }

    @Override
    public Collection<Payments> getAllPayments() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Collection<Payments> payments = em.createNamedQuery("Payments.findAll").getResultList();
          return payments;
    }

    @Override
    public Collection<Payments> getPaymentByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          try{
              return em.createNamedQuery("Payments.findByUser",Payments.class).setParameter("uid", uid).getResultList();
          }catch(NoResultException ex){
              return null;
          }
    }

    @Override
    public Collection<Payments> getPaymentByStatus(String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          try{
              return em.createNamedQuery("Payments.findByStatus",Payments.class).setParameter("status", status).getResultList();
          }catch(NoResultException ex){
              return null;
          }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
