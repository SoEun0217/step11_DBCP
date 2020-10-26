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
		//전송된 데이터 받아서 Member객체에 담기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		
		//유효성 체크 !! 권장
		if(id==null||id.equals("")||
				pwd==null||pwd.equals("")||
				name==null||name.equals("")||
				age==null||age.equals("")||
				addr==null||addr.equals("")||
				phone==null||phone.equals("")) {
			request.setAttribute("msg", "입력값이 충분하지 않습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		
		
		
		//등록전에 id 중복체크 한다.
		boolean ch = dao.idCheck(id);
		
		//만약 id가 중복이라면 error.jsp 이동 (오류메시지를 전달)
		if(ch==true) {//중복이라면..
			request.setAttribute("msg", id+"는 이미 사용중입니다.");
			//forward방식으로 이동...
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else {
			//등록하기...
			Member member = new Member(id,pwd,name,Integer.parseInt(age),phone,addr,null);
			int result = dao.insert(member);
		
			if(result==1) {
				//성공하면 -> index.jsp 이동 or selectAll로 이동 redirect 방식..
				response.sendRedirect("index.jsp");
				//response.sendRedirect("selectAll");여기로도 이동가능
			}else {
				//만약, 등록이 실패하면 메시지를 가지고 error.jsp로 이동
				request.setAttribute("msg","등록이 실패하였습니다." );
				request.getRequestDispatcher("error.jsp").forward(request, response);
				
			}
			
			
		}
		
		
		
		
	}

}
