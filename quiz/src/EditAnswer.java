

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.Answer;
import test1.QuizDao;

/**
 * Servlet implementation class EditAnswer
 */
@WebServlet("/admin/editanswer")
public class EditAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAnswer() {
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
		if(option.equals("1")) {//addanswer
			Answer answer = new Answer();
			answer.setAnswerText((String)request.getParameter("answerText"));
			answer.setCorrect(Boolean.parseBoolean((String)request.getParameter("isCorrect")));
			qd.addAnswer(answer, Long.parseLong((String)request.getParameter("questionId")));
		}
		else if(option.equals("2")) {//removeanswer
			qd.removeAnswer(Long.parseLong((String)request.getParameter("answerId")), Long.parseLong((String)request.getParameter("questionId")));
		}
		else if(option.equals("3")) {//editanswer
			qd.editAnswer(Long.parseLong((String)request.getParameter("answerId")), (String)request.getParameter("answerText"), Boolean.parseBoolean((String)request.getParameter("isCorrect")));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
