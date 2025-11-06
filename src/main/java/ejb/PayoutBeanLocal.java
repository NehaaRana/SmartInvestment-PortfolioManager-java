/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Payouts;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Local
public interface PayoutBeanLocal {
    void addPayout(Integer rid, Integer uid, double amount, String referenceNo, String status);
    void updatePayout(Integer payId, Integer rid, Integer uid, double amount, String referenceNo, String status);
    void removePayout(Integer payId);
    Collection<Payouts> getAllPayouts();
    Payouts findPayoutByPayId(Integer payId);
    Collection<Payouts> findPayoutsByStatus(String status);
    Collection<Payouts> findPayoutsByUser(Integer uid);
}
