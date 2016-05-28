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

import DataServices.ProfileDataServices;
import Model.Notifications;
import Model.SkillsLearntWithActivityId;
import Model.SkillsTaught;
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
		int userId = 1;//Integer.parseInt((String)session.getAttribute("UserId"));
		List<SkillsTaught> skillsTaught = new ArrayList<SkillsTaught>();
		List<String> skillsKnown = new ArrayList<String>();
		List<SkillsLearntWithActivityId> skillsLearnt = new ArrayList<SkillsLearntWithActivityId>();
		List<Notifications> notifications = new ArrayList<Notifications>();
		
		ProfileDataServices profileDataServices = new ProfileDataServices();
		
		try {
			
			skillsTaught = profileDataServices.getSkillsTaught(userId); System.out.println(skillsTaught.size());
			skillsKnown = profileDataServices.getSkillsKnown(userId); System.out.println(skillsTaught.size());
			skillsLearnt = profileDataServices.getSkillsLearnt(userId); System.out.println(skillsTaught.size());
			notifications = profileDataServices.getNotifications(userId); System.out.println(skillsTaught.size());
			
			request.setAttribute("skillsTaught", skillsTaught);
			request.setAttribute("skillsKnown", skillsKnown);
			if(request.getAttribute("userId")==null){
				request.setAttribute("skillsLearnt", skillsLearnt);
			}else{
				request.setAttribute("skillsLearnt", new ArrayList<SkillsLearntWithActivityId>());
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