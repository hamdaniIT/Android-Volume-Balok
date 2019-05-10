package com.example.volumebalok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String STATE_RESULT="state_result";
    EditText edtHeight,edtWidth,edtLength;
    Button btnCalculate;
    TextView tvResult;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtHeight= findViewById(R.id.edt_height);
        edtLength=findViewById(R.id.edt_length);
        edtWidth= findViewById(R.id.edt_width);
        btnCalculate=findViewById(R.id.but_calculate);
        tvResult=findViewById(R.id.tv_result);
        btnCalculate.setOnClickListener(this);
        if(savedInstanceState!= null){
            String result= savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.but_calculate){
            String inputLength= edtLength.getText().toString().trim();
            String inputHeight= edtHeight.getText().toString().trim();
            String inputWidth= edtWidth.getText().toString().trim();
            Boolean isEmtyFields=false;
            Boolean isInvalidDouble=false;
            if (TextUtils.isEmpty(inputLength)){
                isEmtyFields=true;
                edtLength.setError("This field cannot be empty");
            }
            if (TextUtils.isEmpty(inputHeight)){
                isEmtyFields=true;
                edtHeight.setError("This fiels canot be empty");
            }
            if (TextUtils.isEmpty(inputWidth)){
                isEmtyFields=true;
                edtWidth.setError("This fiels canot be empty");
            }
            Double length= toDouble(inputLength);
            Double width= toDouble(inputWidth);
            Double height= toDouble(inputHeight);
             if(length==null){
                 isInvalidDouble=true;
                 edtLength.setError("This field must be the correct number");
             }
            if(height==null){
                isInvalidDouble=true;
                edtHeight.setError("This field must be the correct number");
            }
            if(width==null){
                isInvalidDouble=true;
                edtWidth.setError("This field must be the correct number");
            }

            if(!isEmtyFields && !isInvalidDouble){
                double volume=length* width*height;
                tvResult.setText(String.valueOf(volume));
            }


        }
    }
    Double toDouble(String str){
        try {
            return Double.valueOf(str);

        }catch (NumberFormatException e){
            return null;
        }
    }
}
