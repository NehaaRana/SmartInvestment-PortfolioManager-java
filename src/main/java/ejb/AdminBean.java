/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Assets;
import Entity.GroupMaster;
import Entity.Orders;
import Entity.Payments;
import Entity.Payouts;
import Entity.Portfolio;
import Entity.Redemptions;
import Entity.Transactions;
import Entity.Users;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author User
 */
@Stateless
@DeclareRoles({"ADMIN","USER"})
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    
    @Override
    @RolesAllowed("ADMIN")
    public void addUser(String fullName, String email, String password, String phone, int gid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = new Users();
        GroupMaster g = em.find(GroupMaster.class, gid);
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(password);
        u.setPhone(phone);
        u.setGid(g);
        u.setCreatedAt(new Date());
        em.persist(u);
    }

//    @Override
//  
//    public void updateUser(int uid, String fullName, String phone) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    @RolesAllowed("ADMIN")
    public void deleteUser(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, uid);
        if (u != null) {
            em.remove(u);
        }
    }

    @Override
    @RolesAllowed("ADMIN")
    public Collection<Users> getAllUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Users.findAll",Users.class).getResultList();
    }

    @Override
    @RolesAllowed("ADMIN")
    public Users findUserById(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.find(Users.class, uid);
    }

    @Override
    @RolesAllowed("ADMIN")
    public void addAsset(String symbol, String name, String type, BigDecimal currentPrice, String status, String risk) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Assets a = new Assets();
        a.setSymbol(symbol);
        a.setName(name);
        a.setType(type);
        a.setCurrentPrice(currentPrice);
        a.setStatus(status);
        a.setRisk(risk);
        a.setCreatedAt(new Date());
        em.persist(a);
    }

    @Override
    @RolesAllowed("ADMIN")
    public void updateAssetPrice(int aid, BigDecimal newPrice) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Assets a = em.find(Assets.class, aid);
        if (a != null) {
            a.setCurrentPrice(newPrice);
            em.merge(a);

            // Optionally update portfolio current value for users who hold this asset
            Collection<Portfolio> q = em.createNamedQuery("Portfolio.findByAsset",Portfolio.class).setParameter("aid", aid).getResultList();
            for (Portfolio p : q) {
                BigDecimal currentValue = p.getUnits().multiply(newPrice);
                p.setCurrentValue(currentValue);
                em.merge(p);
            }
        }
    }

    @Override
    @RolesAllowed("ADMIN")
    public void updateAssetStatus(int aid, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Assets a = em.find(Assets.class, aid);
          if(a != null){
              a.setStatus(status);
              em.merge(a);
          }
    }

    @Override
    @RolesAllowed("ADMIN")
    public Collection<Assets> getAllAssets() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        em.flush();   //push pending changes

        em.clear();  
        em.getEntityManagerFactory().getCache().evictAll();  // ✅ remove all JPA cache

        return em.createNamedQuery("Assets.findAll").getResultList();
    }

    @Override
    @RolesAllowed("ADMIN")
    public Assets findAssetById(int aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.find((Assets.class), aid);
    }

    @Override
    @RolesAllowed("ADMIN")
    public Collection<Orders> getAllOrders() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Orders.findAll").getResultList();
    }

    @Override
    @RolesAllowed("ADMIN")
    public Collection<Transactions> getAllTransactions() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Transactions.findAll").getResultList();
    }

    @Override
    @RolesAllowed("ADMIN")
    public void updateOrderStatus(int oid, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Orders o = em.find(Orders.class, oid);
        if (o != null) {
            o.setStatus(status);
            em.merge(o);
        }
    }

    @Override
    @RolesAllowed("ADMIN")
    public void updateTransactionStatus(int tid, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Transactions t = em.find(Transactions.class, tid);
        if (t != null) {
            t.setStatus(status);
            em.merge(t);
        }
    }

    @Override
    @RolesAllowed("ADMIN")
    public Collection<Payments> getAllPayments() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Payments.findAll").getResultList();
    }

    @Override
    @RolesAllowed("ADMIN")
    public void updatePaymentStatus(int pid, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Payments p = em.find(Payments.class, pid);
          if(p != null){
              p.setStatus(status);
              em.merge(p);
          }
    }

    @Override
    @RolesAllowed("ADMIN")
    public Collection<Redemptions> getAllRedemptions() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Redemptions.findAll",Redemptions.class).getResultList();
    }

    @Override
    @RolesAllowed("ADMIN")
    public void approveRedemption(int rid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Redemptions r = em.find(Redemptions.class, rid);
        if (r != null && !"APPROVED".equalsIgnoreCase(r.getStatus())) {
            r.setApprovedAt(new Date());
            r.setStatus("APPROVED");
            em.merge(r);
            
            Portfolio p = em.createNamedQuery("Portfolio.findByUserAsset", Portfolio.class)
                .setParameter("uid", r.getUid().getUid())
                .setParameter("aid", r.getAid().getAid())
                .getSingleResult();

            if(p != null) {
                // subtract unit value
                BigDecimal newUnits = p.getUnits().subtract(r.getUnits());
                p.setUnits(newUnits);

                // if units become zero remove portfolio value
                if(newUnits.compareTo(BigDecimal.ZERO) == 0) {
                    em.remove(p);
                } else {
                    em.merge(p);
                }
            }

            // When approved, optionally create a payout record (admin can do this manually too)
            // BigDecimal amount = calculate payout amount (units * asset current price - exit_fee_amt)
        }
    }

    @Override
    @RolesAllowed("ADMIN")
    public void rejectRedemption(int rid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Redemptions r = em.find(Redemptions.class, rid);
        if (r != null) {
            r.setStatus("REJECTED");
            em.merge(r);
        }
    }

