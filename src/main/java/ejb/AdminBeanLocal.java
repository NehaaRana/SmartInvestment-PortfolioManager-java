/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Assets;
import Entity.Orders;
import Entity.Payments;
import Entity.Payouts;
import Entity.Redemptions;
import Entity.Transactions;
import Entity.Users;
import jakarta.ejb.Local;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author User
 */
@Local
public interface AdminBeanLocal {
    //User Management
    void addUser(String fullName, String email, String password, String phone, int gid);
//    void updateUser(int uid, String fullName, String phone);
    void deleteUser(int uid);
    Collection<Users> getAllUsers();
    Users findUserById(int uid);

    
    //Asset Management
    void addAsset(String symbol, String name, String type, BigDecimal currentPrice, String status, String risk);
    void updateAssetPrice(int aid, BigDecimal newPrice);
    void updateAssetStatus(int aid, String status);
    void deleteAsset(int aid);
    Collection<Assets> getAllAssets();
    Assets findAssetById(int aid);
    
    //Order & Transaction Oversight
    Collection<Orders> getAllOrders();
    Collection<Transactions> getAllTransactions();
    void updateOrderStatus(int oid, String status);
    void updateTransactionStatus(int tid, String status);
    
    //Payments, Redemptions & Payouts
    Collection<Payments> getAllPayments();
    void updatePaymentStatus(int pid, String status);

    Collection<Redemptions> getAllRedemptions();
    void approveRedemption(int rid);
    void rejectRedemption(int rid);

    void addPayout(int rid, int uid, BigDecimal amount, String referenceNo, String status);
    Collection<Payouts> getAllPayouts();
    void updatePayoutStatus(int payId, String status);

    //System Insights
    BigDecimal getTotalInvestedAmount();
    BigDecimal getTotalPayouts();
    long getTotalUsers();
    long getTotalAssets();

    
}
