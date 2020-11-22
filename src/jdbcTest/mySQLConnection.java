package jdbcTest;

import java.sql.*;
import javax.swing.*;

public class mySQLConnection {
	static Connection conn = null;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static Connection dbConnector() {

		try {
			
			
			String enderecoBD = "jdbc:mysql://127.0.0.1:3306/lp3_semestre2020.2";
			String usuario = "studant";
			String senha = "12345";
			conn = DriverManager.getConnection(enderecoBD + "?useTimezone=true&serverTimezone=UTC", usuario,
					senha);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

	}

}
