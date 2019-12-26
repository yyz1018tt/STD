<%--
  Created by IntelliJ IDEA.
  User: yangyuze
  Date: 15/12/19
  Time: 9:20 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Found student</title>
<%--    <link type="text/css" rel="stylesheet" href="./css/style.css" />--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/css/bootstrap.min.css">--%>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Found Student(s)</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="tempStudent" items="${STUDENTS}">

                    <c:url var="tempLink" value="/students">
                        <c:param name="command" value="LOAD" />
                        <c:param name="studentId" value="${tempStudent.id}" />
                    </c:url>

                    <c:url var="tempDeleteLink" value="/students">
                        <c:param name="command" value="DELETE"/>
                        <c:param name="studentId" value="${tempStudent.id}"/>
                    </c:url>

                    <tr>
                        <td>${tempStudent.firstName}</td>
                        <td>${tempStudent.lastName}</td>
                        <td>${tempStudent.email}</td>
                        <td><a href="${tempLink}" class="btn-primary">Update</a>|<a href="${tempDeleteLink}"
                                                                                    onclick="if(!(confirm('Are you sure?'))) return false" class="btn-danger">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
