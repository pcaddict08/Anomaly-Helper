package com.phtiv.anomalyhelper.utils

import com.phtiv.anomalyhelper.R

class GeneralUtil {
    companion object {
        fun getWinningScore(WINNER: String, RES_SCORE: String, ENL_SCORE: String): String {
            var winningScore = "N/A"
            if (WINNER.equals("RES"))
                winningScore = RES_SCORE
            else if (WINNER.equals("ENL"))
                winningScore = ENL_SCORE
            return winningScore
        }

        fun getWinningColor(WINNER: String): Int {
            var winningColor = R.color.darkgray
            if (WINNER.equals("RES"))
                winningColor = R.color.darkblue
            else if (WINNER.equals("ENL"))
                winningColor = R.color.darkgreen
            return winningColor
        }

        fun getLoserColor(WINNER: String): Int {
            var losingColor = R.color.darkgray
            if (WINNER.equals("RES"))
                losingColor = R.color.darkgreen
            else if (WINNER.equals("ENL"))
                losingColor = R.color.darkblue
            return losingColor
        }

        fun getLosingScore(WINNER: String, RES_SCORE: String, ENL_SCORE: String): CharSequence? {
            var losingScore = "N/A"
            if (WINNER.equals("RES"))
                losingScore = ENL_SCORE
            else if (WINNER.equals("ENL"))
                losingScore = RES_SCORE
            return losingScore
        }
    }
}