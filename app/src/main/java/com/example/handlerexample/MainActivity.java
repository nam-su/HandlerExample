package com.example.handlerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    Handler handler;

    // TextView 에 띄워줄 변수
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰 초기화
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        // 핸들러 객체 초기화
        handler = new Handler();

        // 버튼 클릭 리스너
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 버튼을 누를 때 마다 새로운 스레드 생성
                // 별도의 스레드 작성
                new Thread(){

                    @Override
                    public void run() {

                        for (int i = 0; i < 10; i++) {

                            try {

                                count ++;

                                // UI 를 지속적 으로 변경 해줘야 하기 때문에 handler 작성
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        textView.setText(""+count);

                                    }
                                });

                                // 1초 딜레이
                                Thread.sleep(1000);

                            } catch (InterruptedException e) {

                                throw new RuntimeException(e);

                            }

                        }


                    }

                }.start(); // Thread.start() 메서드 호출

            }
        });

    }
}