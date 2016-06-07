package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EmptyStackException;

import javax.sql.DataSource;

import springbook.user.domain.User;

public class UserDao {
	
//	private SimpleConnectionMaker simpleConnectionMaker;
//	private ConnectionMaker connectionMaker;
	
	private JdbcContext jdbcContext;
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public UserDao() {
//		simpleConnectionMaker = new SimpleConnectionMaker();
//		connectionMaker = new DConnectionMaker();
	}
	
//	public UserDao(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
//	
//	public void setConnectionMaker(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
	
	// 4.
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(final User user) throws Exception {
//		Connection c = simpleConnectionMaker.makeNewConnection();
//		Connection c = connectionMaker.makeConnection();
		// 4.
//		Connection c = dataSource.getConnection();
//
//		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//		ps.setString(1, user.getId());
//		ps.setString(2, user.getName());
//		ps.setString(3, user.getPassword());
//
//		ps.executeUpdate();
//
//		ps.close();
//		c.close();
		
		// 5. strategy pattern
//		StatementStrategy stmt = new AddStatement(user);
//		jdbcContextWithStatementStrategy(stmt);
		
		// 6. internal class
//		class AddStatement implements StatementStrategy {
//			
//			@Override
//			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//				ps.setString(1,  user.getId());
//				ps.setString(2,  user.getName());
//				ps.setString(3,  user.getPassword());
//				return ps;
//			}
//
//		}
//		StatementStrategy stmt = new AddStatement();
//		jdbcContextWithStatementStrategy(stmt);
		
		// 7. anonymous class
		jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			
			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
				ps.setString(1,  user.getId());
				ps.setString(2,  user.getName());
				ps.setString(3,  user.getPassword());
				return ps;
			}
		});
		
	}


	public User get(String id) throws Exception {
//		Connection c = simpleConnectionMaker.makeNewConnection();
//		Connection c = connectionMaker.makeConnection();

		// 4.
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		} 
		if (user == null) {
			throw new EmptyStackException();
		}

		rs.close();
		ps.close();
		c.close();

		return user;
	}
	
	public void deleteAll() throws Exception {
//		Connection c = dataSource.getConnection();
//
//		PreparedStatement ps = c.prepareStatement("delete from users");
//		ps.executeUpdate();
//		ps.close();
//		c.close();
//		StatementStrategy stmt = new DeleteAllStatement();
//		jdbcContextWithStatementStrategy(stmt);
		
		// 7. anonymous class
		jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			
			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				return c.prepareStatement("delete from users");
			}
		});
	}
	
	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select count(*) from users");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
	
//	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
//		Connection c = null;
//		PreparedStatement ps = null;
//		
//		try {
//			c = dataSource.getConnection();
//			
//			ps = stmt.makePreparedStatement(c);
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			throw e;
//		} finally {
//			if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
//			if (c != null) { try { c.close(); } catch (SQLException e) {} }
//		}
//	}
	
}
