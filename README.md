# Guess the Movie Game


## Technology being used
* Spring Boot
* Swagger Code gen - to generate rest client

**db url** http://localhost:8080/h2-console

https://api.themoviedb.org/3/movie/550?api_key=

#### Popular Movies
 https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=

### Road Map
* Quiz Should be sent with unique Id and 20 random questions. Quiz should persist.
* Persist the db in file. Dont call the service if questions exist.

##### Quiz
* Quiz should not have answers
* Answers could be submitted to a quiz and result should be available

##### Session
* Session should have one quiz and multiple users to play.
* Each user get their uniqe id
* Session can be in memory

##### Leader Board
* Session should have leader board

##### Real time Quiz
* Use WebSockets
* Each question should appear for certain time and answer should be shown at the end
* Real time LeaderBoard should be available
* Winner should be shown at the end of the quiz
