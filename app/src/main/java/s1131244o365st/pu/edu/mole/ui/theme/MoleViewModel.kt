package s1131244o365st.pu.edu.mole.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MoleViewModel : ViewModel() {

    var counter by mutableLongStateOf(0)
        private set

    var time by mutableLongStateOf(0)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    var offsetX by mutableStateOf(0)
        private set

    var offsetY by mutableStateOf(0)
        private set

    private var screenWidth = 0
    private var screenHeight = 0
    private var moleSize = 0

    fun updateGameArea(width: Int, height: Int, moleSizePx: Int) {
        screenWidth = width
        screenHeight = height
        moleSize = moleSizePx
    }

    fun incrementCounter() {
        if (!isGameOver) counter++
    }

    fun startGame() {
        if (isGameOver || screenWidth == 0) return
        viewModelScope.launch {
            while (time < 60) {
                delay(1000L)
                time++
                moveMoleRandomly()
            }
            isGameOver = true
        }
    }

    private fun moveMoleRandomly() {
        if (screenWidth > 0 && screenHeight > 0 && moleSize > 0) {
            val padding = 8  // 避免太貼邊
            val minX = padding
            val maxX = (screenWidth - moleSize - padding).coerceAtLeast(minX)
            val minY = padding
            val maxY = (screenHeight - moleSize - padding).coerceAtLeast(minY)

            offsetX = Random.nextInt(minX, maxX)
            offsetY = Random.nextInt(minY, maxY)
        }
    }
}
