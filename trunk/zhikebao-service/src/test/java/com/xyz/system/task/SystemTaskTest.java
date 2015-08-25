package com.xyz.system.task;

import static org.junit.Assert.*;

import org.junit.Test;

import com.xyz.LocalDatastoreTestCase;

public class SystemTaskTest extends LocalDatastoreTestCase {
	
	ISysTask sysTask;

	@Test
	public void testTradeSysn() {
		sysTask = (ISysTask)getAc().getBean("systemTask");
		sysTask.tradeSysn();
	}

}
