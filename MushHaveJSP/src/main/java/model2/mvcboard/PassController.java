package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("mode", request.getParameter("mode"));
		request.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//매개변수 저장
		String idx = request.getParameter("idx");
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");
		
		//비밀번호 확인
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		if(confirmed) {
			if(mode.equals("edit")) { //수정 모드
				HttpSession session = request.getSession();
				session.setAttribute("pass", pass);
				response.sendRedirect("../mvcboard/edit.do?idx=" + idx);
			} else if(mode.equals("delete")) { //삭제 모드
				dao = new MVCBoardDAO();
				MVCBoardDTO dto = dao.selectView(idx);
				int result = dao.deletePost(idx);
				dao.close();
				if(result ==1) {
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(request, "/Uploads", saveFileName);
				}
				JSFunction.alertLocation(response, "삭제되었습니다.", "../mvcboard/list.do");
			}
		}else { //비밀번호 불일치
			JSFunction.alertBack(response, "비밀번호 검증에 실패했습니다.");
		}
	}

}
