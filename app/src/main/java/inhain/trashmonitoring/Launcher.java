package inhain.trashmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getActionBar().hide();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);


        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);


//        final AnimationSet animSet = new AnimationSet(true);
        Animation fade = AnimationUtils.loadAnimation(Launcher.this, R.anim.fade);
//        animSet.addAnimation(scaleZoom);


        imageView.startAnimation(fade);
        imageView.setVisibility(View.VISIBLE);



        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Launcher.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();


    }
}