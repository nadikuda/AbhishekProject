package com.capgemini.asset.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.asset.exception.AssetException;
import com.capgemini.asset.service.AssetInformationValidator;

public class AssetInformationValidatorTest {
	AssetInformationValidator test=new AssetInformationValidator();

	@Test
	public void testValidateUserId() throws AssetException {
		assertEquals(true,test.validateUserId("188888"));
	}

	@Test
	public void testValidateAssetId() throws AssetException {
		assertEquals(true,test.validateAssetId("1126"));
	}

	@Test
	public void testValidateAllocationId() throws AssetException {
		assertEquals(true,test.validateAllocationId("140"));
	}

	@Test
	public void testValidateEmpNo() throws AssetException {
		assertEquals(true,test.validateEmpNo("10023"));
	}

	@Test
	public void testValidatePassword() throws AssetException {
		assertEquals(true,test.validatePassword("155168"));
	}

	@Test
	public void testValidateQuantity() throws AssetException {
		assertEquals(true,test.validateQuantity("25"));
	}

	@Test
	public void testValidateEmpName() throws AssetException {
		assertEquals(true,test.validateEmpName("abkaushi"));
	}

	@Test
	public void testValidateEmpJob() throws AssetException {
		assertEquals(true,test.validateEmpJob("research"));
	}

	@Test
	public void testValidateDeptId() throws AssetException {
		assertEquals(true,test.validateDeptId("1001"));
	}

}
