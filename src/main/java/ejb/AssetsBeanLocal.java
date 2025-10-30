/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Assets;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Local
public interface AssetsBeanLocal {
    void addAsset(String symbol,String name,String type,double current_price,String status,String risk);
    void updateAsset(Integer aid,String symbol,String name,String type,double current_price,String status,String risk);
    void removeAsset(Integer aid);
    
    Collection<Assets> getAllAssets();
    Collection<Assets> findAssetsByName(String name);    
    Collection<Assets> findAssetsBySymbol(String symbol);
    Collection<Assets> findAssetsByType(String type);


}
