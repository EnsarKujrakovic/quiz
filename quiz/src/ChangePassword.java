

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.SecurityUtil;
import test1.User;
import test1.UserDao;
import test1.UserService;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/admin/chngpass")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		UserDao ud = new UserDao();
		UserService uService = new UserService(ud);
		User user = uService.findById((Long)request.getSession().getAttribute("userId"));
		String hashed = user.getPassword();
		if(SecurityUtil.checkPassword(request.getParameter("oldPass"), hashed)) {
			uService.changePassword((Long)request.getSession().getAttribute("userId"), (String)request.getParameter("newPass"));
			out.println("Your password has been succesfully changed!");
		}else out.println("Failed to change Password!");
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
