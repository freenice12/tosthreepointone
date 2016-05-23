package springbook.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws Exception {
		// 1.
//		ConnectionMaker connectionMaker = new DConnectionMaker();
//		UserDao dao = new UserDao(connectionMaker);
		
		// 2.
//		UserDao dao = new DaoFactory().userDao();
		
		// 3. 
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		
		User user = new User();
		user.setId("id");
		user.setName("name");
		user.setPassword("password");
		
		dao.add(user);
		
		System.out.println("등록 성공: " + user.getName());
		
		User user2 = dao.get("id");
		System.out.println("로드 성공: " + user2.getPassword());
	}
	
}
