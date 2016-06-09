package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataServices.AcceptRejectDataServices;
import DataServices.HomeDataServices;
import Model.CategoriesModel;
import Model.SkillsModel;

/**
 * Servlet implementation class AcceptRejectController
 */
@WebServlet("/AcceptRejectController")
public class AcceptRejectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptRejectController() {
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
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
	
        int ActivityId = Integer.parseInt(request.getParameter("ActivityId"));
        String Accept = request.getParameter("update");
        String Reject = request.getParameter("remove");
        System.out.println(ActivityId+" "+ Accept+" "+ Reject);
        
        AcceptRejectDataServices acceptRejectDataServices = new AcceptRejectDataServices();
        
        
        int UserId = (int) session.getAttribute("UserId");
        
        if(Accept != null){
        	
        	acceptRejectDataServices.updateActivity(ActivityId, 1);
        	
        }else if(Reject!=null){
        	
        	acceptRejectDataServices.updateActivity(ActivityId, 2);
        }
        RequestDispatcher rs3=request.getRequestDispatcher("NotificationController");
        
        rs3.forward(request, response);
		
	}

}
