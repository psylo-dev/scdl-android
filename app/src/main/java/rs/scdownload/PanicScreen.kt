package rs.scdownload

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.scdownload.ui.theme.SCDLTheme

@Composable
fun PanicScreen(message: String) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center),
                style = MaterialTheme.typography.headlineLarge,
                text = "SCDL Panicked :("
            )
            SelectionContainer {
                Text(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .padding(horizontal = 8.dp, vertical = 20.dp)
                        .verticalScroll(rememberScrollState())
                        .horizontalScroll(rememberScrollState()),
                    text = message,
                    softWrap = false
                )
            }
        }
    }
}

@Preview(name = "Panic - Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Panic - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PanicScreenPreview() {
    SCDLTheme {
        PanicScreen(
            message = "Error: Panicked :("
    }
}
