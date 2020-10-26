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
			//�����ϸ� -> index.jsp �̵� or selectAll�� �̵� redirect ���..
			response.sendRedirect("index.jsp");
			//response.sendRedirect("selectAll");����ε� �̵�����
		}else {
			//����, ����� �����ϸ� �޽����� ������ error.jsp�� �̵�
			request.setAttribute("msg","������ �����Ͽ����ϴ�." );
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		}
	}

}
