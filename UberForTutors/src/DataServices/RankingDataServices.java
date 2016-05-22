package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.UserLanguageModel;
import Model.UserSkillRatingsModel;

import com.mysql.jdbc.PreparedStatement;

public class RankingDataServices {

	public static ArrayList<UserSkillRatingsModel> getUserSkillRatingsBySkillId(int skillId) {
		// TODO Auto-generated method stub
		Connection connection=null;
		String query=null;
		ResultSet resultSet=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			query="select * from userskillratings where SkillId=? and Taught=1";
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
		String query=null;
		ResultSet resultSet=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
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
		String query=null;
		ResultSet resultSet=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
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

}
