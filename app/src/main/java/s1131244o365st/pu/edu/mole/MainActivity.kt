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
import androidx.compose.ui.layout.onGloballyPositioned
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
    val time = moleViewModel.time
    val offsetX = moleViewModel.offsetX
    val offsetY = moleViewModel.offsetY
    val isGameOver = moleViewModel.isGameOver

    val density = LocalDensity.current
    val moleSizeDp = 150.dp
    val moleSizePx = with(density) { moleSizeDp.roundToPx() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { layoutCoordinates ->
                // ✅ 用 onGloballyPositioned 拿實際螢幕像素大小
                val width = layoutCoordinates.size.width
                val height = layoutCoordinates.size.height
                moleViewModel.updateGameArea(width, height, moleSizePx)
            },
        contentAlignment = Alignment.Center
    ) {
        if (!isGameOver) {
            Text("分數: $counter\n時間: $time 秒")

            Image(
                painter = painterResource(id = R.drawable.mole),
                contentDescription = "地鼠",
                modifier = Modifier
                    // ✅ 根據 offset (px) 設定位置
                    .offset { IntOffset(offsetX, offsetY) }
                    .size(moleSizeDp)
                    .clickable {
                        moleViewModel.incrementCounter()
                    }
            )
        } else {
            Text("遊戲結束！\n最終分數: $counter")
        }
    }

    LaunchedEffect(Unit) {
        moleViewModel.startGame()
    }
}
