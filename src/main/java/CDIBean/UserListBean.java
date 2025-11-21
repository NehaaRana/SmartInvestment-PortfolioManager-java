package CDIBean;

import Entity.Users;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import record.keepRecord;

@Named(value = "userManagedBean")
@ViewScoped
public class UserListBean implements Serializable {

    @Inject
    private keepRecord keepRecord;
    private Collection<Users> originalList; 
    private Collection<Users> userList;
    private String searchKeyword;

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Collection<Users> getOriginalList() {
        return originalList;
    }

    public void setOriginalList(Collection<Users> originalList) {
        this.originalList = originalList;
    }

    // ===== Getter =====
    public Collection<Users> getUserList() {
        return userList;
    }

    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void loadUsers() {
        try {
            System.out.println("=== Loading Users ===");

            AdminClient ac = new AdminClient(keepRecord.getToken());            
            userList = ac.getAllUsers();
            originalList = new ArrayList<>(userList);
            System.out.println("Loaded " + userList.size() + " users");

        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
            e.printStackTrace();
            userList = new ArrayList<>();
            originalList = new ArrayList<>();
        }
    }
    
    public void searchUsersAjax() {
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            userList = new ArrayList<>(originalList);  // show all users
            return;
        }

        String key = searchKeyword.toLowerCase();

        userList = originalList.stream()
                .filter(u -> u.getFullName().toLowerCase().contains(key)
                          || u.getEmail().toLowerCase().contains(key)
                          || String.valueOf(u.getUid()).contains(key))
                .collect(Collectors.toList());
    }

    public String deleteUser(String uid) {
        try {
            AdminClient ac = new AdminClient(keepRecord.getToken());
            ac.deleteUser(uid);    // <-- Your API call

            loadUsers();           // Refresh table

            return "AllUsers?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String viewUser(int uid) {
        return "/Admin/UserDetails.xhtml?faces-redirect=true&amp;uid=" + uid;
    }

}
