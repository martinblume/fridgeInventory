package info.martinblume.fridgeinventory.rfidregistration.application.resources;

/**
 * Created by mblume on 01.02.15.
 */

import info.martinblume.fridgeinventory.rfidregistration.application.dao.RfidItemDAO;
import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Logger;

@Path("/rfidItems/toggle")
public class PostRfidItemResource {

    private static final Logger LOG = Logger.getLogger(RfidItemResource.class.getName());

    private final RfidItemDAO dao;

    private RfidItem lastScanned = RfidItem.NONE;

    public PostRfidItemResource(RfidItemDAO dao) {
        this.dao = dao;
    }

    @Suspend(contentType = "application/json")
    @GET
    public String suspend() {
        return "";
    }

    @Broadcast(writeEntity = false)
    @POST
    @Produces("application/json")
    public RfidItem addItem(final RfidItem rfidItem) {
        dao.addItem(rfidItem.getId(), rfidItem.getName());
        return rfidItem;
    }

}

