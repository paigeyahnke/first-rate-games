# Documentation

## GET 
#### /game/top:year:genre:responseType
####Example URL: 
`http://138.68.57.130:8080/first-rate-games/game/top?year=2016&genre=Sports&responseType=json`

|Field|Type|Description|
|-----|:----:|:---------------:|
|genre|String|Name of the genre|
|year|Integer|Year of release|
|returnType|String|Type|

###Success 200
|Field|Type|Description|
|-----|:----:|:---------------:|
|id|Int|API id|
|name|String|Name of the game|
|genres|Integer[]|What genres the game is|
|first_release_date|Integer|Year game was first released|


###Example JSON Response
```{  
  "id": 15909,  
  "name": "The Bug Butcher",  
  "genres": [  
    33,  
    32  
  ],  
  "first_release_date": 2016  
}```  

