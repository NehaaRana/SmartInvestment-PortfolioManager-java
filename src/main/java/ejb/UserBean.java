/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Assets;
import Entity.Goals;
import Entity.GroupMaster;
import Entity.Orders;
import Entity.Payments;
import Entity.Payouts;
import Entity.Portfolio;
import Entity.Redemptions;
import Entity.Transactions;
import Entity.Users;
import Entity.Watchlist;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Stateless
@DeclareRoles({"ADMIN","USER"})
public class UserBean implements UserBeanLocal {
    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    
    
    @Inject
    Pbkdf2PasswordHash passwordHasher;
    
    @Override
//    @RolesAllowed("USER")
    public void register(String fullName, String email, String rawPassword, String phone) {

        String hashedPassword = passwordHasher.generate(rawPassword.toCharArray());
        GroupMaster g = em.find(GroupMaster.class, 2);
        Users u = new Users();
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(hashedPassword);
        u.setPhone(phone);
        u.setGid(g);
        em.persist(u);
    }

    @Override
    @RolesAllowed("USER")
    public Users getProfile(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Users.class, uid);
    
    }

//    @Override
//    @RolesAllowed("USER")
//    public void updateProfile(int uid, String fullName, String phone, String password) {
////      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        Users u = em.find(Users.class, uid);
//        if (u != null) {
//            if (fullName != null) u.setFullName(fullName);
//            if (phone != null) u.setPhone(phone);
//            if (password != null) u.setPassword(password);
//            em.merge(u);
//        }
//    
//    }
    
    @Override
    @RolesAllowed("USER")
    public void updateProfile(int uid, String fullName, String phone, String password) {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, uid);
        if (u != null) {
//            if (fullName != null) u.setFullName(fullName);
//            if (phone != null) u.setPhone(phone);
//            if (password != null) u.setPassword(password);
              // Update name
            if (fullName != null && !fullName.trim().isEmpty()) {
                u.setFullName(fullName);
            }

            // Update phone
            if (phone != null && !phone.trim().isEmpty()) {
                u.setPhone(phone);
            }

            if (password != null && !password.trim().isEmpty()) {
                String hashed = passwordHasher.generate(password.toCharArray());
                u.setPassword(hashed);
            }
            em.merge(u);
        }
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public Collection<Assets> getAllAssets() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Assets> assets = em.createNamedQuery("Assets.findAll").getResultList();
        return assets;
    }

    @Override
    @RolesAllowed("USER")
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
    @RolesAllowed("USER")
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

//    @Override
//    public void removeFromWatchlist(int wid) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//          Watchlist wl=em.find(Watchlist.class,wid);
//          if(wl !=null){
//              em.remove(wl);
//          }
//          
//    
//    }
    
    
    @Override
    @RolesAllowed("USER")
    public void removeFromWatchlist(int  wid) {
        Watchlist wl = em.find(Watchlist.class, wid);

        if (wl != null) {
            Users user = wl.getUid();
            Assets asset = wl.getAid();

            if (user != null) {
                Collection<Watchlist> userWatchlist = user.getWatchlistCollection();
                userWatchlist.remove(wl);
                user.setWatchlistCollection(userWatchlist);
                em.merge(user);
            }

            if (asset != null) {
                Collection<Watchlist> assetWatchlist = asset.getWatchlistCollection();
                assetWatchlist.remove(wl);
                asset.setWatchlistCollection(assetWatchlist);
                em.merge(asset);
            }

            em.remove(wl);
        }
    }

    
    

    @Override
    @RolesAllowed("USER")
    public Collection<Watchlist> getUserWatchlist(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Watchlist> watchlist=em.createNamedQuery("Watchlist.findByUser", Watchlist.class)
                                        .setParameter("uid",uid)
                                        .getResultList();
        
        return watchlist;
    }

    @Override
    @RolesAllowed("USER")
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
    @RolesAllowed("USER")
    public Collection<Orders> getUserOrders(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Orders> orders=em.createNamedQuery("Orders.findByUid",Orders.class)
                            .setParameter("uid",uid)
                             .getResultList();
        return orders;
    }

