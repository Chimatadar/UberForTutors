package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import DataContracts.RecoCategoryDataContract;
import DataServices.RecommendationDataServices;
import Model.SkillsModel;

public class RecoController {
	public ArrayList<SkillsModel> RecoSkills(int userId){
			try{
			RecommendationDataServices recommendationDataServices=new RecommendationDataServices();
			
			//find category in which he has learnt maximum skills
			ArrayList<RecoCategoryDataContract> skillsList=recommendationDataServices.getSkillsAndCategories(userId);
			ArrayList<RecoCategoryDataContract> skillsWithCategory=new ArrayList<RecoCategoryDataContract>();
			if(skillsList!=null)
			{
				TreeMap<Integer, Integer> category=new TreeMap<Integer, Integer>();
				for(RecoCategoryDataContract skill:skillsList){
				if(category.containsKey(skill.categoryId))
				{
					int count=category.get(skill.categoryId);
					count++;
					category.put(skill.categoryId, count);
				}
				else {
					category.put(skill.categoryId, 1);
				}
				}
				
				int highestCategoryId=category.firstKey();
				
				
				for(RecoCategoryDataContract skilllist:skillsList)
				{
					if(skilllist.categoryId==highestCategoryId)
					{
						skillsWithCategory.add(skilllist);
					}
				}
				//System.out.println(skillsWithCategory.size());//1
				
				//Get the characteristics weight
				
				ArrayList<Integer> characteristicsList=new ArrayList<Integer>();
				
				for(RecoCategoryDataContract skillWithCategory:skillsWithCategory){
				characteristicsList.addAll(recommendationDataServices.getCharacteristicsBySkill(skillWithCategory.skillId));
				}
				//System.out.println(characteristicsList.size()); //3
				
				HashMap<Integer, Integer> characteristicsWieght=new HashMap<Integer,Integer>();
				for(Integer chInteger:characteristicsList)
				{
					if(characteristicsWieght.containsKey(chInteger))
					{
						int count=characteristicsWieght.get(chInteger);
						count++;
						characteristicsWieght.put(chInteger, count);
					}
					else {
						characteristicsWieght.put(chInteger, 1);
					}
				}
				
				Set<Entry<Integer, Integer>> set = characteristicsWieght.entrySet();
		        List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(set);
		        Collections.sort( list, new Comparator<Map.Entry<Integer, Integer>>()
		        {
		            public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 )
		            {
		                return (o2.getValue()).compareTo( o1.getValue() );
		            }
		        } );
		        
		       /* for(Map.Entry<Integer, Integer> entry:list){
		        	System.out.println(entry.getKey()+" "+entry.getValue());
		        }*/
		        
		        
		        TreeMap<Integer, Integer> weightedCharacteristics=new TreeMap<Integer, Integer>();
		        for(Map.Entry<Integer, Integer> entry:list){
		        	int weight=(int) Math.pow(10, entry.getValue());
		        	weightedCharacteristics.put(entry.getKey(), weight);
		            //System.out.println(weightedCharacteristics.get(entry.getKey()));
		        }
		        
		       
		      //Rank the characteristics
		        ArrayList<Integer> removeSkills=new ArrayList<Integer>();
		        for(RecoCategoryDataContract skillList:skillsList)
		        {
		        	removeSkills.add(skillList.skillId);
		        }
		        
		        ArrayList<Integer> skills=recommendationDataServices.getAllSkillsByCategory(highestCategoryId);
		        if(skills!=null)
		        {
		        	skills.removeAll(removeSkills);
		        	
		        	
		        	
		        	HashMap<Integer, Integer> rankSkills=new HashMap<Integer, Integer>();
			        for(Integer skill:skills)
			        {
			        	int sum=0;
			        	ArrayList<Integer> chaArrayList=recommendationDataServices.getCharacteristicsBySkill(skill);
			        	if(chaArrayList!=null){
			        	for(Integer charcterisitic: chaArrayList)
			        	{
			        		if(weightedCharacteristics.containsKey(charcterisitic))
			        		{
			        			sum+=weightedCharacteristics.get(charcterisitic);
			        		}
			        	}
			        	}
			        	else {
							sum=0;
						}
			        	rankSkills.put(skill, sum);
			        	System.out.println(skill+" "+sum);
			        	
			        }
			        
			        Set<Entry<Integer, Integer>> set1 = rankSkills.entrySet();
			        List<Entry<Integer, Integer>> list1 = new ArrayList<Entry<Integer, Integer>>(set1);
			        Collections.sort( list1, new Comparator<Map.Entry<Integer, Integer>>()
			        {
			            public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 )
			            {
			            	
			                return (o2.getValue()).compareTo( o1.getValue() );
			            }
			        } );
			        
			        
			        ArrayList<Integer> recommendedSkillIds=new ArrayList<Integer>();
			        for(Map.Entry<Integer, Integer> entry:list1){
			        	recommendedSkillIds.add(entry.getKey());
			        	//System.out.println(entry.getKey()+" "+entry.getValue());
			        }
			        
			        ArrayList<SkillsModel> recommendedSkills=new ArrayList<SkillsModel>();
			        for (Integer recoSkillId : recommendedSkillIds) {
						SkillsModel recommendedSkill=new SkillsModel();
						recommendedSkill.SkillName=recommendationDataServices.getSkillNameById(recoSkillId);
						recommendedSkill.SkillId=recoSkillId;
						recommendedSkills.add(recommendedSkill);
					}
			        
			        return recommendedSkills;
			        
		        }
		        
		        
		        		
		        
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}
}