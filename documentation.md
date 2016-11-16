# Documentation
## Base URL
`http://138.68.57.130:8080/first-rate-games/`

## GET 
#### /game/top:year:genre:responseType
####Example URL: 
`http://138.68.57.130:8080/first-rate-games/game/top?year=2016&genre=Sports&responseType=json`

|Field|Type|Description|
|-----|----|---------------|
|genre|String|Name of the genre|
|year|Integer|Year of release|
|responseType|String|HTML or JSON response type|

###Success 200
|Field|Type|Description|
|-----|----|---------------|
|id|Int|API id|
|name|String|Name of the game|
|rating|Double|Rating based on critics & users (if available)|
|genres|Integer[]|What genres the game is|
|first_release_date|Integer|Year game was first released|

###Server Error 500
|Field|Type|Description|
|-----|----|---------------|
|error|String|Error message|

###Example JSON Response
```json
HTTP/1.1 200 Success

{
  "id": 14548,
  "name": "Borderlands: The Handsome Collection",
  "rating": 84.51020408163266,
  "genres": [
    "RPG",
    "Shooter"
  ],
  "first_release_date": 2015
}
```  

###Example JSON Response
```json
HTTP/1.1 500 Server Error

{
  "error" : "Server error. Could not complete request"
}
```  

###Example HTML Response
```html
<div class='firstRateGame' id='2366'>
  <h4 id='gameName'>Name: Xenoblade Chronicles X</h4>
  <h4 id='gameRating'>Rating: 97/100</h4>
  <h4 id='gameGenres'>Genres: </h4>
  <ul id='gameGenreList'>
    <li>RPG</li>
  </ul>
  <h4 id='gameReleaseYear'>Year: 2015</h4>
</div>
```

###Available Genres
- Arcade
- Adventure
- Action
- Strategy
- Sports
- RPG
- RTS
- Racing
- Puzzler
- Platformer
- Shooter
- Fighting






