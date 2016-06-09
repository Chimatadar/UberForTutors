package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataContracts.UserModelWithActivity;
import DataServices.ActivityDataServices;
import DataServices.ProfileDataServices;
import Model.Notifications;
import Model.SkillsLearntWithActivityId;
import Model.SkillsModel;
import Model.SkillsTaught;
import Model.UserModel;
import Model.UserSkillRatingsModel;

import java.util.*;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("UserId");
		
		
		List<SkillsTaught> skillsTaught = new ArrayList<SkillsTaught>();
		List<SkillsModel> skillsKnown = new ArrayList<SkillsModel>();
		List<SkillsLearntWithActivityId> skillsLearnt = new ArrayList<SkillsLearntWithActivityId>();
		List<Notifications> notifications = new ArrayList<Notifications>();
		List<UserModelWithActivity> rateTutorsList = new ArrayList<UserModelWithActivity>();
		List<SkillsModel> allSkillsList = new ArrayList<SkillsModel>();
		
		ProfileDataServices profileDataServices = new ProfileDataServices();
		ActivityDataServices activityDataServices = new ActivityDataServices();
		
		try {
			
			if(request.getParameter("knownSkills")!=null){
				String[] knownSkillsArray = request.getParameterValues("knownSkills");
				System.out.println(knownSkillsArray[0]);
				List<String> knownSkillsList=new ArrayList<String>(Arrays.asList(knownSkillsArray));
				activityDataServices.addNewSkill(knownSkillsList, userId);
				System.out.println("i");
			}
			
			if(request.getParameter("userId")==null){
				
				skillsTaught = profileDataServices.getSkillsTaught(userId);
				skillsKnown = profileDataServices.getSkillsKnown(userId);
				skillsLearnt = profileDataServices.getSkillsLearnt(userId);
				notifications = profileDataServices.getNotifications(userId);
				rateTutorsList = profileDataServices.getTutorsList(userId);
				allSkillsList = profileDataServices.getAllSkillsList();
				//System.out.println(allSkillsList.size());
				allSkillsList.removeAll(skillsKnown);
				//System.out.println(allSkillsList.size());
				
			}else{
				userId = Integer.parseInt(request.getParameter("userId"));
				skillsTaught = profileDataServices.getSkillsTaught(userId);
				skillsKnown = profileDataServices.getSkillsKnown(userId);
			}
			
			System.out.println("SkillsTaught "+skillsTaught.size());
			request.setAttribute("skillsTaught", skillsTaught);
			request.setAttribute("skillsKnown", skillsKnown);
			
			if(request.getParameter("userId")==null){
				request.setAttribute("skillsLearnt", skillsLearnt);
				request.setAttribute("rateTutorsList", rateTutorsList);
				request.setAttribute("allSkillsList", allSkillsList);
				
			}else{
				request.setAttribute("skillsLearnt", new ArrayList<SkillsLearntWithActivityId>());
				request.setAttribute("rateTutorsList", new ArrayList<UserModelWithActivity>());
				request.setAttribute("allSkillsList", new ArrayList<SkillsModel>());
			}
			
			request.setAttribute("notifications", notifications);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
            requestDispatcher.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}