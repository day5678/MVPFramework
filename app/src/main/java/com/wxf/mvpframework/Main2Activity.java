package com.wxf.mvpframework;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    private final int[] mColors = new int[] {
            0xFFFF80AB,
            0xFFFF4081,
            0xFFFF5177,
            0xFFFF7997
    };

    private RelativeLayout mLayout;

    private NewCreditSesameView newCreditSesameView;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mLayout = findViewById(R.id.layout);
        Button mButton = findViewById(R.id.btn);
        newCreditSesameView = findViewById(R.id.sesame_view);
        mLayout.setBackgroundColor(mColors[0]);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int i = random.nextInt(950);
                newCreditSesameView.setSesameValues(i);
//                startColorChangeAnim();
            }
        });
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onTestEvent(TestEvent event)
    {
        Log.e("2222222222","22222222222");
    }

    public void startColorChangeAnim() {

        ObjectAnimator animator = ObjectAnimator.ofInt(mLayout, "backgroundColor", mColors);
        animator.setDuration(3000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }
}
