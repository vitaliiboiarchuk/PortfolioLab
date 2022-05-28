<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">

            <ul class="nav--actions">
                <li class="logged-user">
                    Witaj ${user.username}
                    <ul class="dropdown">
                        <li><sec:authorize access="hasRole('ADMIN')"><a href="/admin">Admin</a></sec:authorize></li>
                        <li><sec:authorize access="hasRole('USER')"><a href="/myDonations">My Donations</a></sec:authorize></li>
                        <li><a href="/edit">Edytuj</a></li>

                        <form action="<c:url value="/logout"/>" method="post">
                            <input class="btn btn--small btn--without-border" type="submit" value="Wyloguj"></a>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>

                    </ul>
                </li>
            </ul>


        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Edytuj</h2>
    <form:form modelAttribute="user" method="post">
        <div class="form-group">
            <form:input path="username" type="text" name="username"  />
            <h1><form:errors path="username" class="alert alert-danger"/></h1>
        </div>
        <div class="form-group">
            <input path="password" type="password" name="password"  />
            <h1><form:errors path="password" class="alert alert-danger"/></h1>
        </div>

        <form:hidden path="id"/>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Wyślij</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>