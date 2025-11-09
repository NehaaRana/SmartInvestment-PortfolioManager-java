/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:UserResource [User]<br>
 * USAGE:
 * <pre>
 *        UserClient client = new UserClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class UserClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public UserClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("User");
    }

    public void requestRedemption(String uid, String aid, String units, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("requestRedemption/{0}/{1}/{2}/{3}", new Object[]{uid, aid, units, status})).request().post(null);
    }

    public void makePayment(String uid, String tid, String method, String referenceNo, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("makePayment/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, tid, method, referenceNo, status})).request().post(null);
    }

    public void updatePortfolioValue(String uid, String aid, String currentValue) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePortfolioValue/{0}/{1}/{2}", new Object[]{uid, aid, currentValue})).request().put(null);
    }

    public void updateProfile(String uid, String fullName, String phone, String password) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateProfile/{0}/{1}/{2}/{3}", new Object[]{uid, fullName, phone, password})).request().put(null);
    }

    public void placeOrder(String uid, String aid, String orderType, String units, String amount) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("placeOrder/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, aid, orderType, units, amount})).request().post(null);
    }

    public <T> T getAssetById(Class<T> responseType, String aid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAssetById/{0}", new Object[]{aid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserTransactions(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserTransactions/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProfile(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProfile/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserPortfolio(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserPortfolio/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserGoals(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserGoals/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserWatchlist(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserWatchlist/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addToWatchlist(String uid, String aid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addToWatchlist/{0}/{1}", new Object[]{uid, aid})).request().post(null);
    }

    public void recordTransaction(String oid, String uid, String amount, String txnType, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("recordTransaction/{0}/{1}/{2}/{3}/{4}", new Object[]{oid, uid, amount, txnType, status})).request().post(null);
    }

    public void updateGoalProgress(String goalId, String currentAmt) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateGoalProgress/{0}/{1}", new Object[]{goalId, currentAmt})).request().put(null);
    }

    public <T> T getUserOrders(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserOrders/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserPayments(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserPayments/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserRedemptions(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserRedemptions/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void createGoal(String uid, String goalName, String targetAmt, String targetDate) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("createGoal/{0}/{1}/{2}/{3}", new Object[]{uid, goalName, targetAmt, targetDate})).request().post(null);
    }

    public <T> T getUserPayouts(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserPayouts/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeFromWatchlist(String wid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeFromWatchlist/{0}", new Object[]{wid})).request().delete();
    }

    public <T> T getAllAssets(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllAssets");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
