package DataServices;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;
import javax.management.NotificationBroadcasterSupport;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

import DataContracts.ReqNotificationDataContract;
import Model.ActivityModel;
import Model.SkillsModel;
import Model.UserModel;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class HomeDataServices {
	
	public ArrayList<String> allCategories() {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		ArrayList<String> categoryList=new ArrayList<String>();
		try{
			
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/UFTdb");
			
			query="select * from categories";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			resultSet = preparedStmt1.executeQuery();
			
			
			
			while(resultSet.next())
			{
				categoryList.add(resultSet.getString("CategoryName"));
				
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
		return categoryList;
	
	}


	
	public ArrayList<String> searchSkills(String searchSkill) {
		Connection connection=null;
		String query=null;
		ResultSet resultSet=null;
		ArrayList<String> skillList = new ArrayList<String>();
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//to be changed
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			query="select * from skills where SkillName = ?";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStmt1.setString(1, searchSkill);
			resultSet = preparedStmt1.executeQuery();
			
			
			
			if(resultSet.next())
			{
				skillList.add(resultSet.getString("SkillName"));
								
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
		return skillList;
	
	}
	
	
	
	public ArrayList<ReqNotificationDataContract> checkNotification(int UserId){
	
		Connection connection=null;
		String query=null;
		ResultSet resultSet=null;
		ArrayList<ReqNotificationDataContract> requests = new ArrayList<>();
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//to be changed
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			
			//query="select FromUser,SkillId,Time from activity where ToUser = ? and Status = 2 ";
			query = "select activity.FromUser,activity.SkillId,activity.Time,skills.SkillName,user.Email "
					+ "from activity "
					+ "Inner Join skills"
					+ "on activity.SkillId=skills.SkillId "
					+ "Inner Join user"
					+ "on activity.FromUser=user.UserId"
					+ "where ToUser=? and Status=0 ";
			
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStmt1.setInt(1, UserId);
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next())
			{
				ReqNotificationDataContract requestDataContract = new ReqNotificationDataContract();
				//ActivityModel activityModel=new ActivityModel();
				requestDataContract.FromUser = resultSet.getInt("FromUser");
				requestDataContract.SkillId = resultSet.getInt("SkillId");
				requestDataContract.Time = resultSet.getString("Time");
				requestDataContract.SkillName = resultSet.getString("SkillName");
				requestDataContract.Email = resultSet.getString("Email");
				requests.add(requestDataContract);
		
			}
			
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
		
		
		
		return requests;  
	
	}
	
	
}
