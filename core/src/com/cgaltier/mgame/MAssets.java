package com.cgaltier.mgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 23/12/2015.
 */
public class MAssets implements Disposable{
   public static AssetManager assetManager;
   public static MAssets thisInstance=null;

   public MAssets (){
      assetManager = new AssetManager();
      thisInstance = this;
   }
   public static MAssets getThisInstance (){
      return thisInstance;
   }
   public void loadAssets(){
      assetManager.load(Global.SKIN_ASSET,Skin.class);
      assetManager.load(Global.PLIC_SOUND,Sound.class);
      assetManager.finishLoading();

   }
   @Override
   public void dispose(){
      assetManager.dispose();
   }

   public Skin getSkin() {
      return assetManager.get(Global.SKIN_ASSET,Skin.class);
   }

   public Sound getPlicSound () {return assetManager.get(Global.PLIC_SOUND,Sound.class);}
}
