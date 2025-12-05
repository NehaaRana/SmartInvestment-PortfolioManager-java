/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Watchlist;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "watchListBean")
//@SessionScoped
@ViewScoped
public class WatchListBean implements Serializable {

    
    private static final long serialVersionUID = 1L;

    private UserClient userClient; // REST client
    private List<Watchlist> watchlist; // User's watchlist

    @Inject
    private keepRecord keepRecord; // Inject session user info

    @PostConstruct
    public void init() {
        userClient = new UserClient();
        watchlist = new ArrayList<>();
        loadWatchlist(null);
    }

    // In WatchListBean.java
     public void loadWatchlist(ComponentSystemEvent event) {
        try {
            String uid = keepRecord.getUid(); // Get logged-in user id
            if (uid != null && !uid.isEmpty()) {
                Watchlist[] wlArray = userClient.getUserWatchlist(Watchlist[].class, uid);
                watchlist = new ArrayList<>();
                if (wlArray != null) {
                    for (Watchlist w : wlArray) {
                        watchlist.add(w);
                    }
                }
            } else {
                watchlist = new ArrayList<>();
            }
        } catch (Exception e) {
            watchlist = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to load watchlist."));
            e.printStackTrace();
        }
    }




    // Add asset to watchlist
    public void addToWatchlist(int assetId) {
        try {
            if (keepRecord.getUid() != null) {
                userClient.addToWatchlist(keepRecord.getUid(), String.valueOf(assetId));
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Asset added to watchlist."));
                loadWatchlist(null); // Refresh the list
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add asset to watchlist."));
            e.printStackTrace();
        }
}


    // Remove asset from watchlist
    public void removeFromWatchlist(int watchlistId) {
        try {
            userClient.removeFromWatchlist(String.valueOf(watchlistId));
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Asset removed from watchlist."));
            loadWatchlist(null); // Refresh the list
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to remove asset from watchlist."));
            e.printStackTrace();
        }
    }


    // Check if an asset is already in the watchlist
    public boolean isInWatchlist(int assetId) {
        if (watchlist == null) return false;
        return watchlist.stream().anyMatch(w -> w.getAid().getAid() == assetId);
    }


    // Getters and Setters
    public List<Watchlist> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Watchlist> watchlist) {
        this.watchlist = watchlist;
    }
}










//@Named(value = "watchListBean")
//@SessionScoped
//public class WatchListBean implements Serializable{
//
// 
//      @Inject
//    private keepRecord keepRecord;
//
//    private Set<Integer> assetIds;     // asset IDs inside watchlist
//    private Map<Integer, Integer> assetToWid; // assetId → wid mapping
//    private List<Assets> watchlistItems;
//
//    public WatchListBean() {
//        assetIds = new HashSet<>();
//        assetToWid = new HashMap<>();
//        watchlistItems = new ArrayList<>();
//    }
//
//    @PostConstruct
//    public void init() {
//        loadWatchlist();
//    }
//
//    public void loadWatchlist() {
//        try {
//            String uid = keepRecord.getUid() + "";
//            UserClient uc = new UserClient(keepRecord.getToken());
//
//            List<Object> rawList = uc.getUserWatchlist(List.class, uid);
//
//            assetIds.clear();
//            assetToWid.clear();
//            watchlistItems.clear();
//
//            JSONArray arr = new JSONArray(rawList);
//
//            for (int i = 0; i < arr.length(); i++) {
//
//                JSONObject watchObj = arr.getJSONObject(i);
//
//                int wid = watchObj.getInt("wid"); // 👈 IMPORTANT
//                JSONObject assetObj = watchObj.getJSONObject("aid");
//
//                int aid = assetObj.getInt("aid");
//                String name = assetObj.optString("assetName", "N/A");
//                String symbol = assetObj.optString("symbol", "N/A");
//                double price = assetObj.optDouble("currentPrice", 0.0);
//
//                assetIds.add(aid);
//                assetToWid.put(aid, wid); // 👈 STORE MAPPING
//                
//                Assets asset = new Assets(aid, name, symbol);
//
////                Assets asset = new Assets(aid, symbol, name);
//                asset.setCurrentPrice(BigDecimal.valueOf(price));
//
//                watchlistItems.add(asset);
//            }
//
//            System.out.println("LOADED WATCHLIST:");
//            System.out.println("Assets: " + assetIds);
//            System.out.println("Mapping: " + assetToWid);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean isInWatchlist(int aid) {
//        return assetIds.contains(aid);
//    }
//
//    public void addToWatchlist(int aid) {
//        try {
//            String uid = keepRecord.getUid() + "";
//            UserClient uc = new UserClient(keepRecord.getToken());
//
//            uc.addToWatchlist(uid, aid + "");
//
//            loadWatchlist(); // refresh state
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeFromWatchlist(int aid) {
//        try {
//            if (!assetToWid.containsKey(aid)) return;
//
//            int wid = assetToWid.get(aid); // 👈 REMOVE USING WID NOT AID
//
//            UserClient uc = new UserClient(keepRecord.getToken());
//            uc.removeFromWatchlist(String.valueOf(wid));
//
//            loadWatchlist();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void toggleWatchlist(int aid) {
//        if (isInWatchlist(aid))
//            removeFromWatchlist(aid);
//        else
//            addToWatchlist(aid);
//    }
//
//    public List<Assets> getWatchlistItems() {
//        return watchlistItems;
//    }
//}
