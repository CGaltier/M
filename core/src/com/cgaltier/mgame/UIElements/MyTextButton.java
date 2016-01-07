package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by christiangaltier on 26/12/2015.
 */
public class MyTextButton extends TextButton{
    public Sound sound ;

    public MyTextButton(String text, Skin skin, Sound sound) {
        super(text, skin);
        setWidth(50.0f);
        this.sound = sound;
    }
   public void clicked (boolean playsound){
      if (playsound)
         playSound();
   }
    public void playSound (){
        if (sound != null)
           sound.play();
    }


}
