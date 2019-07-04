<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <div id="navbar" class="navbar-collapse collapse">
              <form:form method="POST" commandName="search" action="find" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                  <form:input path="txt" placeholder="Найти" class="form-control" />
                </div>
                <button type="submit" class="btn btn-success">Найти</button>
              </form:form>
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
          <div class="row">
              <c:choose>
                  <c:when test="${errormsg != ''}">
                      <p><c:out value="${errormsg}" /></p>
                  </c:when>
              </c:choose>
              <c:forEach items="${notes}" var="note">
                  <div class="col-md-4">
                      <c:choose>
                          <c:when test="${note.header != ''}">
                              <h2><c:out value="${note.header}" /></h2>
                          </c:when>
                          <c:otherwise>
                            <h2><c:out value="${fn:substring(note.body, 0, 50)}" /></h2>
                          </c:otherwise>
                      </c:choose>
                      <p><sub><fmt:formatDate value="${note.creationDate}" pattern="dd MMMM HH:mm" /></sub></p>
                      <p><c:out value="${fn:substring(note.body, 0, 200)}" /></p>
                      <a class="btn btn-default" role="button" href="<c:url value="/show"><c:param name="noteid" value="${note.id}"/>${note.id}</c:url>">Просмотр</a>
                      <a class="btn btn-default" role="button" href="<c:url value="/delete"><c:param name="noteid" value="${note.id}"/>${note.id}</c:url>">Удалить</a>
                  </div>
              </c:forEach>
          </div>
          <hr>
          <footer>
          </footer>
        </div>
    </body>
</html>
