<%@ page import = "java.sql.*" %>
<%@ page import = "common.JDBConnect" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	//DB연결
	JDBConnect jdbc = new JDBConnect();
	
	//쿼리문
	String sql = "SELECT id, pass, name, regidate from member";
	Statement stmt = jdbc.con.createStatement();
	
	//쿼리 수정
	ResultSet rs = stmt.executeQuery(sql);
	
	//결과 확인
	while(rs.next()){
		String id = rs.getString("id");
		String pw = rs.getString("pass");
		String name = rs.getString("name");
		java.sql.Date regidate = rs.getDate("regidate");
		
		out.println(String.format("%s %s %s %s", id, pw, name, regidate) + "<br>");
	}
	
	//연결 닫기
	jdbc.close();
	%>
</body>
</html>