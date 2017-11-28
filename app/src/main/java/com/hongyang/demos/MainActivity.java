package com.hongyang.demos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //methods();
        Button btnPlay = (Button) findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AntiEmulator.CheckEmulatorFiles();
                AntiEmulator.checkPipes();
            }
        });
    }

    private void methods() {
        Class<?> classType = null;
        try {
            classType = Class.forName("android.graphics.Bitmap");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = classType.getMethods();
        Method[] declaredMethods = classType.getDeclaredMethods();

        System.out.println("methods="+methods.length + "  ++++++  declaredMethods=" + declaredMethods.length);

        for (String name : removeRepeat(methods, 1)) {
            //System.out.println(methed);
            System.out.println("--------------"+name);
        }
        for (String name : removeRepeat(declaredMethods, 2)) {
            //System.out.println(methed);
            System.out.println("=============="+name);

        }
    }


    private List<String> removeRepeat(Method[] methods, int index) {
        Set set = new HashSet<String>();
        List<String> list = new ArrayList();
        for (int i = 0; i < methods.length; i++) {
            if (set.add(methods[i].getName())) {
                list.add(methods[i].getName());
            }
        }
       if(index == 1) {
           System.out.println("正是我需要的methods=-----------"+list.size());
       }
       if(index == 2) {
           System.out.println("declaredMethods==========="+list.size());
       }
        return list;
    }
}
