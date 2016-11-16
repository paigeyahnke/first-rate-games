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
|returnType|String|HTML or JSON response type|

###Success 200
|Field|Type|Description|
|-----|----|---------------|
|id|Int|API id|
|name|String|Name of the game|
|rating|Double|Rating based on critics & users (if available)|
|genres|Integer[]|What genres the game is|
|first_release_date|Integer|Year game was first released|

###Success 500
|Field|Type|Description|
|-----|----|---------------|
|id|Int|API id|
|name|String|Name of the game|
|rating|Double|Rating based on critics & users (if available)|
|genres|Integer[]|What genres the game is|
|first_release_date|Integer|Year game was first released|


###Example JSON Response
```json
HTTP/1.1 200 Success

{
  "id": 4756,
  "name": "Child of Light",
  "rating": 88.68588498563096,
  "genres": [
    8,
    12,
    16
  ],
  "first_release_date": 2014
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
<div class='firstRateGame' id='12517'>
  <h4 id='gameName'>Name: Undertale</h4>
  <h4 id='gameRating'>Rating: 92/100</h4>
  <h4 id='gameGenres'>Genres: </h4>
  <ul id='gameGenreList'>
    <li>12</li>
    <li>31</li>
    <li>32</li>
    <li>16</li>
  </ul>
  <h4 id='gameReleaseYear'>Year: 2015</h4>
</div>
```






