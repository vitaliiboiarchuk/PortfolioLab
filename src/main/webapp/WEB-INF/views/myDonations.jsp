<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
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
                    <li><sec:authorize access="hasRole('USER')"><a href="/myDonations">My Donations</a></sec:authorize>
                    </li>
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

<section class="help">
    <h2>My Donations</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">


        <ul class="help--slides-items">

            <li>
                <c:forEach items="${donations}" var="donation" varStatus="status">
                        <div class="col">
                            <c:forEach items="${donation.categories}" var="category">
                                <div class="title">${category.name};</div>
                            </c:forEach>
                            <br>
                            <div class="title">Liczba worków: ${donation.quantity}</div>
                            <br>
                            <div class="title">${donation.institution}</div>
                            <br>
                            <div class="subtitle">Adres odbioru: ${donation.street} ${donation.city} ${donation.zipCode}</div>
                            <br>
                            <div class="subtitle">Termin odbioru: ${donation.pickUpDate} ${donation.pickUpTime}</div>
                            <br>
                            <div class="subtitle">Numer telefonu: ${donation.number}</div>
                            <br>
                            <div class="subtitle">Uwagi: ${donation.pickUpComment}</div>
                            <br>
                            <h1><a href="<c:url value="/deleteMyDonation/${donation.id}/"/>">Usuń</a></h1>
                        </div>

                </c:forEach>
            </li>

        </ul>

    </div>

</section>


<script src="<c:url value="resources/js/app.js"/>"></script>

</body>
</html>
