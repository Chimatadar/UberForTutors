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

import DataContracts.UserModelWithActivity;
import Model.Notifications;
import Model.SkillsLearntWithActivityId;
import Model.SkillsModel;
import Model.SkillsTaught;
import Model.UserSkillRatingsModel;

public class ProfileDataServices {
	public List<SkillsTaught> getSkillsTaught(int userId) throws SQLException{
		
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			List<SkillsTaught> skillsTaught = new ArrayList<SkillsTaught>();
			
			query = "select Skills.SkillName, UserSkillRatings.RatingId from UserSkillRatings "
					+ "inner join Skills on UserSkillRatings.SkillId=Skills.SkillId where UserSkillRatings.UserId=? "
					+ "and UserSkillRatings.taught=?";
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, userId);
			preparedStmt1.setInt(2, 1);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next()){
				SkillsTaught skillsTaughtRow = new SkillsTaught();
				skillsTaughtRow.skill = resultSet.getString(1);
				skillsTaughtRow.rating = resultSet.getInt(2);
				skillsTaught.add(skillsTaughtRow);
			}
			
			return skillsTaught;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<SkillsTaught>();
		}
		finally {
			connection.close();
			
		}
	}
	
	public List<SkillsModel> getSkillsKnown(int userId) throws SQLException{
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			List<SkillsModel> skillsKnown = new ArrayList<SkillsModel>();
			
			query = "select Skills.SkillId, Skills.SkillName, Skills.Image  from UserSkillRatings "
					+ "inner join Skills on UserSkillRatings.SkillId=Skills.SkillId and UserSkillRatings.UserId=?"
					+ " and UserSkillRatings.Taught=? or UserSkillRatings.Taught=?";
					
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, userId);
			preparedStmt1.setInt(2, 0);
			preparedStmt1.setInt(3, 2);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next()){
				SkillsModel skillsKnownRow = new SkillsModel();
				skillsKnownRow.SkillId = resultSet.getInt("SkillId");
				skillsKnownRow.SkillName = resultSet.getString("SkillName");
				skillsKnownRow.image = resultSet.getString("Image");
				skillsKnown.add(skillsKnownRow);
			}
			
			return skillsKnown;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<SkillsModel>();
		}
		finally {
			connection.close();
			
		}
	}
	
	public List<SkillsLearntWithActivityId> getSkillsLearnt(int userId) throws SQLException{
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			List<SkillsLearntWithActivityId> skillsLearnt = new ArrayList<SkillsLearntWithActivityId>();
			
			query = "select user.UserId, activity.ActivityId, skills.SkillName, user.Email  from userskillratings "
					+ "inner join skills on userskillratings.SkillId=skills.SkillId and UserSkillRatings.UserId=? "
					+ "and UserSkillRatings.taught=? inner join Activity on UserSkillRatings.UserId=Activity.FromUser "
					+ "inner join User on Activity.ToUser=User.UserId";
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, userId);
			preparedStmt1.setInt(2, 2);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next()){
				SkillsLearntWithActivityId skillsLearntWithActivityId = new SkillsLearntWithActivityId();
				skillsLearntWithActivityId.tutorId=resultSet.getInt(1);
				skillsLearntWithActivityId.activityId=resultSet.getInt(2);
				skillsLearntWithActivityId.skill=resultSet.getString(3);
				skillsLearntWithActivityId.tutorEmail=resultSet.getString(4);
						
			}
			
			
			return skillsLearnt;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<SkillsLearntWithActivityId>();
		}
		finally {
			connection.close();
			
		}
	}
	
	public List<Notifications> getNotifications(int userId) throws SQLException{
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			List<Notifications> skillsLearnt = new ArrayList<Notifications>();
			
			query = "select user.UserId, Activity.ActivityId, Skills.SkillName, User.Email from UserSkillRatings "
					+ "inner join skills on UserSkillRatings.SkillId=Skills.SkillId and UserSkillRatings.UserId=? "
					+ "and UserSkillRatings.taught=? inner join activity on UserSkillRatings.UserId=Activity.ToUser "
					+ "inner join user on Activity.FromUser=user.UserId";
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, userId);
			preparedStmt1.setInt(2, 2);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next()){
				
				Notifications notifications = new Notifications();
				notifications.userId=resultSet.getInt(1);
				notifications.activityId=resultSet.getInt(2);
				notifications.skill=resultSet.getString(3);
				notifications.tuteeEmail=resultSet.getString(4);
						
			}
			
			
			return skillsLearnt;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<Notifications>();
		}
		finally {
			connection.close();
			
		}
	}
	
	public List<UserModelWithActivity> getTutorsList(int userId) throws SQLException{
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			List<UserModelWithActivity> rateTutorsList = new ArrayList<UserModelWithActivity>();
			
			query = "select user.UserId, user.Email, user.UserName, Activity.ActivityId, Skills.SkillName from Activity "
					+ "inner join User on Activity.FromUser=? and Activity.Status=1 and Activity.IsDeleted=0 inner join skills on"
					+ " Activity.SkillId=Skills.skillId";
					
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, userId);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next()){
				
				UserModelWithActivity userModelWithActivity = new UserModelWithActivity();
				userModelWithActivity.UserId = resultSet.getInt(1);
				userModelWithActivity.Email = resultSet.getString(2);
				userModelWithActivity.UserName = resultSet.getString(3);
				userModelWithActivity.ActivityId = resultSet.getInt(4);
				userModelWithActivity.SkillName = resultSet.getString(5);
						
			}
			
			
			return rateTutorsList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<UserModelWithActivity>();
		}
		finally {
			connection.close();
			
		}
	}
	
	public List<SkillsModel> getAllSkillsList() throws SQLException{
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			List<SkillsModel> allSkillsList = new ArrayList<SkillsModel>();
			
			query = "select SkillId, SkillName, Image from Skills";
					
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next()){
				
				SkillsModel skillsModelRow = new SkillsModel();
				skillsModelRow.SkillId = resultSet.getInt("SkillId");
				skillsModelRow.SkillName = resultSet.getString("SkillName");
				skillsModelRow.image = resultSet.getString("Image");
				allSkillsList.add(skillsModelRow);
						
			}
			
			
			return allSkillsList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<SkillsModel>();
		}
		finally {
			connection.close();
			
		}
	}
}