<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form class="form--contact">
            <div class="form-group form-group--50"><input type="text" name="name" placeholder="Imię"/></div>
            <div class="form-group form-group--50"><input type="text" name="surname" placeholder="Nazwisko"/></div>

            <div class="form-group"><textarea name="message" placeholder="Wiadomość" rows="1"></textarea></div>

            <button class="btn" type="submit">Wyślij</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="<c:url value="resources/images/icon-facebook.svg"/>"/></a>
            <a href="#" class="btn btn--small"><img src="<c:url value="resources/images/icon-instagram.svg"/>"/></a>
        </div>
    </div>
</footer>

<script src="<c:url value="resources/js/app.js"/>"></script>

<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>

<script type="text/javascript">
    $("#zipCode").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#zipCodeSummary").html($(this).val());
    });
    $("#quantity").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#quantitySummary").html($(this).val());
    });
    $("#street").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#streetSummary").html($(this).val());
    });
    $("#city").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#citySummary").html($(this).val());
    });
    $("#number").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#numberSummary").html($(this).val());
    });
    $("#pickUpDate").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#pickUpDateSummary").html($(this).val());
    });
    $("#pickUpTime").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#pickUpTimeSummary").html($(this).val());
    });
    $("#pickUpComment").change(function () {
        console.log(this);
        console.log($(this).val());
        $("#pickUpCommentSummary").html($(this).val());
    });
    $('input[name=categories]').change(function () {
        $('#categoriesSummary').html('');
        $('input[name=categories]:checked').each(function () {
            $('#categoriesSummary').append($(this).next().text() + "; ");
            console.log($(this).next().text());
        });
    });
    $('input[name=institution]').change(function () {
        $('#institutionSummary').html('');
        $('input[name=institution]:checked').each(function () {
            $('#institutionSummary').append($(this).next().text() + " ");
            console.log($(this).next().text());
        });
    });
</script>

</body>
</html>