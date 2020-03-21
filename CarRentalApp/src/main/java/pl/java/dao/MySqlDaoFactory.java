package pl.java.dao;

public class MySqlDaoFactory extends DaoFactory {
	@Override
	public CustomerDao getUserDao() {
		return new MySqlCustomerDao();
	}

	@Override
	public CarDao getCarDao() {
		return new MySqlCarDao();
	}

}
