package Beans;

import java.io.Serializable;

public class BookingBean implements Serializable{
	private int userId;
	private int trainNumber;
	private Double price;
	private Long pnr;
	private String source;
	private String destination;
	private String time;
	public BookingBean() {
		}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getPnr() {
		return pnr;
	}
	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
