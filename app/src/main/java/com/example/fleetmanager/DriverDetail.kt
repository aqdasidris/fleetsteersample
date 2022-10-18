package com.example.fleetmanager

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder

class DriverDetail{
    val client= HttpClient(){
        install(ContentNegotiation){
            json(Json{

            })
        }
    }
    suspend fun driverdetails(): Job{
        val response= client.get("http://192.168.222.197:8080/job/1"){
            headers{
                Log.d("deserialize","deserialize")
                accept(ContentType.Application.Json)
                contentType(ContentType.parse("application/json"))
            }
        }
        println("response: ${response.status}")
        return response.body()
    }
}
