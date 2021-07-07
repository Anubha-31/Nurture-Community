package com.nurturecommunity.constant;

public class Queries {

	public static String Loginsql="select * from users";
	public static String AddMultipartFiles = "insert into signUpFiles (userId, multipartFileName, multipartFile) values(?,?,?)";
	public static String AddFoodFiles = "insert into foodImages (foodid, multipartFileName, multipartFile) values(?,?,?)";
	public static String foodListsql = "select food_details.location_change,food_details.address1,users.address,users.license_number,users.restaurant_name,food_details.item_name,food_details.item_description,food_details.numberof_packets,food_details.pickup_time,food_details.uploaded_picture from users RIGHT JOIN food_details on food_details.id = users.id where zip = ( select zip from users where users.emailaddress = ? ) order by food_details.numberof_packets";

}
