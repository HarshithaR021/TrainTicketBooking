package Beans;

import java.io.Serializable;

public class ManagementBean implements Serializable{
	private int id;
	private String password; 
	public ManagementBean() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		

}
