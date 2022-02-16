<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: CKS
  Date: 2022-02-16
  Time: 오후 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <tr>
        <%
            for (Member member : members) {
                out.write("<tr>\n");
                out.write("<td>"+member.getId()+"</td>\n");
                out.write("<td>"+member.getUsername()+"</td>\n");
                out.write("<td>"+member.getAge()+"</td>\n");
                out.write("</tr>\n");
            }
        %>
    </tr>

    <tr>
        <%
            for (Member member : members) {
             %>
                <tr>
                <td><%=member.getId()%></td>
                <td><%=member.getUsername()%></td>
                <td><%=member.getAge()%></td>
                </tr>
            <%
            }
        %>
    </tr>
    </tbody>
</table>
</body>
</html>
