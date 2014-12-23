package info.martinblume.fridgeinventory.rfidregistration.application;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mblume on 23.12.14.
 */
public class RfidItem {

    private static final String DEFAULT_NAME = "defaultName";
    private static final String DEFAULT_ID = "defaultId";
    private final String id;

    private final String name;

    /**
     * Default constructor for JSON
     */
    public RfidItem(){
        this(DEFAULT_ID, DEFAULT_NAME);
    }

    public RfidItem(final String id, final String name) {
        this.id = id;
        this.name = name;

    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}
