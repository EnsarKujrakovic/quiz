

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.User;
import test1.UserDao;
import test1.Quiz;
import test1.QuizDao;
import test1.UserService;

/**
 * Servlet implementation class Quizes
 */
@WebServlet("/admin/quizes")
public class Quizes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quizes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QuizDao qd =  new QuizDao();
		UserDao ud = new UserDao();
		UserService uService = new UserService(ud);
		List<Quiz> quizes;
		if(uService.isSuperuser((Long)request.getSession().getAttribute("userId"))) {
			quizes = qd.findAll();
		}	
		else {
			quizes = qd.findByUserId((Long)request.getSession().getAttribute("userId"));
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(request.getParameter("removeQuiz").equals("true")) {
			qd.remove(Long.parseLong(request.getParameter("quizId")));
			return;
		}
		out.println("<table style=\"max-width:600px\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">");
		for (int i = 0; i < quizes.size(); ++i) {
			out.println("<tr>");
			out.println("<td class=\"mdl-data-table__cell--numeric\">" + quizes.get(i).getId() + "</td>");
			out.println("<td id=userId"+quizes.get(i).getId()+ " class=\"mdl-data-table__cell--non-numeric\">" + quizes.get(i).getUserId() + "</td>");
			out.println("<td id=quizTitle"+quizes.get(i).getId()+ " class=\"mdl-data-table__cell--non-numeric\">" + quizes.get(i).getTitle() + "</td>");
			out.println("<td id=quizDescription"+quizes.get(i).getId()+ " class=\"mdl-data-table__cell--non-numeric\">" + quizes.get(i).getDescription() + "</td>");
			
			//out.println("<td style=\"width:10px\" class=\"mdl-data-table__cell--non-numeric\"><a onclick=\"moveQuestion('up')\" href=\"javascript:void(0);\">&#8593;</a</td>");
			//out.println("<td class=\"mdl-data-table__cell--non-numeric\"><a onclick=\"moveQuestion('down')\" href=\"javascript:void(0);\">&#8595;</a</td>");
			out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+quizes.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.removeQuiz(this.id)\" type=\"submit\" value=\"Delete\" /></td>");
			out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+quizes.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.editQuiz(this.id)\" type=\"submit\" value=\"Edit\" /></td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<input style=\"width:10%\" id=\"addNewQuiz\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.addQuiz()\" type=\"submit\" value=\"Add new\" />");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