//    @Override
//    @RolesAllowed("USER")
//    public void recordTransaction(int oid, int uid, BigDecimal amount, String txnType, String status) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//          Orders o = em.find(Orders.class, oid);
//          Users u = em.find(Users.class, uid);
//          Collection<Transactions> orderTransactions = o.getTransactionsCollection();
//          Collection<Transactions> userTransactions = u.getTransactionsCollection();
//          
//          Transactions t = new Transactions();
//          t.setOid(o);
//          t.setUid(u);
//          t.setTxnType(txnType);
//          t.setAmount(amount);
//          t.setStatus(status);
//          t.setCreatedAt(new Date());
//          orderTransactions.add(t);
//          userTransactions.add(t);
//          
//          o.setTransactionsCollection(orderTransactions);
//          u.setTransactionsCollection(userTransactions);
//          em.persist(t);
//          em.merge(o);
//          em.merge(u);
//    }
    
    
    @Override
    @RolesAllowed("USER")
    public Transactions recordTransaction(int oid, int uid, BigDecimal amount, String txnType, String status) {

        Orders o = em.find(Orders.class, oid);
        Users u = em.find(Users.class, uid);

        Transactions t = new Transactions();
        t.setOid(o);
        t.setUid(u);
        t.setAmount(amount);
        t.setTxnType(txnType);
        t.setStatus(status);
        t.setCreatedAt(new Date());

        em.persist(t);
        em.flush();   // ⭐ VERY IMPORTANT → ensures TID is generated

        return t;
    }


    @Override
    @RolesAllowed("USER")
    public Collection<Transactions> getUserTransactions(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Transactions> transactions=em.createNamedQuery("Transactions.findByUid",Transactions.class)
                            .setParameter("uid",uid)
                             .getResultList();
        return transactions;
    
    }

    @Override
    @RolesAllowed("USER")
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
    @RolesAllowed("USER")
    public Collection<Payments> getUserPayments(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Payments> payments=em.createNamedQuery("Payments.findByUser", Payments.class)
                            .setParameter("uid", uid)
                            .getResultList();
        return payments;
        
    
    }
    
    
    @Override
    @RolesAllowed("USER")
    public boolean updateOrderStatus(int oid, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Orders order = em.find(Orders.class, oid);

           if (order == null) {
               return false;
           }

           order.setStatus(status);
           em.merge(order);
           return true;
    }
    
    @Override
    @RolesAllowed("USER")
    public Collection<Portfolio> getUserPortfolio(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Collection<Portfolio> portfolio = em.createNamedQuery("Portfolio.findByUser",Portfolio.class)
                                .setParameter("uid",uid)
                                .getResultList();
            return portfolio;
    }

    @Override
    @RolesAllowed("USER")
    public void updatePortfolioValue(int uid, int aid, BigDecimal currentValue) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Collection<Portfolio> portfolioList = em.createNamedQuery("Portfolio.findByUserAsset", Portfolio.class)
            .setParameter("uid", uid)
            .setParameter("aid", aid)
            .getResultList();
            Users user = em.find(Users.class,uid);
            Assets asset = em.find(Assets.class, aid);  
                    
            Collection<Portfolio> userUpdatePortfolio = user.getPortfolioCollection();
            Collection<Portfolio> assetUpdatePortfolio = asset.getPortfolioCollection();
            
            if (!portfolioList.isEmpty()) {
                Portfolio p = portfolioList.iterator().next();
                if (p.getUid().equals(user)) { // ownership check (entity comparison)
                    p.setCurrentValue(currentValue);
                    p.setUpdatedAt(new Date());
                    
                    userUpdatePortfolio.add(p);
                    assetUpdatePortfolio.add(p);
                    
                    user.setPortfolioCollection(userUpdatePortfolio);
                    asset.setPortfolioCollection(assetUpdatePortfolio);
                    
                    em.merge(p);
                    
                    em.merge(user);
                    em.merge(asset);
                }
            }
    }

    @Override
    @RolesAllowed("USER")
    public void createGoal(int uid, String goalName, BigDecimal targetAmt, String  targetDate) {
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
    @RolesAllowed("USER")
    public void updateGoalProgress(int goalId, BigDecimal currentAmt) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Goals g = em.find(Goals.class, goalId);
        if (g != null) {
            g.setCurrentAmt(currentAmt);
            em.merge(g);
        }
    }

    @Override
    @RolesAllowed("USER")
    public Collection<Goals> getUserGoals(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Goals> goals = em.createNamedQuery("Goals.findByUser",Goals.class)
                                  .setParameter("uid",uid)
                                  .getResultList();
        return goals;
    }

    @Override
    @RolesAllowed("USER")
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
    @RolesAllowed("USER")
    public Collection<Redemptions> getUserRedemptions(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Redemptions.findByUser",Redemptions.class)
                  .setParameter("uid", uid)
                  .getResultList(); 
    }

    @Override
    @RolesAllowed("USER")
    public Collection<Payouts> getUserPayouts(int uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Payouts.findByUser", Payouts.class)
                 .setParameter("uid", uid)
                 .getResultList();
    
    }

    @Override
