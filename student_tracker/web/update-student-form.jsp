<%--
  Created by IntelliJ IDEA.
  User: yangyuze
  Date: 14/12/19
  Time: 6:58 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    <link rel="stylesheet" href="./css/style.css" />--%>
<%--    <link rel="stylesheet" href="./css/add-student-style.css" />--%>
</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h3>Student Management System</h3>
        </div>
    </div>

    <div id="container">
        <h3>Update Student</h3>

        <form action="/students" method="get">
            <input type="hidden" name="command" value="UPDATE">
            <input type="hidden" name="studentId" value="${THE_STUDENT.id}">
            <table>
                <tbody>
                <tr>
                    <td>First Name: </td>
                    <td><input type="text" name="first-name" value="${THE_STUDENT.firstName}"></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><input type="text" name="last-name" value="${THE_STUDENT.lastName}"></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email" value="${THE_STUDENT.email}"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save" class="save"></td>
                </tr>
                </tbody>
            </table>
        </form>
        <p>
            <a href="/students">Go back to list...</a>
        </p>
    </div>
</body>
</html>
