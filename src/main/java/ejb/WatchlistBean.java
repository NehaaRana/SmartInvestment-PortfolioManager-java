/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Assets;
import Entity.Users;
import Entity.Watchlist;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Stateless
public class WatchlistBean implements WatchlistBeanLocal {
    
    @PersistenceContext(unitName="my_pu")
    EntityManager  em;
    
    @Override
    public void addWatchlist(Integer uid, Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users user = em.find(Users.class, uid);
        Assets asset = em.find(Assets.class, aid);
        if (user != null && asset != null) {
            Watchlist wl = new Watchlist();
            wl.setUid(user);
            wl.setAid(asset);
            em.persist(wl);
        }
    }   

    @Override
    public void removeWatchlist(Integer wid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Watchlist wl = em.find(Watchlist.class, wid);
        if (wl != null) {
            em.remove(wl);
        }
    }

    @Override
    public Collection<Watchlist> getAllWatchlist() {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Watchlist.findAll", Watchlist.class).getResultList();

    
    }

    @Override
    public Watchlist findWatchlistByWid(Integer wid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Watchlist.class, wid);

    }

    @Override
    public Collection<Watchlist> findWatchlistByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Watchlist.findByUser", Watchlist.class)
                .setParameter("uid", uid)
                .getResultList();
    
    }

    @Override
    public Collection<Watchlist> findWatchlistByAsset(Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Watchlist.findByAsset", Watchlist.class)
                .setParameter("aid", aid)
                .getResultList();
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
