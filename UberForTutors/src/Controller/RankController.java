package Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import DataServices.RankingDataServices;
import Model.SkillsModel;
import Model.UserLanguageModel;
import Model.UserModel;
import Model.UserSkillRatingsModel;

public class RankController {
public ArrayList<UserModel> RankUsersBySkills(int skillId, int userId){
    //categorise by rating of the skill
	//int skillId = Integer.parseInt(request.getParameter("skillId"));
    ArrayList<UserSkillRatingsModel> userSkillRatings=RankingDataServices.getUserSkillRatingsBySkillId(1);
    
    if(userSkillRatings!=null){
    Collections.sort(userSkillRatings, new Comparator<UserSkillRatingsModel>() {
        public int compare(UserSkillRatingsModel obj1, UserSkillRatingsModel obj2) {
        	Integer ob1=obj1.RatingId;
        	Integer ob2=obj2.RatingId;
            return ob2.compareTo(ob1);
        }
    });
    
    
    ArrayList<UserSkillRatingsModel> topUsers= new ArrayList<UserSkillRatingsModel>(userSkillRatings.subList(0, 5));

    //categorise by rating of the common skill set and language
    ArrayList<UserSkillRatingsModel> userSkillRatingsCurrentUser=RankingDataServices.getUserSkillRatingsByUserId(userId);
    ArrayList<UserLanguageModel> currentUserLanguage=RankingDataServices.getUserLanguage(userId);
    HashMap<Integer, Integer> userRank=new HashMap<Integer, Integer>();
    for(int i=0;i<topUsers.size();i++)
    {
    	ArrayList<UserSkillRatingsModel> userSkillRatingsUser=RankingDataServices.getUserSkillRatingsByUserId(topUsers.get(i).UserId);
    	int count=1;
    	if(userSkillRatingsUser!=null){
    	for(UserSkillRatingsModel userSkill:userSkillRatingsUser)
    	{
    		if(userSkillRatingsCurrentUser.contains(userSkill))
    		{
    			count=count*10;
    			
    		}
    	}
    	}
    	ArrayList<UserLanguageModel> userLanguages=RankingDataServices.getUserLanguage(topUsers.get(i).UserId);
    	if(userLanguages!=null){
    	for(UserLanguageModel userLanguage:userLanguages)
    	{
    		if(currentUserLanguage.contains(userLanguage))
    		{
    			count=count*5;
    			
    		}
    	}
    	}
    	//System.out.println(topUsers.get(i).UserId+" "+count);
    	userRank.put(topUsers.get(i).UserId, count);
    }
    
    Set<Entry<Integer, Integer>> set1 = userRank.entrySet();
    List<Entry<Integer, Integer>> list1 = new ArrayList<Entry<Integer, Integer>>(set1);
    Collections.sort( list1, new Comparator<Map.Entry<Integer, Integer>>()
    {
        public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 )
        {
        	
            return (o2.getValue()).compareTo( o1.getValue() );
        }
    } );
    
    ArrayList<Integer> userIds=new ArrayList<Integer>();
    for(Map.Entry<Integer, Integer> entry:list1){
    	userIds.add(entry.getKey());
    }
    RankingDataServices rankingDataServices=new RankingDataServices();
    ArrayList<UserModel> users=new ArrayList<UserModel>();
    for (Integer userID : userIds) {
    	UserModel user=new UserModel();
    	user.Email=rankingDataServices.getUserById(userID);
		user.UserId=userID;
		users.add(user);
	}
    return users;
}
    return null;
}

}