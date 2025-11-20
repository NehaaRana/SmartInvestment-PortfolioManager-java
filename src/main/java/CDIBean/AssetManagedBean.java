/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Assets;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
//import jakarta.faces.view.ViewScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "assetManagedBean")
//@RequestScoped
@ViewScoped
public class AssetManagedBean implements Serializable {
    
    @Inject
    private keepRecord keepRecord;
    private Part symbolImage;

    public Part getSymbolImage() {
        return symbolImage;
    }

    public void setSymbolImage(Part symbolImage) {
        this.symbolImage = symbolImage;
    }

    private int aid;
    private String symbol;
    private String name;
    private String type;
    private BigDecimal  current_price;
    private String status;
    private String risk;
    private String searchKeyword;
    private List<Assets> originalList;


    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    
    private Collection<Assets> assetsList;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal  getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(BigDecimal  current_price) {
        this.current_price = current_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    @PostConstruct
    public void init() {
        // Read aid from URL parameter
        String aidParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("aid");

        if (aidParam != null) {
            System.out.println("=== Editing Asset AID: " + aidParam + " ===");

            AdminClient ac = new AdminClient(keepRecord.getToken());
            Assets asset = ac.findAssetById(Assets.class, aidParam);

            if (asset != null) {
                this.aid = asset.getAid();
                this.symbol = asset.getSymbol();
                this.name = asset.getName();
                this.type = asset.getType();
                this.current_price = asset.getCurrentPrice();
                this.status = asset.getStatus();
                this.risk = asset.getRisk();
            }
        } else {
            // No edit parameter → load list
            loadAssets();
        }
    }

    
    
   // @PostConstruct
    //public void init() {
      //  loadAssets();
    //}
    
    
//    @PostConstruct
//    public void loadAssets() {
//        AdminClient ac = new AdminClient(keepRecord.getToken());
//        assetsList = ac.getAllAssets();
//    }

    public void loadAssets() {
        try {
            System.out.println("=== Loading Assets ===");

            AdminClient ac = new AdminClient(keepRecord.getToken());

            assetsList = ac.getAllAssets();
            // Backup full list for searching
            originalList = new ArrayList<>(assetsList);
            System.out.println("Loaded " + assetsList.size() + " assets");

        } catch (Exception e) {
            System.out.println("Error in loadAssets(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    
    public Collection<Assets> getAssetsList() {
        return assetsList;
    }
    
    
//    public String addAsset(){
//    
//        AdminClient ac= new AdminClient(keepRecord.getToken());
//        ac.addAsset(symbol, name, type,  current_price.toString(), status, risk);
//        loadAssets();
//        return "AllAssets?faces-redirect=true";
//    }
    
//    public String addAsset() {
//        try {
//            AdminClient ac = new AdminClient(keepRecord.getToken());
//
//              // --- UPLOAD IMAGE ---
//            if (symbolImage != null) {
//                String fileName = Paths.get(symbolImage.getSubmittedFileName())
//                                       .getFileName().toString();
//
//                // choose upload folder (example)
////                String uploadDir = "D:\\java-ENTERPRISE\\ICT-1-Project\assets";
////                String uploadDir = "D:\\java-ENTERPRISE\\ICT-1-Project\\assets\\";
//                
//                String uploadDir = FacesContext.getCurrentInstance()
//                                .getExternalContext()
//                                .getRealPath("") + "/resources/assets/";
//;
//
//                System.out.println("UPLOAD FOLDER = " + uploadDir);
//
//                Files.copy(symbolImage.getInputStream(),
//                           Paths.get(uploadDir + fileName),
//                           StandardCopyOption.REPLACE_EXISTING);
//
//                this.symbol = fileName;  // Save file name in DB
//            }
//
//            // --- CALL REST API ---
//            ac.addAsset(symbol, name, type, current_price.toString(), status, risk);
//
//            loadAssets();
//            return "AllAssets?faces-redirect=true";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

         public String addAsset() {
            try {
                AdminClient ac = new AdminClient(keepRecord.getToken());

                if (symbolImage != null) {

                    String fileName = Paths.get(symbolImage.getSubmittedFileName())
                                           .getFileName().toString();

                    // FIXED ABSOLUTE PATH TO SRC FOLDER
                    String uploadDir = "D:/java-ENTERPRISE/ICT-1-Project/PortfolioManager/src/main/webapp/resources/assets/";

                    System.out.println("UPLOAD FOLDER = " + uploadDir);

                    File folder = new File(uploadDir);
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }

                    Files.copy(
                        symbolImage.getInputStream(),
                        Paths.get(uploadDir + fileName),
                        StandardCopyOption.REPLACE_EXISTING
                    );

                    this.symbol = fileName;
                }

                ac.addAsset(symbol, name, type, current_price.toString(), status, risk);

                loadAssets();
                return "AllAssets?faces-redirect=true";

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


    
//     public String edit(int aid) {
//        AdminClient ac = new AdminClient(keepRecord.getToken());
//        Assets asset = ac.findAssetById(Assets.class, String.valueOf(aid));
//
//        this.aid = asset.getAid();
//        this.symbol = asset.getSymbol();
//        this.name = asset.getName();
//        this.type = asset.getType();
//        this.current_price = asset.getCurrentPrice();
//        this.status = asset.getStatus();
//        this.risk = asset.getRisk();
//
//        return "EditAsset";
//    }


    public String edit(int aid) {

        AdminClient ac = new AdminClient(keepRecord.getToken());
        Assets asset = ac.findAssetById(Assets.class, String.valueOf(aid));

        this.aid = asset.getAid();
        this.symbol = asset.getSymbol();
        this.name = asset.getName();
        this.type = asset.getType();
        this.current_price = asset.getCurrentPrice();
        this.status = asset.getStatus();
        this.risk = asset.getRisk();
        return "/Admin/EditAsset.xhtml?faces-redirect=true&amp;aid=" + aid;

//        return "/Admin/EditAsset?faces-redirect=false";
    }


//    public String update() {
//        AdminClient ac = new AdminClient(keepRecord.getToken());
////        ac.updateAssetPrice(String.valueOf(aid), String.valueOf(current_price));
////        ac.updateAssetStatus(String.valueOf(aid), status)
    /// 
//        ac.up
//        loadAssets();
//        return "AllAssets?faces-redirect=true";
//    }
    
    
//    public String update() {
//
//        AdminClient ac = new AdminClient(keepRecord.getToken());
//
//        ac.updateAsset(
//                aid,
//                symbol,
//                name,
//                type,
//                current_price.toString(),
//                status,
//                risk
//        );
//
//        loadAssets();
//
//        return "AllAssets?faces-redirect=true";
//    }
    
    
    public String update() {
        try {
            AdminClient ac = new AdminClient(keepRecord.getToken());

            // Check if a new file was uploaded
            if (symbolImage != null && symbolImage.getSize() > 0) {
                String fileName = Paths.get(symbolImage.getSubmittedFileName()).getFileName().toString();

                // Upload directory
                String uploadDir = "D:/java-ENTERPRISE/ICT-1-Project/PortfolioManager/src/main/webapp/resources/assets/";
                File folder = new File(uploadDir);
                if (!folder.exists()) folder.mkdirs();

                // Copy file
                Files.copy(
                    symbolImage.getInputStream(),
                    Paths.get(uploadDir + fileName),
                    StandardCopyOption.REPLACE_EXISTING
                );

                this.symbol = fileName;  // update symbol field
            }
            

            ac.updateAsset(
                    aid,
                    symbol,
                    name,
                    type,
                    current_price.toString(),
                    status,
                    risk
            );

            loadAssets();
            return "AllAssets?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void searchAssetsAjax() {
    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
        loadAssets();   // show all assets when field is empty
            return;
        }

        String key = searchKeyword.toLowerCase();

        assetsList = originalList.stream() // keep originalList for full backup
                .filter(a -> a.getName().toLowerCase().contains(key)
                          || a.getType().toLowerCase().contains(key)
                          || String.valueOf(a.getAid()).contains(key))
                .collect(Collectors.toList());
    }
    
    public String deleteAsset(int aid) {
        AdminClient ac = new AdminClient(keepRecord.getToken());
        ac.deleteAsset(aid);   // calling REST delete
        loadAssets();
        return "AllAssets?faces-redirect=true";
    }

    
    
    /**
     * Creates a new instance of AssetManagedBean
     */
    public AssetManagedBean() {
    }
    
}
