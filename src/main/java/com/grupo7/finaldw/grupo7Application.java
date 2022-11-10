package com.grupo7.finaldw;

import com.grupo7.finaldw.auth.DropwizardBlogAuthenticator;
import com.grupo7.finaldw.auth.DropwizardBlogAuthorizer;
import com.grupo7.finaldw.auth.User;
import com.grupo7.finaldw.health.DropwizardBlogApplicationHealthCheck;
import com.grupo7.finaldw.resources.PartsResource;
import com.grupo7.finaldw.services.PartsService;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Environment;

import javax.sql.DataSource;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;


public class grupo7Application extends Application<grupo7Configuration> {

    PartsResource i;
    private static final String SQL = "sql";
    private static final String DROPWIZARD_BLOG_SERVICE = "Dropwizard blog service";
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
    }
}


