package DataServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.UserModel;

import com.mysql.jdbc.PreparedStatement;

public class FriendsDataServices {

	public ArrayList<Integer> getFriends(int userId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		ArrayList<Integer> friends=new ArrayList<Integer>();
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");

			query="select * from userfriends where UserId=?";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);

			preparedStmt1.setInt(1, userId);
			resultSet = preparedStmt1.executeQuery();
			while(resultSet.next())
			{
				friends.add(resultSet.getInt("FriendId"));
			}

			return friends;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return friends;
		}
	}

	public UserModel getTutorsByFriends(Integer friendId, int skillId) {
		Connection connection=null;
		ResultSet resultSet=null;
		String query=null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/uftdb2","root","admin");

			query="select user.UserId,user.UserName,user.Email,user.Points "
					+ "from activity "
					+ "Inner Join user "
					+ "on activity.ToUser=user.UserId "
					+ "where FromUser=? and SkillId=?";
			PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);

			preparedStmt1.setInt(1, friendId);
			preparedStmt1.setInt(2, skillId);
			resultSet = preparedStmt1.executeQuery();
			if(resultSet.next())
			{
				UserModel userModel=new UserModel();
				userModel.UserId=resultSet.getInt("UserId");
				userModel.UserName=resultSet.getString("UserName");
				userModel.Email=resultSet.getString("Email");
				return userModel;
			}

			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

}
