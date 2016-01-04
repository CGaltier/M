package com.cgaltier.mgame.Stages.UIStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.Stages.MapStage.AbstractStage;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 23/12/2015.
 */
public class UIStage extends Stage {
   public MGame game;

   public UIStage(MGame game){
      //this.getViewport().setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      float ratioWH = (float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
      int width = (int)(1024.0*ratioWH);
      this.getViewport().setScreenSize(width, Gdx.graphics.getHeight());
      //getViewport().setWorldSize(1024.0f,768.0f);
      this.game = game ;
      Table table = new Table();
      this .addActor(table);

      table.setFillParent(true);

      TextButton button = new TextButton("Start Game", game.mAssets.getSkin());
      TextButton button1 = new TextButton("Start Game", game.mAssets.getSkin());
      TextButton button2 = new TextButton("Start Game", game.mAssets.getSkin());
      TextButton button3 = new TextButton("Start Game", game.mAssets.getSkin());
      TextButton button4 = new TextButton("Start Game", game.mAssets.getSkin());

      table.defaults().fill();
      table.add(button).colspan(3);
      table.row();
      table.add(button1);
      table.add(button2).expand();
      table.add(button3);
      table.row();
      table.add(button4).colspan(3);

      button.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor) {
            Global.logger.info("button game changed");
         }
      });

      button.addListener(new InputListener() {
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Global.logger.info("button game touched");
            return true;
         }
      });

      table.debug();


   };
   public void resize (int width, int height){
      this.getViewport().update(width, height,true);
   }

}
