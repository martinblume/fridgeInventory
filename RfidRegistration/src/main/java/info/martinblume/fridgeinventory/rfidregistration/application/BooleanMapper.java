package info.martinblume.fridgeinventory.rfidregistration.application;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mblume on 26.12.14.
 */
public class BooleanMapper implements ResultSetMapper<Boolean> {

    @Override
    public Boolean map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return resultSet.getBoolean(0);
    }
}
