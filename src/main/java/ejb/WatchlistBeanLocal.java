/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Watchlist;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Local
public interface WatchlistBeanLocal {
    void addWatchlist(Integer uid, Integer aid);

    void removeWatchlist(Integer wid);

    Collection<Watchlist> getAllWatchlist();

    Watchlist findWatchlistByWid(Integer wid);

    Collection<Watchlist> findWatchlistByUser(Integer uid);

    Collection<Watchlist> findWatchlistByAsset(Integer aid);
}
