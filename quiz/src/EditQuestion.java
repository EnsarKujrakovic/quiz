

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.Answer;
import test1.Question;
import test1.QuizDao;

/**
 * Servlet implementation class RemoveQuestion
 */
@WebServlet("/admin/editquestion")
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QuizDao qd = new QuizDao();
		String option = (String) request.getParameter("option");
		PrintWriter out = response.getWriter();
		if(option.equals("1")) {//addquestion
			Question question = new Question();
			question.setQuestionText((String)request.getParameter("questionText"));
			qd.addQuestion(question, Long.parseLong((String)request.getParameter("quizId")));
		}
		else if(option.equals("2")) {//removequestion
			qd.removeQuestion(Long.parseLong((String)request.getParameter("questionId")),Long.parseLong((String)request.getParameter("quizId")));
		}else if(option.equals("3")){//movequestion
			qd.moveQuestion(Long.parseLong((String)request.getParameter("questionId")), Long.parseLong((String)request.getParameter("quizId")), (String)request.getParameter("direction"));
			String s = (String)request.getParameter("direction");
			if(s.equals("up")) out.println("up");
		}
		else if(option.equals("4")) {//editquestion
			Question question = qd.findQuestionById(Long.parseLong((String)request.getParameter("questionId")));
			out.println("<input style=\"width:10%\" id=\"backToQuestions\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.editQuiz("+Long.parseLong((String)request.getParameter("quizId"))+")\" type=\"submit\" value=\"<\" />");
			
			out.println("<div id=\"quizIdDiv\" style=\"display:none\">"+Long.parseLong((String)request.getParameter("quizId"))+"</div>");
			out.println("<div id=\"questionIdDiv\" style=\"display:none\">"+Long.parseLong((String)request.getParameter("questionId"))+"</div>");
			out.println("<table style=\"max-width:600px\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">");
			out.println("<tr><th colspan=\"5\" class=\"mdl-data-table__cell--non-numeric\"><div>");
			out.println("<h5>" + question.getQuestionText() +"</h6>");
			out.println("</div></th></tr>");
			for (int i = 0; i < question.answers.size(); ++i) {
				int j = i+1;
				String check;
				if(question.answers.get(i).getCorrect())check = "checked";else check="unchecked";
				out.println("<tr>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input id="+question.answers.get(i).getId()+" type=\"checkBox\" disabled=\"disabled\" "+check+" /></td>");
				out.println("<td class=\"mdl-data-table__cell--numeric\">" + j + "</td>");
				out.println("<td id=answerId"+question.answers.get(i).getId()+ " class=\"mdl-data-table__cell--non-numeric\">" + question.answers.get(i).getAnswerText() + "</td>");
				
				out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+question.answers.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.removeAnswer(this.id)\" type=\"submit\" value=\"Delete\" /></td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+question.answers.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.editAnswer(this.id, "+question.answers.get(i).getCorrect()+");\" type=\"submit\" value=\"Edit\" /></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<div id=dialogDiv></div>");
			out.println("<input style=\"width:10%\" id=\"addNewAnswer\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.addAnswer()\" type=\"submit\" value=\"Add new\" />");
		}
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
