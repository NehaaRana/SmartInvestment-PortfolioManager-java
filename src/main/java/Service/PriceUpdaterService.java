/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tax
 */
@Singleton
@Startup
public class PriceUpdaterService {
    
    @PersistenceContext(unitName="my_pu")
    private EntityManager em;
    Random random = new Random();
    @PostConstruct
    public void init() {
        System.out.println(" PriceUpdaterService LOADED");
    }

    // Runs every 1 minute
    @Schedule(hour="*", minute="*/1", persistent=false)
    public void updatePrices() {
        
         System.out.println(" Scheduler running...");

        em.flush();
        em.clear();
        List<Object[]> list = em.createNativeQuery(
            "SELECT aid, current_price, type FROM assets"
        ).getResultList();

        for (Object[] row : list) {
            int aid = ((Number) row[0]).intValue();
            BigDecimal price = (BigDecimal) row[1];
            String type = row[2] != null ? row[2].toString() : "OTHER";

            BigDecimal newPrice = generatePrice(type, price);

            em.createNativeQuery(
                "UPDATE assets SET current_price=? WHERE aid=?"
            ).setParameter(1, newPrice)
             .setParameter(2, aid)
             .executeUpdate();
        }
        
        em.flush();
        em.clear();

        // After price update, update portfolio value
        em.createNativeQuery(
            "UPDATE portfolio p JOIN assets a ON p.aid=a.aid " +
            "SET p.current_value = p.units * a.current_price"
        ).executeUpdate();
        //HARD JPA CACHE RESET
         em.getEntityManagerFactory().getCache().evictAll();
        System.out.println("Asset prices auto updated.");
    }

    private BigDecimal generatePrice(String type, BigDecimal oldPrice) {
        double changePercent;

        switch(type.toUpperCase()) {
            case "CRYPTO":
                changePercent = randomInRange(-5, 5);
                break;
            case "EQUITY":
                changePercent = randomInRange(-2, 2);
                break;
            case "MUTUAL FUND":
                changePercent = randomInRange(-1, 1);
                break;
            case "COMMODITY":
                changePercent = randomInRange(-1.5, 1.5);
                break;
            case "BOND":
                changePercent = randomInRange(-0.5, 0.5);
                break;
            default:
                changePercent = randomInRange(-1, 1);
        }

        double multiplier = 1 + (changePercent / 100);
        double newPrice = oldPrice.doubleValue() * multiplier;

        return BigDecimal.valueOf(Math.max(newPrice, 1));  // no negative prices
    }

    private double randomInRange(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }
}
