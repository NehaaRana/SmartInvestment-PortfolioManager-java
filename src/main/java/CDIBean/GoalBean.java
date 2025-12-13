/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Goals;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import record.keepRecord;

/**
 *
 * @author User
 */
@Named(value = "goalBean")
@SessionScoped
public class GoalBean implements Serializable {

    @Inject
    private keepRecord keepRecord;

    private UserClient userClient;

    private Collection<Goals> goalList;  

    private String goalName;
    private String targetAmt;
    private LocalDate targetDate;


    private String updateAmt;
    
    private Map<Integer, String> updateAmounts = new HashMap<>();
  
    @PostConstruct
    public void init() {
        try {
            String token = keepRecord.getToken();
            userClient = new UserClient(token);

            if (keepRecord.getUid() != null) {
                loadGoals();
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Error initializing GoalBean: " + e.getMessage()));
        }
    }
    
    // Load goals of logged-in user
    public void loadGoals() {
        try {
//            goalList = userClient.getUserGoals(new jakarta.ws.rs.core.GenericType<Collection<Goals>>() {},
                goalList = new java.util.ArrayList<>(
                    userClient.getUserGoals(
                        new jakarta.ws.rs.core.GenericType<Collection<Goals>>() {},
                        keepRecord.getUid()
                    )
            );

           
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Error loading goals: " + e.getMessage()));
        }
    }

    // Create new goal
    public void createGoal() {
        try {
            String formattedDate =targetDate.toString();
            userClient.createGoal(keepRecord.getUid(), goalName, targetAmt, formattedDate);
            loadGoals();

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Goal Created Successfully!"));

            clearForm();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Error creating goal: " + e.getMessage()));
        }
    }

     public void updateProgress(Integer goalId) {
        try {
            Goals selectedGoal = null;

            for (Goals g : goalList) {
                if (g.getGoalId().equals(goalId)) {
                    selectedGoal = g;
                    break;
                }
            }

            if (selectedGoal == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Goal not found", null));
                return;
            }

            String amountStr = updateAmounts.get(goalId);

            if (amountStr == null || amountStr.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Enter amount", null));
                return;
            }

            java.math.BigDecimal addAmt = new java.math.BigDecimal(amountStr);
            java.math.BigDecimal current = selectedGoal.getCurrentAmt();
            java.math.BigDecimal target = selectedGoal.getTargetAmt();

            if (current == null) current = java.math.BigDecimal.ZERO;

            java.math.BigDecimal remaining = target.subtract(current);

            //BLOCK if exceeding target
            if (addAmt.compareTo(remaining) > 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Limit Exceeded",
                        "You can only add ₹" + remaining + " or less"
                    )
                );
                return;
            }

            //Allow update
            userClient.updateGoalProgress(goalId.toString(), addAmt.toPlainString());
            loadGoals();

            updateAmounts.put(goalId, "");

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Amount added successfully"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    
    public int calculateProgress(Goals g) {
        if (g.getCurrentAmt() == null || g.getTargetAmt() == null) return 0;

        int percent = (int) ((g.getCurrentAmt().doubleValue() / g.getTargetAmt().doubleValue()) * 100);

        if (percent > 100) {
            return 100;  // Progress capped visually
        }

        return percent;
    }


    // Reset fields after creation
    private void clearForm() {
        goalName = "";
        targetAmt = "";
        targetDate = null;
    }


    
    public Collection<Goals> getGoalList() {
        return goalList;
    }

    public void setGoalList(Collection<Goals> goalList) {
        this.goalList = goalList;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getTargetAmt() {
        return targetAmt;
    }

    public void setTargetAmt(String targetAmt) {
        this.targetAmt = targetAmt;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public String getUpdateAmt() {
        return updateAmt;
    }

    /**
     * Creates a new instance of GoalBean
     */
    public void setUpdateAmt(String updateAmt) {
        this.updateAmt = updateAmt;
    }

    public Map<Integer, String> getUpdateAmounts() {
        return updateAmounts;
    }

    public void setUpdateAmounts(Map<Integer, String> updateAmounts) {
        this.updateAmounts = updateAmounts;
    }
    public GoalBean() {
    }
    
}
