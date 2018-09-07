/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/************Method implementation of Admin Role**********************/

package com.capgemini.asset.service;
import java.util.ArrayList;

import com.capgemini.asset.bean.*;
import com.capgemini.asset.dao.*;
import com.capgemini.asset.exception.AssetException;

public class AdminAssetInformationHelper implements IAdminAssetInformationHelper {
	
		AdminAssetInformationDatabase dao =  new AdminAssetInformationDatabase();
		
		public int check(AssetDetails asset) throws AssetException {
	       return dao.check(asset);
		}
		public int newManagerAdd(AssetDetails asset) throws AssetException{
			 return dao.newManagerAdd(asset);
		}
		public int addAsset(AssetDetails asset) throws AssetException{
			return dao.addAsset(asset);
		}
		public int modifyAsset(AssetDetails asset) throws AssetException{
			return dao.modifyAsset(asset);
		}
		public ArrayList<AssetDetails> checkRequest() throws AssetException{
			 return dao.checkRequest();
		 }
		 public ArrayList<AssetDetails> checkInventory() throws AssetException{
			 return dao.checkInventory();
		 }
		 public int approveRequest(AssetDetails asset) throws AssetException{
			 return dao.approveRequest(asset);
		 }
		 public int updateAssetDesc(AssetDetails asset) throws AssetException{
			 return dao.updateAssetDesc(asset);
		 }
		 public int updateAssetQuantity(AssetDetails asset) throws AssetException{
			 return dao.updateAssetQuantity(asset);
		 }
		 public int updateAssetStatus(AssetDetails asset) throws AssetException{
			 return dao.updateAssetStatus(asset);
		 }
		 public int updateAssetName(AssetDetails asset) throws AssetException{
			 return dao.updateAssetName(asset);
		 }
		 public int rejectRequest(AssetDetails asset) throws AssetException{
			 return dao.rejectRequest(asset);
		 }
		public ArrayList<AssetDetails> displayAll() throws AssetException {
			return dao.displayAll();
		
		}
		public int insert_into_employee(AssetDetails asset) throws AssetException {
			return dao.insert_into_employee(asset);
		}
		public int checkDeptId(AssetDetails asset) throws AssetException{
			return dao.checkDeptId(asset);
		}
		public int checkManagerId(AssetDetails asset) throws AssetException{
			// TODO Auto-generated method stub
			return dao.checkManagerId(asset);
		}
}

