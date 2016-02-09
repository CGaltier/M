package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Christian on 09/02/2016.
 */
public class MyButtonInToolbar extends TextButton{
      public float memoPosX;
      public float memoPosHeight;
      public float memoPosY;
      public MyButtonInToolbar(String text, Skin skin){
         super(text,skin);
         memoPosX = 0;
         memoPosHeight = 0;
         memoPosY = 0;
      }

   public void memoPosition() {
      memoPosX= getX();
      memoPosY = getY();
      memoPosHeight= getHeight();
   }
}
