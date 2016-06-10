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

import DataContracts.ReqNotificationDataContract;
import DataServices.FriendsDataServices;
import DataServices.HomeDataServices;

/**
 * Servlet implementation class FriendController
 */
@WebServlet("/FriendController")
public class FriendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session=request.getSession();
			int userId=(int) session.getAttribute("UserId");
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
			int friendUserId = Integer.parseInt(request.getParameter("friendUserId"));
			System.out.println(friendUserId);
			FriendsDataServices friendsDataServices=new FriendsDataServices();
			friendsDataServices.addFriend(userId,friendUserId);
			 request.setAttribute("userId", userId);
			 RequestDispatcher rs3=request.getRequestDispatcher("ProfileController");
		        rs3.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
