package com.example.fleetmanager

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fleetmanager.repository.JobRepository
import com.example.fleetmanager.repository.database.*
import com.example.fleetmanager.repository.network.IJobRemoteDataStore
import com.example.fleetmanager.repository.network.JobRemoteAPI
import com.example.fleetmanager.repository.network.JobRemoteDataStore
import com.example.fleetmanager.ui.theme.FleetManagerTheme
import io.ktor.http.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val TAG="MainActivity"

    //var repo:JobRepository?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = getRepoInstance()
        setContent {
            FleetManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scope = rememberCoroutineScope()
                    var text by remember {
                        mutableStateOf("")
                    }
                    DriverDetails(text, {
                        println("getting detail")
                        scope.launch {
                            try {
                                val list= repo.getAllJobs()
                                text=list.toString()
                                Log.v("job detail",text)

                            }catch (e: Exception){
                                Log.e("$TAG",e.toString())
                            }
                        }
                    })
                }
            }
        }
    }

    private fun getRepoInstance(): JobRepository {
        val jobApi = JobRemoteAPI()
        DatabaseProvider.initialize(applicationContext)
        val database = DatabaseProvider.getInstance()
        val localDataStore: IJobLocalDataStore = LocalDataStore(database!!.jobDao())
        val remoteProvider: IJobRemoteDataStore = JobRemoteDataStore(jobAPi = jobApi)
        val repo = JobRepository(remoteProvider = remoteProvider, localProvider = localDataStore)
        return repo
    }
}

@Composable
fun DriverDetails(text : String, onDownload: ()-> Unit,  modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
        Button(onClick = {
           onDownload.invoke()
        }) {
            Text(text = "Download")
        }
    }
}


//private suspend fun fetchJob(): String {
//    return try {
//        JobRemoteAPI().driverdetails().toString()
//    } catch (e: Exception) {
//        e.localizedMessage ?: "error"
//        ""
//    }
//}




