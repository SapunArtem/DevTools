package com.example.calculatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.databinding.ActivityCalculatorBinding

class CalcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNumberButtons()
        setupOperationButtons()
        setupOtherButtons()
    }


    private fun setupNumberButtons(){
       with(binding){
           listOf(
               btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9
           ).forEach{button->
               button.setOnClickListener {
                   updateDisplay(calculator.processInput(button.text.toString()))
               }
           }
       }
    }

    private fun setupOperationButtons(){
        with(binding){
            listOf(
                btnMinus,btnMinus,btnDivision,btnMultiplication,btnPlus
            ).forEach{button->
                button.setOnClickListener {
                    when(button){
                        btnMinus -> updateDisplay(calculator.processInput("-"))
                        btnProcent ->  updateDisplay(calculator.processInput("%"))
                        btnDivision ->  updateDisplay(calculator.processInput("/"))
                        btnMultiplication ->  updateDisplay(calculator.processInput("*"))
                        btnPlus ->  updateDisplay(calculator.processInput("+"))
                    }
                }
            }
        }
    }

    private fun setupOtherButtons(){
        with(binding){
            listOf(
                btnAC,btnDel,btnComma,btnEquals
            ).forEach{button->
                button.setOnClickListener {
                    when(button){
                        btnAC -> updateDisplay(calculator.processInput("AC"))
                        btnDel -> updateDisplay(calculator.processInput("DEL"))
                        btnComma -> updateDisplay(calculator.processInput("."))
                        btnEquals ->{
                            updateDisplay(calculator.processInput("="))
                            tvAnswer.textSize = 45F
                            tvOperation.textSize = 25F
                        }
                    }
                }
            }
        }
    }

    private fun updateDisplay(text: String){

       with(binding){
           tvOperation.text = text
           if (text=="") tvAnswer.text = "" else tvAnswer.text = "=$text"
           tvOperation.textSize =45F
           tvAnswer.textSize =25F
       }


    }
}