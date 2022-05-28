<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="adminheader.jsp"/>
<jsp:include page="sidebar.jsp"/>

<main id="main" class="main">

    <div class="pagetitle">
        <h1>Wypełnij formularz!</h1>
    </div><!-- End Page Title -->
    <section class="section">
        <div class="row">

            <div class="col-lg-6">

                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Edytowanie administratora</h5>

                        <!-- Floating Labels Form -->

                        <form:form method="post" modelAttribute="user" action="/admin/updateAdmin" class="row g-3">

                        <div class="col-md-12">
                            <div class="form-floating">
                                <form:input path="username" type="text" name="username" placeholder="Username" />
                            </div>
                        </div>
                        <form:errors path="username" class="alert alert-danger"/>


                        <div class="col-md-12">
                            <div class="form-floating">
                                <input path="password" type="password" name="password" placeholder="Password" />

                            </div>
                        </div>
                        <form:errors path="password" class="alert alert-danger"/>


                    </div>
                </div>

            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary">Submit</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

            <form:hidden path="id"/>

            </form:form>

        </div>
    </section>

</main>
<!-- End #main -->

<jsp:include page="adminfooter.jsp"/>