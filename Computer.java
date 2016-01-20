package test;

public class Computer {
	private int id = 0;
	private String title = "";
	private String type = "";
	private int amount = 0;
	
	public Computer (int id, String title, String type, int amount){
		this.id = id;
		this.title = title;
		this.type = type;
		this.amount = amount;
	}
	
	public int getId(){
		return id;
	}
	public String getTitle(){
		return title;
	}
	public String getType(){
		return type;
	}
	public int getAmount(){
		return amount;
	}
}
