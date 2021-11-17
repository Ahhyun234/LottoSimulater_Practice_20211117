package com.nepplus.lottosimulater_practice_20211117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    val mMyLottoNumArr = ArrayList<Int>()
    var mEarnedMoney = 0L
    var mSpendMoney = 0L
    val mWinLottoNumArr = arrayListOf(17, 18, 21, 27, 29, 33, 26)
    val mRankCountList = arrayListOf(0, 0, 0, 0, 0, 0)
    lateinit var mLottoNumTxtList: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvent()
    }


    fun setupEvent() {

        btnBuyLotto.setOnClickListener {

                makeLottoNumbers()
                checkLottoRank()

        }

    }

    fun checkLottoRank() {
        mSpendMoney += 1000
        var correctCount = 0

        for (myNum in mWinLottoNumArr) {
            if (mMyLottoNumArr.contains(myNum)) {
                correctCount++
            }
        }
        when (correctCount) {
            6 -> {
                mEarnedMoney += 5000000000L
                mRankCountList[0]++
                txtWinCount1.text = "${mRankCountList[0]}회"

            }
            5 -> {
                mEarnedMoney += 50000000L
                mRankCountList[1]++
                txtWinCount2.text = "${mRankCountList[1]}회"
            }
            4 -> {
                mEarnedMoney += 50000L
                mRankCountList[3]++
                txtWinCount4.text = "${mRankCountList[3]}회"
            }
            3 -> {
                mSpendMoney -= 5000L
                mRankCountList[4]++
                txtWinCount5.text = "${mRankCountList[4]}회"
            }
            else -> {
                mRankCountList[5]++
                txtWinCount6.text = "${mRankCountList[5]}회"
            }

        }
        txtHowMuchSpendMoney.text = "${NumberFormat.getInstance().format(mSpendMoney)}원"
        txtHoWMuchEarnMoney.text = "${NumberFormat.getInstance().format(mEarnedMoney)}원"
    }

    fun makeLottoNumbers() {
        mMyLottoNumArr.clear()

        for (i in 0 until 6) {
            while (true) {
                val randomNum = (Math.random() * 45 + 1).toInt()
                val isDupOk = !mMyLottoNumArr.contains(randomNum)
                if (isDupOk) {
                    mMyLottoNumArr.add(randomNum)
                    break
                }
            }
        }
        mMyLottoNumArr.sort()
        for (i in 0 until 6) {
            mLottoNumTxtList[i].text = mMyLottoNumArr[i].toString()
        }

    }

    fun setValues() {

        mLottoNumTxtList = arrayListOf(
            txtBuyLottoNum1, txtBuyLottoNum2, txtBuyLottoNum3, txtBuyLottoNum4, txtBuyLottoNum5, txtBuyLottoNum6
        )

    }


}