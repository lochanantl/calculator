package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var a: TextView? = null/// we are creating an var a,which is a type of textview
    private var lastnumeric: Boolean = false
    private var lastdoc: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        a = findViewById(R.id.tvinput) /////we are assign a tvinput id to var a
    }

    fun onDigit(view: View) {
        a?.append((view as Button).text)
        lastnumeric = true
        lastdoc = false
    }

    fun onclear(view: View) {
        a?.text = " "
    }

    fun ondecimalpoint(View: View) {
        if (lastnumeric && !lastdoc) {
            a?.append(".")
            lastnumeric = false
            lastdoc = true
        }
    }

    fun onoperator(View: View) {
        a?.text?.let {

            if (lastnumeric && !isoperatoradded(it.toString())){
                a?.append((View as Button).text)
                lastnumeric=false
                lastdoc=false
            }
        }
    }


    fun onequal(View: View)
    {
        if(lastnumeric)
        {
            var tvvalue = a?.text.toString()
            var prefix = " "
            try{
                if(tvvalue.startsWith("-"))
                {
                    prefix="-"
                    tvvalue=tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                    val splitvalue=tvvalue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    a?.text=removezeroafterdot((one.toDouble() - two.toDouble()).toString())
                }else if(tvvalue.contains("+")){
                    val splitvalue=tvvalue.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    a?.text=removezeroafterdot((one.toDouble() + two.toDouble()).toString())
                }else if(tvvalue.contains("/")){
                    val splitvalue=tvvalue.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    a?.text=removezeroafterdot((one.toDouble() / two.toDouble()).toString())
                }else if(tvvalue.contains("*")){
                    val splitvalue=tvvalue.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    a?.text=removezeroafterdot((one.toDouble() * two.toDouble()).toString())
                }


            }catch(e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }
private fun removezeroafterdot(result : String) : String
{
    var value = result
    if (result.contains("0"))
        value = result.substring(0,result.length -2)
    return value
}

    private fun isoperatoradded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}
