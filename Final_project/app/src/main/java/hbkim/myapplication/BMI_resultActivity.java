package hbkim.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMI_resultActivity extends AppCompatActivity {
    TextView BMI_results;
    Button ok;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_result);

        BMI_results = findViewById(R.id.textView2);
        ok = findViewById(R.id.goBack);
        String height;
        String weight;
        double BMI = 0;
        String grade = "";
        Bundle extras = getIntent().getExtras();
        height = extras.getString("height");
        weight = extras.getString("weight");

        //인텐트로 받아온 키과 몸무게를 계산해서 BMI 구하기
        int ht = Integer.parseInt(height);
        int wt = Integer.parseInt(weight);
        BMI = wt / (ht*ht*0.0001);
        BMI = Math.round(BMI * 100) / 100.0; //소수점 둘째자리까지만 구하기

        if (BMI <= 18.5){
            grade = "저체중";
        }else if(BMI>18.5 && BMI<= 22.9){
            grade = "정상";
        }else if(BMI>=23.0 && BMI<= 24.9){
            grade = "과체중";
        }else if(BMI>= 25.0){
            grade = "비만";
        }


        BMI_results.setText("회원님의 BMI는" +BMI+ "이고," + grade+ "입니다.\n");

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent confirm = new Intent(BMI_resultActivity.this, MainActivity.class);
                startActivity(confirm);
            }
        });
    }
}
