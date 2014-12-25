package info.martinblume.fridgeinventory.rfidregistration.views;


import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import io.dropwizard.views.View;

import java.util.Collection;

/**
 * Created by mblume on 23.12.14.
 */
public class RfidItemsView extends View{

    private final Collection<RfidItem> rfidItems;

    public RfidItemsView(final Collection<RfidItem> rfidItems) {
        super("rfidItems.ftl");
        this.rfidItems = rfidItems;
    }

    public Collection<RfidItem> getRfidItems() {
        return rfidItems;
    }
}
