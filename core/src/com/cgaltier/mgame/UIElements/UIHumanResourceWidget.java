package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.cgaltier.mgame.HumanResources;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.Utils.Global;


/**
 * Created by Christian on 07/01/2016.
 */
public class UIHumanResourceWidget extends Button {

   private Image cryoImage;
   private Image idleImage;
   private Image maintenanceImage;
   private Image productionImage;
   private Image missionImage;
   private Image emergencyImage;

   private Label cryoLabel;
   private Label idleLabel;
   private Label maintenanceLabel;
   private Label productionLabel;
   private Label missionLabel;
   private Label emergencyLabel;

   private UIHumanResourceWidgetStyle style;

   private MGame game;


   public UIHumanResourceWidget(MGame game, UIHumanResourceWidgetStyle style) {
      super(style);
      this.game = game;
      clearChildren();
      this.style = style;
      createWidget();
      setSize(getPrefWidth(), getPrefHeight());
   }
   private void createWidget(){
      this.cryoImage = new Image(style.cryoDrawableHR);
      this.idleImage = new Image(style.idleDrawableHR);
      this.maintenanceImage = new Image (style.maintenanceDrawableHR);
      this.productionImage = new Image(style.productionDrawableHR);
      this.missionImage = new Image(style.missionDrawableHR);
      this.emergencyImage = new Image(style.emergencyDrawableHR);

      this.cryoImage.setScaling(Scaling.fit);
      this.idleImage.setScaling(Scaling.fit);
      this.maintenanceImage.setScaling(Scaling.fit);
      this.productionImage.setScaling(Scaling.fit);
      this.missionImage.setScaling(Scaling.fit);
      this.emergencyImage.setScaling(Scaling.fit);

      this.cryoLabel = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      this.idleLabel = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      this.maintenanceLabel = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      this.productionLabel = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      this.missionLabel = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      this.emergencyLabel = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));


      add(cryoImage).pad(2.0f, 2.0f, 2.0f, 5.0f);
      add(cryoLabel).fillX().expandX();
      add(idleImage).pad(2.0f, 5.0f, 2.0f, 2.0f);;
      add(idleLabel).fillX().expandX();
      add(maintenanceImage).pad(2.0f, 2.0f, 2.0f, 5.0f);;
      add(maintenanceLabel).fillX().expandX();
      row();
      add(productionImage).pad(2.0f, 5.0f, 2.0f, 2.0f);
      add(productionLabel).fillX().expandX();
      add(missionImage).pad(2.0f,2.0f,2.0f,5.0f);;
      add(missionLabel).fillX().expandX();
      add(emergencyImage).pad(2.0f, 5.0f, 2.0f, 2.0f);
      add(emergencyLabel).fillX().expandX();

      //setSize(getPrefWidth(),getPrefHeight());

   }
   public void setValues (final HumanResources resources){
      cryoLabel.setText(String.valueOf(resources.getCryo()));
      idleLabel.setText(String.valueOf(resources.getIdle()));
      maintenanceLabel.setText(String.valueOf(resources.getMaintenance()));
      productionLabel.setText(String.valueOf(resources.getProduction()));
      missionLabel.setText(String.valueOf(resources.getMission()));
      emergencyLabel.setText(String.valueOf(resources.getEmergency()));
      invalidate();
   }

   private class MyDialog extends Dialog{
      class MyCloseButtonListener extends ChangeListener{
         Dialog dialog;
         public MyCloseButtonListener (Dialog dialog){
            super();
            this.dialog=dialog;
         }
          @Override
         public void changed(ChangeEvent event, Actor actor) {
            dialog.hide();
         }
      }
      public MyDialog(String title, WindowStyle windowStyle, TextButtonStyle style) {
         super(title, windowStyle);
         Button button =new MyButtonInToolbar("Close", game.mAssets.getSkinAdvanced(),true);
         add(button).fill();
         MyCloseButtonListener listener = new MyCloseButtonListener(this);
         button.addListener(listener);

      }
   }
   @Override
   public void draw (Batch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);
   }

   public void clicked(){
      Dialog dialog = new MyDialog("Human resources", game.mAssets.getSkinAdvanced().get(Window.WindowStyle.class) /*new Window.WindowStyle (style.font, Color.WHITE,null)*/,style);
      dialog.setSize(100,200);
      dialog.show(this.getStage());
   }

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
}
