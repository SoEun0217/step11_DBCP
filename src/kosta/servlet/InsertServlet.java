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
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDaoImpl();
		//���۵� ������ �޾Ƽ� Member��ü�� ���
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		
		//��ȿ�� üũ !! ����
		if(id==null||id.equals("")||
				pwd==null||pwd.equals("")||
				name==null||name.equals("")||
				age==null||age.equals("")||
				addr==null||addr.equals("")||
				phone==null||phone.equals("")) {
			request.setAttribute("msg", "�Է°��� ������� �ʽ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		
		
		
		//������� id �ߺ�üũ �Ѵ�.
		boolean ch = dao.idCheck(id);
		
		//���� id�� �ߺ��̶�� error.jsp �̵� (�����޽����� ����)
		if(ch==true) {//�ߺ��̶��..
			request.setAttribute("msg", id+"�� �̹� ������Դϴ�.");
			//forward������� �̵�...
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else {
			//����ϱ�...
			Member member = new Member(id,pwd,name,Integer.parseInt(age),phone,addr,null);
			int result = dao.insert(member);
		
			if(result==1) {
				//�����ϸ� -> index.jsp �̵� or selectAll�� �̵� redirect ���..
				response.sendRedirect("index.jsp");
				//response.sendRedirect("selectAll");����ε� �̵�����
			}else {
				//����, ����� �����ϸ� �޽����� ������ error.jsp�� �̵�
				request.setAttribute("msg","����� �����Ͽ����ϴ�." );
				request.getRequestDispatcher("error.jsp").forward(request, response);
				
			}
			
			
		}
		
		
		
		
	}

}
