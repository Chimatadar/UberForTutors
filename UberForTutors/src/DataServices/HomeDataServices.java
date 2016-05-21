package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Model.ComplaintModel;

public class HomeDataServices {
	
	public void updateComplaints(ComplaintModel complaintModel) {
		Connection connection=null;
		String query=null;
		ResultSet resultSet=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ngss","root","myPassword");
			query="Update complaint Set ID=?,DepartmentID=?,PhotoURL=?,Location=?,Caption=? where ID=?";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStmt1.setString(1,complaintModel.complaintID );
			preparedStmt1.setInt(2,complaintModel.DepartmentId);
			preparedStmt1.setString(3, complaintModel.photoURL);
			preparedStmt1.setString(4, complaintModel.location);
			preparedStmt1.setString(5, complaintModel.caption);
			
			preparedStmt1.setString(6, complaintModel.complaintID);
			
			preparedStmt1.executeUpdate();

			
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
	
	}

}
