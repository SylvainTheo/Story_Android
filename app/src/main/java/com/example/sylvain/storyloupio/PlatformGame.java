package com.example.sylvain.storyloupio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PlatformGame extends AppCompatActivity {
    private int idChapterBack;
    private Button btBackToChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Intent intent = getIntent();
        this.idChapterBack = intent.getIntExtra("idChapterBack",1);

        this.btBackToChapter = findViewById(R.id.btBackGame);
        btBackToChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),StoryLoupioActivity.class);
                otherActivity.putExtra("idChapter", idChapterBack);
                startActivity(otherActivity);
                finish();
            }
        });

    }
}
