package springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.User;

public class UserDao {
//	
////	private SimpleConnectionMaker simpleConnectionMaker;
////	private ConnectionMaker connectionMaker;
//	
//	public UserDao() {
////		simpleConnectionMaker = new SimpleConnectionMaker();
////		connectionMaker = new DConnectionMaker();
//	}
//	
////	public UserDao(ConnectionMaker connectionMaker) {
////		this.connectionMaker = connectionMaker;
////	}
////	
////	public void setConnectionMaker(ConnectionMaker connectionMaker) {
////		this.connectionMaker = connectionMaker;
////	}
//	
//	// 4.
//	private DataSource dataSource;
//
////	public void setDataSource(DataSource dataSource) {
////		this.dataSource = dataSource;
////	}
//	
//	// 8.
//	private JdbcContext jdbcContext;
//	public void setDataSource(DataSource dataSource) {
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcContext = new JdbcContext();
//		jdbcContext.setDataSource(dataSource);
//		this.dataSource = dataSource;
//	}
//	
//	// 10.
//	private JdbcTemplate jdbcTemplate;
//
//	public void add(final User user) throws Exception {
////		Connection c = simpleConnectionMaker.makeNewConnection();
////		Connection c = connectionMaker.makeConnection();
//		// 4.
////		Connection c = dataSource.getConnection();
////
////		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
////		ps.setString(1, user.getId());
////		ps.setString(2, user.getName());
////		ps.setString(3, user.getPassword());
////
////		ps.executeUpdate();
////
////		ps.close();
////		c.close();
//		
//		// 5. strategy pattern
////		StatementStrategy stmt = new AddStatement(user);
////		jdbcContextWithStatementStrategy(stmt);
//		
//		// 6. internal class
////		class AddStatement implements StatementStrategy {
////			
////			@Override
////			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
////				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
////				ps.setString(1,  user.getId());
////				ps.setString(2,  user.getName());
////				ps.setString(3,  user.getPassword());
////				return ps;
////			}
////
////		}
////		StatementStrategy stmt = new AddStatement();
////		jdbcContextWithStatementStrategy(stmt);
//		
//		// 7. anonymous class
////		jdbcContext.workWithStatementStrategy(new StatementStrategy() {
////			
////			@Override
////			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
////				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
////				ps.setString(1,  user.getId());
////				ps.setString(2,  user.getName());
////				ps.setString(3,  user.getPassword());
////				return ps;
////			}
////		});
//		
//		
//		// 9. template/callback pattern
//		jdbcContext.executeSql("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
//	}
//
//
//	public User get(String id) throws Exception {
////		Connection c = simpleConnectionMaker.makeNewConnection();
////		Connection c = connectionMaker.makeConnection();
//
//		// 4.
//		Connection c = dataSource.getConnection();
//		
//		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
//		ps.setString(1, id);
//
//		ResultSet rs = ps.executeQuery();
//		User user = null;
//		if (rs.next()) {
//			user = new User();
//			user.setId(rs.getString("id"));
//			user.setName(rs.getString("name"));
//			user.setPassword(rs.getString("password"));
//		} 
//		if (user == null) {
//			throw new EmptyStackException();
//		}
//
//		rs.close();
//		ps.close();
//		c.close();
//
//		return user;
//	}
//	
//	public void deleteAll() throws Exception {
////		Connection c = dataSource.getConnection();
////
////		PreparedStatement ps = c.prepareStatement("delete from users");
////		ps.executeUpdate();
////		ps.close();
////		c.close();
////		StatementStrategy stmt = new DeleteAllStatement();
////		jdbcContextWithStatementStrategy(stmt);
//		
//		// 7. anonymous class
////		jdbcContext.workWithStatementStrategy(new StatementStrategy() {
////			
////			@Override
////			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
////				return c.prepareStatement("delete from users");
////			}
////		});
//		
//		// 9. template/callback pattern
////		jdbcContext.executeSql("delete from users");
//		
//		// 10. jdbc callback template
//		jdbcTemplate.update("delete from users");
//	}
//	
//	public int getCount() throws SQLException {
//		Connection c = dataSource.getConnection();
//		
//		PreparedStatement ps = c.prepareStatement("select count(*) from users");
//		
//		ResultSet rs = ps.executeQuery();
//		rs.next();
//		int count = rs.getInt(1);
//		
//		rs.close();
//		ps.close();
//		c.close();
//		
//		return count;
//	}
//	
////	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
////		Connection c = null;
////		PreparedStatement ps = null;
////		
////		try {
////			c = dataSource.getConnection();
////			
////			ps = stmt.makePreparedStatement(c);
////			
////			ps.executeUpdate();
////		} catch (SQLException e) {
////			throw e;
////		} finally {
////			if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
////			if (c != null) { try { c.close(); } catch (SQLException e) {} }
////		}
////	}
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<User> userMapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};
	
	public void add(final User user) {
		jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}
	
	public User get(String id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id}, this.userMapper);
	}
	
	public void deleteAll() {
		jdbcTemplate.update("delete from users");
	}
	
	public Integer getCount() {
		return jdbcTemplate.queryForInt("select count(*) from users");
	}
	
	public List<User> getAll() {
		return jdbcTemplate.query("select * from users order by id",  userMapper);
	}

}
