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
		String query2=null;
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
				
				
				query1="update userSkillRatings set RatingId=?,TotalPeople=?,Taught=? where UserId= ? and SkillId = ?";
				PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query1);
				preparedStmt1.setInt(1, newRating);
				preparedStmt1.setInt(2, people);
				preparedStmt1.setInt(3, 1);
				preparedStmt1.setInt(4, UserId);
				preparedStmt1.setInt(5, SkillId);
				
				int id =preparedStmt1.executeUpdate();
				
				if(id<0)
					System.out.println("update Unsuccesful");
				 int points=people*10;
				query2="update user set points=? where UserId= ?";
				PreparedStatement preparedStmt2 = (PreparedStatement) connection.prepareStatement(query1);
				preparedStmt2.setInt(1, points);
				preparedStmt2.setInt(2, UserId);
				int id1 =preparedStmt1.executeUpdate();
				
				if(id1<0)
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
			query="update activity set RatingId=?,IsDeleted=? where ActivityId= ?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, rating);
			preparedStmt.setInt(2, 1);
			preparedStmt.setInt(3, activityId);
						
			int id =preparedStmt.executeUpdate();	
			
			if(id<0)
				System.out.println("update Unsuccesful");	
			
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
			query="Insert into userSkillRatings(UserId,SkillId,RatingId,Taught,TotalPeople) values(?,?,?,?,?)";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, UserId);
			preparedStmt.setInt(2, SkillId);
			preparedStmt.setInt(3, 1);
			preparedStmt.setInt(4, 3);
			preparedStmt.setInt(5, 0);
			
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
