package s1131244o365st.pu.edu.mole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import s1131244o365st.pu.edu.mole.ui.theme.MoleTheme
import s1131244o365st.pu.edu.mole.ui.theme.MoleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoleTheme {
                MoleScreen()
            }
        }
    }
}
@Composable
fun MoleScreen(
    moleViewModel: MoleViewModel = viewModel()
) {
    val counter = moleViewModel.counter
    val stay = moleViewModel.stay
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.Center
    ) {
        Text("分數: $counter \n時間: $stay")
    }

    Image(
        painter = painterResource(id = R.drawable.mole),
        contentDescription = "地鼠",
        modifier = Modifier
            .offset { IntOffset(50, 200) }
            .size(150.dp)
            .clickable {
                moleViewModel.incrementCounter()
            }
    )
}
