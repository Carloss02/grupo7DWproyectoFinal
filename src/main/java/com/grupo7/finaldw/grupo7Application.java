package com.grupo7.finaldw;

import com.grupo7.finaldw.health.DropwizardBlogApplicationHealthCheck;
import com.grupo7.finaldw.health.VuelosServiceHealthCheck;
import com.grupo7.finaldw.resources.PartsResource;
import com.grupo7.finaldw.resources.VuelosResource;
import com.grupo7.finaldw.services.PartsService;
import com.grupo7.finaldw.services.VuelosService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;


public class grupo7Application extends Application<grupo7Configuration> {

    PartsResource i;
    VuelosResource j;
    private static final String SQL = "sql";
    private static final String DROPWIZARD_BLOG_SERVICE = "Dropwizard blog service";
    private static final String DROPWIZARD_VUELOS_SERVICE = "Dropwizard Vuelos service";
    private static final String BEARER = "Bearer";

    public static void main(final String[] args) throws Exception {
        new grupo7Application().run(args);
    }

    @Override
    public void run(final grupo7Configuration configuration,
            final Environment environment) {
        // Datasource configuration
        final DataSource dataSource
                = configuration.getDataSourceFactory().build(environment.metrics(), SQL);
        DBI dbi = new DBI(dataSource);

        // Register Health Check
        DropwizardBlogApplicationHealthCheck healthCheck
                = new DropwizardBlogApplicationHealthCheck(dbi.onDemand(PartsService.class));
        environment.healthChecks().register(DROPWIZARD_BLOG_SERVICE, healthCheck);
        // Register Health Check
        VuelosServiceHealthCheck healthCheckVuelos
                = new VuelosServiceHealthCheck(dbi.onDemand(VuelosService.class));
        environment.healthChecks().register(DROPWIZARD_VUELOS_SERVICE, healthCheckVuelos);

        // Register OAuth authentication
        /*
        environment.jersey()
                .register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new DropwizardBlogAuthenticator())
                        .setAuthorizer(new DropwizardBlogAuthorizer()).setPrefix(BEARER).buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        */

        // Register resources
        environment.jersey().register(new PartsResource(dbi.onDemand(PartsService.class)));
        environment.jersey().register(new VuelosResource(dbi.onDemand(VuelosService.class)));
    }
}


