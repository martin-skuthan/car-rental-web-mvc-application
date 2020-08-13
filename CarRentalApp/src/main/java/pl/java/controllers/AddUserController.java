package pl.java.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.controllers.enums.ControllerAction;
import pl.java.exceptions.ItemWithThisIdAlreadyExistsExcpetion;
import pl.java.model.User;
import pl.java.services.UserService;

@WebServlet("/addUser")
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserService userService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/hidden-views/new-user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null;
		String mail = null;
		String password = null;
		try {
			request.setCharacterEncoding("UTF-8");
			username = request.getParameter("inputUsername");
			mail = request.getParameter("inputMail");
			password = request.getParameter("inputPassword");
			userService.createUser(username, mail, password);
			final String operation = "Adding user";
			request.setAttribute("operation", operation);
			final String formAction = "addUser";
			request.setAttribute("formAction", formAction);
			request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
		}catch (ItemWithThisIdAlreadyExistsExcpetion e) {
			request.setAttribute("controllerAction", ControllerAction.CORRECT);
			request.setAttribute("user", new User(username, mail, password));
			request.getRequestDispatcher("/WEB-INF/hidden-views/new-user.jsp").forward(request, response);
		}	
	}

}
