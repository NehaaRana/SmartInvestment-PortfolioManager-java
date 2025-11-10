/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

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
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Stateless
public class UserBean implements UserBeanLocal {
    @PersistenceContext(unitName="my_pu")
    EntityManager em;

    @Override
    public Users getProfile(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Users.class, uid);
    
    }

    @Override
    public void updateProfile(int uid, String fullName, String phone, String password) {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, uid);
        if (u != null) {
            if (fullName != null) u.setFullName(fullName);
            if (phone != null) u.setPhone(phone);
            if (password != null) u.setPassword(password);
            em.merge(u);
        }
    
    }

    @Override
    public Collection<Assets> getAllAssets() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Assets> assets = em.createNamedQuery("Assets.findAll").getResultList();
        return assets;
    }

    @Override
    public Assets getAssetById(int aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         try {
                return em.createNamedQuery("Assets.findByAid", Assets.class)
                         .setParameter("aid", aid)
                         .getSingleResult();
            }catch (NoResultException ex) {
                return null; // safely handle when no record found
            }
    }

    
    
    @Override
    public void addToWatchlist(int uid, int aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        Users user = em.find(Users.class,uid);
        Assets asset= em.find(Assets.class,aid);
         Collection<Watchlist> userWatchlist = user.getWatchlistCollection();
        Collection<Watchlist> assetsWatchlist = asset.getWatchlistCollection();
//        if (user != null && asset != null) {
            Watchlist wl = new Watchlist();
            wl.setUid(user);
            wl.setAid(asset);
            wl.setAddedAt(new Date());
            
             userWatchlist.add(wl);
            assetsWatchlist.add(wl);
            
            user.setWatchlistCollection(userWatchlist);
            asset.setWatchlistCollection(assetsWatchlist);
            em.persist(wl);
            em.merge(user);
            em.merge(asset);
//        }
    
    }

    @Override
    public void removeFromWatchlist(int wid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Watchlist wl=em.find(Watchlist.class,wid);
          if(wl !=null){
              em.remove(wl);
          }
          
    
    }

    @Override
    public Collection<Watchlist> getUserWatchlist(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Watchlist> watchlist=em.createNamedQuery("Watchlist.findByUser", Watchlist.class)
                                        .setParameter("uid",uid)
                                        .getResultList();
        
        return watchlist;
    }

    @Override
    public void placeOrder(int uid, int aid, String orderType, BigDecimal units, BigDecimal amount) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Orders o  = new Orders();
         Assets asset = em.find(Assets.class, aid);
         Collection<Orders> orders = asset.getOrdersCollection();
         Users user = em.find(Users.class,uid);
         o.setUid(user);
         o.setAid(asset);
         o.setOrderType(orderType);
         o.setUnits(units);
         o.setAmount(amount);
         o.setStatus("PENDING");
         
         orders.add(o);
         asset.setOrdersCollection(orders);
         em.persist(o);
         em.merge(asset);
    }

    @Override
    public Collection<Orders> getUserOrders(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Orders> orders=em.createNamedQuery("Orders.findByUid",Orders.class)
                            .setParameter("uid",uid)
                             .getResultList();
        return orders;
    }

    @Override
    public void recordTransaction(int oid, int uid, BigDecimal amount, String txnType, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Orders o = em.find(Orders.class, oid);
          Users u = em.find(Users.class, uid);
          Collection<Transactions> orderTransactions = o.getTransactionsCollection();
          Collection<Transactions> userTransactions = u.getTransactionsCollection();
          
          Transactions t = new Transactions();
          t.setOid(o);
          t.setUid(u);
          t.setTxnType(txnType);
          t.setAmount(amount);
          t.setStatus(status);
          t.setCreatedAt(new Date());
          orderTransactions.add(t);
          userTransactions.add(t);
          
          o.setTransactionsCollection(orderTransactions);
          u.setTransactionsCollection(userTransactions);
          em.persist(t);
          em.merge(o);
          em.merge(u);
    }

    @Override
    public Collection<Transactions> getUserTransactions(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Transactions> transactions=em.createNamedQuery("Transactions.findByUid",Transactions.class)
                            .setParameter("uid",uid)
                             .getResultList();
        return transactions;
    
    }

    @Override
    public void makePayment(int uid, int tid, String method, String referenceNo, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Payments p = new Payments();
        Users u = em.find(Users.class, uid);
        Transactions t = em.find(Transactions.class, tid);
        Collection<Payments> userPayments = u.getPaymentsCollection();
        Collection<Payments> transactionPayments =t.getPaymentsCollection();
        p.setUid(u);
        p.setTid(t);
        p.setMethod(method);
        p.setReferenceNo(referenceNo);
        p.setStatus(status);
        p.setCreatedAt(new Date());
        
        userPayments.add(p);
        transactionPayments.add(p);
        
        u.setPaymentsCollection(userPayments);
        t.setPaymentsCollection(transactionPayments);
        em.persist(p);
        em.merge(u);
        em.merge(t);
    }

    @Override
    public Collection<Payments> getUserPayments(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Payments> payments=em.createNamedQuery("Payments.findByUser", Payments.class)
                            .setParameter("uid", uid)
                            .getResultList();
        return payments;
        
    
    }

    @Override
    public Collection<Portfolio> getUserPortfolio(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Collection<Portfolio> portfolio = em.createNamedQuery("Portfolio.findByUser",Portfolio.class)
                                .setParameter("uid",uid)
                                .getResultList();
            return portfolio;
    }

    @Override
    public void updatePortfolioValue(int uid, int aid, BigDecimal currentValue) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Collection<Portfolio> portfolioList = em.createNamedQuery("Portfolio.findByUserAsset", Portfolio.class)
            .setParameter("uid", uid)
            .setParameter("aid", aid)
            .getResultList();
            Users user = em.find(Users.class,uid);
            if (!portfolioList.isEmpty()) {
                Portfolio p = portfolioList.iterator().next();
                if (p.getUid().equals(user)) { // ownership check (entity comparison)
                    p.setCurrentValue(currentValue);
                    p.setUpdatedAt(new Date());
                    em.merge(p);
                }
            }
    }

    @Override
    public void createGoal(int uid, String goalName, BigDecimal targetAmt, Date  targetDate) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users user = em.find(Users.class, uid);
        Collection<Goals> userGoal = user.getGoalsCollection();
        
        Goals g = new Goals();
            g.setUid(user);
            g.setGoalName(goalName);
            g.setTargetAmt(targetAmt);
            g.setCurrentAmt(BigDecimal.ZERO);
            g.setTargetDate(targetDate);
            g.setCreatedAt(new Date());
            
            userGoal.add(g);
            
            user.setGoalsCollection(userGoal);
            em.persist(g);
            
            em.merge(user); 
    
    }

    @Override
    public void updateGoalProgress(int goalId, BigDecimal currentAmt) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Goals g = em.find(Goals.class, goalId);
        if (g != null) {
            g.setCurrentAmt(currentAmt);
            em.merge(g);
        }
    }

    @Override
    public Collection<Goals> getUserGoals(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Goals> goals = em.createNamedQuery("Goals.findByUser",Goals.class)
                                  .setParameter("uid",uid)
                                  .getResultList();
        return goals;
    }

    @Override
    public void requestRedemption(int uid, int aid, BigDecimal units, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, uid);
        Assets a = em.find(Assets.class, aid);
        Collection<Redemptions> userRedemption = u.getRedemptionsCollection();
        Collection<Redemptions> assetRedemption = a.getRedemptionsCollection();
            Redemptions r = new Redemptions();
            r.setUid(u);
            r.setAid(a);
            r.setUnits(units);
            r.setStatus(status == null ? "PENDING" : status);
            userRedemption.add(r);
            assetRedemption.add(r);
            
            u.setRedemptionsCollection(userRedemption);
            a.setRedemptionsCollection(assetRedemption);
