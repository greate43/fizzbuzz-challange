package com.sk.greate43.thefactorytestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val charArr = fizzbuzz(100)

        Observable.fromIterable(charArr)
            .subscribeOn(Schedulers.newThread())
            .delay(2, TimeUnit.SECONDS, Schedulers.trampoline())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG,"$it\n")
                tvFizzBuzz.append("$it\n")
            }


    }


    fun fizzbuzz(num: Int): MutableList<String> {
        val list = mutableListOf<String>()
        for (x in 1..num) {
            if (x % 3 == 0) {
                list += "fizz"
            } else if (x % 5 == 0) {
                list += "buzz"
            } else if (x % 15 == 0) {
                list += "fizzbuzz"
            } else {
                list += "$x"
            }
        }
        return list
    }

}