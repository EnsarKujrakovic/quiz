

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test1.*;

/**
 * Servlet implementation class Editors
 */
@WebServlet("/admin/editors")
public class Editors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao ud =  new UserDao();
		
		UserService uService = new UserService(ud);
		List<User> users = uService.findAll();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("removeEditor").equals("true")) {
			uService.remove(Long.parseLong(request.getParameter("editorId")));
			return;
		}
		
		out.println("<table style=\"max-width:600px\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">");
		for (int i = 0; i < users.size(); ++i) {
			if(users.get(i).getId()!=99999999) {
				out.println("<tr>");
				out.println("<td class=\"mdl-data-table__cell--numeric\">" + users.get(i).getId() + "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" + users.get(i).getFirstName() + "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" + users.get(i).getLastName() + "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" + users.get(i).getUsername() + "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+users.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.removeEditor(this.id)\" type=\"submit\" value=\"Delete\" /></td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\"><input style=\"width:100%\" id="+users.get(i).getId()+" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.editEditor(this.id)\" type=\"submit\" value=\"Edit\" /></td>");
				out.println("</tr>");
			}
		}
		out.println("</table>");
		out.println("<input style=\"width:10%\" id=\"addNewEditor\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"adminHome.addEditor()\" type=\"submit\" value=\"Add new\" />");
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
