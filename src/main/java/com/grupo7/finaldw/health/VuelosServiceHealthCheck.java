/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo7.finaldw.health;

import com.codahale.metrics.health.HealthCheck;
import com.grupo7.finaldw.services.VuelosService;
/**
 *
 * @author carlos
 */
public class VuelosServiceHealthCheck extends HealthCheck {
    private static final String HEALTHY = "The Dropwizard Vuelos Service is healthy for read and write";
    private static final String UNHEALTHY = "The Dropwizard Vuelos Service is not healthy. ";
    private static final String MESSAGE_PLACEHOLDER = "{}";

    private final VuelosService vuelosService;

    public VuelosServiceHealthCheck(VuelosService vuelosService) {
        this.vuelosService = vuelosService;
    }

    @Override
    public Result check() throws Exception {
        String mySqlHealthStatus = vuelosService.performHealthCheck();

        if (mySqlHealthStatus == null) {
            return Result.healthy(HEALTHY);
        } else {
            return Result.unhealthy(UNHEALTHY + MESSAGE_PLACEHOLDER, mySqlHealthStatus);
        }
    }
}
