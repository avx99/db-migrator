package ma.inwi.innov.migration_app.utils;



import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SqlUtils {

    private SqlUtils() {

    }


    public List<String> getColumnNames(Query query, EntityManager entityManager) {
        var columnNames = query.unwrap(org.hibernate.query.NativeQuery.class);


        return null;
    }

}
