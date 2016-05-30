package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.apache.catalina.User;
import org.apache.jasper.tagplugins.jstl.core.Catch;

import Model.SkillsModel;
import Model.SkillsTaught;

public class SkillsListPageDataServices {

	public List<SkillsModel> getAllSkills() throws SQLException{

		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;

		try{

			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");

			List<SkillsModel> skillsList = new ArrayList<SkillsModel>();

			query = "select * from skills";

			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			resultSet = preparedStmt1.executeQuery();

			while(resultSet.next()){

				SkillsModel skillsListRow = new SkillsModel();
				skillsListRow.SkillId = resultSet.getInt(1);
				skillsListRow.SkillName = resultSet.getString(2);
				skillsList.add(skillsListRow);

			}

			return skillsList;

		}catch(Exception ex){

			ex.printStackTrace();
			return new ArrayList<SkillsModel>();

		}finally {

			connection.close();

		}
	}

	public List<SkillsModel> getAllSkillsByCategoryId(int categoryId) throws SQLException{

		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");

			List<SkillsModel> skillsList = new ArrayList<SkillsModel>();

			query = "select * from skills where CategoryId=?";

			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			preparedStmt1.setInt(1, categoryId);
			resultSet = preparedStmt1.executeQuery();

			while(resultSet.next()){

				SkillsModel skillsListRow = new SkillsModel();
				skillsListRow.SkillId = resultSet.getInt(1);
				skillsListRow.SkillName = resultSet.getString(2);
				skillsList.add(skillsListRow);

			}

			return skillsList;

		}catch(Exception ex){

			ex.printStackTrace();
			return new ArrayList<SkillsModel>();

		}finally {

			connection.close();

		}
	}

	public List<SkillsModel> getAllSkillsBySearch(String searchSkill) throws SQLException{

		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;

		try{

			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb","root","admin");

			List<SkillsModel> skillsList = new ArrayList<SkillsModel>();

			query = "select * from skills where SkillName like '%"+searchSkill+"%'";

			PreparedStatement preparedStmt1 = connection.prepareStatement(query);
			resultSet = preparedStmt1.executeQuery();

			while(resultSet.next()){

				SkillsModel skillsListRow = new SkillsModel();
				skillsListRow.SkillId = resultSet.getInt(1);
				skillsListRow.SkillName = resultSet.getString(2);
				skillsList.add(skillsListRow);

			}

			return skillsList;

		}catch(Exception ex){

			ex.printStackTrace();
			return new ArrayList<SkillsModel>();

		}finally {

			connection.close();

		}
	}
}
