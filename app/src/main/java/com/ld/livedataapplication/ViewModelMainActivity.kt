package com.ld.livedataapplication

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelMainActivity: ViewModel() {
    private lateinit var timer:CountDownTimer
    private val  _second= MutableLiveData<Int>()
    var timerValue=MutableLiveData<Long>()
    var finished= MutableLiveData<Boolean>()
    fun seconds():LiveData<Int>{
        return _second
    }
    fun startTimer(){
        timer= object : CountDownTimer(timerValue.value!!.toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft= millisUntilFinished /1000
                _second.value= timeLeft.toInt()
            }

            override fun onFinish() {
                finished.value=true
            }
        }.start()
    }
    fun stopTimer(){
        timer.cancel()
    }
}