package com.example.sylvain.storyloupio;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StoryLoupioActivity extends AppCompatActivity {
    private int code;
    private Button bChoice;
    private DatabaseManager databaseManager;
    private TextView allChaptersView;
    private TextView oneChapterView;
    private TextView tvChapterTitle;
    private TextView tvChapterContent;
    private Button btChapterProp1;
    private Button btChapterProp2;
    private Button btChapterProp3;
    private Button btChapterProp4;
    private Button btSaveGame;

    private MediaPlayer MusicMenu;
    private ChapterData oneChapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_loupio);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout ll = findViewById(R.id.llContent);
        ll.setBackgroundResource(R.drawable.foret);


//        this.bChoice=  findViewById(R.id.bChoice);
//        bChoice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i1 = new Intent( StoryLoupioActivity.this, StoryLoupioActivity.class );
//                i1.putExtra("idChapter", 12);
//                startActivity(i1);
//                finish();
//            }
//        });


        final Intent intent = getIntent();
        this.code = intent.getIntExtra("idChapter",1);

//        final TextView tv1 = findViewById( R.id.tvResultat );
//        tv1.setText( String.valueOf(message) );

        databaseManager = new DatabaseManager( this );

//        allChaptersView= findViewById(R.id.tvChaptersData);
//        List<ChapterData> allChapterData= databaseManager.getAllChaptersData();
//        for (ChapterData allChap : allChapterData ){
//            allChaptersView.append(allChap.toString()+ "\n\n");
//        }

//        oneChapterView= findViewById(R.id.tvChapterData);
//        if (tv1.getText() != ""){
//            String bla = String.valueOf(tv1.getText());
//            this.code= Integer.valueOf(bla);
//        }
//        else {
//            this.code =1;
//        }
        oneChapterData= databaseManager.getOneChapterData(this.code);
        databaseManager.close();
//        oneChapterView.append(oneChapterData.toString());

        tvChapterTitle= findViewById(R.id.tvTitle);
        tvChapterTitle.append(oneChapterData.getTitle());

        tvChapterContent= findViewById(R.id.tvContent);
        tvChapterContent.append(oneChapterData.getContent());

        btChapterProp1 = findViewById(R.id.btProp1);
        btChapterProp2 = findViewById(R.id.btProp2);
        btChapterProp3 = findViewById(R.id.btProp3);
        btChapterProp4 = findViewById(R.id.btProp4);

        btSaveGame = findViewById(R.id.btSaveLeave);
        btSaveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseManager.insertGameSave(code);
                databaseManager.close();
                Intent il = new Intent(StoryLoupioActivity.this, MainActivity.class);
                startActivity(il);
                finish();
            }
        });
        validationButton();

        MusicMenu = MediaPlayer.create(this, R.raw.menu);
        MusicMenu.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicMenu.release();
    }

    private void launchGame(ChapterData chapter){
        if (chapter.getIdGame()==1){
            Intent i1 = new Intent( StoryLoupioActivity.this, PlatformGame.class );
            i1.putExtra("idChapterBack", chapter.getIdChapter());
            Toast toast = Toast.makeText(getApplicationContext(), "Une épreuve vous attend ! Préparez-vous !", Toast.LENGTH_LONG);
            toast.show();
            startActivity(i1);
            finish();
        }
        else {
            tvChapterTitle.setText(chapter.getTitle());
            tvChapterContent.setText(chapter.getContent());
            btChapterProp1.setText(chapter.getTitleProp1());
            btChapterProp2.setText(chapter.getTitleProp2());
            btChapterProp3.setText(chapter.getTitleProp3());
            btChapterProp4.setText(chapter.getTitleProp4());
            validationButton();
//            Intent i1 = new Intent( StoryLoupioActivity.this, StoryLoupioActivity.class );
//            i1.putExtra("idChapter", chapter.getIdChapter());
//            startActivity(i1);
//            finish();
        }
    }

    private void validationButton() {
        System.out.println(oneChapterData.getTitleProp1());
        System.out.println(oneChapterData.getTitleProp2());
        System.out.println(oneChapterData.getTitleProp3());
        System.out.println(oneChapterData.getTitleProp4());

        if (oneChapterData.getTitleProp1() == null || oneChapterData.getTitleProp1().isEmpty()){
            btChapterProp1.setVisibility(View.GONE);
            System.out.println("prop 1 est nul");
        }
        else {
            btChapterProp1.setText(oneChapterData.getTitleProp1());
            btChapterProp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneChapterData= databaseManager.getOneChapterData(oneChapterData.getIdChapterProp1());
                    databaseManager.close();
                    launchGame(oneChapterData);
                }
            });
            btChapterProp1.setVisibility(View.VISIBLE);
        }

        if (oneChapterData.getTitleProp2() == null || oneChapterData.getTitleProp2().isEmpty()){
            btChapterProp2.setVisibility(View.GONE);
            System.out.println("prop 2 est nul");
        }
        else {
            btChapterProp2.setText(oneChapterData.getTitleProp2());
            btChapterProp2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneChapterData= databaseManager.getOneChapterData(oneChapterData.getIdChapterProp2());
                    launchGame(oneChapterData);
                }
            });
            btChapterProp2.setVisibility(View.VISIBLE);
        }

        if (oneChapterData.getTitleProp3() == null || oneChapterData.getTitleProp3().isEmpty()){
            btChapterProp3.setVisibility(View.GONE);
            System.out.println("prop 3 est nul");
        }
        else {
            btChapterProp3.setText(oneChapterData.getTitleProp3());
            btChapterProp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneChapterData= databaseManager.getOneChapterData(oneChapterData.getIdChapterProp3());
                    launchGame(oneChapterData);
                }
            });
            btChapterProp3.setVisibility(View.VISIBLE);
        }

        if (oneChapterData.getTitleProp4() == null || oneChapterData.getTitleProp4().isEmpty()){
            btChapterProp4.setVisibility(View.GONE);
            System.out.println("prop 4 est nul");
        }
        else {
            btChapterProp4.setText(oneChapterData.getTitleProp4());
            btChapterProp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneChapterData= databaseManager.getOneChapterData(oneChapterData.getIdChapterProp4());
                    launchGame(oneChapterData);
                }
            });
            btChapterProp4.setVisibility(View.VISIBLE);
        }

    }
}
