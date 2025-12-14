/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Assets;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
//import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
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
@ViewScoped
public class AssetManagedBean implements Serializable {
    
    @Inject
    private keepRecord keepRecord;
    private Part symbolImage;
    
    private String selectedType = "ALL";

    
    
    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }


    
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

//    @PostConstruct
//    public void init() {
//        // Read aid from URL parameter
//        String aidParam = FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap()
//                .get("aid");
//
//        if (aidParam != null) {
//            System.out.println("=== Editing Asset AID: " + aidParam + " ===");
//
//            AdminClient ac = new AdminClient(keepRecord.getToken());
//            Assets asset = ac.findAssetById(Assets.class, aidParam);
//
//            if (asset != null) {
//                this.aid = asset.getAid();
//                this.symbol = asset.getSymbol();
//                this.name = asset.getName();
//                this.type = asset.getType();
//                this.current_price = asset.getCurrentPrice();
//                this.status = asset.getStatus();
//                this.risk = asset.getRisk();
//            }
//        } else {
//            // No edit parameter → load list
//            loadAssets();
//        }
//    }
    
    
    @PostConstruct
    public void init() {
        try {
            String page = FacesContext.getCurrentInstance()
                            .getViewRoot()
                            .getViewId();   // e.g. /User/ViewPlans.xhtml

            System.out.println("Current Page: " + page);

            // If user is on ViewPlans.xhtml → always load plans LIST
            if (page.contains("ViewPlans")) {
                loadAssetsForPlans();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // For all admin pages — normal logic
        String aidParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("aid");

        if (aidParam != null) {
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
            loadAssets();   // Admin list only
        }
    }
    
//    @PostConstruct
//    public void init() {
//        try {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            ExternalContext ec = fc.getExternalContext();
//
//            // Get token from session (if user is logged in)
//            String token = (String) ec.getSessionMap().get("token");
//            AdminClient ac;
//
//            if (token != null && !token.isEmpty()) {
//                // Logged-in user: use token if needed for other authenticated calls
//                ac = new AdminClient(token);
//                System.out.println("INFO: Logged-in user token: " + token);
//            } else {
//                // Guest user: no token
//                ac = new AdminClient();
//                System.out.println("INFO: Guest user loading public assets");
//            }
//
//            // Load public assets for both guest and logged-in users
//            assetsList = ac.getAllAssetsPublic();
//            originalList = new ArrayList<>(assetsList); // <--- Add this line
//
//            System.out.println("INFO: Assets loaded: " + (assetsList != null ? assetsList.size() : 0));
//
//            ac.close(); // Always close the client
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("ERROR: Failed to load assets: " + e.getMessage());
////            assetsList = new ArrayList<>(); // fallback empty list
////            originalList = new ArrayList<>();
//
//        }
//    }



    
    
   

//    public void loadAssets() {
//        try {
//            System.out.println("=== Loading Assets ===");
//
//            AdminClient ac = new AdminClient(keepRecord.getToken());
//
//            assetsList = ac.getAllAssets();
//            // Backup full list for searching
//            originalList = new ArrayList<>(assetsList);
//            System.out.println("Loaded " + assetsList.size() + " assets");
//
//        } catch (Exception e) {
//            System.out.println("Error in loadAssets(): " + e.getMessage());
//            e.printStackTrace();
//        }finally {
//            if (ac != null) ac.close();   //VERY IMPORTANT
//        }
//    }
    
    public void loadAssets() {
        
        System.out.println("POLL TRIGGERED loadAssets()");

        AdminClient ac = null;   //Declare first

        try {
            System.out.println("=== Loading Assets ===");

            ac = new AdminClient(keepRecord.getToken());

            assetsList = ac.getAllAssets();
            originalList = new ArrayList<>(assetsList);

            System.out.println("Loaded " + assetsList.size() + " assets");
            for (Assets a : assetsList) {
                System.out.println(">> PRICE: " + a.getName() + " = " + a.getCurrentPrice());
            }
        } catch (Exception e) {
            System.out.println("Error in loadAssets(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ac != null) {
                ac.close();    //Now this works
            }
        }
    }


    
    
    public Collection<Assets> getAssetsList() {

        return assetsList;
    }
    
    
    private void saveImageToBothLocations(Part file, String fileName) throws Exception {

        // Read file into bytes ONCE
        byte[] imageBytes = file.getInputStream().readAllBytes();

        // ------------------------------------------------
        // 1. Save in PROJECT folder
        // ------------------------------------------------
        String projectPath = "D:/java-ENTERPRISE/ICT-1-Project/PortfolioManager/src/main/webapp/resources/assets/";
//          String projectPath = "E:/PortfolioManager/src/main/webapp/resources/assets/";
//        String projectPath = "D:/java-ENTERPRISE/ICT-1-Project/PortfolioManager/src/main/webapp/resources/assets/";

        File projectFolder = new File(projectPath);
        if (!projectFolder.exists()) projectFolder.mkdirs();

        Files.write(
            Paths.get(projectPath + fileName),
            imageBytes,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        );

        System.out.println(" Saved to PROJECT: " + projectPath + fileName);


        // ------------------------------------------------
        // 2. Save in DEPLOYED (WAR) folder
        // ------------------------------------------------
        String deployedDir = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRealPath("/resources/assets/");

        if (deployedDir != null) {

            File deployedFolder = new File(deployedDir);
            if (!deployedFolder.exists()) deployedFolder.mkdirs();

            Files.write(
                Paths.get(deployedDir + fileName),
                imageBytes,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );

            System.out.println(" Saved to DEPLOYED: " + deployedDir + fileName);

        } else {
            System.out.println("deployedDir = null (running from IDE, not WAR)");
        }
    }




         public String addAsset() {
            try {
                AdminClient ac = new AdminClient(keepRecord.getToken());

                if (symbolImage != null) {

                    String fileName = Paths.get(symbolImage.getSubmittedFileName())
                                           .getFileName().toString();

                    saveImageToBothLocations(symbolImage, fileName);

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

    }



    
    
    public String update() {
        try {
            AdminClient ac = new AdminClient(keepRecord.getToken());


            if (symbolImage != null && symbolImage.getSize() > 0) {

                String fileName = Paths.get(symbolImage.getSubmittedFileName())
                                       .getFileName().toString();

                saveImageToBothLocations(symbolImage, fileName);

                this.symbol = fileName;
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
    
    public String deleteAsset(int aid) {
        AdminClient ac = new AdminClient(keepRecord.getToken());
        ac.deleteAsset(aid);   // calling REST delete
        loadAssets();
        return "AllAssets?faces-redirect=true";
    }
    
   public void filterByType(String type) {
    System.out.println("FILTER CLICKED: " + type);

    this.selectedType = type;

    // Reset to full list if ALL
    if (type.equals("ALL")) {
        assetsList = new ArrayList<>(originalList);
        return;
    }

    // Filter only matching type
    assetsList = originalList.stream()
            .filter(a -> a.getType().equalsIgnoreCase(type))
            .collect(Collectors.toList());
}


    
    public void searchAssetsAjax() {

        // If empty → reload original full dataset
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {

            FacesContext fc = FacesContext.getCurrentInstance();
            String page = fc.getViewRoot().getViewId();

            if (page.contains("AllAssets")) {
                loadAssets();  // Admin Page
            } else {
                // ViewPlans page uses original public list
                assetsList = new ArrayList<>(originalList);
            }
            return;
        }

        String key = searchKeyword.toLowerCase();

        assetsList = originalList.stream()
                .filter(a -> a.getName().toLowerCase().contains(key)
                          || a.getType().toLowerCase().contains(key)
                          || String.valueOf(a.getAid()).contains(key))
                .collect(Collectors.toList());
    }




    
    public void loadAssetsForPlans() {
        try {
            System.out.println("=== Loading Assets for SIP Plans ===");

            AdminClient ac = new AdminClient();  
            // Always use PUBLIC endpoint for user-facing SIP plans
            assetsList = ac.getAllAssetsPublic();

            originalList = new ArrayList<>(assetsList);

            System.out.println("Loaded " + assetsList.size() + " assets for SIP Plans");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    
    

    
    /**
     * Creates a new instance of AssetManagedBean
     */
    public AssetManagedBean() {
    }
    
}
