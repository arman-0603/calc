package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber = 0.0
    private var operation=""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView)

        //numbers

        val btn0: Button = findViewById<Button>(R.id.zeroBtn)
        val btn1: Button = findViewById<Button>(R.id.oneBtn)
        val btn2: Button = findViewById<Button>(R.id.twoBtn)
        val btn3: Button = findViewById<Button>(R.id.threeBtn)
        val btn4: Button = findViewById<Button>(R.id.fourBtn)
        val btn5: Button = findViewById<Button>(R.id.fiveBtn)
        val btn6: Button = findViewById<Button>(R.id.sixBtn)
        val btn7: Button = findViewById<Button>(R.id.sevenBtn)
        val btn8: Button = findViewById<Button>(R.id.eightBtn)
        val btn9: Button = findViewById<Button>(R.id.nineBtn)

        //operators

        val btnPlus: Button = findViewById<Button>(R.id.plusBtn)
        val btnMinus: Button = findViewById<Button>(R.id.minusBtn)
        val btnMultiply: Button = findViewById<Button>(R.id.multiplyBtn)
        val btnDivide: Button = findViewById<Button>(R.id.divideBtn)
        val btnPercent: Button = findViewById<Button>(R.id.percentBtn)
        val btnDot: Button = findViewById<Button>(R.id.dotBtn)

        //operations

        val btnEqual: Button = findViewById<Button>(R.id.equalBtn)
        val btnClear: Button = findViewById<Button>(R.id.clearBtn)
        val btnBack: Button = findViewById<Button>(R.id.backBtn)

        btn0.setOnClickListener{appendNumber("0")}
        btn1.setOnClickListener{appendNumber("1")}
        btn2.setOnClickListener{appendNumber("2")}
        btn3.setOnClickListener{appendNumber("3")}
        btn4.setOnClickListener{appendNumber("4")}
        btn5.setOnClickListener{appendNumber("5")}
        btn6.setOnClickListener{appendNumber("6")}
        btn7.setOnClickListener{appendNumber("7")}
        btn8.setOnClickListener{appendNumber("8")}
        btn9.setOnClickListener{appendNumber("9")}
        btnDot.setOnClickListener{appendNumber(".")}

        btnPlus.setOnClickListener{setOperation("+")}
        btnMinus.setOnClickListener{setOperation("-")}
        btnMultiply.setOnClickListener{setOperation("×")}
        btnDivide.setOnClickListener{setOperation("÷")}
        btnPercent.setOnClickListener{setOperation("%")}


        btnEqual.setOnClickListener{calculateResult()}
        btnClear.setOnClickListener{clear()}
        btnBack.setOnClickListener{back()}

    }
    private fun appendNumber(number: String) {
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else {
            resultTextView.text="${resultTextView.text}$number"
        }
}
    private fun setOperation(operation: String) {
            firstNumber = resultTextView.text.toString().toDouble()
            this.operation = operation
            isNewOperation = true
            previousCalculationTextView.text = "$firstNumber $operation"
    }
    private fun calculateResult() {
        try {
            val secondNumber = resultTextView.text.toString().toDouble()
            var result = 0.0
            when (operation) {
                "+" -> result = firstNumber + secondNumber
                "-" -> result = firstNumber - secondNumber
                "×" -> result = firstNumber * secondNumber
                "÷" -> result = firstNumber / secondNumber
                "%" -> result = firstNumber % secondNumber
                else ->result=secondNumber
        }
        resultTextView.text = result.toString()
        previousCalculationTextView.text = "$firstNumber $operation $secondNumber ="
        isNewOperation=true
        }
        catch(e:Exception) {
            resultTextView.text = "Error"
            return
        }
    }
    private fun clear() {
        resultTextView.text = "0"
        previousCalculationTextView.text = ""
        isNewOperation = true
        firstNumber=0.0
        operation=""
    }
    private fun back() {
        val currentText = resultTextView.text.toString()
        if (currentText.length > 1 && currentText!= "Error") {
            resultTextView.text = currentText.substring(0, currentText.length - 1)
        }
        else{
            resultTextView.text="0"
        }
    }
}
