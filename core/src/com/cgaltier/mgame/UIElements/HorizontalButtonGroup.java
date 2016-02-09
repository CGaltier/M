package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.cgaltier.mgame.MGame;
import com.badlogic.gdx.scenes.scene2d.ui.Button;


/**
 * Created by Christian on 05/02/2016.
 */
public class HorizontalButtonGroup extends HorizontalGroup{




   Array<MyButtonInToolbar> array;
   MGame game;

   public HorizontalButtonGroup (MGame game){
      array = new Array<MyButtonInToolbar>();
   }

   public void memoPosition() {
      for (MyButtonInToolbar button : array) {
         button.memoPosition();
      }
   }
   public void addButton (MyButtonInToolbar button)
   {
      this.addActor(button);
      array.add(button);

   }

   public void hideToolbar(){
      float duration = 0.2f;
      float move = -10.0f;
      float alphaStart = 1.0f;
      float alphaEnd = 0.0f;
      Interpolation alphaInterpolation = Interpolation.fade;
      Interpolation moveInterpolation = Interpolation.linear;
      int buttonIndex = 0;
      for (MyButtonInToolbar button : array) {
         buttonIndex++;
         //button.setPosition(button.memoPosX, button.memoPosY);
         //button.setColor(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart);
         button .addAction(
            Actions.sequence(
               Actions.moveTo(button.memoPosX, button.memoPosY),
               Actions.color(new Color(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart)),
               Actions.show(),
               Actions.delay(((float) buttonIndex) * duration / 2.0f),
               Actions.parallel(
                  Actions.moveBy(move, 0, duration, moveInterpolation),
                  Actions.alpha(alphaEnd, duration, alphaInterpolation)),
                  Actions.after(Actions.hide()),
                  Actions.after(Actions.sizeTo(button.getWidth(), button.memoPosHeight))));

      }
   }
   public void showToolbar(){
      //float duration = 0.25f;
      float duration = 1.0f;
      float move = 10.0f;
      float alphaStart = 0.0f;
      float alphaEnd = 1.0f;
      Interpolation alphaInterpolation = Interpolation.fade;
      Interpolation moveInterpolation = Interpolation.linear;
      int buttonIndex = 0;
      for (MyButtonInToolbar button : array) {
         //button.setPosition(button.memoPosX- move, button.memoPosY);
         //button.setColor(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart);
         //warning with show/hide, if hide somewhere show must be done, else sequence doesn't seem to work and only final pos / alpha will pop (i.e. no animation)
         button.addAction(
            Actions.sequence(
            Actions.hide(),
            Actions.moveTo(button.memoPosX- move, button.memoPosY),
            Actions.sizeTo(button.getWidth(), 0.0f),
            Actions.color(new Color(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart)),

            Actions.delay(((float) buttonIndex) * duration / 2.0f),
                  Actions.show(),
                  Actions.parallel(
                     Actions.moveBy(move, 0, duration, moveInterpolation),
                     Actions.alpha(alphaEnd, duration, alphaInterpolation)),
                     new Action() {
                        public boolean act(float delta) {
                           float alphaEnd = 1.0f;
                           this.actor.setColor(getColor().r, getColor().g, getColor().b, alphaEnd);
                           this.actor.setPosition(((MyButtonInToolbar) actor).memoPosX,((MyButtonInToolbar)actor).memoPosY);
                           return true;
                        };
                     }));
         buttonIndex++;
      }
   }
   public void showTopDown(){
      float duration = 0.25f;
      float alphaStart = 0.0f;
      final float alphaEnd = 1.0f;
      Interpolation alphaInterpolation = Interpolation.fade;
      Interpolation moveInterpolation = Interpolation.linear;
      int buttonIndex = 0;
      for (MyButtonInToolbar button : array) {

         //button.setPosition(button.memoPosX, button.memoPosY+button.memoPosHeight);
         //button.setSize(button.getWidth(), 0.0f);
         //button.setColor(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart);
         button.addAction(
            Actions.sequence(
               Actions.hide(),
               Actions.moveTo(button.memoPosX, button.memoPosY+button.memoPosHeight),
               Actions.sizeTo(button.getWidth(), 0.0f),
               Actions.color(new Color(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart)),
               Actions.delay(((float) buttonIndex) * duration / 2.0f),
               Actions.show(),
               Actions.parallel(
                  Actions.moveBy(0, -button.memoPosHeight, duration, moveInterpolation),
                  Actions.sizeTo(button.getWidth(), button.memoPosHeight, duration, moveInterpolation),
                  Actions.alpha(alphaEnd, duration, alphaInterpolation)),
                  new Action() {
                     public boolean act(float delta) {
                        this.actor.setVisible(true);
                        this.actor.setColor(getColor().r, getColor().g, getColor().b, alphaEnd);
                        this.actor.setPosition(((MyButtonInToolbar) actor).memoPosX, ((MyButtonInToolbar) actor).memoPosY);
                        return true;
                        };
                     }));
            buttonIndex++;
      }
   }
   public void showDownTop(){
      float duration = 0.25f;
      float alphaStart = 0.0f;
      final float alphaEnd = 1.0f;
      Interpolation alphaInterpolation = Interpolation.fade;
      Interpolation moveInterpolation = Interpolation.linear;

      int buttonIndex = 0;
      for (MyButtonInToolbar button : array) {

         //button.setPosition(button.memoPosX, button.memoPosY);
         //button.setSize(button.getWidth(), 0.0f);
         //button.setColor(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart);

         button.addAction(
            Actions.sequence(
               Actions.hide(),
               Actions.moveTo(button.memoPosX, button.memoPosY),
               Actions.sizeTo(button.getWidth(), 0.0f),
               Actions.color(new Color(button.getColor().r, button.getColor().g, button.getColor().b, alphaStart)),
               Actions.delay(((float) buttonIndex) * duration / 2.0f),
               Actions.show(),
               Actions.parallel(
                  Actions.sizeTo(button.getWidth(), button.memoPosHeight, duration, moveInterpolation),
                  Actions.alpha(alphaEnd, duration, alphaInterpolation)),
                  new Action() {
                     public boolean act(float delta) {
                        this.actor.setVisible(true);
                        this.actor.setColor(getColor().r, getColor().g, getColor().b, alphaEnd);
                        this.actor.setPosition(((MyButtonInToolbar) actor).memoPosX, ((MyButtonInToolbar)actor).memoPosY);
                        return true;
                     };
                  }));
         buttonIndex++;
      }
   }

   public void hideToolbarNoAnim() {

   }
}
