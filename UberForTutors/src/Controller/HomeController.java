package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataServices.HomeDataServices;
import DataServices.LoginDataServices;
import Model.SkillsModel;
import Model.UserModel;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
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
        
		String searchSkill = request.getParameter("searchSkill");
        String notification = request.getParameter("notification");
        String logout = request.getParameter("logout");
        
        HomeDataServices homeDataServices=new HomeDataServices();
        SkillsModel skillModel= homeDataServices.allSkills();
        
        if(skillModel != null)
        {
        	RequestDispatcher rs = request.getRequestDispatcher("ProfileController");
            rs.forward(request, response);
        }
        else
        {
        out.println("<p>No Skills</p>");  

        }

        
		
	}

}
