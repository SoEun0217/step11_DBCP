package kosta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kosta.dto.Member;

public class MemberDaoImpl implements MemberDAO {

	@Override
	public List<Member> selectAll() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Member> list= new ArrayList<Member>();
		String sql="SELECT ID,PWD,NAME,AGE,PHONE,ADDR,JOIN_DATE FROM MEMBER ORDER BY JOIN_DATE DESC";
		//�ε�� �̹� �� ���� 
		try {
			//���� ����
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?�� �ִٸ� ?�� ������� ������ŭ ps.setXxx( , )�ʿ��ϴ�.
			
			rs = ps.executeQuery();
			while(rs.next()) {//Ŀ���� �Ʒ��� �̵��ϸ鼭 �����͸� ��ȸ�ϱ�
				String id = rs.getString(1);//ID
				String pwd = rs.getString(2);//pwd
				String name= rs.getString(3);
				int age = rs.getInt(4);
				String phone = rs.getString(5);
				String addr = rs.getString(6);
				String joinDate = rs.getString(7);
				
				//Member��ü�� �����Ѵ�.
				//list�� �����Ѵ�.
				list.add(new Member(id,pwd,name,age,phone,addr,joinDate));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ݱ�
			DbUtil.dbClose(rs, ps, con);
		}
		return list;
	}

	@Override
	public List<Member> searchByKeyWord(String keyField, String keyWord) {
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Member>list = new ArrayList<Member>();
		String sql="select  ID,PWD,NAME,AGE,PHONE,ADDR,JOIN_DATE from memeber where ";
		if(keyField.equals("id")) {
			sql+="id like ?";
		}else if(keyField.equals("name")) {
			sql+="name like ?";
		}else if(keyField.equals("addr")){
			sql+="addr like ?";
		}
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,"%"+keyWord+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);//ID
				String pwd = rs.getString(2);//pwd
				String name= rs.getString(3);
				int age = rs.getInt(4);
				String phone = rs.getString(5);
				String addr = rs.getString(6);
				String joinDate = rs.getString(7);
				list.add(new Member(id,pwd,name,age,phone,addr,joinDate));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return list;
		
	}

	@Override
	public int insert(Member member) {
		Connection con=null;
		PreparedStatement ps = null;
		int result=0;
		String sql="insert into member values(?,?,?,?,?,?,sysdate)";
		try {
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,member.getId());
			ps.setString(2,member.getPwd());
			ps.setString(3,member.getName());
			ps.setInt(4,member.getAge());
			ps.setString(5,member.getPhone());
			ps.setString(6 ,member.getAddr());
			result=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public boolean idCheck(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "select * from member where upper(id)=upper(?)";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {//Ŀ���� �Ʒ��� �̵��ϸ鼭 �����͸� ��ȸ
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return result;
	}

	@Override
	public int delete(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from member where id=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

}
