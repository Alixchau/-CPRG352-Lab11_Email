<%-- 
    Document   : reset
    Created on : 21-Nov-2021, 9:51:54 PM
    Author     : Alix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <c:if test="${sentEmail eq null}">
            <p>Please enter your email address to reset your password.</p>
            <form action="reset" method="post">      
                <label>Email address: </label>
                <input type="text" name="email" value="">
                <input type="submit" value="Submit">
            </form>
        </c:if>
        <c:if test="${sentEmail}">
            <p>If the address you entered is valid, you will receive an email very soon. Please check your email for your password</p>
        </c:if>
    </body>
</html>
