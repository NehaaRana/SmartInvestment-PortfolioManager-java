/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Portfolio;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Local
public interface PortfolioBeanLocal {
    void updatePortfolio(Integer uid, Integer aid, double units, double avgPrice);
    void removePortfolio(Integer pf_id);
    Collection<Portfolio> getAllPortfolio();
    Portfolio findPortfolioById(Integer pf_id);

    Collection<Portfolio> findPortfolioByUser(Integer uid);

    Collection<Portfolio> findPortfolioByAsset(Integer aid);
}
