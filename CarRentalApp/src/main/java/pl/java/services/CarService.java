package pl.java.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.java.comparators.CarBrandComparator;
import pl.java.comparators.CarModelComparator;
import pl.java.comparators.CarStateComparator;
import pl.java.comparators.CarTransmissionComparator;
import pl.java.comparators.enums.CarComparatorType;
import pl.java.converters.DateConverter;
import pl.java.dao.CarDao;
import pl.java.exceptions.NoSuchActionException;
import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.Car;
import pl.java.model.LightCommercialCar;
import pl.java.model.PassengerCar;
import pl.java.model.Customer;
import pl.java.model.enums.CarFields;
import pl.java.model.enums.Transmission;
import pl.java.model.enums.TypeOfCar;
import pl.java.model.enums.TypeOfDrive;

@ApplicationScoped
public class CarService {
	@Inject
	private CarDao carDao;
	@Inject
	CustomerService customerService;
	
	public void createCar(HashMap<CarFields, String> carDetails, TypeOfCar typeOfCar) {
		Car car = createCarFromDetails(carDetails, typeOfCar);
		carDao.create(car);
	}
	
	public void readCar(String carId) {
		
	}
	
	public void rentCar(String registrationNumber, String startDateString, String endDateString, String customerPesel ) {
		LocalDate startDate = DateConverter.convertDayMonthYearStringToLocalDate(startDateString);
		LocalDate endDate = DateConverter.convertDayMonthYearStringToLocalDate(endDateString);
		Car carFromDb = readCarByRegistrationNumber(registrationNumber);
		Customer customer = customerService.readCustomerByPesel(customerPesel);
		carFromDb.setStartDate(startDate);
		carFromDb.setEndDate(endDate);
		carFromDb.setUser(customer);
		carDao.update(carFromDb);
	}
	
	public void returnCar(String registrationNumber) {
		Car carFromDb = readCarByRegistrationNumber(registrationNumber);
		carFromDb.setStartDate(null);
		carFromDb.setEndDate(null);
		carFromDb.setUser(null);
		carDao.update(carFromDb);
	}
	/*
	public void updateCar(Car car) {
		carDao.update(car);
	}
	*/
	
	public void updateCar(HashMap<CarFields, String> carDetails, String registrationNumber) {
		Car carFromDb = readCarByRegistrationNumber(registrationNumber);
		updateCarFromDetails(carDetails, carFromDb);
		carDao.update(carFromDb);
	}
	
	public void deleteCarByRegistrationNumber(String registrationNumber) {
		carDao.deleteByRegistrationNumber(registrationNumber);
	}
	
	public Car readCarByRegistrationNumber(String registrationNumber) {
		Car car = carDao.readCarByRegistrationNumber(registrationNumber);
		return car;
	}
	
	public List<Car> readAllCars(TypeOfCar typeOfCar) {
		List<Car> cars = carDao.readAllCars(typeOfCar);
		return cars;
	}
	
	public List<Car> readRangeOfCars(TypeOfCar typeOfCar, int noOfPage, int noOfRecords, CarComparatorType carComparatorType) {
		List<Car> cars = readAllCars(typeOfCar);
		if (carComparatorType != null) {
			Comparator<Car> comparator = getComparatorFromDescription(carComparatorType);
			cars.sort(comparator);
		}
		
		int firstResult = noOfPage * noOfRecords - noOfRecords;
		int lastResult = firstResult + noOfRecords;
		if (lastResult > cars.size()) {
			lastResult = cars.size();
		}
		
		List<Car> rangeCar = cars.subList(firstResult, lastResult);
		return rangeCar;
	}
	
