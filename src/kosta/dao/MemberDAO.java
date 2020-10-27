package kosta.dao;

import java.util.List;

import kosta.dto.Member;

/**
 *회원 관리 
 **/
public interface MemberDAO {
	/**
	 *전체 검색 
	 **/
	List<Member> selectAll();
	
	/**
	 *조건 검색(검색필드와 검색단어에 해당하는 레코드 검색)
	 **/
	List<Member> searchByKeyWord(String keyField, String keyWord);
	
	/**
	 * 등록하기
	 * */
	int insert(Member member);
	
	/**
	 * ID중복체크하기
	 * @return  : true이면 존재하는 아이디 false이면 없다.
	 * */
	boolean idCheck(String id);
	
	/**
	 * 삭제하기
	 * */
	int delete(String id);
	
	
	/**
	 * 상세보기
	 * */
	Member detailClient(String id);
}
