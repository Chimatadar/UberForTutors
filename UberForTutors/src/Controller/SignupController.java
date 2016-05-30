package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataServices.SignupDataServices;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SignupController() {
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
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String location = request.getParameter("address");
        String userName = request.getParameter("userName");
        String[] languageArray = request.getParameterValues("language");
        //String language = request.getParameter("language");
        
        email=email.trim();
        
        List<String> languageList=new ArrayList<String>(Arrays.asList(languageArray));
        SignupDataServices signupDataServices = new SignupDataServices();
        
        
        try {
        	
			int result = signupDataServices.signupUser(email, password, location, userName, languageList);
			
			if(result==1){
				out.println("This Email is already registered");
				response.sendRedirect("signUp.jsp");
			}else{
				out.println("Sign up is successful");
				response.sendRedirect("Index.jsp");
			}
        	
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
	}

}