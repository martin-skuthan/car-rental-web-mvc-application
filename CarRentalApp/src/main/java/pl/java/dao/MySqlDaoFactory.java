package pl.java.dao;

public class MySqlDaoFactory extends DaoFactory {
	@Override
	public UserDao getUserDao() {
		return new MySqlUserDao();
	}

	@Override
	public CarDao getCarDao() {
		return new MySqlCarDao();
	}

}
