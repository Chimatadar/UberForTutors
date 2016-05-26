package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

import DataContracts.RecoCategoryDataContract;

public class RecommendationDataServices {

	public ArrayList<RecoCategoryDataContract> getSkillsAndCategories(int userId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/UFTdb");
			
			connection=ds.getConnection();
			query="select userskillratings.UserId,userskillratings.SkillId,userskillratings.RatingId,skills.SkillName,skills.CategoryId "
					+ "from userskillratings "
					+ "Inner Join skills "
					+ "on userskillratings.SkillId=skills.SkillId "
					+ "where UserId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, userId);
			
			resultSet = preparedStmt.executeQuery();
			ArrayList<RecoCategoryDataContract> reContracts=new ArrayList<RecoCategoryDataContract>();
			if(resultSet.next()){
				resultSet.beforeFirst();
			while(resultSet.next())
			{
				RecoCategoryDataContract reContract=new RecoCategoryDataContract();
				reContract.userId=resultSet.getInt("UserId");
				reContract.skillId=resultSet.getInt("SkillId");
				reContract.ratingId=resultSet.getInt("RatingId");
				reContract.skillName=resultSet.getString("SkillName");
				reContract.categoryId=resultSet.getInt("CategoryId");
				reContracts.add(reContract);
			}
			}
			else 
				return null;
			
			return reContracts;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public ArrayList<Integer> getCharacteristicsBySkill(int skillId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/UFTdb");
			query="select * from skillcharacteristics where SkillId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, skillId);
			
			resultSet = preparedStmt.executeQuery();
			ArrayList<Integer> characteristics=new ArrayList<Integer>();
			if(resultSet.next()){
				resultSet.beforeFirst();
			while(resultSet.next())
			{
				
				characteristics.add(resultSet.getInt("CharacterId"));
			}
			}
			else 
				return null;
			
			return characteristics;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public ArrayList<Integer> getAllSkillsByCategory(int categoryId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		
		try{
			
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/UFTdb");
			query="select * from skills where CategoryId=?";
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, categoryId);
			
			resultSet = preparedStmt.executeQuery();
			ArrayList<Integer> skills=new ArrayList<Integer>();
			if(resultSet.next()){
				resultSet.beforeFirst();
			while(resultSet.next())
			{
				skills.add(resultSet.getInt("SkillId"));
			}
			}
			else 
				return null;
			
			return skills;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