	public Car createCarFromDetails(HashMap<CarFields, String> carDetails, TypeOfCar typeOfCar) {
		String registrationNumber = carDetails.get(CarFields.REGISTRATION_NUMBER);
		String brand = carDetails.get(CarFields.BRAND);
		String model = carDetails.get(CarFields.MODEL);
		int seats = Integer.valueOf(carDetails.get(CarFields.SEATS));
		boolean airConditioning = Boolean.parseBoolean(carDetails.get(CarFields.AIR_CONDITIONING)); 
		Transmission transmission = Transmission.getFromDescription(carDetails.get(CarFields.TRANSMISSION));
		Customer user = null;
		
		switch (typeOfCar) {
		case PASSENGER_CAR:
			int numberOfDoors = Integer.valueOf(carDetails.get(CarFields.NUMBER_OF_DOORS));
			TypeOfDrive typeOfDrive = TypeOfDrive.getFromDescription(carDetails.get(CarFields.TYPE_OF_DRIVE));
			int trunkCapacity = Integer.valueOf(carDetails.get(CarFields.TRUNK_CAPACITY));
			
			return new PassengerCar(registrationNumber, brand, model, seats, airConditioning, transmission, user, numberOfDoors,
					typeOfDrive, trunkCapacity);
		case LIGHT_COMMERCIAL_CAR:
			double payload = Double.valueOf(carDetails.get(CarFields.PAYLOAD));
			double loadVolume = Double.valueOf(carDetails.get(CarFields.LOAD_VOLUME));
			double loadHeight = Double.valueOf(carDetails.get(CarFields.LOAD_HEIGHT));
			double loadWidth = Double.valueOf(carDetails.get(CarFields.LOAD_WIDTH));
			double loadLength = Double.valueOf(carDetails.get(CarFields.LOAD_LENGTH));
			
			return new LightCommercialCar(registrationNumber, brand, model, seats, airConditioning, transmission, user, payload,
					  loadVolume, loadHeight, loadWidth, loadLength);
		default:
			throw new NoSuchTypeException("There is no typeOfCar:" + typeOfCar.toString());
		}
	}
	
	public void updateCarFromDetails(HashMap<CarFields, String> carDetails, Car carFromDb) {
		carFromDb.setRegistrationNumber(carDetails.get(CarFields.REGISTRATION_NUMBER));
		carFromDb.setBrand(carDetails.get(CarFields.BRAND));
		carFromDb.setModel(carDetails.get(CarFields.MODEL));
		carFromDb.setSeats(Integer.valueOf(carDetails.get(CarFields.SEATS)));
		carFromDb.setAirConditioning(Boolean.parseBoolean(carDetails.get(CarFields.AIR_CONDITIONING)));
		carFromDb.setTransmission(Transmission.getFromDescription(carDetails.get(CarFields.TRANSMISSION)));
		carFromDb.setUser(null);
		
		switch (carFromDb.getTypeOfCar()) {
		case PASSENGER_CAR:
			PassengerCar passengerCarFromDb = (PassengerCar)carFromDb;
			passengerCarFromDb.setNumberOfDoors(Integer.valueOf(carDetails.get(CarFields.NUMBER_OF_DOORS)));
			passengerCarFromDb.setTypeOfDrive(TypeOfDrive.getFromDescription(carDetails.get(CarFields.TYPE_OF_DRIVE)));
			passengerCarFromDb.setTrunkCapacity(Integer.valueOf(carDetails.get(CarFields.TRUNK_CAPACITY)));
			break;
		case LIGHT_COMMERCIAL_CAR:
			LightCommercialCar lightCommercialCarFromDb = (LightCommercialCar)carFromDb;
			lightCommercialCarFromDb.setPayload(Double.valueOf(carDetails.get(CarFields.PAYLOAD)));
			lightCommercialCarFromDb.setLoadVolume(Double.valueOf(carDetails.get(CarFields.LOAD_VOLUME)));
			lightCommercialCarFromDb.setLoadHeight(Double.valueOf(carDetails.get(CarFields.LOAD_HEIGHT)));
			lightCommercialCarFromDb.setLoadLength(Double.valueOf(carDetails.get(CarFields.LOAD_LENGTH)));
			break;
		default:
			throw new NoSuchTypeException("There is no typeOfCar");
		}
	}
	
	private Comparator<Car> getComparatorFromDescription(CarComparatorType carComparatorType) {
		
		switch (carComparatorType) {
		case BRAND:
			return new CarBrandComparator();
		case MODEL:
			return new CarModelComparator();
		case STATE:
			return new CarStateComparator();
		case TRANSMISSION:	
			return new CarTransmissionComparator();
		default:
			throw new NoSuchTypeException("");
		}
	}
			
}
