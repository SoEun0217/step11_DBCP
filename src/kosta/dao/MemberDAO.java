package kosta.dao;

import java.util.List;

import kosta.dto.Member;

/**
 *ȸ�� ���� 
 **/
public interface MemberDAO {
	/**
	 *��ü �˻� 
	 **/
	List<Member> selectAll();
	
	/**
	 *���� �˻�(�˻��ʵ�� �˻��ܾ �ش��ϴ� ���ڵ� �˻�)
	 **/
	List<Member> searchByKeyWord(String keyField, String keyWord);
	
	/**
	 * ����ϱ�
	 * */
	int insert(Member member);
	
	/**
	 * ID�ߺ�üũ�ϱ�
	 * @return  : true�̸� �����ϴ� ���̵� false�̸� ����.
	 * */
	boolean idCheck(String id);
	
	/**
	 * �����ϱ�
	 * */
	int delete(String id);
	
	
	/**
	 * �󼼺���
	 * */
	Member detailClient(String id);
}
