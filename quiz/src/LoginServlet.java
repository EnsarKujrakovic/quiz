
import test1.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.UserDao;
import test1.User;
import test1.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        userService = new UserService(new UserDao());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*if(Validate.validate("a", "b")) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			request.getRequestDispatcher("/admin/home").forward(request, response);
		}*/
		if (request.getParameter("logout") != null) {
			request.getSession().invalidate();
		}

		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//Map<String, String> messages = new HashMap<String, String>();
		String messages = new String();
		if (username == null || username.isEmpty()) {
			messages += "Please enter a username<br>";
		}

		if (password == null || password.isEmpty()) {
			messages += "Please enter a password<br>";
		}

		if (messages.isEmpty()) {

			User user = userService.authenticate(username, password);

			if (user != null) {
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("userId", user.getId());
				response.sendRedirect(request.getContextPath() + "/admin/home");
				return;
			} else {
				messages += "Unknown login, please try again";
			}
		}

		request.setAttribute("messages", messages);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
