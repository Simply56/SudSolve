package com.bluemarble.sudsolve

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val solveButton: Button = findViewById(R.id.solve_button)
        val clearButton: Button = findViewById(R.id.clear_button)

        solveButton.setOnClickListener { solveBoard(getBoard()) }
        clearButton.setOnClickListener { clearBoard() }

        fitBoard()
    }

    private fun fitBoard() {
        val viewBoard = getViews()
        val horizontalHigh: View = findViewById(R.id.horizontal_high)
        val horizontalLow: View = findViewById(R.id.horizontal_low)
        val verticalRight: View = findViewById(R.id.vertical_right)
        val verticalLeft: View = findViewById(R.id.vertical_left)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels


        val size = if (width <= height) (width / 9) - 5 else (height / 9) - 5

        for (row in viewBoard) {
            for (cell in row) {
                cell.width = size
                cell.height = size
            }
        }
        var layoutParams = horizontalHigh.layoutParams
        layoutParams.width = size * 9
        horizontalHigh.layoutParams = layoutParams

        layoutParams = horizontalLow.layoutParams
        layoutParams.width = size * 9
        horizontalLow.layoutParams = layoutParams

        layoutParams = verticalLeft.layoutParams
        layoutParams.height = size * 9
        verticalLeft.layoutParams = layoutParams

        layoutParams = verticalRight.layoutParams
        layoutParams.height = size * 9
        verticalRight.layoutParams = layoutParams


    }

    private fun solveBoard(board: List<IntArray>) {

        fun findEmpty(bo: List<IntArray>): Pair<Int, Int>? {
            for (i in bo.indices) {
                for (j in bo[i].indices) {
                    if (bo[i][j] == 0) {
                        return Pair(i, j)
                    }
                }
            }
            return null
        }

        fun valid(bo: List<IntArray>, num: Int, row: Int, col: Int): Boolean {
            // Check row
            for (i in bo.indices) {
                if (bo[row][i] == num && i != col) {
                    return false
                }
            }

            // Check column
            for (i in bo.indices) {
                if (bo[i][col] == num && i != row) {
                    return false
                }
            }

            // Check box
            val boxStartRow = 3 * (row / 3)
            val boxStartCol = 3 * (col / 3)
            for (i in boxStartRow until boxStartRow + 3) {
                for (j in boxStartCol until boxStartCol + 3) {
                    if (bo[i][j] == num && (i != row || j != col)) {
                        return false
                    }
                }
            }

            return true
        }


        fun solve(bo: List<IntArray>): Boolean {
            val find = findEmpty(bo)
            if (find == null) {
                return true
            } else {
                val (row, col) = find
                for (i in 1..9) {
                    if (valid(bo, i, row, col)) {
                        bo[row][col] = i
                        if (solve(bo)) {
                            return true
                        }
                        bo[row][col] = 0
                    }
                }
                return false
            }
        }


        fun checkBoard(): Boolean {
            for (i in 0 until 9) {
                for (j in 0 until 9) {

                    if (board[i][j] != 0 && !valid(board, board[i][j], i, j)) {
                        Toast.makeText(
                            this,
                            "Invalid board, check ${getCellId(i, j)}",
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }
                }
            }
            return true
        }

        if (!checkBoard()) { // check if the provided board can be solved
            return
        } else {
            solve(board)
            setBoard(board)
        }


    }

    private fun getCellId(row: Int, col: Int): String {
        val letters = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I")
        return "${letters[col]}${row + 1}"
    }

    private fun getViews(): List<List<EditText>> {
        return listOf(
            listOf(
                findViewById(R.id.A1),
                findViewById(R.id.B1),
                findViewById(R.id.C1),
                findViewById(R.id.D1),
                findViewById(R.id.E1),
                findViewById(R.id.F1),
                findViewById(R.id.G1),
                findViewById(R.id.H1),
                findViewById(R.id.I1),
            ),
            listOf(
                findViewById(R.id.A2),
                findViewById(R.id.B2),
                findViewById(R.id.C2),
                findViewById(R.id.D2),
                findViewById(R.id.E2),
                findViewById(R.id.F2),
                findViewById(R.id.G2),
                findViewById(R.id.H2),
                findViewById(R.id.I2),
            ),
            listOf(
                findViewById(R.id.A3),
                findViewById(R.id.B3),
                findViewById(R.id.C3),
                findViewById(R.id.D3),
                findViewById(R.id.E3),
                findViewById(R.id.F3),
                findViewById(R.id.G3),
                findViewById(R.id.H3),
                findViewById(R.id.I3),
            ),
            listOf(
                findViewById(R.id.A4),
                findViewById(R.id.B4),
                findViewById(R.id.C4),
                findViewById(R.id.D4),
                findViewById(R.id.E4),
                findViewById(R.id.F4),
                findViewById(R.id.G4),
                findViewById(R.id.H4),
                findViewById(R.id.I4),
            ),
            listOf(
                findViewById(R.id.A5),
                findViewById(R.id.B5),
                findViewById(R.id.C5),
                findViewById(R.id.D5),
                findViewById(R.id.E5),
                findViewById(R.id.F5),
                findViewById(R.id.G5),
                findViewById(R.id.H5),
                findViewById(R.id.I5),
            ),
            listOf(
                findViewById(R.id.A6),
                findViewById(R.id.B6),
                findViewById(R.id.C6),
                findViewById(R.id.D6),
                findViewById(R.id.E6),
                findViewById(R.id.F6),
                findViewById(R.id.G6),
                findViewById(R.id.H6),
                findViewById(R.id.I6),
            ),
            listOf(
                findViewById(R.id.A7),
                findViewById(R.id.B7),
                findViewById(R.id.C7),
                findViewById(R.id.D7),
                findViewById(R.id.E7),
                findViewById(R.id.F7),
                findViewById(R.id.G7),
                findViewById(R.id.H7),
                findViewById(R.id.I7),
            ),
            listOf(
                findViewById(R.id.A8),
                findViewById(R.id.B8),
                findViewById(R.id.C8),
                findViewById(R.id.D8),
                findViewById(R.id.E8),
                findViewById(R.id.F8),
                findViewById(R.id.G8),
                findViewById(R.id.H8),
                findViewById(R.id.I8),
            ),
            listOf(
                findViewById(R.id.A9),
                findViewById(R.id.B9),
                findViewById(R.id.C9),
                findViewById(R.id.D9),
                findViewById(R.id.E9),
                findViewById(R.id.F9),
                findViewById(R.id.G9),
                findViewById(R.id.H9),
                findViewById(R.id.I9),
            )
        )
    }

    private fun getBoard(): List<IntArray> {
        val viewBoard = getViews()
        val numBoard = mutableListOf(
            IntArray(9),
            IntArray(9),
            IntArray(9),
            IntArray(9),
            IntArray(9),
            IntArray(9),
            IntArray(9),
            IntArray(9),
            IntArray(9),
        )

        for (i in 0..8) {
            for (j in 0..8) {
                if (viewBoard[i][j].text.isEmpty()) {
                    numBoard[i][j] = 0
                } else {
                    numBoard[i][j] = viewBoard[i][j].text.toString().toInt()
                }
            }
        }

        return numBoard
    }

    private fun clearBoard() {
        val viewBoard = getViews()
        for (i in 0..8) {
            for (j in 0..8) {
                viewBoard[i][j].text.clear()
            }
        }
    }

    private fun setBoard(board: List<IntArray>) {
        val viewBoard: List<List<EditText>> = getViews()
        for (i in 0..8) {
            for (j in 0..8) {
                viewBoard[i][j].setText(board[i][j].toString())
            }
        }
    }
}