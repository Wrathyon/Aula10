package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final ThreadLocal<Connection> conn = new ThreadLocal<>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// Obtém conexão com o banco de dados
	public static Connection obterConexao() throws SQLException {
		if (conn.get() == null) {
			conn.set(DriverManager.getConnection("jdbc:mysql://localhost/exercicio2?user=Antonio&password=Antonio@30"));
		}
		return conn.get();
	}
	public static void fecharConexao() throws SQLException{
			if(conn.get()!=null){
				conn.get().close();
				conn.set(null);				
			}
	}

}
