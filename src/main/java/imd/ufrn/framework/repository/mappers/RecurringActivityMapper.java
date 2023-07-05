package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import imd.ufrn.framework.model.RecurringActivity;

public class RecurringActivityMapper implements RowMapper<RecurringActivity> {

    @Override
    @Nullable
    public RecurringActivity mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        RecurringActivity recurringActivity = new RecurringActivity();
        recurringActivity.setId(resultSet.getLong("id"));
        return recurringActivity;
    }
    
}
