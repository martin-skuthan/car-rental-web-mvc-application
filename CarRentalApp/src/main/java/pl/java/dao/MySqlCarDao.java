package pl.java.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.Car;
import pl.java.model.Customer;
import pl.java.model.PassengerCar;
import pl.java.model.enums.Transmission;
import pl.java.model.enums.TypeOfCar;
import pl.java.model.enums.TypeOfDrive;

@ApplicationScoped
@Default
public class MySqlCarDao implements CarDao {
	
	@Inject
	private EntityManager entityManager;
	
	public void create(Car car) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(car);
		entityTransaction.commit();
	}

	public void update(Car car) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(car);
		entityTransaction.commit();
	}

	public void deleteByRegistrationNumber(String registrationNumber) {
		Query query = entityManager.createNamedQuery("Car.deleteCarByRegistrationNumber");	
		query.setParameter("registrationNumber", registrationNumber);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
	}
	
	public Car readCarByRegistrationNumber(String registrationNumber) {
		TypedQuery<Car> typedQuery = entityManager.createNamedQuery("Car.readCarByRegistrationNumber", Car.class);
		typedQuery.setParameter("registrationNumber", registrationNumber);
		Car car = typedQuery.getSingleResult();
		return car;
	}
	
	public List<Car> readAllCars(TypeOfCar typeOfCar) {
		TypedQuery<Car> typedQuery = createReadAllCarsQuery(typeOfCar);
		List<Car> cars = typedQuery.getResultList();
		return cars;
	}
	
	public List<Car> readRangeOfCars(TypeOfCar typeOfCar, int noOfRecords, int firstResult) {
		TypedQuery<Car> typedQuery = createReadAllCarsQuery(typeOfCar).setMaxResults(noOfRecords).setFirstResult(firstResult);
		List<Car> cars = typedQuery.getResultList();
		return cars;
	}	
		
	private TypedQuery<Car> createReadAllCarsQuery(TypeOfCar typeOfCar) {
		TypedQuery<Car> typedQuery = null;
		switch (typeOfCar) {
		case PASSENGER_CAR:
			typedQuery = entityManager.createNamedQuery("Car.readAllPassengerCars", Car.class);
			break;
        case LIGHT_COMMERCIAL_CAR:
        	typedQuery = entityManager.createNamedQuery("Car.readAllLightCommercialCars", Car.class);
        	break;
		}
		return typedQuery;
	}	
}
