package DataServices;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Model.SkillsModel;
import Model.UserModel;

public class HomeDataServices {
	
	public ArrayList<String> allSkills() {
		Connection connection=null;
		String query=null;
		ResultSet resultSet=null;
		ArrayList<String> skillList = new ArrayList<String>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//to be changed
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ubt","root","myPassword");
			query="select * from skills";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			resultSet = preparedStmt1.executeQuery();
			
			
			
			while(resultSet.next())
			{
				//skillsModel.SkillId=resultSet.getInt("SkillId");
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

	
	public ArrayList<String> searchSkills(String searchSkill) {
		Connection connection=null;
		String query=null;
		ResultSet resultSet=null;
		ArrayList<String> skillList = new ArrayList<String>();
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//to be changed
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ubt","root","myPassword");
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

}
