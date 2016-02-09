package com.cgaltier.mgame.Stages.UIStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.UIElements.MyTextButton;
import com.cgaltier.mgame.UIElements.UIHumanResourceWidget;
import com.cgaltier.mgame.UIElements.UIHumanResourceWidget.UIHumanResourceWidgetStyle;
import com.cgaltier.mgame.UIElements.UINaturalResourcesWidget;
import com.cgaltier.mgame.UIElements.UINaturalResourcesWidget.UINaturalResourceWidgetStyle;
import com.cgaltier.mgame.UIElements.UIProjectAdvancementWidget;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 23/12/2015.
 */
public class UIStage extends Stage {
   public MGame game;

   private final float ratio = 1024.0f / 720.0f;
   private MyChangeListener buttonChangedListener;
   public Table RightSideUI;
   public Table BottomBarUI;
   private UIProjectAdvancementWidget projectAdvancementWidget;
   private UIHumanResourceWidget humanResourceWidget;
   private UINaturalResourcesWidget naturalResourcesWidget;

   private Vector3 pos;

   public UIStage(MGame game) {
      super();

      this.game = game;


      createRightScreenUI();
      createBottomScreenUI();

      buttonChangedListener = new MyChangeListener();


      resize(getViewport().getScreenWidth(), getViewport().getScreenHeight());
   }

   private void createBottomScreenUI() {
      MyTextButton button3 = new MyTextButton("Start Game", game.mAssets.getSkin(), game.mAssets.getPlicSound());
      MyTextButton button4 = new MyTextButton("Start Game", game.mAssets.getSkin(), game.mAssets.getPlicSound());

      button3.addListener(buttonChangedListener);
      button4.addListener(buttonChangedListener);


      BottomBarUI = new Table();
      BottomBarUI.addActor(button3);
      BottomBarUI.addActor(button4);
      this.addActor(BottomBarUI);
   }

   private void createRightScreenUI() {

      UIHumanResourceWidgetStyle UIHRResourceStyle = new UIHumanResourceWidgetStyle(game.mAssets.getSkin().get(TextButtonStyle.class), game.mAssets.getHRCryoRegion(),
      game.mAssets.getHRIdleRegion(),
      game.mAssets.getHRMaintenanceRegion(),
      game.mAssets.getHRProductionRegion(),
      game.mAssets.getHRMissionRegion(),
      game.mAssets.getHREmergencyRegion());

      UINaturalResourceWidgetStyle UINRResourceStyle = new UINaturalResourceWidgetStyle(game.mAssets.getSkin().get(TextButtonStyle.class),
      game.mAssets.getNRNitrogenRegion(),
      game.mAssets.getNROxygenRegion(),
      game.mAssets.getNRHydrogenRegion(),
      game.mAssets.getNRGoldRegion(),
      game.mAssets.getNRAluminiumRegion(),
      game.mAssets.getNRTitaniumRegion(),
      game.mAssets.getNRSiliconRegion(),
      game.mAssets.getNRRareElementsRegion(),
      game.mAssets.getNRCarbonRegion());

      projectAdvancementWidget = new UIProjectAdvancementWidget(game.mAssets.getSkin(), game.mAssets.getWormHoleAnimation());
      humanResourceWidget = new UIHumanResourceWidget(game,UIHRResourceStyle);
      naturalResourcesWidget = new UINaturalResourcesWidget(game,UINRResourceStyle);

      RightSideUI = new Table();

      RightSideUI.add(projectAdvancementWidget);
      RightSideUI.row();
      RightSideUI.add(humanResourceWidget).fill();
      RightSideUI.row();
      RightSideUI.add(naturalResourcesWidget).fill();

      projectAdvancementWidget.getPrefWidth();
      RightSideUI.debugAll();
      RightSideUI.setPosition(getViewport().getWorldWidth() - RightSideUI.getPrefWidth(), getViewport().getWorldHeight() - RightSideUI.getPrefHeight() / 2.0f);


      humanResourceWidget.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor) {
            if (actor == null)
               return;
            Global.logger.info("HR button clicked");
            if (actor instanceof UIHumanResourceWidget) {
               ((UIHumanResourceWidget) actor).clicked();
            }
         }
      });


      naturalResourcesWidget.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor) {
            if (actor == null)
               return;
            Global.logger.info("NR button clicked");
            if (actor instanceof UINaturalResourcesWidget) {
               ((UINaturalResourcesWidget) actor).clicked();
            }
         }
      });
      this.addActor(RightSideUI);
   }


   public void resize(int width, int height) {

      float hSize, wSize;
      if (height < width) {
         hSize = (float) height;
         wSize = hSize * ratio;
      } else {
         wSize = (float) width;
         hSize = wSize / ratio;

      }
      this.getViewport().update((int) wSize, (int) hSize, true);
      RightSideUI.setPosition(getViewport().getWorldWidth() - RightSideUI.getPrefWidth(), getViewport().getWorldHeight() - RightSideUI.getPrefHeight() / 2.0f);
   }

   /*@Override
   public void act(float delta){
      super.act();
      projectAdvancementWidget.setTimePlayedMs (game.gameWorld.gameWorldController.getTimePlayedMs());
   }*/
   private void updateTimePlayed() {
      projectAdvancementWidget.setTimePlayedMs(game.gameWorld.gameWorldController.getTimePlayedMs());
   }
   private void updateHumanResources(){
      this.humanResourceWidget.setValues(game.gameWorld.gameWorldController.getHumanResources());
   }
   private void updateResources(){
      naturalResourcesWidget.setValues(game.gameWorld.gameWorldController.getResources());
   }
   public void updateUI(){
      updateTimePlayed();
      updateHumanResources();
      updateResources();
   }
   public class MyChangeListener extends ChangeListener{
      public MyChangeListener (){
         super();
      }


      public void changed(ChangeEvent event, Actor actor) {
         if (actor == null)
            return;
         Global.logger.info("button game changed");
         if (actor instanceof MyTextButton){
            ((MyTextButton)actor).setChecked(false);
            ((MyTextButton)actor).clicked(true);
         }
      }

   }

}