//            r.setExitFeeAmt(BigDecimal.valueOf(exit_fee_amt));
            em.persist(r);
            em.merge(u);
            em.merge(a);
        
    }

    @Override
    public Collection<Redemptions> getUserRedemptions(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Redemptions.findByUser",Redemptions.class)
                  .setParameter("uid", uid)
                  .getResultList(); 
    }

    @Override
    public Collection<Payouts> getUserPayouts(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Payouts.findByUser", Payouts.class)
                 .setParameter("uid", uid)
                 .getResultList();
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
//    @Override
//    public void registerUser(String fullName, String email, String password, String phone) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//          Users u = new Users();
//          u.setFullName(fullName);
//          u.setEmail(email);
//          u.setPassword(password);
//          u.setPhone(phone);
//          em.persist(u);
//          
//    }
//
//    // Add business logic below. (Right-click in editor and choose
//    // "Insert Code > Add Business Method")
//
//    @Override
//    public Users findByEmail(String email) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//       try{
//           return em.createNamedQuery("Users.findByEmail",Users.class)
//                   .setParameter("email", email)
//                   .getSingleResult();
//       }catch(NoResultException ex){
//            return null;
//       }
//    }
//
//    @Override
//    public void updateUser(Integer uid, String fullName, String email, String password, String phone) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        Users u =(Users) em.find(Users.class ,uid);
//        u.setFullName(fullName);
//        u.setEmail(email);
//        u.setPassword(password);
//        u.setPhone(phone);
//        
//        em.merge(u);
//    
//    }
//
//    @Override
//    public void deleteUser(Integer uid) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        Users u = (Users) em.find(Users.class, uid);
//        if (u != null) {
//            em.remove(u);
//        }
//    }
//
//    @Override
//    public Collection<Users> getAllUsers() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//         return em.createNamedQuery("Users.findAll",Users.class).getResultList();
//    }
}
