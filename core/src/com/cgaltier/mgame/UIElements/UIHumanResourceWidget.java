package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.cgaltier.mgame.Utils.Global;


/**
 * Created by Christian on 07/01/2016.
 */
public class UIHumanResourceWidget extends Button {
   private UIHumanResourceWidgetDetail cryoButton;
   private UIHumanResourceWidgetDetail idleButton;
   private UIHumanResourceWidgetDetail maintenanceButton;
   private UIHumanResourceWidgetDetail productionButton;
   private UIHumanResourceWidgetDetail missionButton;
   private UIHumanResourceWidgetDetail emergencyButton;
   private UIHumanResourceWidgetStyle style;



   public UIHumanResourceWidget(UIHumanResourceWidgetStyle style) {
      super(style);
      this.style = style;
      createButtons();
   }
   private void createButtons(){
      cryoButton = new UIHumanResourceWidgetDetail(style, Global.strings.MOCKUP_NUMBER_DISPLAY, style.cryoDrawableHR,subButtontypeHR.cryo);
      idleButton = new UIHumanResourceWidgetDetail(style,Global.strings.MOCKUP_NUMBER_DISPLAY,style.idleDrawableHR,subButtontypeHR.idle);
      maintenanceButton = new UIHumanResourceWidgetDetail(style,Global.strings.MOCKUP_NUMBER_DISPLAY, style.maintenanceDrawableHR,subButtontypeHR.maintenance);
      productionButton = new UIHumanResourceWidgetDetail(style,Global.strings.MOCKUP_NUMBER_DISPLAY, style.productionDrawableHR,subButtontypeHR.production);
      missionButton = new UIHumanResourceWidgetDetail(style,Global.strings.MOCKUP_NUMBER_DISPLAY,style.missionDrawableHR,subButtontypeHR.mission);
      emergencyButton = new UIHumanResourceWidgetDetail(style,Global.strings.MOCKUP_NUMBER_DISPLAY,style.emergencyDrawableHR ,subButtontypeHR.emergency);
      this.add(idleButton);
      this.add(cryoButton);
      this.row();
      this.add(productionButton);
      this.add(maintenanceButton);
      this.row();
      this.add(emergencyButton);
      this.add(missionButton);
   }

   public void clicked(){
      Dialog dialog = new Dialog("Human resources", new Window.WindowStyle (style.font, Color.WHITE,null));
      dialog.show(this.getStage());
   }

   //-------
   public enum subButtontypeHR {
      none, cryo,idle,maintenance,production,mission, emergency;
   };

   static public class UIHumanResourceWidgetStyle extends TextButtonStyle {

      public Drawable cryoDrawableHR, idleDrawableHR, maintenanceDrawableHR, productionDrawableHR, missionDrawableHR,emergencyDrawableHR ;


      public UIHumanResourceWidgetStyle () {
         super();
      }


      public UIHumanResourceWidgetStyle (UIHumanResourceWidgetStyle style) {
         super (style);
         this.cryoDrawableHR= style.cryoDrawableHR;
         this.emergencyDrawableHR= style.emergencyDrawableHR;
         this.missionDrawableHR= style.missionDrawableHR;
         this.productionDrawableHR= style.productionDrawableHR;
         this.idleDrawableHR= style.idleDrawableHR;
         this.maintenanceDrawableHR= style.maintenanceDrawableHR;
      }
      public UIHumanResourceWidgetStyle (TextButtonStyle style,AtlasRegion cryoRegion,AtlasRegion idleRegion, AtlasRegion maintenanceRegion, AtlasRegion productionRegion, AtlasRegion missionRegion, AtlasRegion emergencyRegion) {
         super(style);
         this.cryoDrawableHR = new TextureRegionDrawable(cryoRegion);
         this.idleDrawableHR = new TextureRegionDrawable(idleRegion);
         this.maintenanceDrawableHR = new TextureRegionDrawable(maintenanceRegion);
         this.productionDrawableHR = new TextureRegionDrawable(productionRegion);
         this.missionDrawableHR = new TextureRegionDrawable(missionRegion);
         this.emergencyDrawableHR = new TextureRegionDrawable(emergencyRegion);
      }
   }


   public class UIHumanResourceWidgetDetail extends Button{
      subButtontypeHR typeHR;
      Image image;
      Label label;

      public UIHumanResourceWidgetDetail(TextButtonStyle style, String text, Drawable drawable, subButtontypeHR typeHR) {
         super(style);
         setTouchable(Touchable.disabled);
         this.typeHR = typeHR;
         if (drawable != null) {
            this.image = new Image(drawable);
            image.setScaling(Scaling.fit);
            add(image);
         }
         label = new Label (text, new Label.LabelStyle(style.font, style.fontColor));
         add(label);
		   setSize(getPrefWidth(), getPrefHeight());
      }
      @Override
      public void draw (Batch batch, float parentAlpha) {
		   super.draw(batch, parentAlpha);
	   }
   }
}
