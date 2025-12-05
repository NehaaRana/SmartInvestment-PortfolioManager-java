/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Watchlist;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
@SessionScoped
public class WatchListBean implements Serializable {

    
    private static final long serialVersionUID = 1L;

    private UserClient userClient; // REST client
    private List<Watchlist> watchlist; // User's watchlist

    @Inject
    private keepRecord keepRecord; // Inject session user info

    @PostConstruct
    public void init() {
//        userClient = new UserClient();
        userClient = new UserClient(keepRecord.getToken());

        watchlist = new ArrayList<>();
//        loadWatchlist(null);
            loadWatchlist();

    }

//    // In WatchListBean.java
//     public void loadWatchlist(ComponentSystemEvent event) {
//        try {
//            String uid = keepRecord.getUid(); // Get logged-in user id
//            if (uid != null && !uid.isEmpty()) {
//                Watchlist[] wlArray = userClient.getUserWatchlist(Watchlist[].class, uid);
//                watchlist = new ArrayList<>();
//                if (wlArray != null) {
//                    for (Watchlist w : wlArray) {
//                        watchlist.add(w);
//                    }
//                }
//            } else {
//                watchlist = new ArrayList<>();
//            }
//        } catch (Exception e) {
//            watchlist = new ArrayList<>();
//            FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to load watchlist."));
//            e.printStackTrace();
//        }
//    }

    public void loadWatchlist() {
        try {
            String uid = keepRecord.getUid();
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
//                loadWatchlist(null); // Refresh the list
                    loadWatchlist();

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
//            loadWatchlist(null); // Refresh the list
                loadWatchlist();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to remove asset from watchlist."));
            e.printStackTrace();
        }
    }


    // Check if an asset is already in the watchlist
//    public boolean isInWatchlist(int assetId) {
//        if (watchlist == null) return false;
//        return watchlist.stream().anyMatch(w -> w.getAid().getAid() == assetId);
//    }
    
    public boolean isInWatchlist(int assetId) {
        if (watchlist == null) return false;
        return watchlist.stream().anyMatch(w ->
            w.getAid() != null && w.getAid().getAid() == assetId
        );
    }

    
//    toggleWtchList
    public void toggleWatchlist(int assetId) {
         try {
             String uid = keepRecord.getUid();
             if (uid == null || uid.isEmpty()) {
                 FacesContext.getCurrentInstance().addMessage(null,
                     new FacesMessage("Login required"));
                 return;
             }

             // Remove if exists
             for (Watchlist w : watchlist) {
//                 if (w.getAid().getAid() == assetId) {
                if (w.getAid() != null && w.getAid().getAid() == assetId) {

                        userClient.removeFromWatchlist(String.valueOf(w.getWid()));
//                     loadWatchlist(null);
                    loadWatchlist();
                     return;
                 }
             }

             // Add new
             userClient.addToWatchlist(uid, String.valueOf(assetId));
//             loadWatchlist(null);
                loadWatchlist();

         } catch (Exception e) {
             e.printStackTrace();
         }
     }



    // Getters and Setters
    public List<Watchlist> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Watchlist> watchlist) {
        this.watchlist = watchlist;
    }
}







