package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Model.UserLanguageModel;
import Model.UserModel;
import Model.UserSkillRatingsModel;

import com.mysql.jdbc.PreparedStatement;

public class RankingDataServices {

	public static ArrayList<UserSkillRatingsModel> getUserSkillRatingsBySkillId(int skillId) {
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			query="select * from userskillratings where SkillId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, skillId);
			
			resultSet = preparedStmt.executeQuery();
			ArrayList<UserSkillRatingsModel> userSkillsRatings=new ArrayList<UserSkillRatingsModel>();
			if(resultSet.next()){
				resultSet.beforeFirst();
			while(resultSet.next())
			{
				UserSkillRatingsModel userSkillRating=new UserSkillRatingsModel();
				userSkillRating.Id=resultSet.getInt("Id");
				userSkillRating.UserId=resultSet.getInt("UserId");
				userSkillRating.RatingId=resultSet.getInt("RatingId");
				userSkillRating.SkillId=skillId;
				userSkillsRatings.add(userSkillRating);
			}
			}
			else 
				return null;
			
			return userSkillsRatings;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static ArrayList<UserSkillRatingsModel> getUserSkillRatingsByUserId(int userId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			query="select * from userskillratings where UserId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, userId);
			
			resultSet = preparedStmt.executeQuery();
			ArrayList<UserSkillRatingsModel> userSkillsRatings=new ArrayList<UserSkillRatingsModel>();
			if(resultSet.next()){
				resultSet.beforeFirst();
			while(resultSet.next())
			{
				UserSkillRatingsModel userSkillRating=new UserSkillRatingsModel();
				userSkillRating.Id=resultSet.getInt("Id");
				userSkillRating.SkillId=resultSet.getInt("SkillId");
				userSkillRating.RatingId=resultSet.getInt("RatingId");
				userSkillRating.UserId=userId;
				userSkillsRatings.add(userSkillRating);
			}
			}
			else 
				return null;
			
			return userSkillsRatings;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static ArrayList<UserLanguageModel> getUserLanguage(int userId) {
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			query="select * from userlanguage where UserId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, userId);
			
			resultSet = preparedStmt.executeQuery();
			ArrayList<UserLanguageModel> userLanguages=new ArrayList<UserLanguageModel>();
			if(resultSet.next()){
				resultSet.beforeFirst();
			while(resultSet.next())
			{
				UserLanguageModel userlanguage=new UserLanguageModel();
				userlanguage.Id=resultSet.getInt("Id");
				userlanguage.LanguageId=resultSet.getInt("LanguageId");
				userlanguage.UserId=userId;
				userLanguages.add(userlanguage);
			}
			}
			else 
				return null;
			
			return userLanguages;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public String getUserById(int userID) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			
			query="select * from user where UserId=?";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStmt1.setInt(1, userID);
			resultSet = preparedStmt1.executeQuery();
			if(resultSet.next())
			{
				return resultSet.getString("UserName");
			}
			
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("user");
			return null;
		}
	}
	
	public String getUserEmailById(int userID) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			
			query="select * from user where UserId=?";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStmt1.setInt(1, userID);
			resultSet = preparedStmt1.executeQuery();
			if(resultSet.next())
			{
				return resultSet.getString("Email");
			}
			
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("user");
			return null;
		}
	}

}