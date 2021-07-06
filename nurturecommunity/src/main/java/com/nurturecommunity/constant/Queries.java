package com.nurturecommunity.constant;

public class Queries {

	public static String Loginsql="select * from users";
	public static String FoodListsql ="select * from food_details";
	public static String AddMultipartFiles = "insert into signUpFiles (userId, multipartFileName, multipartFile) values(?,?,?)";
	
	public static String AddFoodFiles = "insert into foodImages (foodid, multipartFileName, multipartFile) values(?,?,?)";
}
