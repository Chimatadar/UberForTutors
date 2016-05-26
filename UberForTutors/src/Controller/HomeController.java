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

import DataContracts.ReqNotificationDataContract;
import DataServices.HomeDataServices;
import DataServices.LoginDataServices;
import Model.ActivityModel;
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
        
		//check the variable for search button
        //this is value for search text
        String searchSkill = request.getParameter("searchSkill");
        //String notification = request.getParameter("notification");
        int UserId = Integer.parseInt(request.getParameter("UserId"));
        //logout is a button if it si true...i.e pressed
        boolean logout = true;
        
        HomeDataServices homeDataServices=new HomeDataServices();
        ArrayList<String> categoryList = homeDataServices.allCategories();
        

        if(categoryList != null)
        {
        	request.setAttribute("categoryList", categoryList);
            RequestDispatcher rs=request.getRequestDispatcher("Home.jsp");
            
            rs.forward(request, response);
        	
        	
        }
        else{
        	 out.println("<p>No Skills</p>");  

        }
        
        ArrayList<String> skillList = homeDataServices.searchSkills(searchSkill);
        
        if(skillList != null)
        {
        	request.setAttribute("categoryList", categoryList);
            RequestDispatcher rs1=request.getRequestDispatcher("Home.jsp");
            
            rs1.forward(request, response);
        	
        }
        else
        {
        	out.println("<p>No Skills</p>");  
        }
        
        if(logout){
        	//to be changed if v change the entry page logic
        	RequestDispatcher rs2 = request.getRequestDispatcher("SignupController");
        	rs2.forward(request, response);
        	
        }
        
        
        //notification
        ArrayList<ReqNotificationDataContract> notifications = new ArrayList<>();
        notifications= homeDataServices.checkNotification(UserId);
        
        request.setAttribute("notificationNo", notifications.size());
        request.setAttribute("notifications", notifications);
        RequestDispatcher rs3=request.getRequestDispatcher("Home.jsp");
        rs3.forward(request, response);
        
        
        
		
	}

}
