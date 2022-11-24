package com.exito.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView screenSolution, screenResult;
    MaterialButton buttonClear, buttonOpenParenthesis, buttonCloseParenthesis, buttonDivide;
    MaterialButton buttonSeven, buttonEight, buttonNine, buttonMultiply;
    MaterialButton buttonFour, buttonFive, buttonSix, buttonMinus;
    MaterialButton buttonOne, buttonTwo, buttonThree, buttonPlus;
    MaterialButton buttonAllClear, buttonZero, buttonComma, buttonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenSolution = findViewById(R.id.screenSolution);
        screenResult = findViewById(R.id.screenResult);

        assignId(buttonClear, R.id.buttonClear);
        assignId(buttonAllClear, R.id.buttonAllClear);
        assignId(buttonComma, R.id.buttonComma);
        assignId(buttonOpenParenthesis, R.id.buttonOpenParenthesis);
        assignId(buttonCloseParenthesis, R.id.buttonCloseParenthesis);
        assignId(buttonDivide, R.id.buttonDivide);
        assignId(buttonMultiply, R.id.buttonMultiply);
        assignId(buttonPlus, R.id.buttonPlus);
        assignId(buttonMinus, R.id.buttonMinus);
        assignId(buttonEqual, R.id.buttonEqual);
        assignId(buttonZero, R.id.buttonZero);
        assignId(buttonOne, R.id.buttonOne);
        assignId(buttonTwo, R.id.buttonTwo);
        assignId(buttonThree, R.id.buttonThree);
        assignId(buttonFour, R.id.buttonFour);
        assignId(buttonFive, R.id.buttonFive);
        assignId(buttonSix, R.id.buttonSix);
        assignId(buttonSeven, R.id.buttonSeven);
        assignId(buttonEight, R.id.buttonEight);
        assignId(buttonNine, R.id.buttonNine);
    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = screenSolution.getText().toString();

        if(buttonText.equals("AC")){
            screenSolution.setText("");
            screenResult.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            screenSolution.setText(screenResult.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        screenSolution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            screenResult.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }

}