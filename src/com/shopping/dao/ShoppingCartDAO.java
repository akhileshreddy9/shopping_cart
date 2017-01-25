package com.shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.controller.DBConnection;
import com.shopping.model.CartTableBean;
import com.shopping.model.UserLoginBean;

public class ShoppingCartDAO 
{
	Connection con;

	public ShoppingCartDAO() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/sys","root","password");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean validateUser(UserLoginBean userLoginBean)
	{
		boolean status = false;

		try
		{
			PreparedStatement preparedStatement= con.prepareStatement("select * from UserLogin where u_name = ? and p_word = ? ");
			preparedStatement.setString(1, userLoginBean.getUserName());
			preparedStatement.setString(1, userLoginBean.getPassWord());
			ResultSet rs;
			rs = preparedStatement.executeQuery();
			if(rs.next()) 
			{
				status = true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Error: "+e.getMessage());
		}
		return status;
	}
public List<CartTableBean> getProducts(int userId) {
		
		String query = "select cart.product_id,product_name,quantity,cart.price from cart inner join product on cart.product_id = product.product_id where uid=?";
		List<CartTableBean> carts = new ArrayList<CartTableBean>();
		Connection connection = DBConnection.getConnection();
		try {
				PreparedStatement pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, userId);
				ResultSet rs=pstmt.executeQuery();
				
				while(rs.next())
				{
					CartTableBean cart = new CartTableBean();
					
					cart.setProductId(rs.getInt("product_id"));
					cart.setProductName(rs.getString("product_name"));
					cart.setQuantity(rs.getInt("quantity"));
					cart.setPrice(rs.getDouble("price"));
					
					carts.add(cart);
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return carts;
	}



	public Double getTotal(int userId) {
		
			Double totalprice = 0.00;
			
			String query="select sum(price) from cart where u_id= ? group by u_id ";
			
			Connection connection =DBConnection.getConnection();
			
			try {
					
					PreparedStatement pstmt= connection.prepareStatement(query);
					pstmt.setInt(1, userId);
					ResultSet rs= pstmt.executeQuery();
					
					while(rs.next())
					{
						totalprice=rs.getDouble("price");
					}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		return totalprice;
	}


}
