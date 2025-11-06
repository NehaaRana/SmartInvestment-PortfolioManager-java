/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Goals;
import Entity.Users;
import ejb.GoalBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("Goal")
@RequestScoped
public class GoalResource {

    @Context
    private UriInfo context;

    @EJB GoalBeanLocal gbl;
    /**
     * Creates a new instance of GoalResource
     */
    public GoalResource() {
    }

    @POST
    @Path("addGoal/{uid}/{goal_name}/{target_amt}/{current_amt}/{target_date}")
    public void addGoal(@PathParam("uid") Integer uid,
            @PathParam("goal_name")String goal_name,
            @PathParam("target_amt") double target_amt,
            @PathParam("current_amt") double current_amt,
            @PathParam("target_date") Date target_date) {

        gbl.addGoal(uid, goal_name, target_amt, current_amt, target_date);
    }

    @PUT
    @Path("updateGoalProgress/{goal_id}/{newCurrentAmt}")
    public void updateGoalProgress(@PathParam("goal_id") Integer goal_id, 
            @PathParam("newCurrentAmt") double newCurrentAmt) {

        gbl.updateGoalProgress(goal_id, newCurrentAmt);
    }

    @PUT
    @Path("updateGoal/{goal_id}/{goal_name}/{target_amt}/{current_amt}/{target_date}")
    public void updateGoal(
             @PathParam("goal_id") Integer goal_id, 
             @PathParam("goal_name") String goal_name, 
              @PathParam("target_amt") double target_amt, 
               @PathParam("current_amt") double current_amt, 
                @PathParam("target_date") Date target_date) {
        gbl.updateGoal(goal_id, goal_name, target_amt, current_amt, target_date);
    }

    @DELETE
    @Path("deleteGoal/{goal_id}")
    public void deleteGoal(@PathParam("goal_id") Integer goal_id) {
        gbl.deleteGoal(goal_id);
    }

    @GET
    @Path("getAllGoals")
    @Produces("application/json")
    public Collection<Goals> getAllGoals() {
        return gbl.getAllGoals();
    }

    @GET
    @Path("getGoalsByUser/{uid}")
    @Produces("application/json")
    public Collection<Goals> getGoalsByUser(Integer uid) {
        return gbl.getGoalsByUser(uid);
    }

    @GET
    @Path("findGoalById/{goal_id}")
    @Produces("application/json")
    public Goals findGoalById(Integer goal_id) {
        return gbl.findGoalById(goal_id);
    }
}
