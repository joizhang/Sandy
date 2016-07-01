package com.joizhang.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class IdCardUtilsTest {

	@Test
	public void testConvert() {
		assertEquals("360301199310260015", new IdCardUtils().convert("360301931026001"));
	}

	@Test
	public void testGetCsrq() {
		assertEquals("19931026", new IdCardUtils().getCsrq("360301199310260015"));
	}

	@Test
	public void testGetXb() {
		assertEquals("男", new IdCardUtils().getXb("360301199310260015"));
	}

	@Test
	public void testGetMm() {
		assertEquals("260015", new IdCardUtils().getMm("360301199310260015"));
	}

	@Test
	public void testSfzhIsNotValid() {
		assertEquals(false, new IdCardUtils().sfzhIsNotValid("360301199310260015"));
	}

	@Test
	public void testIsDate() {
		new IdCardUtils();
		assertEquals(true, IdCardUtils.isDate("19931026"));
	}

}
