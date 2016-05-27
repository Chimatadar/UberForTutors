package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.apache.catalina.User;
import org.apache.jasper.tagplugins.jstl.core.Catch;

import Model.SignupUserDetails;

public class SignupDataServices {
	
	public int signupUser(String email, String password, String location, List<String> languageList ) throws SQLException{
		
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","rax123");
						
			query = "select email from user where email=?";
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setString(1, email);
			resultSet = preparedStmt1.executeQuery();
			if(resultSet.next()){
				return 1;
			}
			
			query = "insert into user (Password, Location, Email) values (?, ?, ?)";
			PreparedStatement preparedStmt2 = connection.prepareStatement(query);
			preparedStmt2.setString(1, password);
			preparedStmt2.setString(2, location);
			preparedStmt2.setString(3, email);
			preparedStmt2.executeUpdate();
			
			for(int i=0; i< languageList.size(); i++){
				
				int languageId=-1, userId=-1;
				
				query = "select LanguageId from language where Language=?";
				PreparedStatement preparedStmt5 = connection.prepareStatement(query);
				preparedStmt5.setString(1, languageList.get(i));
				resultSet = preparedStmt5.executeQuery();
				if(resultSet.next()){
					languageId=resultSet.getInt(1);
				}
				
				query = "select UserId from user where Email=?";
				PreparedStatement preparedStmt6 = connection.prepareStatement(query);
				preparedStmt6.setString(1, email);
				resultSet = preparedStmt6.executeQuery();
				if(resultSet.next()){
					userId=resultSet.getInt(1);
				}
				
				query = "insert into userlanguage (LanguageId, UserId) values (?, ?)";
				PreparedStatement preparedStmt7 = connection.prepareStatement(query);
				preparedStmt7.setInt(1, languageId);
				preparedStmt7.setInt(2, userId);
				preparedStmt7.executeUpdate();
				
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return 1;
		}
		
		return 0;
	}
}
