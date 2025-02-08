# Fetch Take Home

## Description

[TakeHome Description](https://fetch-hiring.s3.amazonaws.com/mobile.html)

## Overview

In order to build the application, I followed a clean architecture pattern. 
Also as the specific logic was well defined by the take home, a part of the logic is 
handle in the mapper file as it's data manipulation which remain the same all the time.
In case the app to control the type of logic, the filtering/sorting/maping will be moved
to the viewModel instead. but in the current scope of work it make more sense to keep it in 
the mapper.
There is room database as the json file is fairly small and there is no need to have it stored in a Db.
In an application that will need more data, I would have setup a database using Room and copy the data
from Retrofit to Room Db and then the UI wouls have pull the data from the database. 
It would have provided a cash mechanism
I have added also a pull to refresh to allow a refresh of data if needed.
I hope that the UI is matching your expectations as my interpretations on this take home was:
1- Pull the data from the api
2- remove any object where name is empty or null
3- Group and sort each object by Id
4- in each group of Id, the name must be sorted as well. As I notice that all the naming was 
`Item <int>`, I decided to sort by int the name.


## Librairies

- Kotlin 2.0
- Jetpack Compose
- Dagger-Hilt
- Retrofit/OkHttp




