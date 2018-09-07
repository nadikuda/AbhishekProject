/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/********Application to fix a meeting between database and java program***********/

package com.capgemini.asset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.capgemini.asset.bean.AssetDetails;
import com.capgemini.asset.exception.AssetException;

public class UserAssetInformationDatabase implements IUserAssetInformationDatabase {
	// method implementation to take inventory from database
    public ArrayList<AssetDetails> showInventory() throws AssetException{
    	ArrayList<AssetDetails> inventory=new ArrayList(); // Arraylist create to take all the inventory details from database
    	String available_options_manager=IqueryMapper.AVAILABLE_OPTIONS_MANAGER; 
    	Connection conn=DbUtilB.getConn();
    	try{
    		PreparedStatement st=conn.prepareStatement(available_options_manager);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				AssetDetails asset = new AssetDetails();
				int assetID = rs.getInt("asset_id"); // storing asset name and asset id from database
				String assetname = rs.getString("asset_name");
                String assetdesc=rs.getString(3);
				asset.setAsset_id(assetID);
				asset.setAsset_name(assetname);
				asset.setAsset_desc(assetdesc);
				inventory.add(asset);                  // added to inventory list
			}
    	}catch (SQLException e1) {
    		throw new AssetException("Unable to provide list");
		}
    	return inventory;
    }
    
    //Method implementation to check Asset Id is valid or not
    public int checkAssetID(AssetDetails asset) throws AssetException{    
		int successful=0;                 // successful takes the return value
		String verify_asset_in_database=IqueryMapper.VERIFY_ASSET_IN_DATABASE;
		Connection conn = DbUtilB.getConn();
		try {
			PreparedStatement pstmt =	conn.prepareStatement(verify_asset_in_database);
				pstmt.setInt(1, asset.getAsset_id());
				successful=pstmt.executeUpdate();		
			} catch (SQLException e1) {
				throw new AssetException("Unable To perform check operation");
			}	
		
		return successful;
	}
    
    //Method implementation to raise a new request
    public int raiseRequest(AssetDetails asset) throws AssetException{    
		int successful=0;
		String generate_new_asset=IqueryMapper.GENERATE_NEW_ASSET;
		Connection conn = DbUtilB.getConn();
		try {
			PreparedStatement pstmt =	conn.prepareStatement(generate_new_asset);
				pstmt.setInt(1, asset.getAsset_id());
				pstmt.setInt(2, asset.getEmp_no());
				successful=pstmt.executeUpdate();
				if(successful==1){
					String give_allocation_id_back=IqueryMapper.GIVE_ALLOCATION_ID_BACK; // getting auto generated allocation id
					PreparedStatement pst =	conn.prepareStatement(give_allocation_id_back);
					ResultSet rs=pst.executeQuery();
					rs.next();
					successful=rs.getInt(1);
				}
			} catch (SQLException e1) {
				throw new AssetException("Unable To Raise request");
			}	
		
		return successful;
	}
    
    //Method implementation to check if employee number exist in database 
	public int checkEmpno(AssetDetails asset) throws AssetException {
		int successful =0;
		int status=0;
		int empno=asset.getEmp_no();
		
		String entered_employee_in_datatbase=IqueryMapper.ENTERED_EMPLOYEE_IN_DATABASE;
		Connection conn = DbUtilB.getConn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(entered_employee_in_datatbase);
				pstmt.setInt(1, asset.getEmp_no());
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()){
					
				status=rs.getInt(1);
				}
				if(status==empno){
					successful=1;
				}
			} catch(SQLException e){
				throw new AssetException("Unable To perform check employee operation");
			}
		
		return successful;
	}
	
	//Method implementation to check status of the raised request from allocation id
	public String checkStatus(AssetDetails asset) throws AssetException{    
		int successful=0;
		String status=null;
		String status_raised_ticket=IqueryMapper.STATUS_RAISED_TICKET;
		Connection conn = DbUtilB.getConn();
		try {
			PreparedStatement pstmt =	conn.prepareStatement(status_raised_ticket);
				pstmt.setInt(1, asset.getAllocation_id());
				ResultSet rs=pstmt.executeQuery();
				rs.next();
			    status=rs.getString(1);
			} catch (SQLException e1) {
				throw new AssetException("Unable To check status");
			}	
		
		return status;
	}
	
	// Method implementation to check if the employee has a pending request
	public int checkAlreadyRequest(AssetDetails asset) throws AssetException {
		String status = null;
		int verification =0;
		String verify_pending_request=IqueryMapper.VERIFY_PENDING_REQUEST;
		Connection conn=DbUtilB.getConn();
		try{
			PreparedStatement pstmt = conn.prepareStatement(verify_pending_request);
			pstmt.setInt(1,asset.getEmp_no());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			status=rs.getString(1);
			}
			if("PENDING".equals(status)){
				verification=1;
			}
		} catch(SQLException e){
			throw new AssetException("Check for a already pending request is not performed");
		}
		return verification;
	}
	
	// Method for verifying the manager who is raising the request has that employee under him
	public int checkMgrForEmployee(AssetDetails asset) throws AssetException {
		String status = null;
		int verification =0;
		String mgr_check_for_employee=IqueryMapper.MGR_CHECK_FOR_EMPLOYEE;
		Connection conn=DbUtilB.getConn();
		try{
			PreparedStatement pstmt = conn.prepareStatement(mgr_check_for_employee);
			pstmt.setInt(1,asset.getEmp_no());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			status=rs.getString(1);        // storing corresponding status in the variable
			}
			if(status.equals(asset.getUser_id())){
				verification=1;
			}
		} catch(SQLException e){
			throw new AssetException("Unable To perform check operation for whether employee is under same manager or not");
		}
		return verification;
	}

}

