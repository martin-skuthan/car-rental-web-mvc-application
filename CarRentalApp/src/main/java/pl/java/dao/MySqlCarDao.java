package pl.java.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.java.model.Car;

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
	
}
