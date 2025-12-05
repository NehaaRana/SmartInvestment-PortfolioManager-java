/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "watchListBean")
@SessionScoped
public class WatchListBean implements Serializable{

    /**
     * Creates a new instance of WatchListBean
     */
    
      
    @Inject
    private keepRecord keepRecord;

    private Set<Integer> assetIds;  // stores all asset IDs in watchlist

    public WatchListBean() {
        assetIds = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        loadWatchlist();
    }

    // Load watchlist for current user
    public void loadWatchlist() {
        try {
            String uid = keepRecord.getUid() + "";
            String token = keepRecord.getToken();

            UserClient uc = new UserClient(token);
            List<Object> rawList = uc.getUserWatchlist(List.class, uid);

            assetIds.clear(); // reset

            // Convert response to JSON Array (safe for Jersey map)
            JSONArray arr = new JSONArray(rawList);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                if (obj.has("aid")) {
                    int aid = obj.getInt("aid");
                    assetIds.add(aid);
                }
            }

            System.out.println("WATCHLIST LOADED: " + assetIds);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("WATCHLIST LOAD ERROR: " + e.getMessage());
        }
    }

    // Add asset to watchlist
    public void addToWatchlist(int aid) {
        try {
            String uid = keepRecord.getUid() + "";
            String token = keepRecord.getToken();

            UserClient uc = new UserClient(token);
            uc.addToWatchlist(uid, aid + "");

            assetIds.add(aid); // instant UI update

            System.out.println("ADDED TO WATCHLIST: " + aid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Remove item from watchlist
    public void removeFromWatchlist(int wid) {
        try {
            String token = keepRecord.getToken();

            UserClient uc = new UserClient(token);
            uc.removeFromWatchlist(wid + "");

            assetIds.remove(wid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check if asset is in watchlist
    public boolean isInWatchlist(int aid) {
        return assetIds.contains(aid);
    }
    
    // Toggle asset in watchlist
    public void toggleWatchlist(int aid) {
        if (isInWatchlist(aid)) {
            removeFromWatchlist(aid);
        } else {
            addToWatchlist(aid);
        }
    }

    
    public Set<Integer> getAssetIds() {
        return assetIds;
    }

    public void setAssetIds(Set<Integer> assetIds) {
        this.assetIds = assetIds;
    }
    
}
