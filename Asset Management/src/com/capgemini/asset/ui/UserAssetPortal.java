/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/************************************* User FUNCTIONS ************************************/
package com.capgemini.asset.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capgemini.asset.bean.AssetDetails;
import com.capgemini.asset.exception.AssetException;
import com.capgemini.asset.service.AssetInformationValidator;
import com.capgemini.asset.service.UserAssetInformationHelper;

public class UserAssetPortal {
	public static void userLogin() throws AssetException, IOException {
		Logger log = Logger.getRootLogger();                                                 
		AssetInformationValidator information_validator = new AssetInformationValidator();              // object of validator
		UserAssetInformationHelper helper = new UserAssetInformationHelper();                           // object of user helper class
		AssetDetails asset = new AssetDetails();                                                        // object of bean class
		Scanner scanner = new Scanner(System.in);
		
		int action = 0;                                                                                  // variable to pass inside while loop
		do {
			try {
				System.out.println("Choose your action");
				System.out.println("****************************************");
				System.out
						.println("1:Raise a new request\n2:View the status of request\n3:LogOut\n4:EXIT");         // menu to the user
				System.out.println("******************************************");
				action = scanner.nextInt();
				switch (action) {
				case 1:                                                                                  // Main switch to raise a request
					System.out.println("POPULAR ARTICLES");
					System.out
							.println("Available Details");
					ArrayList<AssetDetails> inventory = new ArrayList<AssetDetails>();
					inventory = helper.showInventory();
					for (AssetDetails program1 : inventory) {                                             // to print the asset details i.e. asset id
						System.out.println("Asset ID: "
								+ program1.getAsset_id() + "--- Asset Name: "
								+ program1.getAsset_name() +"--- Asset Description: "+program1.getAsset_desc());
					}
					int check = 0;                                                                        // variable to check the return from various method 
					do {
						int asset_id = 0;
						String tovalidate = null;                                                         // variable to save the return from method
						do {
							System.out.println("Enter the AssetID");  
							tovalidate=scanner.next();
						} while (!information_validator
								.validateAssetId(tovalidate));                                           // Asset ID Validation
						asset_id = Integer.parseInt(tovalidate);
						asset.setAsset_id(asset_id);                                                     // Asset ID exists in the database or not
						check = helper.checkAssetID(asset);
						if (check == 1) {
							do {
								String tovalidate1 = "null";
								int emp_no = 0;
								do {
									System.out
											.println("Enter the employee number");
									tovalidate1 = scanner.next();                              // Employee ID validation
								} while (!information_validator
										.validateEmpNo(tovalidate1));
								emp_no = Integer.parseInt(tovalidate1);
								asset.setEmp_no(emp_no);
								check = helper.checkEmpno(asset);                                         // Checking employee if exists in database 
								if (check == 1) {
									
										check = helper
												.checkAlreadyRequest(asset);                               // Check if the employee has already a pending request
										if (check == 0) {
											
											
												check = helper
														.checkMgrForEmployee(asset);                      // Check if the manager has that employee under him or not
												if (check == 1) {

													int raise = helper
															.raiseRequest(asset);
													System.out
															.println("Request allocation Id is:"
																	+ raise
																	+ ". Keep a screenshot of allocation ID for later checking the status of the request");
													log.error("The request for generation new Asset ticket is successful with ID"+raise);
													
													break;
												} else {
													System.out
															.println("Employee code does not belongs to MGR code");
													log.error("Employee code is not under manager whose id is used in logging in");

												}
											
										} else {
											System.out
													.println("Employee number "
															+ asset.getEmp_no()
															+ " has already a pending request.You can,t raise a new request");

										}
									
								} else {
									System.out
											.println("Employee number not found in database");

								}
							} while (check != 1);
						} else {
							System.out
									.println("Please enter a valid asset ID from the list");
						}
					} while (check != 1);
					break;

				case 2:                                                                                           // Main switch to check the status of raised request by manager
					int allocation_id = 0;
					int successful=0;
					String tovalidate2 = null;
					do{
					try{
					do {
						System.out.println(" ");
						System.out.println("Enter the allocation ID");
						tovalidate2 = scanner.next();                                       // allocation id validation according to constraints
					} while (!information_validator
							.validateAllocationId(tovalidate2));
					allocation_id = Integer.parseInt(tovalidate2);
					asset.setAllocation_id(allocation_id);
					String status = helper.checkStatus(asset);                                                  // check for the status
					System.out.println("Status of your ticket is:" + status);
					successful=1;
					}catch(InputMismatchException e){
						System.err.println("PLEASE ENTER A VALID ALLOCATION ID");
						scanner.next();
					}
					catch(Exception e)
					{
						throw new AssetException("Request denied");
					}
					}while(successful!=1);break;
				case 3:                                                                                         // session termination
					System.out.println("Session Log Out");
					AssetPortal.main(null);
					break;
				case 4:	System.out.println("Session is terminated");
						System.exit(0);
						break;
				default:
					System.out.println("");
					System.out.println("you had choosen an invalid option");
					System.out.println("");
					break;
				}
			} catch (InputMismatchException e) {
				scanner.next();
				System.err.println("Invalid Input... Require digits");
				log.error("Invalid Option chosen by user...need a character but entererd a string");

			}
			
		} while (action != 4);

	}
}
