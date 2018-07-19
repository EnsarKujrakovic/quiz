

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.Quiz;
import test1.QuizDao;

/**
 * Servlet implementation class RetrieveQuizes
 */
@WebServlet("/quizes")
public class RetrieveQuizes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveQuizes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		QuizDao qd = new QuizDao();
		List<Quiz> quizes = qd.findAll();
		out.println("<html><head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		out.println("<link rel=\"stylesheet\" href=\"./style/login.css\">");
		out.println("<link rel=\"stylesheet\" href=\"./scripts/mdl/material.min.css\">");
		out.println("<script src=\"./scripts/mdl/material.min.js\"></script>");
		out.println("<script src=\"./scripts/adminHome.js\"></script>");
		out.println("<script src=\"./scripts/quiz.js\"></script>");
		out.println("<script src=\"./scripts/jquery-3.2.1.min.js\"></script>");
		out.println("<script src=\"./scripts/dialog-polyfill.js\"></script>");
		out.println("<link rel=\"stylesheet\"");
		out.println("href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">");
		out.println("<title>Quizes</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div id=\"playQuiz\" style=\"width:600px;margin: 100 auto;\">");
		out.println("<table style=\"width:100%\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">");
		for (int i = 0; i < quizes.size(); ++i) {
			out.println("<tr id="+quizes.get(i).getId()+" onclick=\"q1.fill(this.id)\">");
			out.println("<td><div>");
			out.println("<h5 id=quizTitle"+quizes.get(i).getId()+ " class=\"mdl-data-table__cell--non-numeric\">" + quizes.get(i).getTitle() + "</h5>");
			out.println("<p id=quizDescription"+quizes.get(i).getId()+ " class=\"mdl-data-table__cell--non-numeric\">" + quizes.get(i).getDescription() + "</p>");
			out.println("</div></td");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</div>");
		
		
		
		
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
