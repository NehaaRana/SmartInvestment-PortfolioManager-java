/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Goals;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

/**
 *
 * @author User
 */
@Stateless
public class GoalBean implements GoalBeanLocal {

    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    @Override
    public void addGoal(Integer uid, String goal_name, double target_amt, double current_amt, Date target_date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users user = em.find(Users.class, uid);
        if (user != null) {
            Goals g = new Goals();
            g.setUid(user);
            g.setGoalName(goal_name);
            g.setTargetAmt(BigDecimal.valueOf(target_amt));
            g.setCurrentAmt(BigDecimal.valueOf(current_amt));
            g.setTargetDate(target_date);
            em.persist(g);
        } 
    }

    @Override
    public void updateGoalProgress(Integer goal_id, double newCurrentAmt) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Goals g = em.find(Goals.class, goal_id);
        if (g != null) {
            g.setCurrentAmt(BigDecimal.valueOf(newCurrentAmt));
            em.merge(g);
        }
    }

    @Override
    public void updateGoal(Integer goal_id, String goal_name, double target_amt, double current_amt, Date target_date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Goals g = em.find(Goals.class, goal_id);
        if (g != null) {
            g.setGoalName(goal_name);
            g.setTargetAmt(BigDecimal.valueOf(target_amt));
            g.setCurrentAmt(BigDecimal.valueOf(current_amt));
            g.setTargetDate(target_date);
            em.merge(g);
        }
    }

    @Override
    public void deleteGoal(Integer goal_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Goals g = em.find(Goals.class, goal_id);
        if (g != null) {
            em.remove(g);
        }
    }

    @Override
    public Collection<Goals> getAllGoals() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Goals.findAll",Goals.class).getResultList();
    }

    @Override
    public Collection<Goals> getGoalsByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.createNamedQuery("Goals.findByUser",Goals.class).setParameter("uid", uid).getResultList();
    }

    @Override
    public Goals findGoalById(Integer goal_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return em.find(Goals.class, goal_id);
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
