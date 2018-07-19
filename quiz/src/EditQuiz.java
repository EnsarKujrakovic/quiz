

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.Quiz;
import test1.QuizDao;

/**
 * Servlet implementation class EditQuiz
 */
@WebServlet("/admin/editquiz")
public class EditQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QuizDao qd = new QuizDao();
		PrintWriter out = response.getWriter();
		
		long quizId = Long.parseLong(request.getParameter("quizId"));
		Quiz quiz = qd.findById(quizId);
		out.println("<input style=\"width:10%\" id=\"backToQuizes\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.getQuizes()\" type=\"submit\" value=\"<\" />");
		
		out.println("<div id=\"quizIdDiv\" style=\"display:none\">"+quizId+"</div>");
		out.println("<div id=\"editQuestion\" style=\"display:none\"></div>");
		out.println("<table style=\"max-width:600px\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">");
		out.println("<tr><th colspan=\"6\" class=\"mdl-data-table__cell--non-numeric\"><div>");
		out.println("<h5>" + quiz.getTitle() +"</h6>");
		out.println("<h6>" + quiz.getDescription() + "</h6>");
		out.println("</div></th></tr>");
		for (int i = 0; i < quiz.questions.size(); ++i) {
			int j = i+1;
			out.println("<tr>");
			out.println("<td class=\"mdl-data-table__cell--numeric\">" + j + "</td>");
			out.println("<td id=userId"+quiz.questions.get(i).getId()+ " class=\"mdl-data-table__cell--non-numeric\">" + quiz.questions.get(i).getQuestionText() + "</td>");
			
			out.println("<td class=\"mdl-data-table__cell--non-numeric\"><a style=\"text-decoration:none;font-size:2em;\" id="+quiz.questions.get(i).getId()+ " onclick=\"adminHome.moveQuestion(this.id, 'up')\" href=\"javascript:void(0);\">&#8593;</a</td>");
			out.println("<td class=\"mdl-data-table__cell--non-numeric\"><a style=\"text-decoration:none;font-size:2em;\" id="+quiz.questions.get(i).getId()+" onclick=\"adminHome.moveQuestion(this.id, 'down')\" href=\"javascript:void(0);\">&#8595;</a</td>");
			out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+quiz.questions.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.removeQuestion(this.id)\" type=\"submit\" value=\"Delete\" /></td>");
			out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+quiz.questions.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent editAnswers"+quiz.questions.get(i).getId()+"\"  onclick=\"adminHome.editQuestion(this.id)\" type=\"submit\" value=\"Edit\" /></td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<input style=\"width:10%\" id=\"addNewQuestion\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.addQuestion()\" type=\"submit\" value=\"Add new\" />");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
