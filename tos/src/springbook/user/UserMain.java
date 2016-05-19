package springbook.user;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserMain {

	public static void main(String[] args) throws Exception {
		UserDao dao = new UserDao();
		
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
