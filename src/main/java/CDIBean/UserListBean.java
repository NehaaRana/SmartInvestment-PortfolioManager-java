package CDIBean;

import Entity.Users;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Collection;
import record.keepRecord;

@Named(value = "userManagedBean")
@ViewScoped
public class UserListBean implements Serializable {

    @Inject
    private keepRecord keepRecord;

    private Collection<Users> userList;

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
            System.out.println("Loaded " + userList.size() + " users");

        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
            e.printStackTrace();
        }
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
