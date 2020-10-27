package kosta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.dao.MemberDAO;
import kosta.dao.MemberDaoImpl;
import kosta.dto.Member;

/**
 * Servlet implementation class ClientDetail
 */
@WebServlet("/detail")
public class ClientDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDAO dao = new MemberDaoImpl();
		String url = "read.jsp";
		Member member=dao.detailClient(id);
		if(member==null) {
			request.setAttribute("msg", id+"상세보기 할수 없습니다.");
			url = "error.jsp";
		}else {
			request.setAttribute("member", member);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
