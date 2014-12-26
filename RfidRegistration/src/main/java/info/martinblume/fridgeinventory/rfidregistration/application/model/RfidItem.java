package info.martinblume.fridgeinventory.rfidregistration.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing an RFID item registered in the system
 *
 * Created by mblume on 23.12.14.
 */
public class RfidItem {

    private static final String DEFAULT_NAME = "defaultName";
    private static final String DEFAULT_ID = "defaultId";


    private final String id;

    private final String name;

    private boolean isInFridge;

    /**
     * Default constructor for JSON
     */
    public RfidItem(){
        this(DEFAULT_ID, DEFAULT_NAME);
    }

    public RfidItem(final String id, final String name) {
        this.id = id;
        this.name = name;
        this.isInFridge = false;
    }

    public RfidItem(final String id, final String name, final Boolean isInFridge) {
        this.id = id;
        this.name = name;
        this.isInFridge = isInFridge;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public boolean getIsInFridge() {
        return isInFridge;
    }

    public void setIsInFridge(boolean isInFridge) {
        this.isInFridge = isInFridge;
    }
}
