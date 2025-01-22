package rs.scdownload

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import rs.scdownload.ui.theme.SCDLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            SCDLTheme {
                SCDLNavHost(openSwf = { openSwf(it) })
            }
        }
    }

    private fun openSwf(uri: Uri) {
        val intent = Intent(
            this@MainActivity,
            PlayerActivity::class.java
        ).apply {
            data = uri
        }
        startActivity(intent)
    }
}
