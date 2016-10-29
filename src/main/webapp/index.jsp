<html>

<head>

    <title>First Rate Games</title>
    <link rel="stylesheet"
          type="text/css"
          href="style.css" />

</head>

<body>

    <form action="/game/top" method="get">
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
                <option value="FPS" selected="selected">FPS</option>
                <option value="RPG">RPG</option>
                <option value="Sports">Sports</option>
            </select>
        </div>


        <button>Get Games</button>
    </form>

</body>

</html>
