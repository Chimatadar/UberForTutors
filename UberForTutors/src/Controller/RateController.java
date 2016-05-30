package Controller;

import DataServices.RateDataServices;

public class RateController {

	RateDataServices rateDataServices = new RateDataServices();
	
	public void updateTutor(int ToUser,int SkillId, int rating, int ActivityId){
		
		
		rateDataServices.addTutorRate(ToUser, SkillId,rating);
		rateDataServices.updateActivity(ActivityId,rating);
		
	}
	
	public void updateTutee(int FromUser,int SkillId){
		
		rateDataServices.addTuteeLearnt(FromUser, SkillId);
	}
}
