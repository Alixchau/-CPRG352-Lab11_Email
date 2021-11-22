<%-- 
    Document   : resetNewPassword
    Created on : 21-Nov-2021, 10:43:12 PM
    Author     : Alix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Set New Password</title>
    </head>
    <body>
        <c:if test="${changedPassword eq null}">
            <h1>Enter a new password</h1>
            <form method="post">
                <input type="password" name="newPassword" >
                <input type="hidden" name="uuid" value="${uuid}">
                <input type="submit" value="Submit">
            </form>
        </c:if>
        <c:if test="${changedPassword}">
            <p>The password has been reset.</p>
        </c:if>
    </body>
</html>
