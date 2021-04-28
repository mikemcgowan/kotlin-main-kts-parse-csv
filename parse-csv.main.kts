#!/usr/bin/env kotlin

// known to work with Kotlin 1.4.31

@file:DependsOn("org.slf4j:slf4j-simple:1.7.30")
@file:DependsOn("org.slf4j:slf4j-api:1.7.30")
@file:DependsOn("io.github.microutils:kotlin-logging:1.7.9")
@file:DependsOn("org.json:json:20210307")
@file:DependsOn("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.2")
@file:DependsOn("com.github.kittinunf.fuel:fuel:2.3.1")
@file:DependsOn("com.github.kittinunf.fuel:fuel-json:2.3.1")

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONObject
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

fun die(reason: String) {
    println(reason)
    exitProcess(1)
}

fun getenv(name: String): String {
    val v = System.getenv(name)
    if (v == null) die("Missing env var $name")
    return v
}

if (args.isEmpty()) {
    die("Missing input CSV file as first argument")
}

val inputFile = args[0]
if (!Files.exists(Paths.get(inputFile))) {
    die("""CSV file "$inputFile" does not exist""")
}

val url = getenv("URL")
val clientId = getenv("CLIENT_ID")
val clientSecret = getenv("CLIENT_SECRET")
val credentialsUrl = "$url/auth/oauth/v2/token?grant_type=client_credentials&client_id=$clientId&client_secret=$clientSecret"

val (_, _, result) = credentialsUrl.httpPost().responseJson()
if (result.component2() != null) {
    die("Couldn't obtain an iPaaS API access token")
}
val accessToken = result.component1()?.obj()?.get("access_token").toString()

csvReader().open(inputFile) {
    readAllWithHeaderAsSequence().forEach { processRow(it) }
}

fun processRow(row: Map<String, String>) {
    val id = row["id"]
    val userUpdateUrl = "$url/users/$id"
    val body = JSONObject()
    body.put("IsActive", true)
    val (_, _, result) = userUpdateUrl.httpPost()
        .header(Headers.AUTHORIZATION, "bearer $accessToken")
        .jsonBody(body.toString())
        .also { println(it) }
        .response()
    if (result.component2() != null) {
        println("Update to user $id failed!")
    }
}
