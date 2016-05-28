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

import DataServices.HomeDataServices;
import DataServices.TutorDataServices;
import Model.SkillsModel;
import Model.UserModel;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int SkillId = Integer.parseInt(request.getParameter("SkillId"));
        HttpSession session = request.getSession();
        int UserId = (int)session.getAttribute("UserId");
        String searchTutor = request.getParameter("searchTutor");
        //Learn logic to be handled in jsp
       // String Learn =Integer.parseInt(request.getParameter("Learn"));
        RankController rankController = new RankController();
        
        TutorDataServices tutorDataServices=new TutorDataServices();
        if(searchTutor!=null){
        
        	ArrayList<UserModel> tutorList = tutorDataServices.searchTutors(searchTutor);
        
        	request.setAttribute("tutorList", tutorList);
        	RequestDispatcher rs1=request.getRequestDispatcher("profile2Controller");
            
        	rs1.include(request, response);
        	return;
        
        
        }
        
        
        //change functions
        ArrayList<UserModel> rankedTutors= rankController.RankUsersBySkills(SkillId,UserId);
        request.setAttribute("rankedTutors", rankedTutors);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("tutors.jsp");
        
        
        
        
        //for each tutor...if learn is clicked go to visit page..forward to request controller
        
       
	}

}
