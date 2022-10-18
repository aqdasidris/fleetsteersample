package com.example.fleetmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleetmanager.ui.theme.FleetManagerTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DriverDetails()
                    }
                }
            }
        }
    }



@Composable
fun DriverDetails(modifier:Modifier=Modifier){
    val scope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf("")
    }

    Column(modifier=modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Text(text = text)
        Button(onClick = {println("getting detail")
            scope.launch {
                text = try {
                    DriverDetail().driverdetails().toString()
                } catch (e: Exception) {
                    e.localizedMessage ?: "error"
                }
            }

        }) {
            Text(text = "get details")
        }
    }
}