//    @Override
//    @RolesAllowed("ADMIN")
//    public void addPayout(int rid, int uid, BigDecimal amount, String referenceNo, String status) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        Users u = (Users) em.find(Users.class,uid);
//        Redemptions r = (Redemptions) em.find(Redemptions.class,rid);
//        
//        Collection<Payouts> userPayouts = u.getPayoutsCollection();
//        Collection<Payouts> redemptionPayouts = r.getPayoutsCollection();
//
//        Payouts p = new Payouts();
//       
//        p.setRid(r);
//        p.setUid(u);
//        p.setAmount(amount);
//        p.setReferenceNo(referenceNo);
//        p.setStatus(status);
//        p.setCreatedAt(new Date());
//        
//        userPayouts.add(p);
//        redemptionPayouts.add(p);
//        u.setPayoutsCollection(userPayouts);
//        r.setPayoutsCollection(redemptionPayouts);
//        em.persist(p);
//        em.merge(u);
//        em.merge(r);
//
//        // Mark redemption as paid if appropriate
////        Redemptions r = em.find(Redemptions.class, rid);
//        if (r != null && "APPROVED".equalsIgnoreCase(r.getStatus())) {
//            r.setPaidAt(new Date());
//            em.merge(r);
//        }
//    }
    
    @Override
    @RolesAllowed("ADMIN")
    public void addPayout(int rid, int uid, BigDecimal amount, String referenceNo, String status) {

        Users u = em.find(Users.class, uid);
        Redemptions r = em.find(Redemptions.class, rid);

        if (u == null || r == null) {
            throw new IllegalArgumentException("Invalid UID or RID");
        }

        Payouts p = new Payouts();
        p.setUid(u);
        p.setRid(r);
        p.setAmount(amount);
        p.setReferenceNo(referenceNo);
        p.setStatus(status);
        p.setCreatedAt(new Date());

        em.persist(p);

        // When payout done → mark redemption as PAID
        if ("SUCCESS".equalsIgnoreCase(status)) {
            r.setStatus("PAID");
            r.setPaidAt(new Date());
            em.merge(r);
        }
    }


    @Override
    @RolesAllowed("ADMIN")
    public Collection<Payouts> getAllPayouts() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Payouts.findAll",Payouts.class).getResultList();
    }

    @Override
    @RolesAllowed("ADMIN")
    public void updatePayoutStatus(int payId, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Payouts p = em.find(Payouts.class, payId);
          if(p != null){
              p.setStatus(status);
              em.merge(p);
          }
    }

    @Override
    @RolesAllowed("ADMIN")
    public BigDecimal getTotalInvestedAmount() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Orders.totalInvested",BigDecimal.class).getSingleResult();
    }

    @Override
    @RolesAllowed("ADMIN")
    public BigDecimal getTotalPayouts() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Payouts.totalAmount",BigDecimal.class).getSingleResult();
    }

    @Override
    @RolesAllowed("ADMIN")
    public long getTotalUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Users.totalCount",Long.class).getSingleResult();
    }

    @Override
    @RolesAllowed("ADMIN")
    public long getTotalAssets() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Assets.totalCount",Long.class).getSingleResult();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void deleteAsset(int aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Assets a = (Assets) em.find(Assets.class,aid);
        em.remove(a);
    
    }

    @Override
    @RolesAllowed("ADMIN")
    public void updateAsset(int aid, String symbol, String name, String type, BigDecimal currentPrice, String status, String risk) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Assets a = em.find(Assets.class, aid);

        if (a != null) {
            a.setSymbol(symbol);
            a.setName(name);
            a.setType(type);
            a.setCurrentPrice(currentPrice);
            a.setStatus(status);
            a.setRisk(risk);

            em.merge(a);
            
            // Update portfolios just like your price update method
            Collection<Portfolio> q =
                em.createNamedQuery("Portfolio.findByAsset", Portfolio.class)
                  .setParameter("aid", aid)
                  .getResultList();

            for (Portfolio p : q) {
                BigDecimal newValue = p.getUnits().multiply(currentPrice);
                p.setCurrentValue(newValue);
                em.merge(p);
            }
        
        }
    
    }

    @Override
    public Collection<Assets> getAllAssetsPublic() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           return em.createNamedQuery("Assets.findAll").getResultList();

    }
}
