

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.Quiz;
import test1.QuizDao;

/**
 * Servlet implementation class Random
 */
@WebServlet("/random")
public class Random extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Random() {
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
		int id1 = (int)(Math.random() * quizes.size());
		int id2 = id1;
		while(id1 == id2)
			id2 = (int)(Math.random() * quizes.size());
		response.setContentType("text/html");
		out.println("<!DOCTYPE html><html><body>");
		out.println("<iframe"
				+ " style=\"position:absolute; border:0px; top:0px; left:0px; width:45%; height:100%;\""
				+ " src=\"./quiz?quizId="+quizes.get(id1).getId()+"\">"
				+ "</iframe>");
		out.println("<iframe"
				+ " style=\"position:absolute; border:0px; top:0px; left:50%; width:45%; height:100%;\""
				+ " src=\"./quiz?quizId="+quizes.get(id2).getId()+"\">"
				+ "</iframe>");
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
