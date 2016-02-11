package com.cgaltier.mgame.UIElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.cgaltier.mgame.Good;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.Resources;
import com.cgaltier.mgame.Utils.Global;


/**
 * Created by Christian on 07/01/2016.
 */
public class UINaturalResourcesWidget extends Button {

   private MGame game;
   //NITROGEN, OXYGEN, HYDROGEN, GOLD, ALUMINIUM, TITANIUM, SILICON, RARE_ELEMENTS, CARBON;
   private Image nitrogenImage;
   private Image oxygenImage;
   private Image hydrogenImage;
   private Image goldImage;
   private Image aluminiumImage;
   private Image titaniumImage;
   private Image siliconImage;
   private Image rareElementsImage;
   private Image carbonImage;

   private Label     nitrogenLabel;
   private Label       oxygenLabel;
   private Label     hydrogenLabel;
   private Label         goldLabel;
   private Label    aluminiumLabel;
   private Label     titaniumLabel;
   private Label      siliconLabel;
   private Label rareElementsLabel;
   private Label       carbonLabel;

   private UINaturalResourceWidgetStyle style;



   public UINaturalResourcesWidget(MGame game, UINaturalResourceWidgetStyle style) {
      super(style);
      this.style = style;
      createWidget();
      this.game = game;
      //setSize(getPrefWidth(), getPrefHeight());
   }
   private void createWidget(){
      this.nitrogenImage    = new Image(style.nitrogenDrawableNR);
      this.oxygenImage      = new Image(style.oxygenDrawableNR);
      this.hydrogenImage    = new Image(style.hydrogenDrawableNR);
      this.goldImage        = new Image(style.goldDrawableNR);
      this.aluminiumImage   = new Image(style.aluminiumDrawableNR);
      this.titaniumImage    = new Image(style.titaniumDrawableNR);
      this.siliconImage     = new Image(style.siliconDrawableNR);
      this.rareElementsImage= new Image(style.rareElementsDrawableNR);
      this.carbonImage      = new Image(style.carbonDrawableNR);

      this.nitrogenImage    .setScaling(Scaling.fit);
      this.oxygenImage      .setScaling(Scaling.fit);
      this.hydrogenImage    .setScaling(Scaling.fit);
      this.goldImage        .setScaling(Scaling.fit);
      this.aluminiumImage   .setScaling(Scaling.fit);
      this.titaniumImage    .setScaling(Scaling.fit);
      this.siliconImage     .setScaling(Scaling.fit);
      this.rareElementsImage.setScaling(Scaling.fit);
      this.carbonImage      .setScaling(Scaling.fit);

      nitrogenLabel     =  new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      oxygenLabel       = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      hydrogenLabel     = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      goldLabel         = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      aluminiumLabel    = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      titaniumLabel     = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      siliconLabel      = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      rareElementsLabel = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));
      carbonLabel       = new Label (Global.strings.MOCKUP_NUMBER_DISPLAY, new Label.LabelStyle(style.font, style.fontColor));



      add(nitrogenImage).pad(2.0f, 2.0f, 2.0f, 5.0f);
      add(nitrogenLabel).fillX().expandX();
      add(oxygenImage).pad(2.0f, 5.0f, 2.0f, 2.0f);;
      add(oxygenLabel).fillX().expandX();
      add(hydrogenImage).pad(2.0f, 2.0f, 2.0f, 5.0f);;
      add(hydrogenLabel).fillX().expandX();
      row();
      add(goldImage).pad(2.0f, 5.0f, 2.0f, 2.0f);
      add(goldLabel).fillX().expandX();
      add(aluminiumImage).pad(2.0f, 2.0f, 2.0f, 5.0f);;
      add(aluminiumLabel).fillX().expandX();
      add(titaniumImage).pad(2.0f, 5.0f, 2.0f, 2.0f);
      add(titaniumLabel).fillX().expandX();
      row();
      add(siliconImage).pad(2.0f, 5.0f, 2.0f, 2.0f);
      add(siliconLabel).fillX().expandX();
      add(carbonImage).pad(2.0f, 2.0f, 2.0f, 5.0f);;
      add(carbonLabel).fillX().expandX();
      add(rareElementsImage).pad(2.0f, 5.0f, 2.0f, 2.0f);
      add(rareElementsLabel).fillX().expandX();






      //setSize(getPrefWidth(),getPrefHeight());

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
      Dialog dialog = new MyDialog("Natural resources", game.mAssets.getSkinAdvanced().get(Window.WindowStyle.class)/*new Window.WindowStyle (style.font, Color.WHITE,null)*/,style);
      dialog.setSize(100,200);
      dialog.show(this.getStage());
   }
   public void setValues (final Resources resources){
      nitrogenLabel.setText(String.valueOf(resources.getStock(Good.Nitrogen)));
      oxygenLabel.setText(String.valueOf(resources.getStock(Good.Oxygen)));
      hydrogenLabel.setText(String.valueOf(resources.getStock(Good.Hydrogen)));
      goldLabel.setText(String.valueOf(resources.getStock(Good.Gold)));
      aluminiumLabel.setText(String.valueOf(resources.getStock(Good.Aluminium)));
      titaniumLabel.setText(String.valueOf(resources.getStock(Good.Titanium)));
      siliconLabel.setText(String.valueOf(resources.getStock(Good.Silicon)));
      carbonLabel.setText(String.valueOf(resources.getStock(Good.Carbon)));
      rareElementsLabel.setText(String.valueOf(resources.getStock(Good.Rare_Elements)));
      invalidate();
   }

   static public class UINaturalResourceWidgetStyle extends TextButtonStyle {

      public Drawable nitrogenDrawableNR, oxygenDrawableNR, hydrogenDrawableNR, goldDrawableNR, aluminiumDrawableNR, titaniumDrawableNR, siliconDrawableNR, rareElementsDrawableNR, carbonDrawableNR ;


      public UINaturalResourceWidgetStyle () {
         super();
      }


      public UINaturalResourceWidgetStyle (UINaturalResourceWidgetStyle style) {
         super (style);
         this.nitrogenDrawableNR = style.nitrogenDrawableNR;
         this.oxygenDrawableNR = style.oxygenDrawableNR;
         this.hydrogenDrawableNR = style.hydrogenDrawableNR;
         this.goldDrawableNR = style.goldDrawableNR;
         this.aluminiumDrawableNR = style.aluminiumDrawableNR;
         this.titaniumDrawableNR = style.titaniumDrawableNR;
         this.siliconDrawableNR = style.siliconDrawableNR;
         this.rareElementsDrawableNR = style.rareElementsDrawableNR;
         this.carbonDrawableNR = style.carbonDrawableNR;
      }
      public UINaturalResourceWidgetStyle (TextButtonStyle style,         AtlasRegion nitrogenRegion,
                                           AtlasRegion oxygenRegion,
                                           AtlasRegion hydrogenRegion,
                                           AtlasRegion goldRegion,
                                           AtlasRegion aluminiumRegion,
                                           AtlasRegion titaniumRegion,
                                           AtlasRegion siliconRegion,
                                           AtlasRegion rareElementsRegion,
                                           AtlasRegion carbonRegion) {

         super(style);
         this.nitrogenDrawableNR = new TextureRegionDrawable    (nitrogenRegion);
         this.oxygenDrawableNR = new TextureRegionDrawable      (oxygenRegion);
         this.hydrogenDrawableNR = new TextureRegionDrawable    (hydrogenRegion);
         this.goldDrawableNR = new TextureRegionDrawable        (goldRegion);
         this.aluminiumDrawableNR = new TextureRegionDrawable   (aluminiumRegion);
         this.titaniumDrawableNR = new TextureRegionDrawable    (titaniumRegion);
         this.siliconDrawableNR = new TextureRegionDrawable     (siliconRegion);
         this.rareElementsDrawableNR = new TextureRegionDrawable(rareElementsRegion);
         this.carbonDrawableNR = new TextureRegionDrawable      (carbonRegion);
      }
   }
}
