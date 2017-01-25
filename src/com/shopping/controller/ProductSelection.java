package com.shopping.controller;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSelection {

	public static void insertIntoCart(int u_id,int product_id,int quantity)
	{
		Connection con=null;
		java.sql.PreparedStatement pstmt;
		ResultSet rs;
		

		try {
				con=DBConnection.getConnection();
		
				String priceValue="select product_id,price from product where product_id= ?";
				
				pstmt=con.prepareStatement(priceValue,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				pstmt.setInt(1, product_id);
				rs=pstmt.executeQuery();
				rs.next();
				int price=rs.getInt("price");		
				String sql="INSERT INTO cart(uid,product_id,quantity,price)VALUES(?,?,?,?)";
				
				int total_price = price * quantity;
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, u_id);
				pstmt.setInt(2, product_id);
				pstmt.setInt(3, quantity);
				pstmt.setInt(4, total_price);
				
				pstmt.execute();
				
				pstmt.clearParameters();
				pstmt.close();
				
				
					
				
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
