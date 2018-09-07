/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/******Interface for Admin class containing Admin methods*******************/

package com.capgemini.asset.service;

import java.util.ArrayList;

import com.capgemini.asset.bean.AssetDetails;
import com.capgemini.asset.exception.AssetException;

public interface IAdminAssetInformationHelper {
	public abstract int check(AssetDetails asset) throws AssetException;
	public abstract int newManagerAdd(AssetDetails asset) throws AssetException;
	public abstract int addAsset(AssetDetails asset) throws AssetException;
	public abstract int modifyAsset(AssetDetails asset) throws AssetException;
	public abstract ArrayList<AssetDetails> checkRequest() throws AssetException;
	public abstract ArrayList<AssetDetails> checkInventory() throws AssetException;
	public abstract int approveRequest(AssetDetails asset) throws AssetException;
	public abstract int updateAssetName(AssetDetails asset) throws AssetException;
	public abstract int updateAssetDesc(AssetDetails asset) throws AssetException;
	public abstract int updateAssetQuantity(AssetDetails asset) throws AssetException;
	public abstract int updateAssetStatus(AssetDetails asset) throws AssetException;
	public abstract int rejectRequest(AssetDetails asset) throws AssetException;
	public abstract ArrayList<AssetDetails> displayAll() throws AssetException;
	public abstract int insert_into_employee(AssetDetails asset) throws AssetException;
	public abstract int checkDeptId(AssetDetails asset) throws AssetException;
	public abstract int checkManagerId(AssetDetails asset) throws AssetException;
	
	

}
