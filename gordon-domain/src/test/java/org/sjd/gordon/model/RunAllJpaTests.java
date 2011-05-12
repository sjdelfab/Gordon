package org.sjd.gordon.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DisplayGroupTest.class,
        TabularDatasetTest.class,
        StockTest.class,
        UnitaryPropertyTest.class,
        GicsTest.class,
        UserTest.class
        })
public class RunAllJpaTests {

}
