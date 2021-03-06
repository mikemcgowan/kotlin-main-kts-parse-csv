# Parse CSV

Requires Kotlin 1.4.10. Known to work on Kotlin 1.4.10 and Kotlin 1.4.31.

## Top level dependencies

* [kotlin-csv](https://github.com/doyaaaaaken/kotlin-csv)
* [fuel](https://github.com/kittinunf/Fuel)
* [fuel-json](https://github.com/kittinunf/fuel/tree/master/fuel-json)

## Usage

Create a `.env` file containing env vars `URL`, `CLIENT_ID`, `CLIENT_SECRET`:

```
$ cat .env

URL=https://gateway-staging.pf-labs.net:443
CLIENT_ID=<cut>
CLIENT_SECRET=<cut>
```

Then run:

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
