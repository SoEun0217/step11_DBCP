package kosta.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.dao.MemberDAO;
import kosta.dao.MemberDaoImpl;
import kosta.dto.Member;

/**
 * Servlet implementation class SelectAllServlet
 */
@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dao�����Ѵ�.
		MemberDAO dao = new MemberDaoImpl();
		//dao�� selectAll()ȣ���ϰ� �װ���� �޾Ƽ�
		List<Member>list = dao.selectAll();
		request.setAttribute("selectAll", list);
		//scope�� �����ϰ� forward������� memberSelect.jsp�̵��Ѵ�.
		request.getRequestDispatcher("memberSelect.jsp").forward(request, response);
	}

}
