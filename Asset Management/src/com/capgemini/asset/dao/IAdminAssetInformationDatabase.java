/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/**************Interface of Admin roles in database layer***********************/

package com.capgemini.asset.dao;

import java.util.ArrayList;

import com.capgemini.asset.exception.*;
import com.capgemini.asset.bean.*;

public interface IAdminAssetInformationDatabase {
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
}

