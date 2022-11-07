package com.example.fleetmanager

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleetmanager.domain.JobData
import com.example.fleetmanager.repository.JobRepository
import com.example.fleetmanager.repository.database.*
import com.example.fleetmanager.repository.network.IJobRemoteDataStore
import com.example.fleetmanager.repository.network.JobRemoteAPI
import com.example.fleetmanager.repository.network.JobRemoteDataStore
import com.example.fleetmanager.ui.theme.FleetManagerTheme
import io.ktor.http.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

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
                    var jobModel by remember {
                        mutableStateOf<JobData?>(null)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = {
                            scope.launch {
                                try {
                                    val list = repo.getAllJobs()
                                    jobModel = list.first()
                                    Log.v("job detail", jobModel.toString())

                                } catch (e: Exception) {
                                    Log.e("$TAG", e.toString())
                                }
                            }
                        }) {
                            Text(text = "Download")
                        }

                        jobModel?.let {
                            DriverDetails(
                                carId = it.car_id,
                                driverID = it.driver_id,
                                lat_start= it.lat_start,
                                long_start= it.long_start,
                                lat_end=it.lat_end,
                                long_end=it.long_end,
                                estimatedTime = it.estimate_time
                            )
                        } ?: CircularProgressIndicator()
                    }

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
fun DriverDetails(
    driverID: String,
    carId: String,
    estimatedTime: Long,
    lat_start:String,
    long_start:String,
    lat_end:String,
    long_end:String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = 10.dp, modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Job Details", modifier = Modifier
                        .padding(10.dp)
                )
                LabelWithText(label = "Driver Id", text = driverID)
                LabelWithText(label = "Car Id", text = carId)
                LabelWithText(label = "lat_start", text = lat_start)
                LabelWithText(label = "Long_start", text = long_start)
                LabelWithText(label = "lat_end", text = lat_end)
                LabelWithText(label = "long_end", text = long_end)
                LabelWithText(label = "Estimated time", text = estimatedTime.toString())

            }
        }
    }
}

@Composable
fun LabelWithText(label: String, text: String) {
    Column() {
        Text(text = label, style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun Loader(){
    CircularProgressIndicator()
}


//private suspend fun fetchJob(): String {
//    return try {
//        JobRemoteAPI().driverdetails().toString()
//    } catch (e: Exception) {
//        e.localizedMessage ?: "error"
//        ""
//    }
//}




