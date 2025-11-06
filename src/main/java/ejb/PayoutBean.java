/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Payouts;
import Entity.Redemptions;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Stateless
public class PayoutBean implements PayoutBeanLocal {
    
    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    
    @Override
    public void addPayout(Integer rid, Integer uid, double amount, String referenceNo, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Payouts p = new Payouts();
        p.setRid(em.find(Redemptions.class, rid));
        p.setUid(em.find(Users.class, uid));
        p.setAmount(BigDecimal.valueOf(amount));
        p.setReferenceNo(referenceNo);
        p.setStatus(status);
        em.persist(p);
    
    }

    @Override
    public void updatePayout(Integer payId, Integer rid, Integer uid, double amount, String referenceNo, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Payouts p = em.find(Payouts.class, payId);
        if (p != null) {
            p.setRid(em.find(Redemptions.class, rid));
            p.setUid(em.find(Users.class, uid));
            p.setAmount(BigDecimal.valueOf(amount));
            p.setReferenceNo(referenceNo);
            p.setStatus(status);
            em.merge(p);
        }
    
    }

    @Override
    public void removePayout(Integer payId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Payouts p = em.find(Payouts.class, payId);
        if (p != null) {
            em.remove(p);
        }
    
    
    }

    @Override
    public Collection<Payouts> getAllPayouts() {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Payouts.findAll", Payouts.class).getResultList();

    
    }

    @Override
    public Payouts findPayoutByPayId(Integer payId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         try {
            return em.createNamedQuery("Payouts.findByPayId", Payouts.class)
                     .setParameter("payId", payId)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    
    }

    @Override
    public Collection<Payouts> findPayoutsByStatus(String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Payouts.findByStatus", Payouts.class)
                 .setParameter("status", status)
                 .getResultList();
    
    }

    @Override
    public Collection<Payouts> findPayoutsByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Payouts.findByUser", Payouts.class)
                 .setParameter("uid", uid)
                 .getResultList();
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
