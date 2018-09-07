
/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/*Main method to validate the users according to their role and validating their authentication*/

package com.capgemini.asset.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

import com.capgemini.asset.bean.*;
import com.capgemini.asset.exception.*;

import java.util.regex.*;

import org.apache.log4j.Logger;





import com.capgemini.asset.service.*;;
 
public class AssetPortal {
	public static void main(String[] args) throws AssetException, IOException {
		
			
			Logger log = Logger.getRootLogger(); //logger
			int validate=0;                                //variable name to transfer control to admin or manager according to return from dao.
			AdminAssetInformationHelper information_helper= new AdminAssetInformationHelper();
			AssetInformationValidator information_validator = new AssetInformationValidator();
			AssetDetails asset  = new AssetDetails();
			Scanner scanner = new Scanner(System.in);
			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
			int action=0;                                  //variable to pass inside switch
			System.out.println("****************************************");
			System.out.println("**Welcome to ASSET MANAGEMENT PORTAL**");
			System.out.println("****************************************");
			do{
			System.out.println("Choose 1 to continue");
			System.out.println("Choose 2 to exit");
				try{
					action=scanner.nextInt();
					switch(action){
						case 1:
			
							int chance=3;  // To give 3 chances to a user for entering correct user and password			
							do{
								String user_id=null;
								String user_password=null;
								do {
								System.out.println("User ID");
								user_id = buffer.readLine();
								user_id=user_id.trim();
								}while (!information_validator.validateUserId(user_id));
								asset.setUser_id(user_id);
								do{
								System.out.println("User PASSWORD");
								user_password= buffer.readLine();
								user_password=user_password.trim();
								}while (!information_validator.validatePassword(user_password));
							
								asset.setUser_password(user_password);
								//Method to verify ID & Password and select user type 
								validate = information_helper.check(asset);
								// checking for return to print login successful or not
								if (validate==1||validate==2){   
									// 2 means successful manager authentication and 1 for manager
									log.info("LOGIN SUCCESSFUL");
									System.out.println("Login Successful");	
							
									break;			
								}
								else{
									log.info("User has entered a wrong user ID or Password");
									System.out.println("Invalid user name and password");
									chance=chance-1;
									System.out.println("You have "+chance+" chance left.");
								}
					
							}while(chance!=0);
	
							//Method call....input system for Manager/User
							if (validate==2){
								log.info("Successful manager login");
								UserAssetPortal.userLogin();     // Method in UserAssetPortal
							}
				
							//Method call....input system for Admin
							if (validate==1){
								log.info("Successful Admin login");
								AdminAssetPortal.adminLogin();     // Method in AdminAssetPortal
							}
			
						case 2: System.out.println("SYSTEM EXIT");
								log.info("User choose to exit");
							System.exit(0);
							break;
						default:System.out.println("You had chosen an invalid choice");
								System.out.println(" ");
							break;
			
					}	
				}catch(InputMismatchException e){
					scanner.next();
					System.out.println(" ");
					log.error("Typed a character......Error....... required number");
					System.err.println("Error : Input should be a number");
					System.out.println();
				}	
			}while(action!=2);
					
		}
	}



