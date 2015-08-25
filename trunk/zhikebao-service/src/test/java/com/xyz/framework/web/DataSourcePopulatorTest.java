package com.xyz.framework.web;

import javax.jdo.PersistenceManagerFactory;

import org.junit.Test;

import com.xyz.LocalDatastoreTestCase;
import com.xyz.framework.data.init.IDataInit;

public class DataSourcePopulatorTest extends LocalDatastoreTestCase {

	@Test
	public void testDataInit() {
		IDataInit dsp = (IDataInit)getAc().getBean("dataInit");
		try {
			dsp.dataInit();
			//tx.commit();
			//pmf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
