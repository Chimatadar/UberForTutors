package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.xml.internal.ws.message.EmptyMessageImpl;

//import javax.servlet.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




import DataServices.ActivityDataServices;
import DataServices.RankingDataServices;


/**
 * Servlet implementation class ActivityController
 */
@WebServlet("/ActivityController")
public class ActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		Boolean status=false;
		int toUserId = Integer.parseInt(request.getParameter("userId"));
		int fromUserId=(int) session.getAttribute("UserId");
		int skillId=Integer.parseInt(request.getParameter("skillId"));
		String message = request.getParameter("message");
		System.out.println(toUserId+" "+fromUserId+" "+skillId);
		System.out.println("message is:" + message);
		
		ActivityDataServices activityDataServices=new ActivityDataServices();
		RankingDataServices rankingDataServices = new RankingDataServices();
		activityDataServices.addActivity(skillId,toUserId,fromUserId,status,message);
		
	
		String to = rankingDataServices.getUserById(toUserId);

	      String from = "ucicomplaint@gmail.com";
	      final String username = "ucicomplaint@gmail.com";
	      final String password = "@uciComplaint1";

	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      Session mail_session = Session.getInstance(props,new javax.mail.Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(username, password);
	           }
	        });

	      try {
	         Message mail_message = new MimeMessage(mail_session);

	         mail_message.setFrom(new InternetAddress(from));

	         mail_message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         mail_message.setSubject("Request to teach from Uber for Tutors");

	         //mail_message.setText("hello");
	         mail_message.setText(" "+message);
	         
	         Transport.send(mail_message);

	            RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomeController");
	            requestDispatcher.forward(request, response);
				

//	         out.println("Sent message successfully....");
	         

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub'
		doGet(request, response);
		
	}

}
