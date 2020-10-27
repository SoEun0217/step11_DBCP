package kosta.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.dao.MemberDAO;
import kosta.dao.MemberDaoImpl;
import kosta.dto.Member;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//keyField,keyWord를 받기
		String keyField = request.getParameter("keyField");
		String keyWord = request.getParameter("keyWord");
		String url = "memberSelect.jsp";
		MemberDAO dao = new MemberDaoImpl();
		List<Member> list = dao.searchByKeyWord(keyField, keyWord);
		//dao쪽의 검색메소드 호출한 후 그 결과를 가지고 memberSelect.jsp이동
		if(list.size()==0) {
			request.setAttribute("msg", keyField+"에 해당하는 정보가 없습니다.");
			url= "error.jsp";
		}else {
			request.setAttribute("selectAll", list);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
