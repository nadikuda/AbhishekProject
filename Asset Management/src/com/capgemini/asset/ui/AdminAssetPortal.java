/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/***************************************ADMIN FUNCTIONS***************************************/
/*******(Main Switch)********* in comments refers to ..........It is not nested inside another switch********/
/*********************(Nested Switch)********* means it is inside another switch****************************/

/***action(variable name)********Action and its successor are to pass inside switch action cases*******/
/********asset(variable name)*******asset and its successor are the object of bean class */

package com.capgemini.asset.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capgemini.asset.bean.AssetDetails;
import com.capgemini.asset.exception.AssetException;
import com.capgemini.asset.service.AdminAssetInformationHelper;
import com.capgemini.asset.service.AssetInformationValidator;

public class AdminAssetPortal {
	public static void adminLogin() throws AssetException, IOException{        //Method to perform functionalities of Manager
		Logger log = Logger.getRootLogger();
		String tovalidate=null;                                                                            // variable used for converting integer to string to use in pattern matcher
		AssetInformationValidator information_validator = new AssetInformationValidator();                 //object of validating class
		Scanner scanner=new Scanner(System.in);
		BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));                        // object of buffer class
		AdminAssetInformationHelper information_helper= new AdminAssetInformationHelper();                 // Object of Admin Service Class
		AssetDetails asset = new AssetDetails();
		int action=0;                                                                                 // Pass in switch to opt between the available functions to Admin
		do{
		try{
			System.out.println("\nChoose your action");
			System.out.println("******************************************");
			System.out.println("1:Include new asset/modify asset details from inventory\n2:Approve or reject the request\n3:Issue of new Manager/user id & password\n4:Add new Employee\n5:Diplay Asset Allocation Details\n6:Log Out\n7:EXIT");
			System.out.println("******************************************");                   
			action=scanner.nextInt();                                                               // Pass in do while loop to exit loop
			switch(action){
				case 1:                                                                                  //Main switch to Include new asset/modify asset details from inventory                                                                                 
	                    include_modify();
	                    break;
		
				case 2:                                                                                          // Main Switch to approve or reject a raised ticket
						System.out.println("All unapproved request are:"); 
						System.out.println(" ");// Showing all not approved request
						ArrayList<AssetDetails> arrayset=new ArrayList<AssetDetails>();
						arrayset =information_helper.checkRequest();
						for (AssetDetails programs : arrayset){
							System.out.println("Allocation Id: ---"+programs.getAllocation_id()
									+" Asset ID: ---"+programs.getAsset_id()
									+" Employee No. ---"+ programs.getEmp_no());
						}
						int action2=0;                                                                   // variable to pass in nested switch to ask according to menu
						int approve_reject=0;                                           // variable to check return of approve or reject method and decide request succeeded
						do{
							System.out.println("Choose your action");
							System.out.println("****************************");
							System.out.println("1) You want to check inventory");
							System.out.println("2) Approve pending request");
							System.out.println("3) Reject pending request");
							System.out.println("4) Main Menu");
							System.out.println("5) Log Out");
							System.out.println("6) EXIT");
							System.out.println("****************************");
							try{
								action2=scanner.nextInt();
								switch(action2){                                                                // Nested Switch to distinct the functions of main switch case 2 
									case 1:
											System.out.println(" ");                                            // Check inventory
										   ArrayList<AssetDetails> inventory = new ArrayList<AssetDetails>();
										   inventory=information_helper.checkInventory();
										   for(AssetDetails program1:inventory){
											   System.out.println("Asset ID: ---"+program1.getAsset_id()+
													  " Asset Name: ---"+program1.getAsset_name()+
													  " Quantity: ---"+program1.getQuantity());
										   }
										   break;
									case 2:                                                                        // approve a request
										tovalidate=null;
										int approve_allocation_id=0;
										do{                                       // will validate according to pattern in validate class
										    System.out.println(" ");
											System.out.println("Enter the allocation ID to be approved");
										   
										   tovalidate=scanner.next();
										}while(!information_validator.validateAllocationId(tovalidate));     // in loop till follows the pattern
											approve_allocation_id=Integer.parseInt(tovalidate);
											AssetDetails asset1  = new AssetDetails();
										   asset1.setAllocation_id(approve_allocation_id);
										   approve_reject=information_helper.approveRequest(asset1);
										   if(approve_reject==1){
											   System.out.println("Request Approved Successfully");
											   log.info("Ticket approved");
										   }
										   else if(approve_reject==2){
											   System.out.println("Quantity not available.You can't approve the request");
										   }
										   else{
											   System.out.println("Invalid allocation ID");
											   log.info("Invalid Entry");
										   }
										   break;
									case 3:                                                                      // reject a request
										tovalidate=null;
										int reject_allocation_id=0;
										do{
										   System.out.println(" ");
										   System.out.println("Enter the allocation ID to be rejected");
										   tovalidate=scanner.next();
										   }while(!information_validator.validateAllocationId(tovalidate));
										   reject_allocation_id=Integer.parseInt(tovalidate);
										   AssetDetails asset2  = new AssetDetails();
										   asset2.setAllocation_id(reject_allocation_id);
										   approve_reject=information_helper.rejectRequest(asset2);
										   if(approve_reject==1){
											   System.out.println("Request Rejected");
											   log.info("Ticket Rejected");
										   }
										   else{
											   System.out.println("Invalid allocation ID");
											   log.info("Invalid Entry");
										   }
										   break;
									case 4: adminLogin();
									        break;
									case 5: System.out.println("LOG OUT");
											AssetPortal.main(null);
											break;
									case 6: System.out.println("Session Terminated");                                // nested switch to system exit
										    System.exit(0);
										    break;
									default:System.out.println("You have entered a invalid choice");
											break;
								}
							}
							catch(InputMismatchException e){
								System.out.println(" ");
								System.err.println("Invalid choice!! please enter 1/2/3");
								System.out.println(" ");
								scanner.next();
								log.error("inavlid entry...should be a number");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}while(action2!=6);
						break;
				case 3:                                                                                          // main switch to give access to a new manager
					   String newid=null;
					   String newpwd=null;
					   do{
					   System.out.println("Enter the new manager ID:");
					   newid= scanner.next();
					   }while(!information_validator.validateUserId(newid));
					   System.out.println("Enter the new user name:");
					   String newname= buffer.readLine();
					   do{
					   System.out.println("Enter the new manager Password:     (Password should be 4 to 6 characters long)");
					   newpwd= buffer.readLine();
					   }while(!information_validator.validatePassword(newpwd));
					   asset.setUser_type("manager");
					   asset.setUser_name(newname);
					   asset.setUser_password(newpwd);
					   asset.setUser_id(newid);
					   int access=information_helper.newManagerAdd(asset);
					   if(access==1){
						   System.out.println("Entry Succeded");
					   log.info("New Manager ID created");
					   }
					   else
						   System.out.println("Entry Declined");
					   break;
				case 4: int emp_no=0,dept_id=0;                                                               // MAIN SWITCH to add a new employee
						String emp_name=null,job=null,mgr=null;
						System.out.println("Enter the details of the new Employee");
						do{
						System.out.println("Enter the employee no");                                            // Input employee number
						tovalidate=buffer.readLine();
						}while(!information_validator.validateEmpNo(tovalidate));                              // validating employee no
						emp_no=Integer.parseInt(tovalidate);
						asset.setEmp_no(emp_no);
						do{
						System.out.println("Enter the employee name");                                         // Input employee name
						emp_name=buffer.readLine();
						}while(!information_validator.validateEmpName(emp_name));                                // validating employee name
					    asset.setEmp_name(emp_name);
					    do{
					    System.out.println("Enter the job of Employee");                                       // Input job of employee
					    job=buffer.readLine();
					    }while(!information_validator.validateEmpJob(job));                                     // validating job 
					    asset.setJob(job);
					    int successful=0;
					    do{
					    do{
					    System.out.println("Enter manager ID");                                             // Input manager ID
					    mgr=buffer.readLine();
					    }while(!information_validator.validateUserId(mgr));                                   // validating manager number
					    asset.setMgr_no(mgr);
					    successful=information_helper.checkManagerId(asset);
					    if(successful==0){
					    	System.out.println("Invalid Manager Id");
					    }
						}while(successful!=1);
					    
					    successful=0; 
					    do{
					    do{
					    System.out.println("Enter the department ID");                                        // Input department Id
					    tovalidate=buffer.readLine();
					    }while(!information_validator.validateDeptId(tovalidate));                               // validating department ID
					    dept_id=Integer.parseInt(tovalidate);
					    asset.setDept_id(dept_id);
					    successful=information_helper.checkDeptId(asset);                                 // checking if department id exist in database or not
					    if(successful==0){
					    	System.out.println("Invalid department Id.Please enter a valid department Id.");
					    }
					    }while(successful!=1);
					    successful=information_helper.insert_into_employee(asset);
					    if(successful==1){
					    	System.out.println("Employee added successfully");
					    }
					    else{
					    	System.out.println("Employee addition failed");
					    }
					    break;
								  
				case 5:  ArrayList<AssetDetails> display = new ArrayList<AssetDetails>();
						display=information_helper.displayAll();
						for(AssetDetails program1:display){
							   System.out.println("Asset ID: --- "+program1.getAsset_id()+
									  " Allocation ID: --- "+program1.getAllocation_id()+
									  " Employee No: --- "+program1.getEmp_no()+" Status: --- "+program1.getStatus());
						   }
						break;
				case 6: System.out.println("LOG OUT SUCCESSFUL");
						AssetPortal.main(null);
						break;
				case 7: System.out.println("Session Terminated");
						System.exit(0);
						break;
				default:System.out.println(" ");
						System.out.println("you had choosen an invalid option");
						System.out.println(" ");
		        		break;
			}
		}
		catch(InputMismatchException e){
			System.err.println(" ");
			System.err.println("Please enter a valid choice (numbers - 1/2/3/4)");
			System.out.println(" ");
			scanner.next();
			log.error("Entered a non numeric where requirement is of numeric");
			adminLogin();
		}
	}while(action!=7);
	}







 




