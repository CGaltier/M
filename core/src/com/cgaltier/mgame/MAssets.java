package com.cgaltier.mgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.cgaltier.mgame.Utils.Global;

import java.util.Comparator;

import javax.xml.soap.Text;

/**
 * Created by Christian on 23/12/2015.
 */
public class MAssets implements Disposable{
   public static AssetManager assetManager;
   public static MAssets thisInstance=null;
   public Animation wormHoleAnimation;
   public Array <AtlasRegion> UIImagesRegions ;


   public MAssets (){
      assetManager = new AssetManager();
      thisInstance = this;
   }
   public static MAssets getThisInstance (){
      return thisInstance;
   }
   public void loadAssets(){

      assetManager.load(Global.SKIN_ASSET,Skin.class);
      assetManager.load(Global.SKIN_ASSET_ADVANCED,Skin.class);

      assetManager.load(Global.PLIC_SOUND,Sound.class);
      assetManager.load(Global.WORMHOLE_ANIM_ATLAS,TextureAtlas.class);
      assetManager.load(Global.UI_IMAGES_ATLAS,TextureAtlas.class);

      assetManager.finishLoading();
      prepareWormHoleAnimation();
      prepareUIImages();

   }

   private void prepareUIImages() {
      TextureAtlas UIAtlas = assetManager.get(Global.UI_IMAGES_ATLAS,TextureAtlas.class);
      UIImagesRegions = new Array<AtlasRegion>(UIAtlas .getRegions());
   }

   @Override
   public void dispose()
   {
      assetManager.dispose();
   }

   public Skin getSkin() {
      return assetManager.get(Global.SKIN_ASSET,Skin.class);
   }

   public Skin getSkinAdvanced(){
      return assetManager.get(Global.SKIN_ASSET_ADVANCED,Skin.class);
   }
   public Sound getPlicSound () {return assetManager.get(Global.PLIC_SOUND,Sound.class);}

   public Animation getWormHoleAnimation() {
      return wormHoleAnimation;
   }
   private void prepareWormHoleAnimation(){
      TextureAtlas wormHoleAtlas = assetManager.get(Global.WORMHOLE_ANIM_ATLAS,TextureAtlas.class);
      Array<AtlasRegion> wormHoleRegions = new Array<AtlasRegion>(wormHoleAtlas.getRegions());
      wormHoleRegions.sort (new AtlasRegionComparator());
      wormHoleAnimation=new Animation(Global.WORMHOLE_ANIM_DURATION, wormHoleRegions, Animation.PlayMode.LOOP);
   }

   public AtlasRegion getHRCryoRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.HR_CRYO_IMAGE)==0)
            return region;
      }
      return null;
   }

   public AtlasRegion getHRIdleRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.HR_IDLE_IMAGE)==0)
            return region;
      }
      return null;
   }

   public AtlasRegion getHRMaintenanceRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.HR_MAINTENANCE_IMAGE)==0)
            return region;
      }
      return null;
   }

   public AtlasRegion getHRProductionRegion () {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.HR_PRODUCTION_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getHRMissionRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.HR_MISSION_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getHREmergencyRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.HR_EMERGENCY_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRNitrogenRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_NITROGEN_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNROxygenRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_OXYGEN_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRHydrogenRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_HYDROGEN_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRGoldRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_GOLD_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRAluminiumRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_ALUMINIUM_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRTitaniumRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_TITANIUM_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRSiliconRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_SILICON_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRRareElementsRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_RARE_ELEMENTS_IMAGE)==0)
            return region;
      }

      return null;
   }

   public AtlasRegion getNRCarbonRegion() {
      for (AtlasRegion region:UIImagesRegions)
      {
         if (region.name.compareTo(Global.NR_CARBON_IMAGE)==0)
            return region;
      }

      return null;
   }

   private static class AtlasRegionComparator implements Comparator<AtlasRegion>{
      @Override
      public int compare (AtlasRegion region1, AtlasRegion region2){
         return region1.name.compareTo(region2.name);
      }
   }
}
