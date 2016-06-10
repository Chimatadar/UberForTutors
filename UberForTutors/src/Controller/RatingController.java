package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import Model.SkillsLearntWithActivityId;

/**
 * Servlet implementation class RatingController
 */
@WebServlet("/RatingController")
public class RatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingController() {
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
		// TODO Auto-generated method stub
		String rateString=(String) request.getAttribute("star");
		HttpSession session=request.getSession();
		
		int ToUser=Integer.parseInt(request.getParameter("tutorId"));
		int SkillId=Integer.parseInt(request.getParameter("skillId"));
		int rating= 4;/*Integer.parseInt(request.getParameter("star"));*/
		int ActivityId=Integer.parseInt(request.getParameter("activityId"));
		int FromUser=(int) session.getAttribute("UserId");
		
		RateController rateController=new RateController();
		rateController.updateTutor(ToUser, SkillId, rating, ActivityId);
		rateController.updateTutee(FromUser, SkillId);
//		int ToUser = skillsLearntWithActivityId.tutorId
//		int SkillId = , int rating, int ActivityId
		
		
		
	}

}
