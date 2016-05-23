package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {

//	@Bean
//	public UserDao userDao() {
//		UserDao userDao = new UserDao();
//		userDao.setConnectionMaker(connectionMaker());
//		return userDao;
//	}
	
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}

//	public AccountDao accountDao() {
//		AccountDao accountDao = new AccountDao(connectionMaker());
//		return accountDao;
//	}
//
//	public MessageDao messageDao() {
//		MessageDao messageDao = new MessageDao(connectionMaker());
//		return messageDao;
//	}
	
	@Bean
	private ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//		Class.forName("org.postgresql.Driver");
//		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/springbook", "spring", "book");
		
		dataSource.setDriverClass(org.postgresql.Driver.class);
		dataSource.setUrl("jdbc:postgresql://localhost/springbook");
		dataSource.setUsername("spring");
		dataSource.setPassword("book");
		
		return dataSource;
		
	}

}