//    @RolesAllowed("USER")
    public Users findByEmail(String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
        return em.createNamedQuery("Users.findByEmail", Users.class)
                 .setParameter("email", email)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null;   // No user found
    }
    }

    @Override
@RolesAllowed("USER")
public void addOrUpdatePortfolio(int uid, int aid, BigDecimal units, BigDecimal pricePerUnit) {

    Users user = em.find(Users.class, uid);
    Assets asset = em.find(Assets.class, aid);

    Collection<Portfolio> portfolioList = em.createNamedQuery("Portfolio.findByUserAsset", Portfolio.class)
            .setParameter("uid", user.getUid())   // pass Integer, not entity
            .setParameter("aid", asset.getAid())  // pass Integer
            .getResultList();

    if (!portfolioList.isEmpty()) {
        Portfolio p = portfolioList.iterator().next();

        BigDecimal existingUnits = p.getUnits();
        BigDecimal existingAvg = p.getAvgPrice();

        BigDecimal totalCost = (existingUnits.multiply(existingAvg))
                 .add(units.multiply(pricePerUnit));

        BigDecimal newUnits = existingUnits.add(units);
        BigDecimal newAvg = totalCost.divide(newUnits, 2, RoundingMode.HALF_UP);

        p.setUnits(newUnits);
        p.setAvgPrice(newAvg);
        p.setUpdatedAt(new Date());

        em.merge(p);

    } else {
        Portfolio p = new Portfolio();
        p.setUid(user);   // Here entity is correct
        p.setAid(asset);  // Here entity is correct
        p.setUnits(units);
        p.setAvgPrice(pricePerUnit);
        p.setCurrentValue(pricePerUnit);
        p.setUpdatedAt(new Date());

        em.persist(p);
    }
}


//    @Override
//    @RolesAllowed("USER")
//    public void addOrUpdatePortfolio(int uid, int aid, BigDecimal units, BigDecimal pricePerUnit) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//           Collection<Portfolio> portfolioList = em.createNamedQuery("Portfolio.findByUserAsset", Portfolio.class)
//            .setParameter("uid", uid)
//            .setParameter("aid", aid)
//            .getResultList();
//
//        Users user = em.find(Users.class, uid);
//        Assets asset = em.find(Assets.class, aid);
//
//        if (!portfolioList.isEmpty()) {
//            // UPDATE Existing Record
//            Portfolio p = portfolioList.iterator().next();
//
//            BigDecimal existingUnits = p.getUnits();
//            BigDecimal existingAvg = p.getAvgPrice();
//
//            // Calculate new average price = weighted average
//            BigDecimal totalCost = (existingUnits.multiply(existingAvg))
//                         .add(units.multiply(pricePerUnit));
//
//            BigDecimal newUnits = existingUnits.add(units);
//            BigDecimal newAvg = totalCost.divide(newUnits, 2, RoundingMode.HALF_UP);
//
//            p.setUnits(newUnits);
//            p.setAvgPrice(newAvg);
//            p.setUpdatedAt(new Date());
//
//            em.merge(p);
//
//        } else {
//            // INSERT New record
//            Portfolio p = new Portfolio();
//            p.setUid(user);
//            p.setAid(asset);
//            p.setUnits(units);
//            p.setAvgPrice(pricePerUnit);
//            p.setCurrentValue(pricePerUnit); // Default same as avg initially
//            p.setUpdatedAt(new Date());
//
//            em.persist(p);
//        }
//    }
}
