/*
 * User: eladh
 * Date: 17/01/2018 
 *
 * Copyright (2005) IDI. All rights reserved.
 * This software is a proprietary information of Israeli Direct Insurance.
 * Created by IntelliJ IDEA. 
 */
package com.idi;

import com.idi.dao.Person;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class CommitChanges {

    @Test
    public void checkCommitSnapshotCount() {

        // prepare Javers Engine
        Javers javers = JaversBuilder.javers().build();

        // init test data
        Person robert = new Person();
        robert.setId(1);
        robert.setName("Robert");
        robert.setDescription("IT Manager");

        //  persist initial commit
        javers.commit("user", robert);

        // do some changes and commit changes
        robert.setDescription("Senior Dev");
        javers.commit("user" , robert);

        // when:
        List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.byInstanceId(robert.getId(),
                Person.class).limit(5).build());

        // then:
        Assert.assertEquals(snapshots.size() , 2);
    }
}
