package info.martinblume.fridgeinventory.rfidregistration.application;

import info.martinblume.fridgeinventory.rfidregistration.application.model.RfidItem;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mblume on 24.12.14.
 */
public class RfidItemMapper implements ResultSetMapper<RfidItem>
{
    public RfidItem map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new RfidItem(r.getString("id"), r.getString("name"));
    }
}
