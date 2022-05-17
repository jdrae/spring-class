<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--
<%=((Member)request.getAttribute("member").getId() %>
대신 ${member.id} 사용.
request.setAttribute 로 정한 객체를 가져온다.
memeber.id 는 getId() 를 호출한다.
--%>
    <li>id= ${member.id}</li>
    <li>username= ${member.username}</li>
    <li>age= ${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
