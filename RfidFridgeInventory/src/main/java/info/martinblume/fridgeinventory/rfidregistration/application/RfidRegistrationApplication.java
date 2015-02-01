package info.martinblume.fridgeinventory.rfidregistration.application;

import info.martinblume.fridgeinventory.rfidregistration.application.dao.RfidItemDAO;
import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import info.martinblume.fridgeinventory.rfidregistration.application.resources.PostRfidItemResource;
import info.martinblume.fridgeinventory.rfidregistration.application.resources.RfidItemResource;
import info.martinblume.fridgeinventory.rfidregistration.configuration.RfidRegistrationConfiguration;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereServlet;
import org.skife.jdbi.v2.DBI;

import javax.servlet.ServletRegistration;

/**
 * @author Martin Blume
 */
public class RfidRegistrationApplication extends Application<RfidRegistrationConfiguration>{

    public static void main(final String[] args) throws Exception {
        new RfidRegistrationApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RfidRegistrationConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/rfidItems", "rfidItems.html"));
    }

    @Override
    public void run(RfidRegistrationConfiguration rfidRegistrationConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, rfidRegistrationConfiguration.getDataSourceFactory(), "h2");
        final RfidItemDAO dao = jdbi.onDemand(RfidItemDAO.class);
        final RfidItemResource resource = new RfidItemResource(dao);
        final PostRfidItemResource postRfidItemResource = new PostRfidItemResource(dao);
        //dao.createRfidItemTable();
        //resource.addItem(new RfidItem("2","SecondItem"));
        environment.jersey().register(resource);
        environment.jersey().register(postRfidItemResource);
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new ErrorMessageBodyWriter());

        final AtmosphereServlet servlet = new AtmosphereServlet();
        servlet.framework().addInitParameter("com.sun.jersey.config.property.packages", "info.martniblume.fridgeinventory.rfidrefistration.application.resources.websocket");
        servlet.framework().addInitParameter(ApplicationConfig.WEBSOCKET_CONTENT_TYPE, "application/json");
        servlet.framework().addInitParameter(ApplicationConfig.WEBSOCKET_SUPPORT, "true");
        ServletRegistration.Dynamic servletHolder = environment.servlets().addServlet("Chat", servlet);
        servletHolder.addMapping("/rfidItems/*");
    }
}
