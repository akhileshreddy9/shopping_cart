/**
 * 
 */
package com.shopping.controller;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.shopping.DAO.ShoppingCartDAO;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
       Statement stmt;
       ResultSet rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();

    }

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession sess=request.getSession();
		
		String username=request.getParameter("user");
		String pwd=request.getParameter("password");
		
		
		try {
			
			con= DBConnection.getConnection();
			
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from login");
			
			RequestDispatcher rd;
			
			while(rs.next())
			{
				if(username.equals(rs.getString("u_name"))&& pwd.equals(rs.getString("p_word")))
				{
					String name=rs.getString("u_name");
					int id = rs.getInt("u_id");
					sess.setAttribute("user", name);
					//sess.setAttribute("u_id", (int)id);
					rd=request.getRequestDispatcher("/Main.jsp");
					rd.forward(request, response);
					
				}
			}
			
			rd=request.getRequestDispatcher("/Login.html");
			rd.forward(request, response);
			
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
