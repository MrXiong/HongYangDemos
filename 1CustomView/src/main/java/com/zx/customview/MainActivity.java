package com.zx.customview;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btngoto = (Button) findViewById(R.id.btn_go);

        btngoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
            }
        });


        EditText etInput = (EditText) findViewById(R.id.et_input);

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                //如果在500毫秒之内又输入了，就会再次返回，然后就再次remove掉了上次
                //如果500毫秒内不输入了，就不会执行remove了，就直接跳转了
                //原理：每次都执行跳转流程，但是有个条件，下一次不会返回的情况下，下次一旦返回就把上次的跳转操作取消了
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                    Log.v("tag", "---" + s.toString());
                }
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.v("tag", "跳转======" + s.toString());
                    }
                };
                Log.v("tag", "(((((" + s.toString());
                handler.postDelayed(runnable, 500);

            }
        });


    }
}
