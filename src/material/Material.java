package material;

import java.sql.SQLException;

public abstract class Material implements Cloneable{
	
	private String id, name, band, description, use;
	private int disp;
	private double price;
	
	public Material(String id, String name, String band, int disp, String description, String use, double price)
	{
		this.id = id;
		this.name = name;
		this.band = band;
		this.description = description;
		this.use = use;
		this.disp = disp;
		this.price = price;
	}
	
	//public abstract String setLinkToOther();
	public abstract void saveToDB() throws SQLException;
	public abstract void updateDisp() throws SQLException;
	public abstract boolean isSave() throws SQLException;
	public abstract void updatePrice() throws SQLException;
	public abstract void deletefromDB() throws SQLException;
	public abstract String myTable();
	
	public String getName()
	{
		return this.name;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public String getBand()
	{
		return this.band;
	}
	
	public String getUse()
	{
		return this.use;
	}
	
	public int getAvaylability()
	{
		return this.disp;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	
	public void setDiscount(double quote) throws SQLException
	{
		if(quote >= 0 && quote < 1)
		{
			this.price = this.price - (this.price * quote);
		}
		//else please insert exception
	}
	
	public boolean controlAvaylability(int numProduct)
	{
		if((this.disp - numProduct) >= 0) return true;
		else return false;
	}
	
	public void removeAvaylability(int numProduct) throws SQLException
	{
		if(this.controlAvaylability(numProduct))
		{
			this.disp = this.disp - numProduct;
		}
	}
	
	public void addAvaylability(int numProduct) throws SQLException
	{
		if(numProduct > 0)
		{
			this.disp = this.disp + numProduct;
		}
	}
	
	
	public String toString()
	{
		return this.getClass().getName() + " id: " + this.id + ", name: " + this.name + ", band: " + this.band + 
				", description: " + this.description + ", use: " + this.use + ", avaylability: " + this.disp + 
				", price: " + this.price;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Material)) return false;
		Material ob = (Material) obj;
		return ob.id.equals(this.id) && ob.name.equals(this.name) && ob.band.equals(this.band) && ob.description.equals(this.description)
				&& ob.use.equals(this.use) && ob.disp == this.disp && ob.price == this.price;
	}
	
	public Material clone()
	{
		try
		{
			Material m = (Material) super.clone();
			return m;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
}
