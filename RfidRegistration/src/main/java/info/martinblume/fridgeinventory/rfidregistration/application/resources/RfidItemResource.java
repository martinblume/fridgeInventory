package info.martinblume.fridgeinventory.rfidregistration.application.resources;

import com.google.common.collect.Lists;
import info.martinblume.fridgeinventory.rfidregistration.application.RfidItem;
import info.martinblume.fridgeinventory.rfidregistration.application.RfidItemDAO;
import info.martinblume.fridgeinventory.rfidregistration.views.RfidItemsView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by mblume on 23.12.14.
 */
@Path("/rfidItems")
public class RfidItemResource{

    private final RfidItemDAO dao;

    public RfidItemResource(RfidItemDAO dao) {
        this.dao = dao;
    }

    @GET
    @Produces(value = MediaType.TEXT_HTML)
    public RfidItemsView getItems(){
        return new RfidItemsView(dao.getItems());
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public RfidItem getItem(@PathParam("id") final String id){
        return dao.findItemById(id);
    }

    @POST
    public Response addItem(final RfidItem rfidItem) {
        dao.addItem(rfidItem.getId(),rfidItem.getName());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") final String id){
        dao.deleteItem(id);
        return Response.ok().build();
    }
}
