package com.wbarcellosn;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ClienteDaoTest.class, ProdutoDaoTest.class, VendaDaoTest.class, ClienteDao2DBsTest.class, ClienteDao3DBsTest.class })
public class AllTests {

}
