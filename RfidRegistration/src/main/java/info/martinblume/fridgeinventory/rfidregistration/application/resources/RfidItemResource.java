package info.martinblume.fridgeinventory.rfidregistration.application.resources;

import com.google.common.collect.Lists;
import info.martinblume.fridgeinventory.rfidregistration.application.RfidItem;
import info.martinblume.fridgeinventory.rfidregistration.application.RfidItemDAO;
import info.martinblume.fridgeinventory.rfidregistration.views.RfidItemsView;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by mblume on 23.12.14.
 */
@Path("/rfidItems")
@Produces(MediaType.TEXT_HTML)
public class RfidItemResource{

    private final Collection<RfidItem> rfidItems = Lists.newArrayList();
    private final RfidItemDAO dao;

    public RfidItemResource(RfidItemDAO dao) {
        this.dao = dao;
    }

    @GET
    @Produces(value = MediaType.TEXT_HTML)
    public RfidItemsView getItems(){
        return new RfidItemsView(rfidItems);
    }

    @POST
    public Response addItem(final RfidItem rfidItem) {
        rfidItems.add(rfidItem);
        return Response.noContent().build();
    }
}