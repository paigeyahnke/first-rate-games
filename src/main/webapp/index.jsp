<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>First Rate Games</title>
    <link rel="stylesheet"
          type="text/css"
          href="style.css" />
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
            crossorigin="anonymous"></script>

<!--
Jquery used to output the json object
returned by the First Rate Games service
for demonstration purposes
-->
<script>
    $(function () {
        $('#game-form').submit(function (e) {

            // stops form from taking the user to a new page
            e.preventDefault();

            var responseDiv = $("#responseJSON");

            if (!responseDiv.length) {
                responseDiv = $("<div>").attr("id", "responseJSON");
                responseDiv.append($("<h2>").text("Response"));
                responseDiv.append($("<pre>").attr("id", "response"));
                $("body").append(responseDiv);
                $('#responseJSON').fadeIn();
            } else {
                $('#response').text("");
            }

            // loading symbol while waiting on response
            $('#response').append("<img src='spinner.gif' id='spinner'/>");

            // display json response
            $.get('/game/top', $('#game-form').serialize(), function (data) {
                $('#response').text(JSON.stringify(data, null, 2));
            }, 'json');
        });
    })
</script>
</head>

<body>
    <form id="game-form">
        <h2>First Rate Games</h2>

        <div id="fields">
            <label for="year">Year</label>
            <input type="number"
                   id="year"
                   name="year"
                   min="2000"
                   max="2016"
                   value="2016" />

            <br />

            <label for="genre">Genre</label>
            <select name="genre" id="genre">

                <c:forEach var="genre" items="${genres}">
                    <option><c:out value="${genre}"/></option>
                </c:forEach>

            </select>
        </div>

        <button>Get Games</button>
    </form>

    <div></div>

</body>

</html>
