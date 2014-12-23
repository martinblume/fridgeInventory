package info.martinblume.fridgeinventory.rfidregistration.application;

import info.martinblume.fridgeinventory.rfidregistration.application.resources.RfidItemResource;
import info.martinblume.fridgeinventory.rfidregistration.configuration.RfidRegistrationConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author Martin Blume
 */
public class RfidRegistrationApplication extends Application<RfidRegistrationConfiguration>{

    public static void main(final String[] args) throws Exception {
        new RfidRegistrationApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RfidRegistrationConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(RfidRegistrationConfiguration rfidRegistrationConfiguration, Environment environment) throws Exception {
        final RfidItemResource resource = new RfidItemResource();
        environment.jersey().register(resource);
    }
}
