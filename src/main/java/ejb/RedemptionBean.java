/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Assets;
import Entity.Redemptions;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author User
 */
@Stateless
public class RedemptionBean implements RedemptionBeanLocal {

    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    @Override
    public void addRedemption(Integer uid, Integer aid, double units, String status, double exit_fee_amt) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, uid);
        Assets a = em.find(Assets.class, aid);

        if (u != null && a != null) {
            Redemptions r = new Redemptions();
            r.setUid(u);
            r.setAid(a);
            r.setUnits(BigDecimal.valueOf(units));
            r.setStatus(status);
            r.setExitFeeAmt(BigDecimal.valueOf(exit_fee_amt));
            em.persist(r);
        }
    }

    @Override
    public void approveRedemption(Integer rid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Redemptions r = em.find(Redemptions.class,rid);
          if(r != null){
              r.setStatus("Approved");
              r.setApprovedAt(new java.util.Date());
              em.merge(r);   
          }
    }

    @Override
    public void markAsPaid(Integer rid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Redemptions r = em.find(Redemptions.class,rid);
          if(r != null){
              r.setStatus("Paid");
              r.setApprovedAt(new java.util.Date());
              em.merge(r);   
          }

    }

    @Override
    public void deleteRedemption(Integer rid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Redemptions r = em.find(Redemptions.class,rid);
            if(r != null){
              em.remove(r);
          }

    }

    @Override
    public Collection<Redemptions> getAllRedemptions() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Redemptions.findAll",Redemptions.class).getResultList();
    }

    @Override
    public Collection<Redemptions> getRedemptionsByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Redemptions.findByUser",Redemptions.class)
                  .setParameter("uid", uid)
                  .getResultList(); 
    }

    @Override
    public Redemptions findRedemptionById(Integer rid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.find(Redemptions.class, rid);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
