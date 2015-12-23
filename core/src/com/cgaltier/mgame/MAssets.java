package com.cgaltier.mgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 23/12/2015.
 */
public class MAssets implements Disposable{
   public AssetManager assetManager;

   public MAssets (){
      assetManager = new AssetManager();
   }
   public void loadAssets(){
      assetManager.load(Global.SKIN_ASSET,Skin.class);
      assetManager.finishLoading();
   }
   @Override
   public void dispose(){
      assetManager.dispose();
   }

   public Skin getSkin() {
      return assetManager.get(Global.SKIN_ASSET,Skin.class);
   }
}
