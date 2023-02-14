package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.one")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		int lastSlash = uri.lastIndexOf("/");
		String commandStr = uri.substring(lastSlash);
		
		if(commandStr.equals("/regist.one")) {
			registFunc(req);
		}else if(commandStr.equals("/login.one")) {
			loginFunc(req);
		}else if(commandStr.equals("/freeboard.one")) {
			freeboardFunc(req);
		}
		
		req.setAttribute("uri", uri);
		req.setAttribute("commandStr", commandStr);
		req.getRequestDispatcher("/13Servlet/FrontController.jsp").forward(req, resp);
	}

	private void freeboardFunc(HttpServletRequest req) {
		// TODO Auto-generated method stub
		req.setAttribute("resultValue", "<h4>자유게시판</h4>");
	}

	private void loginFunc(HttpServletRequest req) {
		// TODO Auto-generated method stub
		req.setAttribute("resultValue", "<h4>로그인</h4>");
	}

	private void registFunc(HttpServletRequest req) {
		// TODO Auto-generated method stub
		req.setAttribute("resultValue", "<h4>회원가입</h4>");
	}
}
