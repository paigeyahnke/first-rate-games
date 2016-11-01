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

            // placeholder text while waiting on response
            $('.response').fadeIn();
            $('#response').text('loading...')

            // display json response
            $.get('/game/top', $('#game-form').serialize(), function (data) {
                $('#response').text(JSON.stringify(data, null, 2));
            }, 'json');
        });
    })
</script>
</head>

<body>
    <form action="/game/top" method="get" id="game-form">
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
            <!-- Values are that of igdb's genre ids -->
            <select name="genre" id="genre">
                <option value="5" selected="selected">FPS</option>
                <option value="12">RPG</option>
                <option value="14">Sports</option>
            </select>
        </div>


        <button>Get Games</button>
    </form>
    <h2 class="response">Response</h2>
    <pre id="response" class="response"></pre>
</body>

</html>
