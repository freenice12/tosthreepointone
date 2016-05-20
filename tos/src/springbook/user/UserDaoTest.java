package springbook.user;

import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.DConnectionMaker;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws Exception {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		UserDao dao = new UserDao(connectionMaker);
		
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
