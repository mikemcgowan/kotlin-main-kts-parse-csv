# Parse CSV

Known to work with Kotlin 1.4.31 (but should work from Kotlin [1.3.70](https://blog.jetbrains.com/kotlin/2020/03/kotlin-1-3-70-released/#scripting)).

## Top level dependencies

* [kotlin-csv](https://github.com/doyaaaaaken/kotlin-csv)
* [fuel](https://github.com/kittinunf/Fuel)
* [fuel-json](https://github.com/kittinunf/fuel/tree/master/fuel-json)

## Usage

After creating an `.env` file with env vars `URL`, `CLIENT_ID`, `CLIENT_SECRET`:

```
$ ./parse-csv.main.kts users.csv

--> POST https://gateway-staging.pf-labs.net:443/users/06a1e66c-a624-4fb9-ae6b-b11efa060e5a
Body : {"IsActive":true}
Headers : (2)
Content-Type : application/json
Authorization : bearer <cut>

--> POST https://gateway-staging.pf-labs.net:443/users/37a6dc4a-a52b-4494-8b3d-a1dc57d305fb
Body : {"IsActive":true}
Headers : (2)
Content-Type : application/json
Authorization : bearer <cut>
```
