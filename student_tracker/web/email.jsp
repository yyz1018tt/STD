<%--
  Created by IntelliJ IDEA.
  User: yangyuze
  Date: 26/12/19
  Time: 3:35 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/css/bootstrap.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="jumbotron text-center">
            <h1>Send Email</h1>
        </div>
        <br>
        <form action="/email" method="post">
            <table class="table table-hover">
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" class="form-control"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email" class="form-control"></td>
                </tr>
                <tr>
                    <td>Subject</td>
                    <td><input type="text" name="subject" class="form-control"></td>
                </tr>
                <tr>
                    <td>Message</td>
                    <td><textarea name="message" cols="130" rows="10"></textarea></td>
                </tr>
                <tr>
                    <td><input type="submit" id="submit" class="btn btn-primary" value="submit"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
