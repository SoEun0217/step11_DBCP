package kosta.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.dao.MemberDAO;
import kosta.dao.MemberDaoImpl;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDaoImpl();
		int result = dao.delete(request.getParameter("id"));

		if(result==1) {
			//성공하면 -> index.jsp 이동 or selectAll로 이동 redirect 방식..
			response.sendRedirect("index.jsp");
			//response.sendRedirect("selectAll");여기로도 이동가능
		}else {
			//만약, 등록이 실패하면 메시지를 가지고 error.jsp로 이동
			request.setAttribute("msg","삭제가 실패하였습니다." );
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		}
	}

}
