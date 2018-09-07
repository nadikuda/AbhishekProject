/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/**************Interface of Manager roles in database layer***********************/

package com.capgemini.asset.dao;

import java.util.ArrayList;

import com.capgemini.asset.bean.AssetDetails;
import com.capgemini.asset.exception.AssetException;

public interface IUserAssetInformationDatabase {
	
	public abstract ArrayList<AssetDetails> showInventory() throws AssetException;
	public abstract int raiseRequest(AssetDetails asset) throws AssetException;
	public abstract int checkAssetID(AssetDetails asset) throws AssetException;
	public abstract int checkEmpno(AssetDetails asset) throws AssetException;
	public abstract String checkStatus(AssetDetails asset) throws AssetException;
	public abstract int checkAlreadyRequest(AssetDetails asset) throws AssetException;
	public abstract int checkMgrForEmployee(AssetDetails asset) throws AssetException;
}
