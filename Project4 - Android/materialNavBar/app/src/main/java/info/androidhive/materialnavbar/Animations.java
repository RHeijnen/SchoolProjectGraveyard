package info.androidhive.materialnavbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;


/**
 * Created by lappie on 06-06-2015.
 * verschillende animations die je op ''dingen'' kan toepassen.
 */
public class Animations {
    private static int counter = 0;

    /**
     *
     * mijn eerste animation
     *      sucks tho
     */
    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX" ,0.5F, 0.8F, 1.0F);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 0.5F, 0.8F, 1.0F);
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 300 : -300, 0);
        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -50, 50, -30, 30, -20, 20, -5, 5, 0);
        animatorSet.playTogether(animatorTranslateX, animatorTranslateY, animatorScaleX, animatorScaleY);
        animatorSet.setInterpolator(new AnticipateInterpolator());
        animatorSet.setDuration(1000);
        animatorSet.start();

    }

    /**
     *
     *  animation van slidenerd tutorial
     *  deze is beetje raar met sliden/lijkt over elkaar te gaan soms
     */
    public static void animatestack(RecyclerView.ViewHolder holder, boolean goesDown) {
        int holderHeight = holder.itemView.getHeight();
        holder.itemView.setPivotY(goesDown == true ? 0 : holderHeight);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 300 : -300, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 1f, 0.4f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 1f, 1.3f, 1f);
        animatorTranslateY.setInterpolator(new AccelerateInterpolator());
        scaleY.setInterpolator(new OvershootInterpolator());
        scaleX.setInterpolator(new OvershootInterpolator());
        animatorSet.play(animatorTranslateY).before(scaleY).before(scaleX);
        animatorSet.setDuration(700);
        animatorSet.start();
    }

    /**
     *
     *  animation van slidenerd tutorial
     *  deze is wel ok
     */
    public static void animateSunblind(RecyclerView.ViewHolder holder, boolean goesDown) {
        int holderHeight = holder.itemView.getHeight();
        holder.itemView.setPivotY(goesDown == true ? 0 : holderHeight);
        holder.itemView.setPivotX(holder.itemView.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 300 : -300, 0);
        ObjectAnimator animatorRotation = ObjectAnimator.ofFloat(holder.itemView, "rotationX", goesDown == true ? -90f : 90, 0f);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 0.5f, 1f);
        animatorSet.playTogether(animatorTranslateY, animatorRotation, animatorScaleX);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     *
     *  animation van slidenerd tutorial
     *  deze is wel vet
     */
    public static void animateScatter(RecyclerView.ViewHolder holder, boolean goesDown) {
        counter = ++counter % 4;
        int holderHeight = holder.itemView.getHeight();
        int holderWidth = holder.itemView.getWidth();
        View holderItemView = holder.itemView;
        holderItemView.setPivotY(goesDown == true ? 0 : holderHeight);
        holderItemView.setPivotX(holderWidth / 2);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holderItemView, "translationY", goesDown == true ? 300 : -300, 0);
        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holderItemView, "translationX", counter == 1 || counter == 3 ? holderWidth : -holderWidth, 0);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(holderItemView, "scaleX", counter == 1 || counter == 2 ? 0 : 2, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(holderItemView, "scaleY", counter == 1 || counter == 2 ? 0 : 2, 1f);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(holderItemView, "alpha", 0f, 1f);
        animatorAlpha.setInterpolator(new AccelerateInterpolator(1.5f));
        animatorSet.playTogether(animatorAlpha, animatorScaleX, animatorScaleY, animatorTranslateX, animatorTranslateY);
        animatorSet.setDuration(1200).setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.start();
    }



}