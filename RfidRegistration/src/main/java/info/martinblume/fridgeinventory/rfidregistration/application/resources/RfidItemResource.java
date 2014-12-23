package info.martinblume.fridgeinventory.rfidregistration.application.resources;

import com.google.common.collect.Lists;
import info.martinblume.fridgeinventory.rfidregistration.application.RfidItem;

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
@Produces(MediaType.APPLICATION_JSON)
public class RfidItemResource{

    private final Collection<RfidItem> rfidItems = Lists.newArrayList();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Collection<RfidItem> getItems(){
        return rfidItems;
    }

    @POST
    public Response publishNewBlog(RfidItem rfidItem) {
        rfidItems.add(rfidItem);
        return Response.noContent().build();
    }
}
