package com.example.fleetmanager.repository.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class JobRemoteAPI{
    private val TAG = "JobRemoteAPI"
    val client= HttpClient(){
        install(Logging){
            logger=object :Logger{
                override fun log(message: String) {
                    Log.v("KTOR", message)
                }
            }
        }
        install(ContentNegotiation){
            json(Json{

            })
        }

    }
    suspend fun driverdetails(): JobNetworkModel? {
        val response= client.get("http://192.168.1.15:8080/job/1"){
            headers{
                Log.d("deserialize","deserialize")
                accept(ContentType.Application.Json)
                contentType(ContentType.parse("application/json"))
            }
        }
        println("$TAG: ${response.status}")
        return if(response.status == HttpStatusCode.OK){
            response.body()
        } else null
    }
}
