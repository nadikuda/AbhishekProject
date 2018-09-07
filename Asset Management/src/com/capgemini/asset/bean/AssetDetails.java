/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/*Bean(POJO) Class*/

package com.capgemini.asset.bean;

public class AssetDetails {
			
	   private static String user_id;
		private String user_name;
		 private String user_password;
		private  String user_type;
		private int dept_id;
		private String dept_name;
		private int emp_no;
		private String emp_name;
		private String job;
		private String mgr_no;
		private String hire_date;
		private int asset_id;
		private String asset_name;
		private String asset_desc;
		private int quantity;
		private String status;
		private int allocation_id;
		private String allocation_date;
		private String release_date;
		
		public AssetDetails() {
			super();
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getUser_password() {
			return user_password;
		}
		public void setUser_password(String user_password) {
			this.user_password = user_password;
		}
		public String getUser_type() {
			return user_type;
		}
		public void setUser_type(String user_type) {
			this.user_type = user_type;
		}
		public int getDept_id() {
			return dept_id;
		}
		public void setDept_id(int dept_id) {
			this.dept_id = dept_id;
		}
		public String getDept_name() {
			return dept_name;
		}
		public void setDept_name(String dept_name) {
			this.dept_name = dept_name;
		}
		public int getEmp_no() {
			return emp_no;
		}
		public void setEmp_no(int emp_no) {
			this.emp_no = emp_no;
		}
		public String getEmp_name() {
			return emp_name;
		}
		public void setEmp_name(String emp_name) {
			this.emp_name = emp_name;
		}
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}
		public String getMgr_no() {
			return mgr_no;
		}
		public void setMgr_no(String mgr_no) {
			this.mgr_no = mgr_no;
		}
		public String getHire_date() {
			return hire_date;
		}
		public void setHire_date(String hire_date) {
			this.hire_date = hire_date;
		}
		public int getAsset_id() {
			return asset_id;
		}
		public void setAsset_id(int asset_id) {
			this.asset_id = asset_id;
		}
		public String getAsset_name() {
			return asset_name;
		}
		public void setAsset_name(String asset_name) {
			this.asset_name = asset_name;
		}
		public String getAsset_desc() {
			return asset_desc;
		}
		public void setAsset_desc(String asset_desc) {
			this.asset_desc = asset_desc;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getAllocation_id() {
			return allocation_id;
		}
		public void setAllocation_id(int allocation_id) {
			this.allocation_id = allocation_id;
		}
		public String getAllocation_date() {
			return allocation_date;
		}
		public void setAllocation_date(String allocation_date) {
			this.allocation_date = allocation_date;
		}
		public String getRelease_date() {
			return release_date;
		}
		public void setRelease_date(String release_date) {
			this.release_date = release_date;
		}

}
