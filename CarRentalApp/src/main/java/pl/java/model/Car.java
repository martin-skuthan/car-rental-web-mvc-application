package pl.java.model;

import java.io.Serializable;

import pl.java.model.enums.Transmission;

public abstract class Car implements Serializable {
	private static final long serialVersionUID = 9171676490784870223L;
	
	private String registrationNumber;
    private String brand;
    private String model;
    private int seats;
    private boolean airConditioning;
    private Transmission transmission;
    private User user;
    
    public Car() {}
    
    public Car(String registrationNumber, String brand, String model,int seats, boolean airConditioning, Transmission transmission,
    		   User user) {
    	this.registrationNumber = registrationNumber;
    	this.brand = brand;
    	this.model = model;
    	this.seats = seats;
    	this.airConditioning = airConditioning;
    	this.transmission = transmission;
    	this.user = user;
    }

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public boolean isAirConditioning() {
		return airConditioning;
	}

	public void setAirConditioning(boolean airConditioning) {
		this.airConditioning = airConditioning;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
