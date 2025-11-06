/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Transactions;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author User
 */
@Local
public interface TransactionBeanLocal {
    void addTransaction(Integer oid, Integer uid,String txn_type,double amount,String status);
    void updateTransaction(Integer tid,Integer oid , Integer uid ,double amount,String txn_type , String status);
    void deleteTransaction(Integer tid);
    
    Collection<Transactions> getAllTransactions();
}
