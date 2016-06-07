package springbook.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class UserDaoTest {
	
	@Autowired
	private ApplicationContext context;
	
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		dao = context.getBean("userDao", UserDao.class);
		user1 = new User("gyumee1", "박성철1", "springno1");
		user2 = new User("gyumee2", "박성철2", "springno2");
		user3 = new User("gyumee3", "박성철3", "springno3");
	}

	@Test
	public void addAndGet() throws Exception {
		// 1.
//		ConnectionMaker connectionMaker = new DConnectionMaker();
//		UserDao dao = new UserDao(connectionMaker);
		
		// 2.
//		UserDao dao = new DaoFactory().userDao();
		
		// 3. 
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		UserDao dao = context.getBean("userDao", UserDao.class);
		
		// 4.
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		UserDao dao = context.getBean("userDao", UserDao.class);
		
//		User user1 = new User("gyumee1", "박성철1", "springno1");
//		User user2 = new User("gyumee2", "박성철2", "springno2");
		
		dao.deleteAll();
		
		assertThat(dao.getCount(), is(0));
		
//		User user = new User();
//		user.setId("id");
//		user.setName("name");
//		user.setPassword("password");
//		
//		dao.add(user);
		dao.add(user1);
		dao.add(user2);
		
		assertThat(dao.getCount(), is(2));
		
//		System.out.println("등록 성공: " + user1.getName());
//		
//		User user3 = dao.get(user1.getId());
//		System.out.println("로드 성공: " + user2.getPassword());
//		System.out.println("===================");
//		
//		if (user.getName().equals(user2.getName())) {
//			System.out.println("Name Failed");
//		} else if (user.getPassword().equals(user2.getPassword())) {
//			System.out.println("Password Failed");
//		} else {
//			System.out.println("Test Success!");
//		}
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
		
	}
	
	@Test
	public void count() throws Exception {
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		UserDao dao = context.getBean("userDao", UserDao.class);
		
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
		
	}
	
	@Test(expected=EmptyStackException.class)
	public void getUserFailure() throws Exception {
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("Unknown_id");
	}
	
}