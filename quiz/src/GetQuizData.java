

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import test1.Quiz;
import test1.QuizDao;
import test1.QuizData;
import test1.SecurityUtil;

/**
 * Servlet implementation class GetQuizData
 */
@WebServlet("/getquizdata")
public class GetQuizData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuizData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		QuizDao qd = new QuizDao();
		Quiz quiz = qd.findById(Long.parseLong((String)request.getParameter("quizId")));
		QuizData qData = new QuizData(quiz);
		String jsonString = "";
		SecurityUtil su = new SecurityUtil();
		Gson gson = new Gson();
		jsonString = gson.toJson(qData);
		out.println(jsonString);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
