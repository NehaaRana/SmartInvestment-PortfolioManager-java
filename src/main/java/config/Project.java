/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author root
 */
//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/index.jsp"
//                ,errorPage = "/loginError.jsp"
//              
//        )
//)

//@CustomFormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/Login.jsf"
//        )
//)


// Identity Store
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/portfolio",
//        callerQuery = "select password from users where username = ?",
//        groupsQuery = "select GROUPNAME from groups where username = ?",
        callerQuery = "select password from users where email = ?",
//        groupsQuery = "select group_name from group_master where user_name = ?",
        groupsQuery = "SELECT gm.group_name FROM group_master gm JOIN users u ON u.gid = gm.id WHERE u.email = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30)


@ApplicationScoped
public class Project {
    
}
