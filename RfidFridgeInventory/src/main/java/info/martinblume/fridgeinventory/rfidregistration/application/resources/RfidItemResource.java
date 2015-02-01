package info.martinblume.fridgeinventory.rfidregistration.application.resources;

import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import info.martinblume.fridgeinventory.rfidregistration.application.dao.RfidItemDAO;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mblume on 23.12.14.
 */
@Path("/rfidItems")
public class RfidItemResource {

    private static final Logger LOG = Logger.getLogger(RfidItemResource.class.getName());

    private final RfidItemDAO dao;

    private RfidItem lastScanned = RfidItem.NONE;

    public RfidItemResource(RfidItemDAO dao) {
        this.dao = dao;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<RfidItem> getItems() {
        return dao.getItems();
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public RfidItem getItem(@PathParam("id") final String id) {
        return dao.findItemById(id);
    }

    @Broadcast(writeEntity = false)
    @POST
    @Produces("application/json")
    public RfidItem addItem(final RfidItem rfidItem) {
        dao.addItem(rfidItem.getId(), rfidItem.getName());
        return rfidItem;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") final String id) {
        dao.deleteItem(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response toggleIsInFridge(@PathParam("id") final String id) {
        LOG.info("Toggle: " + id);
        dao.toggleIsInFridge(id);
        return Response.ok().build();
    }

    @GET
    @Path("/last")
    @Produces(value = MediaType.APPLICATION_JSON)
    public RfidItem getLastScannedItem() {
        return lastScanned;
    }

    @POST
    @Path("/last")
    public void setLastScanned(final RfidItem rfidItem) {
        this.lastScanned = rfidItem;
        for (RfidItem item : dao.getItems()) {
            if (item.getId().equals(rfidItem.getId())) {
                toggleIsInFridge(rfidItem.getId());
                return;
            }
        }
    }

}
