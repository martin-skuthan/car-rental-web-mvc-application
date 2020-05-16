package pl.java.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.enums.Transmission;
import pl.java.model.enums.TypeOfCar;
import pl.java.model.enums.TypeOfDrive;

@Entity
@DiscriminatorValue("Passenger")
public class PassengerCar extends Car implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int numberOfDoors;
    private TypeOfDrive typeOfDrive;
    private int trunkCapacity;
	
    public PassengerCar() {}
    
    public PassengerCar(String registrationNumber, String brand, String model,int seats, boolean airConditioning, Transmission transmission,
 		   				Customer user, int numberOfDoors, TypeOfDrive typeOfDrive, int trunkCapacity) {
    	super(registrationNumber, brand, model, seats, airConditioning, transmission, user);
    	this.numberOfDoors = numberOfDoors;
    	this.typeOfDrive = typeOfDrive; 	
    	this.trunkCapacity = trunkCapacity;
    }

	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	public TypeOfDrive getTypeOfDrive() {
		return typeOfDrive;
	}

	public void setTypeOfDrive(TypeOfDrive typeOfDrive) {
		this.typeOfDrive = typeOfDrive;
	}

	public int getTrunkCapacity() {
		return trunkCapacity;
	}

	public void setTrunkCapacity(int trunkCapacity) {
		this.trunkCapacity = trunkCapacity;
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
