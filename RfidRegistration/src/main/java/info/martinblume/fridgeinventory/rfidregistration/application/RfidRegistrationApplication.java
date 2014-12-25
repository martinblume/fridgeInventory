package info.martinblume.fridgeinventory.rfidregistration.application;

import info.martinblume.fridgeinventory.rfidregistration.application.dao.FridgeItemDAO;
import info.martinblume.fridgeinventory.rfidregistration.application.dao.RfidItemDAO;
import info.martinblume.fridgeinventory.rfidregistration.application.resources.FridgeItemResource;
import io.dropwizard.assets.AssetsBundle;
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
        bootstrap.addBundle(new AssetsBundle("/assets", "", "index.html"));
    }

    @Override
    public void run(RfidRegistrationConfiguration rfidRegistrationConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, rfidRegistrationConfiguration.getDataSourceFactory(), "h2");
        final RfidItemDAO dao = jdbi.onDemand(RfidItemDAO.class);
        final FridgeItemDAO fridgeItemDAO = jdbi.onDemand(FridgeItemDAO.class);
        final RfidItemResource resource = new RfidItemResource(dao);
        final FridgeItemResource fridgeItemResource = new FridgeItemResource(fridgeItemDAO);
        //fridgeItemDAO.createFridgeItemTable();
        //dao.createRfidItemTable();
        //resource.addItem(new RfidItem("1","FirstItem"));
        environment.jersey().register(resource);
        environment.jersey().register(fridgeItemResource);
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new ErrorMessageBodyWriter());
    }
}
