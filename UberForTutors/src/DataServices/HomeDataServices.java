package DataServices;

import java.util.List;
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

import sun.util.BuddhistCalendar;

import com.mysql.jdbc.PreparedStatement;

import DataContracts.ReqNotificationDataContract;
import Model.ActivityModel;
import Model.CategoriesModel;
import Model.SkillsModel;
import Model.UserModel;
//import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class HomeDataServices {
	
	public ArrayList<CategoriesModel> allCategories() {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		ArrayList<CategoriesModel> categoryList=new ArrayList<CategoriesModel>();
		try{
			

						
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");

			query="select * from categories";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			resultSet = preparedStmt1.executeQuery();
			
			while(resultSet.next())
			{
				CategoriesModel categoriesModel=new CategoriesModel();
				categoriesModel.CategoryName=resultSet.getString("CategoryName");
				categoriesModel.CategoryId=resultSet.getInt("CategoryId");
				categoryList.add(categoriesModel);
				
			}
			return categoryList;
			//return skillsModel;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
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


	
	public ArrayList<String> searchSkills(String searchSkill) {
		ArrayList<String> skillList = new ArrayList<String>();
		System.out.println("in search");
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
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
		ArrayList<ReqNotificationDataContract> requests = new ArrayList<>();
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
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



	public String getSkillName(Integer recommendedSkillId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");
			//query="select FromUser,SkillId,Time from activity where ToUser = ? and Status = 2 ";
			query = "select * from skills where SkillId=?";
			
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStmt1.setInt(1, recommendedSkillId);
			resultSet = preparedStmt1.executeQuery();
			
			if(resultSet.next())
			{
				return resultSet.getString("SkillName");
			}
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
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