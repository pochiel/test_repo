package com.mycompany.myapp.testrev;

public class CPoint
{
   private int mx;
	private int my;

	public void sety(int my)
	{
		this.my = my;
	}

	public int gety()
	{
		return my;
	}
   
	public void setx(int mx)
	{
		this.mx = mx;
	}

	public int getx()
	{
		return mx;
	}
	
	public void setPoint(int x,int y)
	{
		this.mx=x;
		this.my=y;
	}
}
