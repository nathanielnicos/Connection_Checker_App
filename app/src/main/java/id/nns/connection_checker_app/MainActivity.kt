package id.nns.connection_checker_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = ConnectionLiveData(this)
        setContent {
            val isNetworkAvailable = connectionLiveData.observeAsState(false).value
            ConnectivityMonitor(isNetworkAvailable = isNetworkAvailable)
        }
    }

    @Composable
    private fun ConnectivityMonitor(isNetworkAvailable: Boolean) {
        val connectionString = if (isNetworkAvailable)
            "Valid Connection" else
                "No Network Connection"
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = connectionString)
        }
    }

}
