/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/********Application to fix a meeting between database and java program***********/

package com.capgemini.asset.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.*;

import com.capgemini.asset.exception.*;
import com.capgemini.asset.ui.AdminAssetPortal;
import com.capgemini.asset.ui.AssetPortal;
import com.capgemini.asset.bean.*;

public class AdminAssetInformationDatabase implements IAdminAssetInformationDatabase {
		// method to check Admin and manager ID & password
	AdminAssetPortal adminPortal = new AdminAssetPortal();
	AssetPortal assetPortal = new AssetPortal();
	     public int check(AssetDetails asset) throws AssetException{      
			Connection conn = DbUtilB.getConn();
			int role_admin = 3;
			int role_manager =0,successful=0;
			int password_success=0;
			try {                                                 // It will check for role of user
				String select_user_type = IqueryMapper.SELECT_USER_TYPE;
				PreparedStatement st=conn.prepareStatement(select_user_type);
				st.setString(1,asset.getUser_id());               // setting user id from user to sql query
				ResultSet rs=st.executeQuery();
				while(rs.next())
				{	
					String str=rs.getString(1);        // getting and storing role against user id from database
					if(str.equals("ADMIN")){
						role_admin= 1;
					}
					else{
						role_manager=1;
					}
					String select_user_password = IqueryMapper.SELECT_USER_PASSWORD;  // It will check for password of the user
					PreparedStatement pstmt =	conn.prepareStatement(select_user_password);
					pstmt.setString(1,asset.getUser_id());
					ResultSet rst =	pstmt.executeQuery();
					while(rst.next()){
						String password= rst.getString(1);
						
						if(password.equals(asset.getUser_password())){  // checking for password mismatching
							password_success=1;
						}
					}
					if(password_success==1&&role_admin==1){
						successful=1;
					}
					if(password_success==1&&role_manager==1)
						successful=2;
				}				
				} catch (SQLException e1) {                   // user defined exception
					throw new AssetException("Unable To Login");
				}
				return successful;
		}
	     
	   //method implementation to add new manager in database
		public int newManagerAdd(AssetDetails asset) throws AssetException {    
			int successful = 0;
			String insert_user_master = IqueryMapper.INSERT_USER_MASTER;
			Connection conn = DbUtilB.getConn();
			try {
				PreparedStatement pstmt =	conn.prepareStatement(insert_user_master);
					pstmt.setString(1, asset.getUser_id());
					pstmt.setString(2, asset.getUser_name());
					pstmt.setString(3, asset.getUser_password());
					pstmt.setString(4, asset.getUser_type());
					successful = pstmt.executeUpdate();
				} catch (SQLException e1) {
					throw new AssetException("Manager Add Not Done");
				  }
			return successful;
		}
		
		// method implementation to add new asset into database
		public int addAsset(AssetDetails asset) throws AssetException{    
			int successful=0;
			String insert_asset=IqueryMapper.INSERT_ASSET;
			Connection conn = DbUtilB.getConn();
			try {
				PreparedStatement pstmt =	conn.prepareStatement(insert_asset);
					pstmt.setString(1, asset.getAsset_name());
					pstmt.setString(2, asset.getAsset_desc());
					pstmt.setInt(3, asset.getQuantity());
					pstmt.setString(4, asset.getStatus());
					successful=pstmt.executeUpdate();	
					if(successful==1){
						if(successful==1){
							String auto_assetid_query=IqueryMapper.AUTO_ASSETID_QUERY; // getting auto generated allocation id
							PreparedStatement pst =	conn.prepareStatement(auto_assetid_query);
							ResultSet rs=pst.executeQuery();
							rs.next();
							successful=rs.getInt(1);
						}
					}
				} catch (SQLException e1) {
					throw new AssetException("Asset not added");
				}	
			
			return successful;
		}
		
		// method implementation to modify the asset in database
        public int modifyAsset(AssetDetails asset) throws AssetException{     
        	int successful=0;
        	String new_asset = IqueryMapper.NEW_ASSET;
        	Connection conn = DbUtilB.getConn();
        	try {
        		PreparedStatement st=conn.prepareStatement(new_asset);
        			st.setInt(5,asset.getAsset_id());
        			st.setString(1, asset.getAsset_name());
        			st.setString(2, asset.getAsset_desc());
        			st.setInt(3, asset.getQuantity());
        			st.setString(4, asset.getStatus());
        			successful=st.executeUpdate();	
        	}
        	catch (SQLException e1) {
        		throw new AssetException("Unable to Modify");
			}	
        	return successful;	
		}
        
