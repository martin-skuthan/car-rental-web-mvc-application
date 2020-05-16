package pl.java.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.enums.Transmission;
import pl.java.model.enums.TypeOfCar;

@Entity
@DiscriminatorValue("LightCommercial")
public class LightCommercialCar extends Car implements Serializable {
	private static final long serialVersionUID = -7292677873611802750L;
	
	private double payload;
    private double loadVolume;
    private double loadHeight;
    private double loadWidth;
    private double loadLength;
    
    public LightCommercialCar() {}
    
    public LightCommercialCar(String registrationNumber, String brand, String model,int seats, boolean airConditioning, Transmission transmission,
				Customer user, double payload, double loadVolume, double loadHeight, double loadWidth, double loadLength) {
    	super(registrationNumber, brand, model, seats, airConditioning, transmission, user);
    	this.payload = payload;
    	this.loadVolume = loadVolume;
    	this.loadHeight = loadHeight;
    	this.loadWidth = loadWidth;
    	this.loadLength = loadLength;
    }

	public double getPayload() {
		return payload;
	}

	public void setPayload(double payload) {
		this.payload = payload;
	}

	public double getLoadVolume() {
		return loadVolume;
	}

	public void setLoadVolume(double loadVolume) {
		this.loadVolume = loadVolume;
	}

	public double getLoadHeight() {
		return loadHeight;
	}

	public void setLoadHeight(double loadHeight) {
		this.loadHeight = loadHeight;
	}

	public double getLoadWidth() {
		return loadWidth;
	}

	public void setLoadWidth(double loadWidth) {
		this.loadWidth = loadWidth;
	}

	public double getLoadLength() {
		return loadLength;
	}

	public void setLoadLength(double loadLength) {
		this.loadLength = loadLength;
	}
	
	@Transient
	public TypeOfCar getTypeOfCar() {
		String typeOfCarDescription = this.getClass().getAnnotation(DiscriminatorValue.class).value();
		TypeOfCar typeOfCar = null;
		try {
			typeOfCar = TypeOfCar.getFromDescription(typeOfCarDescription);
		} catch (NoSuchTypeException e) {
			e.printStackTrace();
		}
		
		return typeOfCar;
	}
}
