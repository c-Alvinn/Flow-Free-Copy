package com.example.flowcopy.play.games

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import com.example.flowcopy.R

class SecondGameActivity : AppCompatActivity() {


    private lateinit var resetImg: ImageView
    private lateinit var closeBtn: ImageView
    private lateinit var gridLayout: GridLayout
    private lateinit var pointsInitial: Array<Array<Int>>
    private lateinit var pointsCurrent: Array<Array<Int>>
    private lateinit var pointsFinal: Array<Array<Int>>
    private val pointColors = intArrayOf(Color.GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE)

    private val viewIds = arrayOf(
        R.id.N2view1, R.id.N2view2, R.id.N2view3, R.id.N2view4, R.id.N2view5,
        R.id.N2view6, R.id.N2view7,R.id.N2view8, R.id.N2view9, R.id.N2view10,
        R.id.N2view11, R.id.N2view12, R.id.N2view13, R.id.N2view14, R.id.N2view15,
        R.id.N2view16, R.id.N2view17, R.id.N2view18, R.id.N2view19, R.id.N2view20,
        R.id.N2view21, R.id.N2view22, R.id.N2view23, R.id.N2view24, R.id.N2view25
    )

    private var startPoint: View? = null
    private var endPoint: View? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_game)


        resetImg = findViewById(R.id.N2resetView)

        resetImg.setOnClickListener(){
            pointsCurrent = pointsInitial
            initializeGrid()
        }

        closeBtn = findViewById(R.id.N2backView)

        closeBtn.setOnClickListener(){
            finish()
        }

        gridLayout = findViewById(R.id.gridLayout)
        pointsInitial = arrayOf(
            arrayOf(1, 0, 2, 0, 4),
            arrayOf(0, 0, 3, 0, 5),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 2, 0, 4, 0),
            arrayOf(0, 1, 3, 5, 0)
        )
        pointsCurrent = pointsInitial.copyOf()
        pointsFinal = arrayOf(
            arrayOf(1, 2, 2, 4, 4),
            arrayOf(1, 2, 3, 4, 5),
            arrayOf(1, 2, 3, 4, 5),
            arrayOf(1, 2, 3, 4, 5),
            arrayOf(1, 1, 3, 5, 5)
        )

        initializeGrid()

        for (i in 0 until gridLayout.childCount) {
            val view = gridLayout.getChildAt(i) as View

            view.setOnTouchListener { _, event ->
                val rawX = event.rawX
                val rawY = event.rawY

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = view
                        endPoint = null
                    }
                    MotionEvent.ACTION_MOVE -> {
                        if (startPoint != null) {
                            for (j in 0 until gridLayout.childCount) {
                                val otherView = gridLayout.getChildAt(j)

                                val location = IntArray(2)
                                otherView.getLocationOnScreen(location)
                                val viewX = location[0]
                                val viewY = location[1]

                                val relativeX = rawX - viewX
                                val relativeY = rawY - viewY

                                if (relativeX >= 0 && relativeX <= otherView.width &&
                                    relativeY >= 0 && relativeY <= otherView.height
                                ) {
                                    endPoint = otherView
                                    drawPath()
                                    break
                                }
                            }
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        startPoint = null
                        endPoint = null

                        if (checkPuzzleCompletion()) {
                            // Verifica se o quebra-cabeça foi concluído
                            Toast.makeText(this, "Parabéns! Você concluiu o quebra-cabeça.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                true
            }
        }
    }

    private fun initializeGrid() {
        for (i in 0 until gridLayout.childCount) {
            val view = gridLayout.getChildAt(i) as View
            val value = pointsInitial[i / gridLayout.columnCount][i % gridLayout.columnCount]

            // Configura o background inicial e cor do ponto baseado no valor
            view.setBackgroundColor(if (value == 0) Color.GRAY else pointColors[value])
            pointsCurrent[i / gridLayout.columnCount][i % gridLayout.columnCount] = value
        }
    }

    private fun drawPath() {
        if (startPoint != null && endPoint != null) {
            val startColor = (startPoint!!.background as? ColorDrawable)?.color ?: Color.GRAY
            startPoint!!.setBackgroundColor(startColor)

            if (startColor != Color.GRAY) {
                val endRow = viewIds.indexOf(endPoint!!.id) / gridLayout.columnCount
                val endCol = viewIds.indexOf(endPoint!!.id) % gridLayout.columnCount

                val startRow = viewIds.indexOf(startPoint!!.id) / gridLayout.columnCount
                val startCol = viewIds.indexOf(startPoint!!.id) % gridLayout.columnCount

                pointsInitial = arrayOf(
                    arrayOf(1, 0, 2, 0, 4),
                    arrayOf(0, 0, 3, 0, 5),
                    arrayOf(0, 0, 0, 0, 0),
                    arrayOf(0, 2, 0, 4, 0),
                    arrayOf(0, 1, 3, 5, 0)
                )
                // Altera o valor na matriz atual apenas se o valor na matriz inicial for 0
                if (pointsInitial[endRow][endCol] == 0) {
                    pointsCurrent[endRow][endCol] = pointsCurrent[startRow][startCol]
                    endPoint!!.setBackgroundColor(pointColors[pointsCurrent[endRow][endCol]])
                }

            }

        }
    }

    private fun checkPuzzleCompletion(): Boolean {
        for (i in pointsCurrent.indices) {
            for (j in pointsCurrent[i].indices) {
                if (pointsCurrent[i][j] != pointsFinal[i][j]) {
                    return false
                }
            }
        }
        return true
    }

    private fun printMatrix() {
        Log.d("Matrix", "Current Matrix:")
        for (row in pointsCurrent) {
            for (value in row) {
                Log.d("Matrix", "$value ")
            }
            Log.d("Matrix", "")
        }
    }
    private fun printMatrixI() {
        Log.d("Matrix", "Initial Matrix:")
        for (row in pointsInitial) {
            for (value in row) {
                Log.d("Matrix", "$value ")
            }
            Log.d("Matrix", "")
        }
    }
}