static void include_modify(){   
	 Logger log = Logger.getRootLogger();
	AssetDetails asset = new AssetDetails();
	int action1=0;  
	String tovalidate="null";
	do{
		AssetInformationValidator information_validator = new AssetInformationValidator();  
		AdminAssetInformationHelper information_helper= new AdminAssetInformationHelper();
		Scanner scanner=new Scanner(System.in);  
		try{
			
			 System.out.println("Choose your action");                                                           //To give choice to Admin to choose between Insert and Modify asset
			 System.out.println("***************************************");
			 System.out.println("1) Insert New Asset");
			 System.out.println("2) Modify Asset");
			 System.out.println("3) Main Menu");
			 System.out.println("4) Log Out");
			 System.out.println("5) EXIT");
			 System.out.println("***************************************");
			 action1 = scanner.nextInt();                                                         
				 switch(action1){
				 case 1:                                                                 //Include new Asset
				 System.out.println("Enter the asset name");
				 String dummy=scanner.nextLine();
				 String asset_name= scanner.nextLine();
				 System.out.println("Enter the asset description");
				 String asset_desciption= scanner.nextLine();
				 int quantity=0;
				 do{
				 System.out.println("Enter the quantity");
				 tovalidate=scanner.next();
				 }while(!information_validator.validateQuantity(tovalidate));
				 quantity=Integer.parseInt(tovalidate);
				 int action2=0;
				 do{
					 System.out.println("Enter the status");
					 System.out.println("1. Available");
					 System.out.println("2. Unavailable");
					 action2= scanner.nextInt();
					 switch(action2){                                                  // Nested Switch to minimize Admin work and directly Admin can give option between available asset or not available
					 	case 1: asset.setStatus("Available");
					 	break;
					 	case 2: asset.setStatus("Unavailable");
					 	break;
					 	default:System.out.println("Invalid Option");
				 }}while(action2!=1 && action2!=2);
			 asset.setAsset_name(asset_name);
			 asset.setAsset_desc(asset_desciption);
			 asset.setQuantity(quantity);
			 int n=information_helper.addAsset(asset);
			 if(n!=0){
				 System.out.println("Entry Succeded. Asset ID = "+n);
			 	 log.info("New Asset Added in database");
			 }
			 else{
				 System.out.println("Entry Declined");
				 log.info("Asset updation failed");
			 }
			 break;
				 case 2:      
				                                                                                 //Modify the asset
		    	 /* ****************** INVENTORY DETAILS********************** */
		    	 ArrayList<AssetDetails> inventory = new ArrayList<AssetDetails>();
		    	 inventory=information_helper.checkInventory();
		    	 for(AssetDetails program1:inventory){
		    		 System.out.println("Asset ID: "+program1.getAsset_id()+
		    				 "--- Asset Name: "+program1.getAsset_name()+
		    				 "--- Quantity: "+program1.getQuantity());
		    	 }
		    	 int asset_id=0;
		    	 
		    	 do{
		    	 System.out.println("Enter the Asset ID you want to update");
		    	 asset_id=scanner.nextInt();
		    	 tovalidate=Integer.toString(asset_id);
		    	 }while(!information_validator.validateAssetId(tovalidate));
		    	 asset.setAsset_id(asset_id);
	
		    	 int action3=0;                                                                 // To choose which asset to modify
		    	 do{
		    		 System.out.println("");
		    		 System.out.println("Choose the correct option you want to update");
		    		 System.out.println("*************************************************");
			    	 System.out.println("1) Update Asset Name");
			    	 System.out.println("2) Update Asset Description");
			    	 System.out.println("3) Update Quantity");
			    	 System.out.println("4) Update Status");
			    	 System.out.println("5) Main Menu");
			    	 System.out.println("6) Log Out");
			    	 System.out.println("7) Exit");
			    	 System.out.println("*************************************************");
		    		 action3=scanner.nextInt();
		    		 int update=0;                                                             // Variable to check return of method and decide update succeed or not 
		    		 switch(action3){                                                          // Nested Switch to update asset as per menu
		    		 case 1:                                                                   // Update Asset Name 
		    		 		System.out.println("New Assset Name");
		    		 		dummy=scanner.nextLine();
		    		 		asset_name= scanner.nextLine();
		    		 		asset.setAsset_name(asset_name);
		    		 		update=information_helper.updateAssetName(asset);
		    		 		if(update==1){
		    		 			System.out.println("Updation succeeded");
		    		 			log.info("Asset name updated");
		    		 		}
		    		 		else{
		    		 			System.out.println("Update Declined!!! Verify asset ID");
		    		 		}
		    		 		break;
		    		 case 2:                                                                    // Update Asset Description
		    			 	System.out.println("New Assset Description");
		    			 	String dummy1= scanner.nextLine();
		    			 	String asset_description= scanner.nextLine();
		    			 	System.out.println(asset_description);
		    			 	asset.setAsset_desc(asset_description);
							asset.setAsset_id(asset_id);
							update=information_helper.updateAssetDesc(asset);
							if(update==1){
								System.out.println("Updation succeeded");
								log.info("Asset description updated");
							}
							else{
								System.out.println("Update Declined!!! Verify asset ID");
							}
							break;
		    		 case 3:                                                                     // Update Asset Quantity 
		    			 	do{
		    			 	System.out.println("New Assset Quantity");
		    			 	tovalidate=scanner.next();
		    			 	}while(!information_validator.validateQuantity(tovalidate));
		    			 	quantity= Integer.parseInt(tovalidate);
		    			 	asset.setQuantity(quantity);
		    			 	update=information_helper.updateAssetQuantity(asset);
		    			 	if(update==1){
		    			 		System.out.println("Updation succeeded");
		    			 		log.info("Asset quantity updated");
		    			 	}
		    			 	else{
		    			 		System.out.println("Update Declined!!! Verify asset ID");
		    			 	}
		    			 	break;
		    		 case 4:                                                                    // Update Asset Status
		    			 	System.out.println("New Status");
		    			 	String asset_status =scanner.next();
		    			 	asset.setStatus(asset_status);
		    			 	update=information_helper.updateAssetStatus(asset);
		    			 	if(update==1){
		    			 		System.out.println("Updation succeeded");
		    			 		log.info("Asset status updated");
		    			 	}
		    			 	else{
		    			 		System.out.println("Update Declined!!! Verify asset ID");
		    			 	}
		    			 	break;
		    		 case 5: adminLogin();
		    		 		 break;
		    		 case 6:System.out.println("Session Log Out");
		    		        AssetPortal.main(null);
		    		        break;
		    		 case 7:System.out.println("System Exit");
		    			 	System.exit(0);                                                        // System exit
		    		 		break;
		    		 default:System.out.println("Invalid Option!! Try Again");
		    		 		break;
		    		 }
		    	 }while(action3!=7);
				 case 3: adminLogin();
				 		break;
				 case 4: System.out.println("Log Out Successful");
				         AssetPortal.main(null);
				         break;
				 case 5: System.out.println("You had chosen to exit the Portal");
				 		System.exit(0);
				 		break;
		     }
			 }
		
		catch(Exception e){
			scanner.next();
			System.out.println(" ");
			System.err.println("Exception in entering details!!! Please enter a valid choice");
			log.info("Entered a non numeric character where required numeric");
			System.out.println(" ");
		}
	}while(action1!=5);
	
}
}

