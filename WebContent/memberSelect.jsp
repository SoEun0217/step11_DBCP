<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
  table{width:100%; border:5px dobule red}
  th,td{padding:5px; border: 1px solid pink ; text-align: center }
  a{text-decoration: none}
 </style>
<script src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	
	//검색을 클릭했을 때
	$("[value=검색]").click(function(){
		if($("[name=keyField]").val()==0){
			alert("항목을 선택해주세요.");
			return;
		}
		if($("[name=keyWord]").val()==""){
			alert("검색 단어를 입력해주세요");
			$("[name=keyWord]").focus();
			return;
		}
		
		//전송하기
		$("[name=search]").submit();
	})
	
	$("[value=삭제하기]").click(function(){
		var id = $(this).attr('id');
		location.href = "${pageContext.request.contextPath}/delete?id="+id;
	});
	
	
	
})

</script>
</head>

<body>


<center>
 <h1>[ 회원 정보 LIST ]</h1>
<table cellspacing="0">
  <tr>
    <th colspan="9" style="text-align:right">
      <a href="memberForm.html">[ 회원가입 ]</a>&nbsp;&nbsp;&nbsp;
      <a href="index.jsp">[ 새로고침 ]</a>&nbsp;&nbsp;&nbsp;
    </th>
  </tr>
  <tr bgColor="pink">
    <th>번호</th>
    <th>아이디</th>
    <th>비밀번호</th>
    <th>이름</th>
    <th>나이</th>
    <th>주소</th>
    <th>연락처</th>
    <th>가입일</th>
    <th>삭제</th>
  </tr>
   
    <!-- DB에서 가져온 데이터(List<Member>)를 화면에 출력 -forEach를 사용하기 -->
    <c:forEach items="${selectAll}" var="selectAll" varStatus="state">
    	<tr>
    	  <td>${state.count}</td>
    	  <td>${selectAll.id}</td>
    	  <td>${selectAll.pwd}</td>
    	  <td>${selectAll.name}</td>
    	  <td>${selectAll.age}</td>
    	  <td>${selectAll.addr}</td>
    	  <td>${selectAll.phone}</td>
    	  <td>${selectAll.joinDate}</td>
    	  <td><input type="button" value="삭제하기" id="${selectAll.id}"/></td>
    	</tr>
    </c:forEach>
 
</table>
<p>

<form name="search" action="${pageContext.request.contextPath}/search" method="post">
 <select name="keyField">
   <option value="0">--선택--</option>
   <option value="id">아이디</option>
   <option value="name">이름</option>
   <option value="addr">주소</option>
 </select>
 
<input type="text" name="keyWord"/>
<input type="button" value="검색" />  

</form>





</center>



</body>
</html>




