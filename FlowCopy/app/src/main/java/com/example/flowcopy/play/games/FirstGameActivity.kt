package com.example.flowcopy.play.games


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flowcopy.R

class FirstGameActivity : AppCompatActivity() {

    private lateinit var resetImg: ImageView
    private lateinit var closeBtn: ImageView
    private lateinit var popUp: LinearLayout
    private lateinit var nextGame: TextView
    private lateinit var backPopUp: TextView
    private lateinit var gridLayout: GridLayout
    private lateinit var pointsInitial: Array<Array<Int>>
    private lateinit var pointsCurrent: Array<Array<Int>>
    private lateinit var pointsFinal: Array<Array<Int>>
    private val pointColors = intArrayOf(Color.GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.WHITE, Color.MAGENTA)

    private val viewIds = arrayOf(
        R.id.view10, R.id.view11, R.id.view12,
        R.id.view13, R.id.view14, R.id.view15,
        R.id.view16, R.id.view17, R.id.view18
    )

    private var startPoint: View? = null
    private var endPoint: View? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_game)

        resetImg = findViewById(R.id.resetView)

        resetImg.setOnClickListener(){
            pointsCurrent = pointsInitial
            initializeGrid()
        }

        closeBtn = findViewById(R.id.backView)

        closeBtn.setOnClickListener(){
            finish()
        }

        gridLayout = findViewById(R.id.gridLayout)
        pointsInitial = arrayOf(
            arrayOf(1, 2, 0),
            arrayOf(0, 3, 2),
            arrayOf(1, 0, 3)
        )
        pointsCurrent = pointsInitial.copyOf()
        pointsFinal = arrayOf(
            arrayOf(1, 2, 2),
            arrayOf(1, 3, 2),
            arrayOf(1, 3, 3)
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
                            popUp = findViewById(R.id.popUp)
                            nextGame = findViewById(R.id.nextGame)
                            backPopUp = findViewById(R.id.backPopUp)
                            popUp.visibility = View.VISIBLE
                            nextGame.setOnClickListener(){
                                val intent = Intent(this, SecondGameActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            backPopUp.setOnClickListener(){
                                finish()
                            }
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
                    arrayOf(1, 2, 0),
                    arrayOf(0, 3, 2),
                    arrayOf(1, 0, 3)
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

}

