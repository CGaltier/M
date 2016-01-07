package com.cgaltier.mgame.Stages.UIStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.UIElements.MyTextButton;
import com.cgaltier.mgame.UIElements.UIHumanResourceWidget;
import com.cgaltier.mgame.UIElements.UIHumanResourceWidget.UIHumanResourceWidgetStyle;
import com.cgaltier.mgame.UIElements.UIProjectAdvancementWidget;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 23/12/2015.
 */
public class UIStage extends Stage {
   public MGame game;
   public Table mainTable;
   private MyChangeListener buttonChangedListener;
   public VerticalGroup RightSideUI;
   public HorizontalGroup BottomBarUI;

   private Vector3 pos;
   public UIStage(MGame game){
      super();
      this.getViewport().update(Gdx.graphics.getHeight(), Gdx.graphics.getHeight(), true);
      this.game = game ;


      mainTable= new Table();
      this .addActor(mainTable);

      mainTable.setFillParent(true);

      mainTable.add().fill().expand();
      createRightScreenUI();
      createBottomScreenUI();

      mainTable.add(RightSideUI).padRight(10.0f);//.align(Align.right).padTop(5).padRight(5).padBottom(5);
      mainTable.row().padBottom(10.0f);
      mainTable.add(BottomBarUI);

      buttonChangedListener =new MyChangeListener();

      mainTable.debug();
      resize(getViewport().getScreenWidth(),getViewport().getScreenHeight());
   }

   private void createBottomScreenUI() {
      MyTextButton button3= new MyTextButton("Start Game", game.mAssets.getSkin(), game.mAssets.getPlicSound());
      MyTextButton button4= new MyTextButton("Start Game", game.mAssets.getSkin(), game.mAssets.getPlicSound());

      button3.addListener(buttonChangedListener );
      button4.addListener(buttonChangedListener );


      BottomBarUI = new HorizontalGroup();
      BottomBarUI.addActor(button3);
      BottomBarUI.addActor(button4);
   }

   private void createRightScreenUI() {
      UIHumanResourceWidgetStyle UIResourceStyle = new UIHumanResourceWidgetStyle(game.mAssets.getSkin().get(TextButtonStyle.class), game.mAssets.getHRCryoRegion(),
      game.mAssets.getHRIdleRegion(),
      game.mAssets.getHRMaintenanceRegion(),
      game.mAssets.getHRProductionRegion(),
      game.mAssets.getHRMissionRegion(),
      game.mAssets.getHREmergencyRegion());

      UIProjectAdvancementWidget projectAdvancementWidget = new UIProjectAdvancementWidget (game.mAssets.getSkin(),game.mAssets.getWormHoleAnimation());
      UIHumanResourceWidget humanResourceWidget = new UIHumanResourceWidget(UIResourceStyle);
      MyTextButton button1 = new MyTextButton("Start Game", game.mAssets.getSkin(), game.mAssets.getPlicSound());
      MyTextButton button2 = new MyTextButton("Start Game", game.mAssets.getSkin(), game.mAssets.getPlicSound());


      button1.addListener(buttonChangedListener);
      button2.addListener(buttonChangedListener);


      RightSideUI = new VerticalGroup();

      RightSideUI.addActor(projectAdvancementWidget);
      RightSideUI.addActor(humanResourceWidget);
      RightSideUI.addActor(button1);
      RightSideUI.addActor(button2);


      humanResourceWidget.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor) {
            if (actor == null)
               return;
            Global.logger.info("HR button clicked");
            if (actor instanceof UIHumanResourceWidget){
            ((UIHumanResourceWidget)actor).clicked();
            }
         }
      });


   }



   public void resize (int width, int height){
      this.getViewport().update(width, height, true);
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
