package pl.java.dao;

import pl.java.exceptions.NoSuchDbException;

public abstract class DaoFactory {
	private static final int MYSQL_DAO_FACTORY = 1;
	public abstract CustomerDao getUserDao();
	public abstract CarDao getCarDao();
	
	public static DaoFactory getFactory() {
		DaoFactory daoFactory = null;
		try {
			daoFactory = getFactory(MYSQL_DAO_FACTORY);
		}catch (NoSuchDbException e) {
			e.printStackTrace();
		}
		return daoFactory;
	}
	
	private static DaoFactory getFactory(int factoryType) throws NoSuchDbException {
		switch (factoryType) {
		case MYSQL_DAO_FACTORY:
			return new MySqlDaoFactory();
		default:
			throw new NoSuchDbException();
		}
	}
}
