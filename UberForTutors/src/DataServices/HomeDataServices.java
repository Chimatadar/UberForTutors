package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Model.SkillsModel;
import Model.UserModel;

public class HomeDataServices {
	
	public SkillsModel allSkills() {
		Connection connection=null;
		String query=null;
		ResultSet resultSet=null;
		SkillsModel skillsModel = new SkillsModel();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//to be changed
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ubt","root","myPassword");
			query="select * from skills";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
			
			resultSet = preparedStmt1.executeQuery();
			
			
			
			if(resultSet.next())
			{
				//skillsModel.SkillId=resultSet.getInt("SkillId");
				skillsModel.SkillName=resultSet.getString("SkillName");
				//return skillsModel;
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
		return skillsModel;
	
	}

}
