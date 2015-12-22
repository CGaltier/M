package com.cgaltier.mgame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by Christian on 22/12/2015.
 */
public class TiledMapCellClickListener extends ClickListener{
   private TiledMapCellActor actor;
   public TiledMapCellClickListener(TiledMapCellActor actor){
      this.actor =actor;
   }
   @Override
   public void clicked (InputEvent event, float x, float y){
      Global.logger.info(actor.cell+"Cell clicked");
   }
}
