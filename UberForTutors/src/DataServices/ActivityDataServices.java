package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class ActivityDataServices {

	public void addActivity(int skillId, int toUserId, int fromUserId, Boolean status , String message) {
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			query="Insert into activity(FromUser,ToUser,SkillId,Status,Message,IsDeleted) Values(?,?,?,?,?,?)";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, fromUserId);
			preparedStmt.setInt(2, toUserId);
			preparedStmt.setInt(3, skillId);
			preparedStmt.setBoolean(4, status);
			preparedStmt.setString(5, message);
			preparedStmt.setInt(6, 0);
			
			int id = preparedStmt.executeUpdate();
			if(id<0)
				System.out.println("addition Unsuccesful");
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void addNewSkill(List<String> knownSkillsList, int userId) {
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			
			for(int i=0; i< knownSkillsList.size(); i++){
				
				query="select SkillId from Skills where SkillName=?";
				PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt1.setString(1, knownSkillsList.get(i).trim());
				resultSet = preparedStmt1.executeQuery();
				
				
				if(resultSet.next()){
					int skillId = resultSet.getInt("SkillId");
					System.out.println(skillId);
					query="Insert into UserSkillRatings(UserId,SkillId,RatingId,Taught,TotalPeople) Values(?,?,1,0,0)";
					PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
					preparedStmt.setInt(1, userId);
					preparedStmt.setInt(2, skillId);
					preparedStmt.executeUpdate();
				}
				
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	

}
