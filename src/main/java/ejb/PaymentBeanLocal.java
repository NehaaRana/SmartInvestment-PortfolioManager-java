/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Payments;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author User
 */
@Local
public interface PaymentBeanLocal {
    void addPayment(Integer uid,Integer tid,String method,String reference_no,String status);
    void updatePayment(Integer pid,Integer uid,Integer tid,String method,String reference_no,String status);
    void deletePayment(Integer pid);
    Payments findPaymentById(Integer pid);
    Collection<Payments> getAllPayments();
    Collection<Payments> getPaymentByUser(Integer uid);
    Collection<Payments> getPaymentByStatus(String status);
}
