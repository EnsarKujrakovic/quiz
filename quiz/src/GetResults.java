

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.QuizDao;
import test1.Result;
import test1.User;
import test1.UserDao;
import test1.UserService;

/**
 * Servlet implementation class GetResults
 */
@WebServlet("/admin/getresults")
public class GetResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao ud =  new UserDao();
		QuizDao qd = new QuizDao();
		UserService uService = new UserService(ud);
		List<Result> results = new ArrayList<Result>();
		if(uService.isSuperuser((long)request.getSession().getAttribute("userId")))
			results = qd.findAllResults();
		else
			results = qd.findResultsById((long)request.getSession().getAttribute("userId"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<table style=\"max-width:600px\" id=resultsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">");
		out.println("<tr>");
		out.println("<th class=\"mdl-data-table__cell--non-numeric\">Date</th>");
		out.println("<th class=\"mdl-data-table__cell--numeric\">Quiz info</th>");
		out.println("<th class=\"mdl-data-table__cell--non-numeric\">User info</th>");
		out.println("<th class=\"mdl-data-table__cell--non-numeric\">Percentage</th>");
		out.println("</tr>");
		for (int i = results.size()-1; i >= 0; --i) {
				double res = Math.round((double)results.get(i).getNumOfCorrect()/results.get(i).getNumOfQuestions()*100);
				
				out.println("<tr>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" + results.get(i).getDate() + "</td>");
				out.println("<td class=\"mdl-data-table__cell--numeric\">" + results.get(i).getTitle()+"<br>"+ results.get(i).getNumOfQuestions() + " Questions</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" + results.get(i).getFirstName()+" "+results.get(i).getLastName()+"<br>"+  results.get(i).getEmail() + "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" + res+ "%</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+results.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.resultDetails(this.id)\" type=\"submit\" value=\"Details\" /></td>");
				//
				out.println("</tr>");
		}
		out.println("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
