package info.martinblume.fridgeinventory.rfidregistration.application.dao;

import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.Collection;
import java.util.List;

/**
 * Created by mblume on 25.12.14.
 */
public interface FridgeItemDAO {

    @SqlUpdate("create table Fridge_ITEMS (id varchar(100), isInFridge boolean)")
    void createFridgeItemTable();

    @SqlQuery("select * from FRIDGE_ITEMS where isInFridge=true")
    Collection<RfidItem> getAllItemsInFridge();

    @SqlUpdate("insert into FRIDGE_ITEMS (id, isInFridge) values (:id, :isInFridge)")
    void addItem(@Bind("id") String id, @Bind("isInFridge") boolean isInFridge);

    @SqlUpdate("delete from FRIDGE_ITEMS where id=:id")
    void deleteItem(@Bind("id") String id);

    @SqlQuery("select isInFridge from FRIDGE_ITEMS where id = :id")
    Boolean getIsInFridge(@Bind("id") String id);

    @SqlQuery("select * from FRIDGE_ITEMS")
    List<RfidItem> getAllItems();

    @SqlQuery("select * from FRIDGE_ITEMS where id = :id")
    RfidItem findItemById(String id);

    @SqlUpdate("update FRIDGE_ITEMS set isInFridge = not isInFridge where id = :id")
    void toggleIsInFridge(@Bind("id") String id);

}
