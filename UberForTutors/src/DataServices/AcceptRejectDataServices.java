package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public class AcceptRejectDataServices {

	public void updateActivity(int ActivityId, int AcceptReject ){
		
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
				
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");
			query="update activity set Status=? where ActicityId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, AcceptReject);
			preparedStmt.setInt(2, ActivityId);
		
			int id = preparedStmt.executeUpdate();
			if(id<0)
				System.out.println("addition Unsuccesful");
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
	}
}
 