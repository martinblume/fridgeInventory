package info.martinblume.fridgeinventory.rfidregistration.application.dao;


import info.martinblume.fridgeinventory.rfidregistration.application.RfidItemMapper;
import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by mblume on 23.12.14.
 */
@RegisterMapper(RfidItemMapper.class)
public interface RfidItemDAO {

    @SqlUpdate("create table RFID_ITEMS (id varchar(100) primary key, name varchar(100), isInFridge boolean)")
    void createRfidItemTable();

    @SqlUpdate("insert into RFID_ITEMS (id, name, isInFridge) values (:id, :name, false)")
    void addItem(@Bind("id") String id, @Bind("name") String name);

    @SqlUpdate("delete from RFID_ITEMS where id=:id")
    void deleteItem(@Bind("id") String id);

    @SqlQuery("select name from RFID_ITEMS where id = :id")
    String findNameById(@Bind("id") String id);

    @SqlQuery("select isInFridge from RFID_ITEMS where id = :id")
    Boolean isInFridge(@Bind("id") String id);

    @SqlQuery("select * from RFID_ITEMS")
    List<RfidItem> getItems();

    @SqlQuery("select * from RFID_ITEMS where id = :id")
    RfidItem findItemById(@Bind("id") String id);

    @SqlUpdate("update RFID_ITEMS set isInFridge = not isInFridge where id=:id")
    void toggleIsInFridge(@Bind("id") String id);
}
