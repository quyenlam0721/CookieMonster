package com.example.cookiemonster;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    int totalcookie=0;
    int cookie = 0;
    int clock = 0;
    int created=0;
    int taken;
    int which;
    Random random = new Random();
    ArrayList<CookieMonster> cookieMonsters;

    TextView Eaten1;
    TextView Eaten2;
    TextView Baked;
    TextView Clock;
    Button startOver;
    Button Cancel;
    ProgressBar progressBarEaten1;
    ProgressBar progressBarEaten2;
    ProgressBar progressBarBaking;
    ProgressBar progressBarClock;
    TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeData();


        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        };
        timer.schedule(timerTask,0,1000);
    }

    protected void TimerMethod()
    {
        if(clock <= 120 && (cookieMonsters.get(0).eaten<100 || cookieMonsters.get(1).eaten<100)){
            clock++;
            if (clock % 5 == 0) {
                created = random.nextInt(11);
                totalcookie+=created;
                cookie+=created;
            }
            if(cookie>0) {
                taken = random.nextInt((cookie+1));
            }

            which = random.nextInt(2);
            cookieMonsters.get(which).cookie+=taken;
            cookie-=taken;
            taken=0;
            cookieMonsters.get(0).EatSomeCookie(clock);
            cookieMonsters.get(1).EatSomeCookie(clock);

            this.runOnUiThread(Timer_Tick);
        }
    }
    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            setProgressAnimate(progressBarEaten1,cookieMonsters.get(0).eaten);
            setProgressAnimate(progressBarEaten2,cookieMonsters.get(1).eaten);
            setProgressAnimate(progressBarBaking,clock%5);
            setProgressAnimate(progressBarClock,clock);

            Eaten1.setText("Eaten cookies so far: "+cookieMonsters.get(0).getEaten() +"( " + cookieMonsters.get(0).getCookie() + " cookies left)");
            Eaten2.setText("Eaten cookies so far: "+cookieMonsters.get(1).getEaten() +"( " + cookieMonsters.get(1).getCookie() + " cookies left)");
            Baked.setText("Baked cookie so far: "+totalcookie+ "\n( " + cookie + " cookies left)");
            Clock.setText("Stimulated Clock: " + clock + "/120s" );
        }
    };

    protected void initializeData(){
        Baked=findViewById(R.id.textViewBaked);
        Clock=findViewById(R.id.textViewClock);
        Eaten1=findViewById(R.id.textViewEaten1);
        Eaten2=findViewById(R.id.textViewEaten2);
        progressBarBaking=findViewById(R.id.progressBarBaking);
        progressBarClock=findViewById(R.id.progressBarClock);
        progressBarEaten1=findViewById(R.id.progressBarEaten1);
        progressBarEaten2=findViewById(R.id.progressBarEaten2);
        cookieMonsters = new ArrayList<CookieMonster>();
        cookieMonsters.add(new CookieMonster());
        cookieMonsters.add(new CookieMonster());
        startOver=findViewById(R.id.buttonStartOver);
        startOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartOverGame();
            }
        });
        Cancel=findViewById(R.id.buttonCancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        });
    }

    private void setProgressAnimate(ProgressBar pb, int progressTo)
    {
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 100);
        animation.setDuration(1500);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }
    private void StartOverGame()
    {
        timer.cancel();
        totalcookie=0;
        cookie = 0;
        clock = 0;
        created=0;
        cookieMonsters.clear();
        cookieMonsters.add(new CookieMonster());
        cookieMonsters.add(new CookieMonster());
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        };
        timer.schedule(timerTask,0,1000);
    }
}
