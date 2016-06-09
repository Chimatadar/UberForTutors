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

import DataServices.HomeDataServices;
import DataServices.LoginDataServices;
import Model.UserModel;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		//doGet(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        
        LoginDataServices loginDataServices=new LoginDataServices();
        HttpSession session=request.getSession();
        UserModel userModel=loginDataServices.authenticateUser(email,password);
        
        if(userModel!=null){
        if(userModel.UserId>0)
        {
        	        	
             session.setAttribute("UserId", userModel.UserId);
             session.setAttribute("userName", userModel.UserName);
             HomeDataServices homeDataServices=new HomeDataServices();
             int count=0;
             count = homeDataServices.getNoOfNotifications(userModel.UserId);
             session.setAttribute("notificationCount", count);
        	 RequestDispatcher requestDispatcher=request.getRequestDispatcher("HomeController");
             requestDispatcher.forward(request, response);
        }
        }
        else
        {
        	request.setAttribute("LoginFailed", 1);
        	 RequestDispatcher requestDispatcher=request.getRequestDispatcher("Index.jsp");
             requestDispatcher.forward(request, response);
        	 

        }

	}

}