package info.martinblume.fridgeinventory.rfidregistration.views;

import info.martinblume.fridgeinventory.rfidregistration.application.model.FridgeItem;
import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import io.dropwizard.views.View;

import java.util.Collection;

/**
 * Created by mblume on 25.12.14.
 */
public class FridgeItemsView extends View{

    private final Collection<FridgeItem> fridgeItems;

    public FridgeItemsView(final Collection<FridgeItem> fridgeItems) {
        super("fridgeItems.ftl");
        this.fridgeItems = fridgeItems;
    }

    public Collection<FridgeItem> getFridgeItems() {
        return fridgeItems;
    }

}
