package hbkim.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class startLoginActivity extends AppCompatActivity {

    Button loginbtn;
    Button joinbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);


        loginbtn = (Button) findViewById(R.id.goLogin);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startLoginActivity.this, loginActivity.class);
                startActivity(intent);

            }
        });

        joinbtn = (Button) findViewById(R.id.goJoin);
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startLoginActivity.this, joinActivity.class);
                startActivity(intent);

            }
        });

    }


}