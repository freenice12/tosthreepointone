package springbook.user.dao;

public class DaoFactory {

	public UserDao userDao() {
		ConnectionMaker connectionMaker = connectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		return userDao;
	}

	public AccountDao accountDao() {
		ConnectionMaker connectionMaker = connectionMaker();
		AccountDao accountDao = new AccountDao(connectionMaker);
		return accountDao;
	}

	public MessageDao messageDao() {
		ConnectionMaker connectionMaker = connectionMaker();
		MessageDao messageDao = new MessageDao(connectionMaker);
		return messageDao;
	}
	
	private ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

}
