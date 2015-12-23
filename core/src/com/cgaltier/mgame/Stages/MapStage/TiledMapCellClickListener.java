package com.cgaltier.mgame.Stages.MapStage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cgaltier.mgame.Stages.MapStage.TiledMapCellActor;


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
      com.cgaltier.mgame.Utils.Global.logger.info(actor.cell+"Cell clicked");
   }
}
