/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
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
import jakarta.ejb.Local;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Local
public interface UserBeanLocal {
    
// User Self-Management
    void register(String fullName, String email, String rawPassword, String phone);
    Users getProfile(int uid);
    void updateProfile(int uid, String fullName, String phone, String password);
    Users findByEmail(String email);

    
// Asset & Watchlist
    Collection<Assets> getAllAssets();
    Assets getAssetById(int aid);
    void addToWatchlist(int uid, int aid);
    void removeFromWatchlist(int wid);
    Collection<Watchlist> getUserWatchlist(int uid);

    
//  Orders & Transactions
    void placeOrder(int uid, int aid, String orderType, BigDecimal units, BigDecimal amount);
    Collection<Orders> getUserOrders(int uid);

    void recordTransaction(int oid, int uid, BigDecimal amount, String txnType, String status);
    Collection<Transactions> getUserTransactions(int uid);

//  Payments
    void makePayment(int uid, int tid, String method, String referenceNo, String status);
    Collection<Payments> getUserPayments(int uid);


//  Portfolio & Goals
    Collection<Portfolio> getUserPortfolio(int uid);
    void updatePortfolioValue(int uid, int aid, BigDecimal currentValue);

    void createGoal(int uid, String goalName, BigDecimal targetAmt, Date targetDate);
    void updateGoalProgress(int goalId, BigDecimal currentAmt);
    Collection<Goals> getUserGoals(int uid);

    
//  Redemptions & Payouts
    void requestRedemption(int uid, int aid, BigDecimal units, String status);
    Collection<Redemptions> getUserRedemptions(int uid);
    Collection<Payouts> getUserPayouts(int uid);

    
    
    
    

//    void registerUser(String fullName , String email , String password ,String phone );
//  
//    Users findByEmail(String email);
//    void updateUser(Integer uid,String fullName , String email , String password ,String phone );
//    void deleteUser(Integer uid);
//    Collection<Users> getAllUsers();


}


