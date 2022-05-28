<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="adminheader.jsp"/>
<jsp:include page="sidebar.jsp"/>

<main id="main" class="main">

    <section class="section">
        <div class="row">
            <div class="col-lg-14">

                <div class="card">
                    <div class="card-body">
                        <a href="<c:url value="/admin/addInst/"/>"><h5 class="card-title">Dodaj</h5></a>

                        <!-- Default Table -->
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Nazwa</th>
                                <th scope="col">Opis</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${institutions}" var="institution">
                                <tr>
                                    <th scope="row">${institution.name}</th>
                                    <td>${institution.description}</td>
                                    <td>
                                        <a href="<c:url value="/admin/editInst/${institution.id}/"/>">Edytuj</a>
                                    </td>
                                    <td>
                                        <a href="<c:url value="/admin/deleteInst/${institution.id}/"/>">Usuń</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- End Default Table Example -->
                    </div>
                </div>

            </div>
        </div>
    </section>

</main><!-- End #main -->

<jsp:include page="adminfooter.jsp"/>