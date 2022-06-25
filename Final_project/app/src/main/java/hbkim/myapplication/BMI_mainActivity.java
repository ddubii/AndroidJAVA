package hbkim.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMI_mainActivity extends AppCompatActivity {
    EditText edit_height;
    EditText edit_weight;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_main);

        //액션바 설정하기//
        getSupportActionBar().setTitle("메인으로 돌아가기");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit_height = (EditText) findViewById(R.id.your_height);
        edit_weight = (EditText) findViewById(R.id.your_weight);
        edit_height.setBackgroundColor(Color.rgb(100, 100, 100));
        edit_weight.setBackgroundColor(Color.rgb(100, 100, 100));
        btn = (Button) findViewById(R.id.btnGetBMI);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height;
                String weight;

                height = edit_height.getText().toString();
                weight = edit_weight.getText().toString();

                Intent intent = new Intent(BMI_mainActivity.this, BMI_resultActivity.class);

                Bundle extras = new Bundle();
                extras.putString("height", height);
                extras.putString("weight", weight);

                intent.putExtras(extras);

                startActivity(intent);

                

            }
        });
    }
    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);
        }
        if (id == R.id.action_heart) {
            Toast.makeText(this, "힘내세요!!!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.gotoBMI) {
            Intent settingIntent = new Intent(this, BMI_mainActivity.class);
            startActivity(settingIntent);
        }
        if (id == R.id.gotoCal) {
            Intent settingIntent = new Intent(this, cal_mainActivity.class);
            startActivity(settingIntent);
        }
        if (id == R.id.action_about) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("plain/text");
            String[] address = {"wow_boo@sookmyung.ac.kr","mjkim12160@sookmyung.ac.kr"};
            email.putExtra(Intent.EXTRA_EMAIL, address);
            email.putExtra(Intent.EXTRA_SUBJECT, "문의 사항 있습니다.");
            email.putExtra(Intent.EXTRA_TEXT, "이름:\n문제사항:\n오류 화면 캡쳐(선택):\n");
            startActivity(email);
        }

        return super.onOptionsItemSelected(item);
    }

}