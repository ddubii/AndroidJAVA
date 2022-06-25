package hbkim.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class cal_mainActivity extends AppCompatActivity {
    ListView listView1;
    ListView listView2;
    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    Button btnAdd;
    Button ok;
    TextView calResult;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_main);

        //액션바 설정하기//
        getSupportActionBar().setTitle("메인으로 돌아가기");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ArrayList<String> list2 = new ArrayList<>(Arrays.asList("Amsterdam", "Paris", "London"));
        listView1 = (ListView)findViewById(R.id.listView1);
        listView2 = (ListView)findViewById(R.id.listView2);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        calResult = (TextView)findViewById(R.id.calResult);
        ok = (Button)findViewById(R.id.btnGetCal);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list1);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list2);

        listView1.setChoiceMode(ListView.CHOICE_MODE_NONE);
        listView1.setAdapter(adapter1);

        listView2.setChoiceMode(ListView.CHOICE_MODE_NONE);
        listView2.setAdapter(adapter2);

        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        final EditText edt2 = (EditText) findViewById(R.id.edt2);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String str = edt1.getText().toString();
                list1.add(str);
                adapter1.notifyDataSetChanged();
                edt1.setText("");

                String str2 = edt2.getText().toString();
                list2.add(str2);
                adapter2.notifyDataSetChanged();
                edt2.setText("");
            }
        });


        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent confirm = new Intent(cal_mainActivity.this, cal_resultActivity.class);
                confirm.putExtra("list2",list2);
                startActivity(confirm);
            }
        });

    }
    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //액션버튼을 클릭했을때의 동작
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