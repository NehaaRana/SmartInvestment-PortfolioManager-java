/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Assets;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Stateless
public class AssetsBean implements AssetsBeanLocal {

    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    
    @Override
    public void addAsset(String symbol, String name, String type, double current_price, String status, String risk) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        Assets a = new Assets();
        a.setSymbol(symbol);
        a.setName(name);
        a.setType(type);
//        a.setCurrentPrice(current_price);
        a.setCurrentPrice(BigDecimal.valueOf(current_price)); 
        a.setStatus(status);
        a.setRisk(risk);
        em.persist(a);
        em.flush();
        em.refresh(a);
    }

    @Override
    public void updateAsset(Integer aid, String symbol, String name, String type, double current_price, String status, String risk) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        Assets a= (Assets) em.find(Assets.class , aid);
        a.setSymbol(symbol);
        a.setName(name);
        a.setType(type);
        a.setCurrentPrice(BigDecimal.valueOf(current_price));
        a.setStatus(status);
        a.setRisk(risk);
        
        em.merge(a);
    }

    @Override
    public void removeAsset(Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Assets a = (Assets) em.find(Assets.class, aid);
            em.remove(a);
    }

    @Override
    public Collection<Assets> getAllAssets() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           Collection<Assets> assets = em.createNamedQuery("Assets.findAll").getResultList();
            return assets;
           
        
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Collection<Assets> findAssetsByName(String name) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
          Collection<Assets> assets = em.createNamedQuery("Assets.findByName")
                                      .setParameter("name", name)
                                       .getResultList();
            return assets;
    }

    @Override
    public Collection<Assets> findAssetsBySymbol(String symbol) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Assets> assets = em.createNamedQuery("Assets.findBySymbol")
                                    .setParameter("symbol", symbol)
                                    .getResultList();
        return assets;
    }

    @Override
    public Collection<Assets> findAssetsByType(String type) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Assets> assets = em.createNamedQuery("Assets.findByType")
                                    .setParameter("type", type)
                                    .getResultList();
        return assets;
    }

    @Override
    public Assets findAssetsById(Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         try {
                return em.createNamedQuery("Assets.findByAid", Assets.class)
                         .setParameter("aid", aid)
                         .getSingleResult();
            }catch (NoResultException ex) {
                return null; // safely handle when no record found
            }
    
    }
    
}
