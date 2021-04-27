#!/usr/bin/env kotlin

// known to work with Kotlin 1.4.31

@file:DependsOn("org.slf4j:slf4j-simple:1.7.30")
@file:DependsOn("org.slf4j:slf4j-api:1.7.30")
@file:DependsOn("io.github.microutils:kotlin-logging:1.7.9")
@file:DependsOn("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.2")

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

if (args.isEmpty()) {
    die("Missing input CSV file as first argument")
}

val inputFile = args[0]
if (!Files.exists(Paths.get(inputFile))) {
    die("""CSV file "$inputFile" does not exist""")
}

csvReader().open(inputFile) {
    readAllWithHeaderAsSequence().forEach { processRow(it) }
}

fun die(reason: String) {
    println(reason)
    exitProcess(1)
}

/**
 * TODO implement this function
 */
fun processRow(row: Map<String, String>) {
    val username = row["userName"]
    val firstName = row["firstName"]
    val lastName = row["lastName"]
    val email = row["email"]
    println("""Username is "$username", first name is "$firstName", last name is "$lastName", email is "$email"""")
}
