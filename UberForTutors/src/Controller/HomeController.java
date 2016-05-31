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

import DataContracts.ReqNotificationDataContract;
import DataServices.HomeDataServices;
import DataServices.LoginDataServices;
import Model.ActivityModel;
import Model.CategoriesModel;
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
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
		
        String searchSkill = request.getParameter("searchSkill");
        HomeDataServices homeDataServices=new HomeDataServices();
        if(searchSkill!=null){

        	ArrayList<SkillsModel> skillList = homeDataServices.searchSkills(searchSkill);
        	
        	if(skillList!=null){
        		request.setAttribute("skillsList", skillList);
        	}

        	RequestDispatcher rs1=request.getRequestDispatcher("skills.jsp");

        	rs1.forward(request, response);
        	return;


        }
        
        
        
        //String notification = request.getParameter("notification");
        int UserId = (int) session.getAttribute("UserId");
        //logout is a button if it si true...i.e pressed
        boolean logout = true;
        RecoController recoController=new RecoController();
        
        int count=0;
        count = homeDataServices.getNoOfNotifications(UserId);
        
        
        ArrayList<CategoriesModel> categoryList = homeDataServices.allCategories();
        
        ArrayList<SkillsModel> recommendedSkills= recoController.RecoSkills(UserId);
        		
       /* ArrayList<SkillsModel> skillsModels=new ArrayList<SkillsModel>();
        for(Integer recommendedSkillId:recommendedSkillIds)
        {
        	SkillsModel skillsModel=new SkillsModel();
        	String skillName = homeDataServices.getSkillName(recommendedSkillId);
        	skillsModel.SkillId=recommendedSkillId;
        	skillsModel.SkillName=skillName;
        	skillsModels.add(skillsModel);
        }*/
        
        
        request.setAttribute("recommendedSkills", recommendedSkills);
        
        if(categoryList != null)
        {
        	
        	request.setAttribute("categoryList", categoryList);
        	
            //RequestDispatcher rs=request.getRequestDispatcher("home.jsp");
          
            //rs.forward(request, response);
        	
        	
        }
        else{
        	 out.println("<p>No Skills</p>");  

        }
        
    
        
        //notification
/*        ArrayList<ReqNotificationDataContract> notifications = new ArrayList<>();
        notifications= homeDataServices.checkNotification(UserId);*/
        
        
        /*request.setAttribute("notificationNo", notifications.size());
        request.setAttribute("notifications", notifications);*/
        request.setAttribute("notificationCount", count);
        RequestDispatcher rs3=request.getRequestDispatcher("home.jsp");
       
        rs3.forward(request, response);
        
        
        
		
	}

}