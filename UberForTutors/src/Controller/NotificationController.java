package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataContracts.ReqNotificationDataContract;
import DataServices.HomeDataServices;

/**
 * Servlet implementation class NotificationController
 */
@WebServlet("/NotificationController")
public class NotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationController() {
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
		try {
			HttpSession session=request.getSession();
			int userId=(int) session.getAttribute("UserId");
			HomeDataServices homeDataServices=new HomeDataServices();
			ArrayList<ReqNotificationDataContract> activities = homeDataServices.getActivity(userId);
			
			ArrayList<ReqNotificationDataContract> requested=new ArrayList<ReqNotificationDataContract>();//00
			ArrayList<ReqNotificationDataContract> unrated=new ArrayList<ReqNotificationDataContract>();//10 21 rejected 11 rated
			ArrayList<ReqNotificationDataContract> rated=new ArrayList<ReqNotificationDataContract>();
			ArrayList<ReqNotificationDataContract> rejected=new ArrayList<ReqNotificationDataContract>();
			
			ArrayList<ReqNotificationDataContract> finalReqNotification=new ArrayList<ReqNotificationDataContract>();
			
			for(ReqNotificationDataContract activity:activities)
			{
				if(activity.IsDeleted==0 && activity.Status==0)
					requested.add(activity);
				else if(activity.IsDeleted==0 && activity.Status==1)
					unrated.add(activity);
				if(activity.IsDeleted==1 && activity.Status==1)
					rated.add(activity);
				if(activity.IsDeleted==1 && activity.Status==2)
					rejected.add(activity);
			}
			finalReqNotification.addAll(requested);
			finalReqNotification.addAll(unrated);
			finalReqNotification.addAll(rated);
			finalReqNotification.addAll(rejected);
			request.setAttribute("activityList", finalReqNotification);
			 RequestDispatcher rs3=request.getRequestDispatcher("home.jsp");
		     rs3.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
