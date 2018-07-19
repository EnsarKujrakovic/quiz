

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import test1.JsonResult;
import test1.QuizDao;
import test1.Result;
import test1.UserDao;
import test1.UserService;

/**
 * Servlet implementation class SubmitQuiz
 */
@WebServlet("/submitresults")
public class SubmitQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitQuiz() {
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
		
		String jsonString = request.getParameter("jsonstring");
		Gson gson = new Gson();
		JsonResult jResult = new JsonResult();
		jResult = gson.fromJson(jsonString, JsonResult.class);
		Result r = jResult.toJPA();
		qd.saveResult(r);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
