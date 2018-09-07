package com.capgemini.asset.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.asset.bean.AssetDetails;
import com.capgemini.asset.dao.AdminAssetInformationDatabase;
import com.capgemini.asset.exception.AssetException;

public class AdminAssetInformationDatabaseTest {
    AdminAssetInformationDatabase admin=new AdminAssetInformationDatabase();
    AssetDetails asset= new AssetDetails();
    
	@Test
	public void testCheck() throws AssetException {
		asset.setUser_id("155164");
		asset.setUser_password("155164");
		assertEquals(1, admin.check(asset));
	}

	@Test
	public void testNewManagerAdd() throws AssetException {
		asset.setUser_id("155163");
		asset.setUser_name("abkaushi");
		asset.setUser_password("155164");
		asset.setUser_type("manager");
		assertEquals(1, admin.newManagerAdd(asset));
	}

	@Test
	public void testAddAsset() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyAsset() {
		fail("Not yet implemented");
	}

	@Test
	public void testApproveRequest() throws AssetException {
		asset.setAsset_id(1010);
		asset.setEmp_no(10023);
		assertEquals(1, admin.approveRequest(asset));
	}

	@Test
	public void testUpdateAssetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAssetDesc() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAssetQuantity() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAssetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testRejectRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert_into_employee() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckDeptId() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckManagerId() {
		fail("Not yet implemented");
	}

}
