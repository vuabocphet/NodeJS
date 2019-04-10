package com.nguyentinhdevelop.devos.nodejs;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class Screen extends AppCompatActivity {

    private TextView txtJS;
    private Button btnStart;
    private Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        txtJS=findViewById(R.id.txtJS);
        btnStart=findViewById(R.id.start);
        Typeface font=Typeface.createFromAsset(getAssets(),"font_1.ttf");
        txtJS.setTypeface(font);
        anim= AnimationUtils.loadAnimation(this,R.anim.animation_1);
        btnStart.setAnimation(anim);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen.this,MainActivity.class));
                finish();
            }
        });

    }
}