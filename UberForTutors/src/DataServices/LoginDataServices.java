package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

import Model.UserModel;

public class LoginDataServices {

	public UserModel authenticateUser(String email , String password){


		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;

		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");

			/*	Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/UFTdb");
			connection = ds.getConnection();*/




			query="select * from user where Email=? and Password=?";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);

			preparedStmt1.setString(1, email);
			preparedStmt1.setString(2, password);
			resultSet = preparedStmt1.executeQuery();
			System.out.println(email+" "+password);
			UserModel userModel=new UserModel();
			if(resultSet.next())
			{
				System.out.println("Entered");
				userModel.UserId=resultSet.getInt("UserId");
				userModel.Password=resultSet.getString("Password");
				userModel.UserName=resultSet.getString("UserName");
				userModel.Points=resultSet.getInt("Points");
				return userModel;
			}

			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		/*finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/


	}
}