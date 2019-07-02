<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html class="no-js" lang="">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title></title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">


	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<style>
		body {
			padding-top: 50px;
			padding-bottom: 20px;
		}
	</style>

	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="<c:url value="/"/>">Notes</a>
            </div>
        </div>
    </nav>

    <div class="jumbotron">
        <div class="container">
            <h1>Заметки</h1>
            <p>Простой сервис заметок</p>
            <p><a class="btn btn-primary btn-lg" href="<c:url value="/create" />" role="button">Создать</a></p>
        </div>
    </div>

    <div class="container">
        <form:form method="POST" commandName="note" action="save" role="form">
            <div class="form-group">
                <h4>Заголовок</h4>
                <form:input path="header" class="form-control" maxlength="50" />
                <form:errors path="header" class="text-danger" />
                <h4>Заметка</h4>
                <form:textarea path="body" class="form-control" rows="15" />
                <form:errors path="body" class="text-danger"/>
            </div>
            <footer>
                <input type="submit" class="btn btn-default" value="Сохранить">
            </footer>
        </form:form>
        <hr>
        <footer>
        </footer>
    </div>
</body>

</html>
