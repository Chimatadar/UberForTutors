package Controller;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataContracts.RecoCategoryDataContract;
import DataServices.RecommendationDataServices;
import Model.SkillsModel;

/**
 * Servlet implementation class RecommendationController
 */
@WebServlet("/RecommendationController")
public class RecommendationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int userId=1;
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
				
				
				//Get the characteristics weight
				
				ArrayList<Integer> characteristicsList=new ArrayList<Integer>();
				
				for(RecoCategoryDataContract skillWithCategory:skillsWithCategory){
				characteristicsList.addAll(recommendationDataServices.getCharacteristicsBySkill(skillWithCategory.skillId));
				}
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
		        
		        int weight=10;
		        TreeMap<Integer, Integer> weightedCharacteristics=new TreeMap<Integer, Integer>();
		        for(Map.Entry<Integer, Integer> entry:list){
		        	
		        	weightedCharacteristics.put(entry.getKey(), weight);
		        	weight--;
		            //System.out.println(entry.getKey()+" ==> "+entry.getValue());
		        }
		        
		      //Rank the characteristics
		        ArrayList<Integer> removeSkills=new ArrayList<Integer>();
		        for(RecoCategoryDataContract skillList:skillsList)
		        {
		        	removeSkills.add(skillList.skillId);
		        }
		        
		        ArrayList<Integer> skills=recommendationDataServices.getAllSkills();
		        if(skills!=null)
		        {
		        	skills.removeAll(removeSkills);
		        	HashMap<Integer, Integer> rankSkills=new HashMap<Integer, Integer>();
			        for(Integer skill:skills)
			        {
			        	int sum=0;
			        	ArrayList<Integer> chaArrayList=recommendationDataServices.getCharacteristicsBySkill(skill);
			        	for(Integer charcterisitic: chaArrayList)
			        	{
			        		if(weightedCharacteristics.containsKey(charcterisitic))
			        		{
			        			sum+=weightedCharacteristics.get(charcterisitic);
			        		}
			        	}
			        	rankSkills.put(skill, sum);
			        	
			        	
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
			        
			        for(Map.Entry<Integer, Integer> entry:list1){
			        	
			            System.out.println(entry.getKey());
			        }
		        }
		        
		        
		        		
		        
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
