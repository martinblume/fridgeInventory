package info.martinblume.fridgeinventory.rfidregistration.application;


import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by mblume on 23.12.14.
 */
public interface RfidItemDAO {

    @SqlUpdate("create table RFID_ITEMS (id varchar(100) primary key, name varchar(100))")
    void createRfidItemTable();

    @SqlUpdate("insert into RFID_ITEMS (id, name) values (:id, :name)")
    void addItem(@Bind("id") String id, @Bind("name") String name);

    @SqlQuery("select name from RFID_ITEMS where id = :id")
    String findNameById(@Bind("id") String id);
}
