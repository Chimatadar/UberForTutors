package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataContracts.TutorDataContract;
import DataServices.HomeDataServices;
import DataServices.RankingDataServices;
import DataServices.TutorDataServices;
import Model.SkillsModel;
import Model.UserModel;
import Model.UserSkillRatingsModel;

/**
 * Servlet implementation class TutorController
 */
@WebServlet("/TutorController")
public class TutorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TutorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entered");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		int SkillId = Integer.parseInt(request.getParameter("sid"));
		HttpSession session = request.getSession();
		int UserId = (int)session.getAttribute("UserId");
		String searchTutor = request.getParameter("searchTutor");
		//Learn logic to be handled in jsp
		// String Learn =Integer.parseInt(request.getParameter("Learn"));
		RankController rankController = new RankController();
		RankingDataServices rankingDataServices=new RankingDataServices();
		HomeDataServices homeDataServices=new HomeDataServices();

		TutorDataServices tutorDataServices=new TutorDataServices();
		if(searchTutor!=null){

			ArrayList<UserModel> tutorList = tutorDataServices.searchTutors(searchTutor);

			ArrayList<TutorDataContract> searchTutorDataContracts=new ArrayList<TutorDataContract>();

			for(UserModel tutModel:tutorList)
			{
				TutorDataContract tutorDataContract=new TutorDataContract();
				tutorDataContract.RatingId= tutorDataServices.getUserSkillRating(tutModel.UserId,SkillId).RatingId;
				tutorDataContract.SkillId=SkillId;
				tutorDataContract.UserId=tutModel.UserId;
				tutorDataContract.Email=tutModel.Email;
				tutorDataContract.SkillName=homeDataServices.getSkillName(SkillId);
				searchTutorDataContracts.add(tutorDataContract);
			}


			request.setAttribute("searchTutorDataContracts", searchTutorDataContracts);
			RequestDispatcher rs1=request.getRequestDispatcher("tutors.jsp");

			rs1.include(request, response);
			return;
		}


		//change functions
		
		ArrayList<UserModel> rankedTutors= rankController.RankUsersBySkills(SkillId,UserId);//rankedTutors
		System.out.println(rankedTutors.size());
		if(rankedTutors!=null){
		ArrayList<UserModel> alreadyReqRaised=tutorDataServices.getActivityForUserSkill(SkillId,UserId);
		System.out.println(alreadyReqRaised.size());
		ArrayList<UserModel> rt=new ArrayList<UserModel>();
		for(UserModel alreadyRaised:alreadyReqRaised)
		{
			for(UserModel ranktut:rankedTutors)
			{
				if(ranktut.UserId!=alreadyRaised.UserId)
				{
					rt.add(ranktut);
				}
			}
		}
		if(rt.size()>0)
		{
			rankedTutors=rt;
		}
		
		//rankedTutors.remove(alreadyReqRaised);
		//System.out.println(rankedTutors.size());
		ArrayList<TutorDataContract> tutorDataContracts=new ArrayList<TutorDataContract>();

		for(UserModel tutModel:rankedTutors)
		{
			TutorDataContract tutorDataContract=new TutorDataContract();
			tutorDataContract.RatingId= tutorDataServices.getUserSkillRating(tutModel.UserId,SkillId).RatingId;
			tutorDataContract.SkillId=SkillId;
			tutorDataContract.UserId=tutModel.UserId;
			tutorDataContract.Email=rankingDataServices.getUserById(tutModel.UserId);
			tutorDataContract.SkillName=homeDataServices.getSkillName(SkillId);
			tutorDataContracts.add(tutorDataContract);
		}
		request.setAttribute("userSkillRatings", tutorDataContracts);
		}
		else{
			request.setAttribute("userSkillRatings", null);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("tutors.jsp");

		requestDispatcher.include(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub



		//for each tutor...if learn is clicked go to visit page..forward to request controller


	}

}
