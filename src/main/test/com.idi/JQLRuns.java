
package com.idi;

import com.idi.dao.Employee;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class JQLRuns {

    @Test
    public void getJQLResults() {

        // prepare Javers Engine
        Javers javers = JaversBuilder.javers().build();

        // init test data and commit changes
        javers.commit("author", new Employee("foo", 30, 1000) );
        javers.commit("author", new Employee("foo", 31, 1000) );
        javers.commit("author", new Employee("foo", 31, 1300) );

        // when:
        JqlQuery query = QueryBuilder.byInstanceId("foo", Employee.class).build();
        List<Change> changes = javers.findChanges(query);

        // then:
        Assert.assertEquals(changes.size() , 2);
        Assert.assertEquals(javers.findSnapshots(query).size() , 3);
    }

}


