/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Orders;
import Entity.Transactions;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transaction;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author User
 */
@Stateless
public class TransactionBean implements TransactionBeanLocal {

    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    @Override
    public void addTransaction(Integer oid, Integer uid,String txn_type,double amount,String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           
          Orders o = em.find(Orders.class, oid);
          Users u = em.find(Users.class, uid);
          Transactions t = new Transactions();
          t.setOid(o);
          t.setUid(u);
          t.setTxnType(txn_type);
          t.setAmount(BigDecimal.valueOf(amount));
          t.setStatus(status);
          em.persist(t);
    }

    @Override
    public void updateTransaction(Integer tid,Integer oid , Integer uid ,double amount,String txn_type , String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Transactions t = (Transactions) em.find(Transactions.class, tid);
        Orders o = (Orders) em.find(Orders.class, oid);
        Users u = (Users) em.find(Users.class, uid);
        t.setOid(o);
        t.setUid(u);
        t.setTxnType(txn_type);
        t.setAmount(BigDecimal.valueOf(amount));
        t.setStatus(status);
        em.merge(t);
    }

    @Override
    public void deleteTransaction(Integer tid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Transactions t = (Transactions) em.find(Transactions.class, tid);
        if(t != null){
                em.remove(t);

        }
    }

    @Override
    public Collection<Transactions> getAllTransactions() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Collection<Transactions> transactions = em.createNamedQuery("Transactions.findAll").getResultList();
          return transactions;
    }

}
