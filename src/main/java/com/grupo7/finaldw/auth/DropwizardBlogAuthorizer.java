/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo7.finaldw.auth;

import java.util.Objects;

import io.dropwizard.auth.Authorizer;

public class DropwizardBlogAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User principal, String role) {
        // Allow any logged in user.
        if (Objects.nonNull(principal)) {
            return true;
        }
        return false;
    }
}
