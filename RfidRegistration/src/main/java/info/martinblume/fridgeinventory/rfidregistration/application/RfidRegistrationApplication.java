package info.martinblume.fridgeinventory.rfidregistration.application;

import info.martinblume.fridgeinventory.rfidregistration.application.resources.RfidItemResource;
import info.martinblume.fridgeinventory.rfidregistration.configuration.RfidRegistrationConfiguration;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;

/**
 * @author Martin Blume
 */
public class RfidRegistrationApplication extends Application<RfidRegistrationConfiguration>{

    public static void main(final String[] args) throws Exception {
        new RfidRegistrationApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RfidRegistrationConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(RfidRegistrationConfiguration rfidRegistrationConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, rfidRegistrationConfiguration.getDataSourceFactory(), "h2");
        final RfidItemDAO dao = jdbi.onDemand(RfidItemDAO.class);
        final RfidItemResource resource = new RfidItemResource(dao);
        //resource.addItem(new RfidItem("1","FirstItem"));
        environment.jersey().register(resource);
    }
}
