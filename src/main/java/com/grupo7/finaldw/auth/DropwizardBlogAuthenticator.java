/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo7.finaldw.auth;

import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public class DropwizardBlogAuthenticator implements Authenticator<String, User> {
 @Override
 public Optional<User> authenticate(String token) throws AuthenticationException {
   if ("test_token".equals(token)) {
     return Optional.of(new User());
   }
   return Optional.empty();
 }
}
