package com.ld.livedataapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelMainActivity= ViewModelProvider(this).get(ViewModelMainActivity::class.java)

        viewModelMainActivity.seconds().observe(this, Observer {
            tvNumber.text= it.toString()
        })
        viewModelMainActivity.finished.observe(this, Observer {
            if (it){
            Toast.makeText(this,"Finished",Toast.LENGTH_LONG).show()
        }
        })
        btnStart.setOnClickListener {
            if (edtNumber.text!!.isEmpty() || edtNumber.text!!.length<4){
                Toast.makeText(this,"Please chevk the value",Toast.LENGTH_LONG).show()
            }else {
                viewModelMainActivity.timerValue.value= edtNumber.text.toString().toLong()
                viewModelMainActivity.startTimer()
            }
        }
        btnStop.setOnClickListener {
            tvNumber.text="0"
            viewModelMainActivity.stopTimer()
        }
    }
}