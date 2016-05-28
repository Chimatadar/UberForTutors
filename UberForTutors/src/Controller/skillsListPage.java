package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataServices.SkillsListPageDataServices;
import Model.SkillsModel;

/**
 * Servlet implementation class skillsListPage
 */
@WebServlet("/skillsListPage")
public class skillsListPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public skillsListPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		List<SkillsModel> skillsList = new ArrayList<SkillsModel>();
		SkillsListPageDataServices skillsListPageDataServices = new SkillsListPageDataServices();
		
		if(request.getParameter("fromProfilePage")!=null){
			try {
				skillsList = skillsListPageDataServices.getAllSkills();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(request.getParameter("categoryId")!=null){
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			System.out.println(categoryId);
			try {
				skillsList = skillsListPageDataServices.getAllSkillsByCategoryId(categoryId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(request.getParameter("searchSkill")!=null){
			String searchSkill = (String)request.getParameter("searchSkill");
			try {
				skillsList = skillsListPageDataServices.getAllSkillsBySearch(searchSkill);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("skillsList", skillsList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("skills.jsp");
        requestDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
