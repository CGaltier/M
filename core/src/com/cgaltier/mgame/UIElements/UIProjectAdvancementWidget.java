package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;



/*
*
*
* */
/**
 * Created by Christian on 05/01/2016.
 */
public class UIProjectAdvancementWidget extends Button {
   Stack stack;
   Image projectAdvancement;
   Label textCompletion;
   Label textTime;
   TextureRegionDrawable drawable;
   Animation wormHoleAnim;
   private float lastdelta;

   public UIProjectAdvancementWidget(Skin skin, Animation animation){
      this (animation, skin.get(TextButtonStyle.class)) ;
   }

   public UIProjectAdvancementWidget(Animation animation, TextButtonStyle style) {

      super(style);
      wormHoleAnim = animation;
      lastdelta=0.0f;
      defaults().space(3);
      drawable = new TextureRegionDrawable(/*new TextureRegion(texture)*/);
      updateDrawable(0.0f);
      projectAdvancement = new Image(drawable);
      projectAdvancement.setScaling(Scaling.fit);
      textTime = new Label("HH:MM:SS",new Label.LabelStyle(style.font, style.fontColor));
      textTime.setAlignment(Align.bottom);

      textCompletion = new Label("XX%",new Label.LabelStyle(style.font, Color.WHITE));
      textCompletion .setAlignment(Align.center);

      stack= new Stack();
      stack.add(projectAdvancement);
      stack.add(textCompletion);

      add(stack);
      //add(textCompletion);
      row();
      add(textTime).align(Align.bottom);
      setSize(getPrefWidth(), getPrefHeight());
      setStyle(style);
      setTouchable(Touchable.disabled);
   }
   public void draw (Batch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);
   }
   public void setTimePlayedMs (long timePlayedMs){
      long timePlayed = timePlayedMs /1000;
      long days = Math.floorDiv (timePlayed,(long)(60*60*24));
      long hours = Math.floorDiv(timePlayed-(days*60*60*24), (long) (60 * 60));
      long minutes = Math.floorDiv(timePlayed-(days*60*60*24)-(hours*60*60), (long) (60));
      long seconds = timePlayed-(days*60*60*24)-(hours*60*60)-(minutes*60);
      String text="";
      if (days!=0)
         text=days+":";
      text+=hours+":"+minutes+":"+seconds;
      textTime.setText(text);
   }
   public void setStyle (TextButtonStyle style) {
      super.setStyle(style);

      if (textTime != null) {
         Label.LabelStyle labelStyle = textTime.getStyle();
         labelStyle.font = style.font;
         labelStyle.fontColor = style.fontColor;
         textTime.setStyle(labelStyle);
      }
      if (textCompletion != null) {
         Label.LabelStyle labelStyle = textCompletion.getStyle();
         labelStyle.font = style.font;
         labelStyle.fontColor = Color.WHITE;
         textCompletion.setStyle(labelStyle);
      }
   }
   @Override
   public void act (float delta){
      super.act(delta);
      updateDrawable(delta);
   }
   private void updateDrawable (float delta) {
      lastdelta+=delta;
      if (lastdelta >wormHoleAnim.getFrameDuration()){
         TextureRegion keyframe = wormHoleAnim.getKeyFrame(lastdelta, true);
         drawable.setRegion(keyframe);
      }
      else
      {
         TextureRegion keyframe = wormHoleAnim.getKeyFrame(0.0f);
         drawable.setRegion(keyframe);
      }

   }

}
