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
import Model.SkillsTaught;
import Model.UserSkillRatingsModel;

public class ProfileDataServices {
	public List<SkillsTaught> getSkillsTaught(int userId) throws SQLException{
		
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			
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
	
	public List<String> getSkillsKnown(int userId) throws SQLException{
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			
			List<String> skillsKnown = new ArrayList<String>();
			
			query = "select Skills.SkillName from UserSkillRatings "
					+ "inner join Skills on UserSkillRatings.SkillId=Skills.SkillId where UserSkillRatings.UserId=?";
					
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, userId);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next()){
				skillsKnown.add(resultSet.getString(1));
			}
			
			return skillsKnown;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<String>();
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
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			
			List<SkillsLearntWithActivityId> skillsLearnt = new ArrayList<SkillsLearntWithActivityId>();
			
			query = "select user.UserId, activity.ActivityId, skills.SkillName, user.Email  from userskillratings "
					+ "inner join skills on userskillratings.SkillId=skills.SkillId and UserSkillRatings.UserId=? "
					+ "and UserSkillRatings.taught=? inner join Activity on UserSkillRatings.UserId=Activity.FromUser "
					+ "inner join User on Activity.ToUser=User.UserId";
			
			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, userId);
			preparedStmt1.setInt(2, 3);
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
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			
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
}