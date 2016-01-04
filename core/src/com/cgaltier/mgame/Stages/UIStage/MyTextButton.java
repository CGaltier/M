package com.cgaltier.mgame.Stages.UIStage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by christiangaltier on 26/12/2015.
 */
public class MyTextButton extends TextButton{
    public Sound plic ;

    public MyTextButton(String text, Skin skin, Sound sound) {
        super(text, skin);
        plic = sound;
    }
    public void playSound (){
        plic.play();
    }


}
