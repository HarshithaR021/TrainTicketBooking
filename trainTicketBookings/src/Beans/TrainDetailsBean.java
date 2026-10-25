package Beans;

import java.io.Serializable;

public class TrainDetailsBean implements Serializable {
	private int trainNumber;
	private String trainName;
	private String trainS;
	private String trainD;
	private String trainTime;
	private Double trainPrice;
	private int trainSeats;
	
	public int getTrainSeats() {
		return trainSeats;
	}

	public void setTrainSeats(int trainSeats) {
		this.trainSeats = trainSeats;
	}

	public TrainDetailsBean() {
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainS() {
		return trainS;
	}

	public void setTrainS(String trainS) {
		this.trainS = trainS;
	}

	public String getTrainD() {
		return trainD;
	}

	public void setTrainD(String trainD) {
		this.trainD = trainD;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public Double getTrainPrice() {
		return trainPrice;
	}

	public void setTrainPrice(Double trainPrice) {
		this.trainPrice = trainPrice;
	}
	
	
	

}
