/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/


/************Method implementation of Manager Role**********************/
package com.capgemini.asset.service;

import java.util.ArrayList;

import com.capgemini.asset.bean.AssetDetails;
import com.capgemini.asset.dao.UserAssetInformationDatabase;
import com.capgemini.asset.exception.AssetException;

public class UserAssetInformationHelper implements IUserAssetInformationHelper {
	UserAssetInformationDatabase dao=new UserAssetInformationDatabase();
	 public ArrayList<AssetDetails> showInventory() throws AssetException{
		 return dao.showInventory();
	 }
	 public int raiseRequest(AssetDetails asset) throws AssetException{
		 return dao.raiseRequest(asset);
	 }
	 public int checkAssetID(AssetDetails asset) throws AssetException{
		 return dao.checkAssetID(asset);
	 }
	@Override
	public int checkEmpno(AssetDetails asset) throws AssetException {
		return dao.checkEmpno(asset);
	}
	@Override
	public String checkStatus(AssetDetails asset) throws AssetException {
		return dao.checkStatus(asset);
	}
	public int checkAlreadyRequest(AssetDetails asset) throws AssetException {
		return dao.checkAlreadyRequest(asset);
	}
	public int checkMgrForEmployee(AssetDetails asset) throws AssetException {
		return dao.checkMgrForEmployee(asset);
	}


}

