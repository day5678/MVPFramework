package com.wxf.mvpframework;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView iv = findViewById(R.id.iv);

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv,"alpha",1f,0f,1f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv,"rotation",0f,90f,180f,360f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv,"translationX",0f,300f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv,"scaleX",1f,2f,1f);
//        objectAnimator.setDuration(2000);
//        objectAnimator.setRepeatCount(-1);
//        objectAnimator.start();

        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv,"alpha",1f,0f,1f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(iv,"rotation",0f,360f,0f);
        rotation.setRepeatCount(-1);
        ObjectAnimator translation = ObjectAnimator.ofFloat(iv,"translationX",0f,300f,0f);
        translation.setRepeatCount(-1);
        ObjectAnimator scale = ObjectAnimator.ofFloat(iv,"scaleX",1f,2f,1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotation).with(translation);
        animatorSet.setDuration(3000);
        animatorSet.start();

//        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.alpha);
//        animator.setTarget(iv);
//        animator.start();
    }
}