       // method to take pending request from database
        public ArrayList<AssetDetails> checkRequest() throws AssetException{     
        	int n=0;
        	ArrayList<AssetDetails> arrayset=new ArrayList();
        	String show_pending_request= IqueryMapper.SHOW_PENDING_REQUEST;
        	Connection conn=DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(show_pending_request);
    			ResultSet rs = st.executeQuery();
    			while (rs.next()) {
    				AssetDetails asset = new AssetDetails();
    				int allocationID = rs.getInt("ALLOCATION_ID");
    				int assetID = rs.getInt("ASSET_ID");
    				int employeeID = rs.getInt("EMP_NO");
    				
    				asset.setAllocation_id(allocationID);
    				asset.setAsset_id(assetID);
    				asset.setEmp_no(employeeID);
    				arrayset.add(asset);
    			}
        	}
        	catch (SQLException e1) {
        		throw new AssetException("Unable to check");
			}	
        	return arrayset;
        }
        
       // method implementation to take inventory from database
        public ArrayList<AssetDetails> checkInventory() throws AssetException{
        	ArrayList<AssetDetails> inventory=new ArrayList();
        	String show_inevntory=IqueryMapper.SHOW_INVENTORY;
        	Connection conn=DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(show_inevntory);
    			ResultSet rs = st.executeQuery();
    			while (rs.next()) {
    				AssetDetails asset = new AssetDetails();
    				int assetID = rs.getInt("asset_id");
    				String assetname = rs.getString("asset_name");
    				int quantity= rs.getInt("quantity");
    				
    				asset.setAsset_id(assetID);
    				asset.setAsset_name(assetname);
    				asset.setQuantity(quantity);
    				inventory.add(asset);
    			}
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To Provide Inventory List");
			}
        	return inventory;
        }
        	
        	
      //method implementation to approve a pending request in database
        public int approveRequest(AssetDetails asset) throws AssetException{ 
        	int successful=0;
        	String check_status=IqueryMapper.CHECK_STATUS;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement stpt=conn.prepareStatement(check_status);
        		stpt.setInt(1,asset.getAllocation_id());
        		ResultSet rs=stpt.executeQuery();
        		while(rs.next()){
        			String status = rs.getString(1);	
        			
        			if(status.equals("AVAILABLE"))
        			{
        		
        		String approve_pending_request = IqueryMapper.APPROVE_PENDING_REQUEST;
        	
        		PreparedStatement st=conn.prepareStatement(approve_pending_request);
    				st.setInt(1,asset.getAllocation_id());
    				st.executeUpdate();
    				String quantity_decrease = IqueryMapper.QUANTITY_DECREASE;
    					PreparedStatement stpt1=conn.prepareStatement(quantity_decrease);
    						stpt1.setInt(1,asset.getAllocation_id());
    						successful=stpt1.executeUpdate();
    						if(successful==1){
    							String update_quantity_to_unavailable = IqueryMapper.UPDATE_QUANTITY_TO_UNAVAILABLE;
    	    					PreparedStatement stmt=conn.prepareStatement(update_quantity_to_unavailable);
    	    					stmt.executeUpdate();
    						}}else{
    							successful=2;}
    						}
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To Approve the Request");
			}
        	
        	return successful;     	
        }
        
        //method to update asset name in asset table in database
        public int updateAssetName(AssetDetails asset) throws AssetException{ 
        	int successful=0;
        	String update_assetname = IqueryMapper.UPDATE_ASSETNAME;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(update_assetname);
    				st.setInt(2,asset.getAsset_id());
    				st.setString(1,asset.getAsset_name());
    				successful=st.executeUpdate();	
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To Update Asset Name");
			}
        	return successful;
        }
        
      //method to update asset description in asset table in database
        public int updateAssetDesc(AssetDetails asset) throws AssetException{ 
        	int successful=0;
        	String update_assetdesc = IqueryMapper.UPDATE_ASSETDESC;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(update_assetdesc);
    				st.setInt(2,asset.getAsset_id());
    				st.setString(1,asset.getAsset_desc());
    				successful=st.executeUpdate();	
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To Update Asset Description");
			}
        	return successful;
        }
        
      //method to update asset quantity in asset table in database
        public int updateAssetQuantity(AssetDetails asset) throws AssetException{ 
        	int successful=0;
        	String update_quantity = IqueryMapper.UPDATE_QUANITY;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(update_quantity);
    				st.setInt(2,asset.getAsset_id());
    				st.setInt(1,asset.getQuantity());
    				successful=st.executeUpdate();	
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To Update Asset Quantity");
			}
        	return successful;
        }
        
      //method to update asset status in asset table in database
        public int updateAssetStatus(AssetDetails asset) throws AssetException{ 
        	int successful=0;
        	String update_status = IqueryMapper.UPDATE_STATUS;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(update_status);
    				st.setInt(2,asset.getAsset_id());
    				st.setString(1,asset.getStatus());
    				successful=st.executeUpdate();	
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To Update Asset Status");
			}
        	return successful;
        }
        
      //method implementation to reject a pending request in database
        public int rejectRequest(AssetDetails asset) throws AssetException{ 
        	int successful=0;
        	String reject_pending = IqueryMapper.REJECT_PENDING;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(reject_pending);
    				st.setInt(1,asset.getAllocation_id());
    				successful=st.executeUpdate();	
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To Reject Request");
			}
        	return successful;     	
        }

	// to display allocated and unallocated request
	public ArrayList<AssetDetails> displayAll() throws AssetException {
		ArrayList<AssetDetails> display=new ArrayList();
    	String update_allocate_and_unallocate=IqueryMapper.DISPLAY_ALLOCATE_AND_UNALLOCATE;
    	Connection conn=DbUtilB.getConn();
    	try{
    		PreparedStatement st=conn.prepareStatement(update_allocate_and_unallocate);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				AssetDetails asset = new AssetDetails();
				int assetID = rs.getInt(2);
				int allocation_id=rs.getInt(1);
				int emp_no=rs.getInt(3);
				String status=rs.getString(4);
				
				asset.setAllocation_id(allocation_id);
				asset.setAsset_id(assetID);
				asset.setEmp_no(emp_no);
				asset.setStatus(status);
				display.add(asset);
			}
    	}catch (SQLException e1) {
    		throw new AssetException("Unable To Provide Inventory List");
		}
    	return display;
		
	}
   // to insert into employee table
	public int insert_into_employee(AssetDetails asset) throws AssetException {
		
		int successful=0;
    	String add_employee = IqueryMapper.ADD_EMPLOYEE;
    	Connection conn = DbUtilB.getConn();
    	try{
    		PreparedStatement st=conn.prepareStatement(add_employee);
				st.setInt(1,asset.getEmp_no());
				
				st.setString(2,asset.getEmp_name());
				
				st.setString(3,asset.getJob());
				
				st.setString(4, asset.getMgr_no());
				
				st.setInt(5, asset.getDept_id());
				
				successful=st.executeUpdate();	
    	}catch (SQLException e1) {
    		throw new AssetException("Unable To Add Employee");
		}
    	return successful; 
    	}
		
		// To check department Id is in table or not
    	public int checkDeptId(AssetDetails asset) throws AssetException {
    		
    		int successful=0;
        	String check_deptid = IqueryMapper.CHECK_DEPTID;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(check_deptid);
    			st.setInt(1,asset.getDept_id());
                ResultSet rst=st.executeQuery();   
    				while(rst.next()){
						int deptid= rst.getInt(1);
						
						if(deptid==asset.getDept_id()){  // checking for department id mismatching
							successful=1;
						}
					}	
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To check department id");
    		}
        	return successful; 
        
	}

		public int checkManagerId(AssetDetails asset) throws AssetException {
			int successful=0;
			String check_managerid = IqueryMapper.CHECK_MANAGERID;
        	Connection conn = DbUtilB.getConn();
        	try{
        		PreparedStatement st=conn.prepareStatement(check_managerid);
    			st.setString(1,asset.getMgr_no());
                ResultSet rst=st.executeQuery();   
    				while(rst.next()){
						String manager_id= rst.getString(1);
						
						if(manager_id.equals(asset.getMgr_no())){  // checking for department id mismatching
							successful=1;
						}
					}	
        	}catch (SQLException e1) {
        		throw new AssetException("Unable To check manager id");
    		}
			return successful;
		}

}

