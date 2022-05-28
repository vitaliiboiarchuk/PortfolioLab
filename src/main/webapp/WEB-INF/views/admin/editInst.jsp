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
                        <h5 class="card-title">Edytowanie fundacji</h5>

                        <!-- Floating Labels Form -->

                        <form:form method="post" modelAttribute="institution" action="/admin/updateInst" class="row g-3">

                        <div class="col-md-12">
                            <div class="form-floating">
                                <form:input path="name" type="text" class="form-control" id="title"
                                            placeholder="Your Name"/>
                                <label for="title">Tytuł</label>
                            </div>
                        </div>
                        <form:errors path="name" class="alert alert-danger"/>


                        <div class="col-md-12">
                            <div class="form-floating">
                                <form:input path="description" type="text" class="form-control" id="title"
                                            placeholder="Your Name"/>
                                <label for="title">Opis</label>
                            </div>
                        </div>
                        <form:errors path="description" class="alert alert-danger"/>


                    </div>
                </div>

            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>

            <form:hidden path="id"/>

            </form:form>

        </div>
    </section>

</main>
<!-- End #main -->

<jsp:include page="adminfooter.jsp"/>