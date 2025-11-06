/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Goals;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author User
 */
@Local
public interface GoalBeanLocal {
    void addGoal(Integer uid,String goal_name,double target_amt,double current_amt,java.sql.Date target_date);
    void updateGoalProgress(Integer goal_id, double newCurrentAmt);
    void updateGoal(Integer goal_id, String goal_name, double target_amt, double current_amt, java.sql.Date target_date);
    void deleteGoal(Integer goal_id);
    Collection<Goals> getAllGoals();
    Collection<Goals> getGoalsByUser(Integer uid);
    Goals findGoalById(Integer goal_id);
}
