package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 09/02/2016.
 */
public class MyButtonInToolbar extends TextButton{
      public float memoPosX;
      public float memoPosHeight;
      public float memoPosY;
      Action pulse;
      Color initialColor ;
      Color hoveredColor1 ;
      Color hoveredColor2 ;
      final float pulseTime=0.85f;
      final float pulseTime2=0.5f;


      public void init (boolean allowHovering){
         memoPosX = 0;
         memoPosHeight = 0;
         memoPosY = 0;
         initialColor = new Color (this.getColor());
         hoveredColor1 = new Color(1.0f,0.5f,0.0f,1.0f);
         hoveredColor2 = new Color(1.0f,1.0f,0.5f,1.0f);



         if (allowHovering){
         this.addListener(new ClickListener(){
            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor){
               //Global.logger.info("Enter on button"+this.toString());
               addPulse();
            }
            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor){
               //Global.logger.info("Exit off button "+this.toString());
               removePulse();
            }
         });
         }
      }
      public void addPulse(){
         //setBackground(getStyle().disabled);
         pulse = Actions.forever(Actions.sequence(Actions.color(hoveredColor1, pulseTime, Interpolation.sine),Actions.color(hoveredColor2, pulseTime2, Interpolation.sine)));
         this.addAction(pulse);
      }
      public void removePulse(){
         removeAction(pulse);
         this.addAction(/*Actions.sequence(*/
         Actions.color(initialColor, pulseTime, Interpolation.sine)/*,
         new Action (){
            @Override
            public boolean act(float delta) {
               resetBackground();
               return true;
            }
         })*/);
      }
      public MyButtonInToolbar(String text, Skin skin, boolean allowHovering){
         super(text, skin);
         init(allowHovering);
      }

      public MyButtonInToolbar(String text, Skin skin, String styleName, boolean allowHovering) {
         super (text,skin,styleName);
         init(allowHovering);
      }

      public MyButtonInToolbar(String text, TextButtonStyle style,boolean allowHovering) {
         super(text,style);
         init(allowHovering);
      }

   public void memoPosition() {
      memoPosX= getX();
      memoPosY = getY();
      memoPosHeight= getHeight();
   }

   public void terminateAnim() {
      this.clearActions();
      this.setColor(initialColor);

   }
}
