/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:GoalResource [Goal]<br>
 * USAGE:
 * <pre>
 *        GoalClient client = new GoalClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class GoalClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public GoalClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Goal");
    }

    public void addGoal(String uid, String goal_name, String target_amt, String current_amt, String target_date) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addGoal/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, goal_name, target_amt, current_amt, target_date})).request().post(null);
    }

    public <T> T getAllGoals(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllGoals");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findGoalById(Class<T> responseType, String goal_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findGoalById/{0}", new Object[]{goal_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getGoalsByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getGoalsByUser/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateGoalProgress(String goal_id, String newCurrentAmt) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateGoalProgress/{0}/{1}", new Object[]{goal_id, newCurrentAmt})).request().put(null);
    }

    public void deleteGoal(String goal_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteGoal/{0}", new Object[]{goal_id})).request().delete();
    }

    public void updateGoal(String goal_id, String goal_name, String target_amt, String current_amt, String target_date) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateGoal/{0}/{1}/{2}/{3}/{4}", new Object[]{goal_id, goal_name, target_amt, current_amt, target_date})).request().put(null);
    }

    public void close() {
        client.close();
    }
    
}
