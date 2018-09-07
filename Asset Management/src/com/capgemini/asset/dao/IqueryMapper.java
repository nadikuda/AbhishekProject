/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/************************QUERIES TO BE USED TO DEAL WITH DATABASE**************************/

package com.capgemini.asset.dao;

public interface IqueryMapper {
	    
	    // ADMIN QUERIES
	
		public static final String SELECT_USER_TYPE="select upper(user_type) from user_master where user_id=?";
		//To check user_type is Admin or manager 
		public static final String SELECT_USER_PASSWORD="select user_password from user_Master where user_id=?";
		//To validate password 
		public static final String INSERT_USER_MASTER="Insert into user_master values(?,?,?,?)";
		//To create new manager id and password
		public static final String INSERT_ASSET="Insert into asset values(auto_assetID.nextval,?,?,?,?)";
		//To enter new asset into database
		public static final String NEW_ASSET="update asset set asset_name=?,asset_desciption=?,quantity=?,status=? where asset_id=? ";
		//To modify the existing asset by asset_id
		public static final String SHOW_PENDING_REQUEST="select allocation_id,asset_id,emp_no from asset_allocation where upper(status)='PENDING' order by allocation_id desc";
		//To check pending requests or tickets......need to be approve
		public static final String SHOW_INVENTORY="select asset_id,asset_name,quantity,status from asset";
		//To check inventory
		public static final String CHECK_STATUS="select upper(status) from asset where asset_id=(select asset_id from asset_allocation where allocation_id=?)";
		//To check status is available or not available
		public static final String APPROVE_PENDING_REQUEST="update asset_allocation set release_date=sysdate+2, status='approved' where allocation_id=? ";
		//To approve a pending request
		public static final String QUANTITY_DECREASE="update asset set quantity=quantity-1 where asset_id=(select asset_id from asset_allocation where allocation_id=?)";
		//To update inventory after approval of a pending request
		public static final String UPDATE_QUANTITY_TO_UNAVAILABLE="update asset set status='UNAVAILABLE' where quantity=0";
		//To change status to not available when quantity becomes 0
		public static final String REJECT_PENDING="update asset_allocation set release_date=sysdate, status='reject' where allocation_id=? ";
		//To reject a pending request
		public static final String UPDATE_ASSETNAME="update asset set asset_name=? where asset_id=? ";
		//To update asset_name
		public static final String UPDATE_ASSETDESC="update asset set asset_desciption=? where asset_id=? ";
		//To update asset_description
		public static final String UPDATE_QUANITY="update asset set quantity=? where asset_id=? ";
		//To update quantity
		public static final String UPDATE_STATUS="update asset set status=? where asset_id=? ";
		//To update quantity
		public static final String DISPLAY_ALLOCATE_AND_UNALLOCATE="select asset_id,allocation_id,emp_no,status from asset_allocation where upper(status)='APPROVED' or upper(status)='REJECT' order by status";
		// To display all allocated and unallocated details
		public static final String AUTO_ASSETID_QUERY="select auto_assetID.currval from dual";
		// To select sequence generated asset id
		public static final String ADD_EMPLOYEE="insert into employee values(?,?,?,?,sysdate,?)";
		// To add employee into database
		public static final String CHECK_DEPTID="select dept_id from department1 where dept_id=?";
		// To check department id exist in database or not
		public static final String CHECK_MANAGERID="select user_id from user_master where user_id=?";
		// To check manager id exist in database or not
		
		
		
		// MANAGER QUERIES
		
		
		public static final String AVAILABLE_OPTIONS_MANAGER="select asset_id,asset_name,asset_desciption from asset";
		//To show available options to manager
		public static final String GENERATE_NEW_ASSET="insert into asset_allocation values(auto_allocationID.nextval,?,?,sysdate,null,'pending')";
		//To Generate a new request
		public static final String VERIFY_ASSET_IN_DATABASE="select asset_id from asset where asset_id=?";
		//To verify entered asset id by manager exist in database or not
		public static final String ENTERED_EMPLOYEE_IN_DATABASE="select emp_no from employee where emp_no=?";
		//To verify entered employee number by manager exist in database or not
		public static final String GIVE_ALLOCATION_ID_BACK="select auto_allocationID.currval from dual";
		//To give allocation ID back to manager
		public static final String STATUS_RAISED_TICKET="select status from asset_allocation where allocation_id=?";
		//To show status of raised ticket to manager when asked for
		public static final String VERIFY_PENDING_REQUEST="select upper(status) from asset_allocation where emp_no=?";
		//To verify if there is no pending request for the same employee
		public static final String MGR_CHECK_FOR_EMPLOYEE="select mgr from employee where emp_no=?";
		//To verify if employee comes under the same manager who is raising the request
}
