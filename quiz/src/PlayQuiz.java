

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
 * Servlet implementation class PlayQuiz
 */
@WebServlet("/quiz")
public class PlayQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Long id = Long.parseLong((String)request.getParameter("quizId"));
		response.setContentType("text/html");
		QuizDao qd = new QuizDao();
		Quiz quiz = qd.findById(id);
		out.println("<html style=\"overflow:hidden\"><head>");
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
		out.println("<body style=\"overflow:hidden\" onload=\"q1.fill("+id+")\">");
		
		out.println("<div id=\"playQuiz\" style=\"width:600px;margin: 100 auto;\">");
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
