/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Assets;
import Entity.Portfolio;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;
import jakarta.persistence.NoResultException;

/**
 *
 * @author Admin
 */
@Stateless
public class PortfolioBean implements PortfolioBeanLocal {
    
    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    
    
    @Override
    public void updatePortfolio(Integer uid, Integer aid, double units, double avgPrice) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            //Try to find existing record
            Portfolio pf = em.createNamedQuery("Portfolio.findByUserAsset", Portfolio.class)
                    .setParameter("uid", uid)
                    .setParameter("aid", aid)
                    .getSingleResult();

            // If exists → update
            pf.setUnits(BigDecimal.valueOf(units));
            pf.setAvgPrice(BigDecimal.valueOf(avgPrice));
            em.merge(pf);

        } catch (NoResultException ex) {
            //  If not exists → insert new
            Portfolio pf = new Portfolio();
            pf.setUid(em.find(Users.class, uid));
            pf.setAid(em.find(Assets.class, aid));
            pf.setUnits(BigDecimal.valueOf(units));
            pf.setAvgPrice(BigDecimal.valueOf(avgPrice));

            em.persist(pf);
        }
    
    }

    @Override
    public void removePortfolio(Integer pf_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Portfolio pf= (Portfolio) em.find(Portfolio.class,pf_id);
        if(pf!=null){
            em.remove(pf);
        }
    
    }

    @Override
    public Collection<Portfolio> getAllPortfolio() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         Collection<Portfolio> portfolio = em.createNamedQuery("Portfolio.findAll").getResultList();
        return  portfolio;
    
    
    }

    @Override
    public Portfolio findPortfolioById(Integer pf_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Portfolio.class,pf_id);
    }

    @Override
    public Collection<Portfolio> findPortfolioByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Portfolio> portfolio = em.createNamedQuery("Portfolio.findByUser",Portfolio.class)
                                          .setParameter("uid",uid)
                                          .getResultList();
        return  portfolio;
       
    }

    @Override
    public Collection<Portfolio> findPortfolioByAsset(Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Portfolio> portfolio = em.createNamedQuery("Portfolio.findByAsset",Portfolio.class)
                                          .setParameter("aid",aid)
                                          .getResultList();
        return  portfolio;
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
