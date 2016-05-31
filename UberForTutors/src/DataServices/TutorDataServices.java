package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Model.UserModel;
import Model.UserSkillRatingsModel;

public class TutorDataServices {
	
	public ArrayList<UserModel> searchTutors(String searchTutor){
		ArrayList<UserModel> tutorList = new ArrayList<UserModel>();
		System.out.println("in search of tutors");
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			query="select UserId from user where Email like '%"+searchTutor+"%'";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			resultSet = preparedStmt1.executeQuery();
						
			
			while(resultSet.next())
			{
				UserModel userModel = new UserModel();
				userModel.UserId = resultSet.getInt("UserId");
				userModel.Email = searchTutor;
				tutorList.add(userModel);
								
			}
			
			//return skillsModel;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
//			return null;
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tutorList;
	
	}

	public UserSkillRatingsModel getUserSkillRating(int userId, int skillId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			query="select * from userskillratings where SkillId=? and UserId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, skillId);
			preparedStmt.setInt(2, userId);
			
			resultSet = preparedStmt.executeQuery();
			
			if(resultSet.next()){
				
			
				UserSkillRatingsModel userSkillRating=new UserSkillRatingsModel();
				userSkillRating.UserId=resultSet.getInt("UserId");
				userSkillRating.RatingId=resultSet.getInt("RatingId");
				userSkillRating.SkillId=skillId;
				return userSkillRating;
			
			}
			else 
				return null;
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
		
	}

	public ArrayList<UserModel> getActivityForUserSkill(int skillId, int userId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		ArrayList<UserModel> users=new ArrayList<UserModel>();
		try{
			
			System.out.println(userId+" "+skillId);
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			query="select * from activity where FromUser=? and SkillId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, userId);
			preparedStmt.setInt(2, skillId);
			
			resultSet = preparedStmt.executeQuery();
			
			if(resultSet.next()){
				resultSet.beforeFirst();
				
			while(resultSet.next()){
				UserModel user=new UserModel();
				user.UserId=resultSet.getInt("ToUser");
				user.Email="rhaji@uci.edu";
				users.add(user);
				System.out.println("sdfjkdfj");
			}
			
		}
			return users;
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return users;
		}
	}
}



