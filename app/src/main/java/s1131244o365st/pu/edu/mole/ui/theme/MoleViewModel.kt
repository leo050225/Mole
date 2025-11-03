package s1131244o365st.pu.edu.mole.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MoleViewModel : ViewModel() {

    var counter by mutableLongStateOf(0)
        private set

    var stay by mutableLongStateOf(0)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    var maxX by mutableStateOf(0)
        private set

    var maxY by mutableStateOf(0)
        private set

    var offsetX by mutableStateOf(0)
        private set

    var offsetY by mutableStateOf(0)
        private set

    fun incrementCounter() {
        if (!isGameOver) counter++
    }

    // 計算地鼠可移動範圍（左上角座標）
    fun getArea(gameSize: IntSize, moleSize: Int) {
        val padding = 8
        maxX = (gameSize.width - moleSize - padding).coerceAtLeast(padding)
        maxY = (gameSize.height - moleSize - padding).coerceAtLeast(padding)
    }

    // 隨機移動地鼠，四角不超出螢幕
    fun moveMole() {
        offsetX = (0..maxX).random()
        offsetY = (0..maxY).random()
    }

    // 開始遊戲計時，每秒移動一次，60 秒後結束
    fun startCounting() {
        if (isGameOver) return
        viewModelScope.launch {
            while (stay < 60) {
                delay(1000L)
                stay++
                moveMole()
            }
            isGameOver = true
        }
    }
}
