package com.nurturecommunity.constant;

public class Queries {

	public static String Loginsql="select * from users";
	public static String AddMultipartFiles = "insert into signUpFiles (userId, multipartFileName, multipartFile) values(?,?,?)";
	public static String AddFoodFiles = "insert into foodImages (foodid, multipartFileName, multipartFile) values(?,?,?)";
	public static String foodListsql = "select food_details.food_id,food_details.address1,food_details.address2,food_details.city,food_details.country,food_details.item_description,food_details.item_name,food_details.location_change,food_details.numberof_packets,food_details.pickup_time,food_details.picture from users LEFT JOIN food_details on food_details.id = users.id where food_details.id = ( select DISTINCT id from users where users.emailaddress = ? ) order by food_details.numberof_packets desc";
	public static String resturantImage = "select * from signupfiles where signupfiles.userid = (select id from users where users.emailaddress = ? ) order by signupfiles.userid";
	public static String updateFood = "Update food_details Set  numberof_packets = ? WHERE id = ?";
}
