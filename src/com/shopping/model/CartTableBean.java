package com.shopping.model;

public class CartTableBean {
	private int cartId;
	private int uId;
	private int productId;
	private String productName;
	private int quantity;
	private	Double price;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	public int getCartId()
	{
		return cartId;
	}
	public void setCartId(int cartId) 
	{
		this.cartId = cartId;
	}
	public int getuId() 
	{
		return uId;
	}
	public void setuId(int uId) 
	{
		this.uId = uId;
	}
	public int getProductId() 
	{
		return productId;
	}
	public void setProductId(int productId)
	{
		this.productId = productId;
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	public Double getProductPrice()
	{
		return productPrice;
	}
	public void setProductPrice(Double productPrice)
	{
		this.productPrice = productPrice;
	}
	private Double productPrice;

}
