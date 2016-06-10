package Controller;

import java.util.ArrayList;

import DataServices.FriendsDataServices;
import Model.UserModel;

public class FriendRankingController {

	public ArrayList<UserModel> getTutorsByFriends(int skillId, int userId) {
		ArrayList<UserModel> tutors=new ArrayList<UserModel>();
		try {
			FriendsDataServices friendsDataServices=new FriendsDataServices();
			ArrayList<Integer> friendsId=friendsDataServices.getFriends(userId);
			
			for(Integer friendId:friendsId)
			{
				UserModel user=friendsDataServices.getTutorsByFriends(friendId,skillId);
				if(user!=null)
				{
					tutors.add(user);
				}
			}
			return tutors;
		} catch (Exception e) {
			// TODO: handle exception
			return tutors;
		}
	}

}
