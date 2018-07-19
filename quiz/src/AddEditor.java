

import java.io.IOException;
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
 * Servlet implementation class AddEditor
 */
@WebServlet({"/admin/adduser", "/addsuperuser"})
public class AddEditor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEditor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao ud = new UserDao();
		User user = new User();
		user.setFirstName((String)request.getParameter("firstName"));
		user.setLastName((String)request.getParameter("lastName"));
		user.setUsername((String)request.getParameter("username"));
		user.setPassword(SecurityUtil.hashPassword((String)request.getParameter("username")));
		ud.save(user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
