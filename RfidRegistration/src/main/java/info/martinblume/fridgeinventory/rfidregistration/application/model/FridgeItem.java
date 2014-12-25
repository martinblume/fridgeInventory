package info.martinblume.fridgeinventory.rfidregistration.application.model;

/**
 * Class representing an item that might be in the fridge. Basically a wrapper for the FridgeItem containing more information e.g. whether or not the item is in the fridge.
 * <p/>
 * Created by mblume on 25.12.14.
 */
public class FridgeItem extends RfidItem{
    private boolean isInFridge = false;

    /**
     * Creates a new fridge item, that initially is not in the fridge.
     * @param rfidItem THe rfidItem
     */
    public FridgeItem(final RfidItem rfidItem){
        super(rfidItem.getId(), rfidItem.getName());
    }

    public boolean isInFridge(){return isInFridge;}

    /**
     * Toggles the isInFridge state.
     * @return The new isInFridge state
     */
    public boolean toggleIsInFrige(){
        isInFridge = !isInFridge;
        return isInFridge;
    }
}
