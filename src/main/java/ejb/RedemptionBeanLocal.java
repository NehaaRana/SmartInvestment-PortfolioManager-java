/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Redemptions;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author User
 */
@Local
public interface RedemptionBeanLocal {
    void addRedemption(Integer uid, Integer aid, double units, String status, double exit_fee_amt);
    void approveRedemption(Integer rid);
    void markAsPaid(Integer rid);
    void deleteRedemption(Integer rid);
    Collection<Redemptions> getAllRedemptions();
    Collection<Redemptions> getRedemptionsByUser(Integer uid);
    Redemptions findRedemptionById(Integer rid);

}
