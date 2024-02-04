package com.example.simplecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //global variable for selected operation
        lateinit var selectedOperation: String

        //declare and initialize objects using their IDs
        val fieldOne: EditText = findViewById(R.id.editTextNumberDecimal)
        val fieldTwo: EditText = findViewById(R.id.editTextNumberDecimal2)
        val spinner: Spinner = findViewById(R.id.spinner2)
        val calculate: Button = findViewById(R.id.button)
        val result: TextView = findViewById(R.id.textView)

        result.text = ""

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            this,
            R.array.operations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                selectedOperation = parent?.getItemAtPosition(pos).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        //onClickListener for calculate button
        calculate.setOnClickListener { view: View ->

            //get the values and convert to Double
            var fieldOneValue: Double = fieldOne.text.toString().toDouble()
            var fieldTwoValue: Double = fieldTwo.text.toString().toDouble()

            //cases for different operators
            if(selectedOperation == "Addition") {
                result.text = (fieldOneValue + fieldTwoValue).toString()
            }
            else if(selectedOperation == "Subtraction") {
                result.text = (fieldOneValue - fieldTwoValue).toString()
            }
            else if(selectedOperation == "Multiplication") {
                result.text = (fieldOneValue * fieldTwoValue).toString()
            }
            else if(selectedOperation == "Division") {
                //check for Divide by Zero
                if(fieldTwoValue == 0.0) {
                    result.text = "Divide by Zero Not Allowed"
                }
                else {
                    result.text = (fieldOneValue / fieldTwoValue).toString()
                }
            }
            else {
                //check for Divide by Zero
                if(fieldTwoValue == 0.0) {
                    result.text = "Modulus by Zero Not Allowed"
                }
                else {
                    result.text = (fieldOneValue % fieldTwoValue).toString()
                }
            }
        }







    }
}