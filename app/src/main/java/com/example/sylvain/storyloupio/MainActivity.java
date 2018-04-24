package com.example.sylvain.storyloupio;

import android.content.Intent;
import android.content.pm.ChangedPackages;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView ibPlay;
    private MediaPlayer sMenu;
    private Button bResume;
    private Button bExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout ll = findViewById(R.id.llContent);
        ll.setBackgroundResource(R.drawable.darker_menu);

        final DatabaseManager databaseManager = new DatabaseManager( this );

        this.bExit = findViewById(R.id.bExit);
        this.bResume = findViewById(R.id.bResume);
        this.bResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Chapter = databaseManager.getGameSave();
                Intent il = new Intent(getApplicationContext(),StoryLoupioActivity.class);
                il.putExtra("idChapter", Chapter);
                startActivity(il);
                finish();
            }
        });

        this.bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        this.ibPlay = findViewById(R.id.ibPlay);
        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),StoryLoupioActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        sMenu = MediaPlayer.create(this, R.raw.menu);
        sMenu.start();
        sMenu.setLooping(true);


//        databaseManager.insertChapter("Loupio le petit loup", "Aller vers la forêt ou vers la montagne ?", "Vers La forêt",2,"Vers La Montagne", 3,"tac1",1,"tac2",4);
//        databaseManager.insertChapter("La forêt", "La forêt est sombre", null,0,null, 0,null,0,null,0);
//        databaseManager.insertChapter("La montagne", "La montagne est haute", null,0,null, 0,null,0,null,0);
//        databaseManager.insertChapter("ruine", "c pa bô mdr", "traverser",1,"noyer", 2,null,0,null,0);
//        databaseManager.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sMenu.release();
    }
}
