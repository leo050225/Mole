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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
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

    val density = LocalDensity.current
    val moleSizeDp = 150.dp
    val moleSizePx = with(density) { moleSizeDp.roundToPx() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { intSize ->
                moleViewModel.getArea(intSize, moleSizePx)
            }
    ) {
        // 最上方文字
        Text(
            text = "資管二A 411312448 施聿觀",
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // 分數與時間在中間
        Text(
            text = "分數: $counter\n時間: $stay 秒",
            modifier = Modifier.align(Alignment.Center)
        )

        // 地鼠圖片
        Image(
            painter = painterResource(id = R.drawable.mole),
            contentDescription = "地鼠",
            modifier = Modifier
                .offset { IntOffset(moleViewModel.offsetX, moleViewModel.offsetY) }
                .size(moleSizeDp)
                .clickable { moleViewModel.incrementCounter() }
        )
    }

    LaunchedEffect(Unit) {
        moleViewModel.startCounting()
    }
}

