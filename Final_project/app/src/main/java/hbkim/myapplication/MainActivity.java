package hbkim.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private static final int GET_DATA_REQUEST = 1;
    TextView textGoal;
    TextView CountTV;
    Button reset;
    SensorManager sensorManager;
    Sensor stepCountSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // process received intent

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        if (username != null) {
            Toast.makeText(getApplicationContext(), "환영합니다. " + username + "님", Toast.LENGTH_LONG).show();
        }

        //액션바 설정하기//
        getSupportActionBar().setTitle("뚜벅뚜벅");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //달리는 사람 애니메이션 넣기
        ImageView iv_runner = (ImageView) findViewById(R.id.runner);
        Animation anim_running = AnimationUtils.loadAnimation(this, R.anim.running);
        iv_runner.startAnimation(anim_running);
        AnimationDrawable animation_drawable = new AnimationDrawable();
        BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(R.drawable.run1);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(R.drawable.run2);
        BitmapDrawable frame3 = (BitmapDrawable)getResources().getDrawable(R.drawable.run3);
        BitmapDrawable frame4 = (BitmapDrawable)getResources().getDrawable(R.drawable.run4);
        BitmapDrawable frame5 = (BitmapDrawable)getResources().getDrawable(R.drawable.run5);
        BitmapDrawable frame6 = (BitmapDrawable)getResources().getDrawable(R.drawable.run6);
        BitmapDrawable frame7 = (BitmapDrawable)getResources().getDrawable(R.drawable.run7);
        BitmapDrawable frame8 = (BitmapDrawable)getResources().getDrawable(R.drawable.run8);
        animation_drawable.addFrame(frame1, 200);
        animation_drawable.addFrame(frame2, 200);
        animation_drawable.addFrame(frame3, 200);
        animation_drawable.addFrame(frame4, 200);
        animation_drawable.addFrame(frame5, 200);
        animation_drawable.addFrame(frame6, 200);
        animation_drawable.addFrame(frame7, 200);
        animation_drawable.addFrame(frame8, 200);
        iv_runner.setBackgroundDrawable(animation_drawable);
        animation_drawable.start();

//        btn1 = (Button) findViewById(R.id.button1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, BMI_mainActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        btn2 = (Button) findViewById(R.id.button2);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, cal_mainActivity.class);
//                startActivity(intent);
//
//            }
//        });

        // 걸음수 기능
        CountTV = findViewById(R.id.stepCountView);
        textGoal = findViewById(R.id.textView);
        reset = (Button) findViewById(R.id.resetButton);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


        // 활동 퍼미션 체크
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {

            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        // 걸음 센서 세팅
// * 옵션
// - TYPE_STEP_DETECTOR:  리턴 값이 무조건 1, 앱이 종료되면 다시 0부터 시작
// - TYPE_STEP_COUNTER : 앱 종료와 관계없이 계속 기존의 값을 가지고 있다가 1씩 증가한 값을 리턴
//
        // 디바이스에 걸음 센서의 존재 여부 체크
        if (stepCountSensor == null) {
            Toast.makeText(this, "No Step Sensor", Toast.LENGTH_SHORT).show();
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 걸음 수 초기화
                var.currentSteps = 0;
                var.goal = 10000;
                CountTV.setText(String.valueOf(var.currentSteps));
                textGoal.setText(String.valueOf(var.goal));
            }
        });
    }

    public void onStart() {
        super.onStart();
        if (stepCountSensor != null) {
            //센서 속도 설정 옵션
            /*
            SENSOR_DELAY_NORMAL , _GAME : 20,000초 딜레이
            _UI : 6,000초 딜레이
            _FASTEST : 딜레이 없음
             */
            sensorManager.registerListener((SensorEventListener) this, stepCountSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if (sensorEvent.values[0] == 1.0f) {
                var.currentSteps++;
                var.goal--;
                CountTV.setText(String.valueOf(var.currentSteps));
                textGoal.setText(String.valueOf(var.goal));
            }

        }
    }

    @Override
    public void onAccuracyChanged (Sensor sensor,int i){

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
            Toast.makeText(this, var.goal+"걸음만 더!!", Toast.LENGTH_SHORT).show();
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