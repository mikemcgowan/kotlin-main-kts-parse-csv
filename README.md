# Parse CSV

Known to work with Kotlin 1.4.31 (but should work from Kotlin [1.3.70](https://blog.jetbrains.com/kotlin/2020/03/kotlin-1-3-70-released/#scripting)).

Uses [kotlin-csv](https://github.com/doyaaaaaken/kotlin-csv).

```
$ sdk current kotlin
Using kotlin version 1.4.31

$ cat users.csv
"employeeId","userName","firstName","lastName","email","language","group1","group2","favourite.colour","lucky.number"
"10001","marty.byrde","Marty","Byrde","marty.byrde@peoplefluent.com","en","Red","Blue","red","7"
"10002","wendy.byrde","Wendy","Byrde","wendy.byrde@peoplefluent.com","en","Red","","blue",""

$ ./parse-csv.main.kts
Missing input CSV file as first argument

$ ./parse-csv.main.kts does-not-exist.csv
CSV file "does-not-exist.csv" does not exist

$ ./parse-csv.main.kts users.csv
Username is "marty.byrde", first name is "Marty", last name is "Byrde", email is "marty.byrde@peoplefluent.com"
Username is "wendy.byrde", first name is "Wendy", last name is "Byrde", email is "wendy.byrde@peoplefluent.com"
```
