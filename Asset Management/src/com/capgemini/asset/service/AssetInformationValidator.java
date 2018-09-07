/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

package com.capgemini.asset.service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.asset.exception.AssetException;

public class AssetInformationValidator {                     
	public boolean validateUserId(String userid)                              // to validate user Id
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9A-Za-z\\s]{6}");            // user id can be of 6 digits containing number and alphabets
		m = p.matcher(userid);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("User ID should be of 6 characters");
		}
		return m.matches();
	}
	
	public boolean validateAssetId(String assetid)                       // to validate asset Id
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9]{3,4}");                   //can be of 3 to 4 digits
		m = p.matcher(assetid);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Asset ID should be of 3-4 digit number");
		}
		return m.matches();
	}
	public boolean validateAllocationId(String allocationid)                // to validate allocation Id
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9]{3,4}");
		m = p.matcher(allocationid);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Allocation ID should be of 3-4 digit number");
		}
		return m.matches();
	}
	public boolean validateEmpNo(String empno)                     // to validate employee no
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9]{5}");
		m = p.matcher(empno);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Emp number should be of 5 digit number");
		}
		return m.matches();
	}
	public boolean validatePassword(String password)                      // to validate password
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9A-Za-z\\s]{4,6}");
		m = p.matcher(password);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Password Pattern does not match. Password shoud be 4 to 6 characters");
		}
		return m.matches();
	}
	public boolean validateQuantity(String quantity)                      // to validate password
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9]{1,5}");
		m = p.matcher(quantity);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Quantity Pattern does not match. Quantity can be in numbers only and max can be (99,999)");
		}
		return m.matches();
	}
	public boolean validateEmpName(String emp_name)                              // to validate user Id
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9A-Za-z\\s]{4,25}");            // user id can be of 6 digits containing number and alphabets
		m = p.matcher(emp_name);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Employee Name should be 4 to 25 characters");
		}
		return m.matches();
	}
	public boolean validateEmpJob(String job)                              // to validate user Id
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9A-Za-z\\s]{4,35}");            // user id can be of 6 digits containing number and alphabets
		m = p.matcher(job);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Job description is wrong. It should be 4 to 35.Please enter correct domain");
		}
		return m.matches();
	}
	public boolean validateDeptId(String dept_id)                              // to validate user Id
			throws AssetException {
		Matcher m = null;
		Pattern p = Pattern.compile("[0-9]{4}");            // user id can be of 6 digits containing number and alphabets
		m = p.matcher(dept_id);
		if (m.matches() == false) {
			System.err.flush();
			System.err.println("Department ID should be of 4 Digits");
		}
		return m.matches();
	}

}

