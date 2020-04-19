package pl.java.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import pl.java.model.Car;
import pl.java.model.Customer;
import pl.java.model.enums.TypeOfCar;

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

	public Car read(String carId) {
		return null;
	}

	public void update(Car car) {
				
	}

	public void delete(String carId) {
				
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
