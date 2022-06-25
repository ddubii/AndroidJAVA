package hbkim.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.stream.Stream;

public class cal_resultActivity extends AppCompatActivity {
    TextView cal_result;
    Button ok;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_result);

        cal_result = findViewById(R.id.calResult);
        ok = findViewById(R.id.goBack);

        // intent로 arraylist받아오기
        Intent intent = getIntent();
        ArrayList<String> list2 = (ArrayList<String>) intent.getSerializableExtra("list2");

        //받아온 list로 총합 계산하기
        String[] result = list2.toArray(new String[0]);
        Integer[] arr = Stream.of(result).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        cal_result.setText("오늘 먹은 음식의 칼로리 총합은" + sum +"cal 입니다.\n");

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent confirm = new Intent(cal_resultActivity.this, MainActivity.class);
                startActivity(confirm);
            }
        });
    }
}

