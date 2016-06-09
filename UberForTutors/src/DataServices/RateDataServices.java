package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.sun.security.auth.NTDomainPrincipal;

//import Model.SkillsModel;

public class RateDataServices {
	public void addTutorRate(int UserId, int SkillId, int currRating) {
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		String query1=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			query="select RatingId,TotalPeople from userSkillRatings where UserId= ? and SkillId = ?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, UserId);
			preparedStmt.setInt(2, SkillId);
			
			resultSet =preparedStmt.executeQuery();	
			
			if(resultSet.next()){

				int rating = resultSet.getInt("RatingId");
				int people = resultSet.getInt("TotalPeople");
			
				int x = (rating *people) + currRating;
				people++;
			    double y = x/(float)people;
				double z = y-Math.floor(y);
				int newRating;
				
				if(z<=0.5){
					newRating = (int) Math.floor(y);
					System.out.println("IN if -0.5"+newRating);		
				}else{
					newRating = (int) Math.floor(y)+1;
					System.out.println(""+newRating);
				}
				
				 
				query1="update userSkillRatings set RatingId=newRating,TotalPeople=people,Taught=1 where UserId= ? and SkillId = ?";
				PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query1);
				preparedStmt.setInt(1, UserId);
				preparedStmt.setInt(2, SkillId);
				
				int id =preparedStmt1.executeUpdate();
				
				if(id<0)
					System.out.println("update Unsuccesful");
				
			}	
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
		
	}

	public void updateActivity(int activityId, int rating) {
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		String query1=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			query="select Status,IsDeleted from activity where ActivityId= ?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, activityId);
						
			resultSet =preparedStmt.executeQuery();	
			
			if(resultSet.next()){

				int Status = resultSet.getInt("Status");
				int IsDeleted = resultSet.getInt("IsDeleted");
				
				if(Status ==1 && IsDeleted==0){
					
					query1="update userSkillRatings set RatingId=rating,IsDeleted=1 where ActivityId= ?";	
					PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query1);
					preparedStmt.setInt(1, activityId);
										
					int id =preparedStmt1.executeUpdate();
					
					if(id<0)
						System.out.println("update Unsuccesful");
					
				}	
				
			}	
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	public void addTuteeLearnt(int UserId, int SkillId) {
		// TODO Auto-generated method stub
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;

		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			query="update userSkillRatings set Taught=3 where UserId= ? and SkillId = ?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, UserId);
			preparedStmt.setInt(2, SkillId);
			
			int id =preparedStmt.executeUpdate();
			if(id<0)
				System.out.println("update Unsuccesful");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}

}
