package com.cgaltier.mgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.UIElements.HorizontalButtonGroup;
import com.cgaltier.mgame.UIElements.MyTextButton;
import com.cgaltier.mgame.UIElements.UIHumanResourceWidget;
import com.cgaltier.mgame.UIElements.UINaturalResourcesWidget;
import com.cgaltier.mgame.UIElements.UIProjectAdvancementWidget;
import com.cgaltier.mgame.UIElements.MyButtonInToolbar;

/**
 * Created by Christian on 20/01/2016.
 */
public class TestScreen extends AbstractMScreen {
   public TextButton btnShowMenu;
   public TextButton btnHideMenu;


   public TextButton btnWindow2;
   public TextButton btnMap2;
   public TextButton btnWait2;

   public TextButton btnWindow3;
   public TextButton btnMap3;
   public TextButton btnWait3;


   public SpriteBatch batch;
   public FitViewport viewport;
   public Skin skin;
   public Stage stage;
   private Texture bgTexture;
   public Container<Window> windowContainer;
   public Table tableContainer1;
   public Table tableContainer2;


   HorizontalButtonGroup menu2;
   HorizontalButtonGroup menu3;

   InputMultiplexer multiplexer ;

   public TestScreen (MGame game){
      super(game);
      multiplexer = new InputMultiplexer();

      viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
      stage = new Stage(viewport);
      //stage.setDebugAll(true);
      tableContainer1 = new Table();
      tableContainer1.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()- 60);
      tableContainer1.setClip(true);
      tableContainer1.left();

      btnWindow2 = new TextButton("container1", game.mAssets.getSkin());
      btnMap2 = new TextButton("container1", game.mAssets.getSkin());
      btnWait2 = new TextButton("container1", game.mAssets.getSkin());
      tableContainer1.add(btnWindow2);
      tableContainer1.add(btnWait2);
      tableContainer1.add(btnMap2);
      stage.addActor(tableContainer1);

      tableContainer2 = new Table();
      tableContainer2.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
      tableContainer2.right();
      tableContainer2.setPosition(Gdx.graphics.getWidth()/2,0);
      btnWindow3 = new TextButton("container2", game.mAssets.getSkin());
      btnMap3 = new TextButton("container2", game.mAssets.getSkin());
      btnWait3 = new TextButton("container2", game.mAssets.getSkin());

      tableContainer2.add(btnWindow3).align(Align.topRight);
      tableContainer2.add(btnWait3).align(Align.topRight);;
      tableContainer2.add(btnMap3).align(Align.topRight);;


      UIHumanResourceWidget.UIHumanResourceWidgetStyle UIHRResourceStyle = new UIHumanResourceWidget.UIHumanResourceWidgetStyle(game.mAssets.getSkin().get(TextButton.TextButtonStyle.class), game.mAssets.getHRCryoRegion(),
      game.mAssets.getHRIdleRegion(),
      game.mAssets.getHRMaintenanceRegion(),
      game.mAssets.getHRProductionRegion(),
      game.mAssets.getHRMissionRegion(),
      game.mAssets.getHREmergencyRegion());

      UINaturalResourcesWidget.UINaturalResourceWidgetStyle UINRResourceStyle = new UINaturalResourcesWidget.UINaturalResourceWidgetStyle(game.mAssets.getSkin().get(TextButton.TextButtonStyle.class),
      game.mAssets.getNRNitrogenRegion(),
      game.mAssets.getNROxygenRegion(),
      game.mAssets.getNRHydrogenRegion(),
      game.mAssets.getNRGoldRegion(),
      game.mAssets.getNRAluminiumRegion(),
      game.mAssets.getNRTitaniumRegion(),
      game.mAssets.getNRSiliconRegion(),
      game.mAssets.getNRRareElementsRegion(),
      game.mAssets.getNRCarbonRegion());

      UIHumanResourceWidget humanResourceWidget = new UIHumanResourceWidget(game, UIHRResourceStyle);
      UINaturalResourcesWidget naturalResourcesWidget = new UINaturalResourcesWidget(game, UINRResourceStyle);
      tableContainer2.row();
      tableContainer2.add(humanResourceWidget).colspan(3).align(Align.topRight).fill();
      tableContainer2.row();
      tableContainer2.add(naturalResourcesWidget).colspan(3).align(Align.topRight).fill();



      stage.addActor(tableContainer2);



      windowContainer = new Container<Window>();
      windowContainer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      stage.addActor(windowContainer);

      HorizontalGroup menu = new HorizontalGroup();
      menu.setPosition(20, 40);
      menu.space(5);
      btnShowMenu = new TextButton("Tool 1", game.mAssets.getSkinAdvanced());
      btnHideMenu = new TextButton("Tool 2", game.mAssets.getSkinAdvanced());

      menu.addActor(btnShowMenu);
      menu.addActor(btnHideMenu);
      stage.addActor(menu);

      menu2 = new HorizontalButtonGroup(game);
      menu2.setPosition(20, 100);
      menu2.space(0.5f);
      menu2.addButton(new MyButtonInToolbar("Text", game.mAssets.getSkinAdvanced()));
      menu2.addButton(new MyButtonInToolbar("lorem ipsum", game.mAssets.getSkinAdvanced()));
      menu2.addButton(new MyButtonInToolbar("cogito ergo", game.mAssets.getSkinAdvanced()));
      menu2.addButton(new MyButtonInToolbar("slurp", game.mAssets.getSkinAdvanced()));
      menu2.addButton(new MyButtonInToolbar("miam", game.mAssets.getSkinAdvanced()));
      menu2.addButton(new MyButtonInToolbar("gloups", game.mAssets.getSkinAdvanced()));
      menu2.addButton(new MyButtonInToolbar("Smurch", game.mAssets.getSkinAdvanced()));
      menu2.addButton(new MyButtonInToolbar("KIKOOULOL", game.mAssets.getSkinAdvanced()));

      stage.addActor(menu2);

      menu3 = new HorizontalButtonGroup(game);
      menu3.setPosition(20, 100);
      menu3.space(0.5f);
      menu3.addButton(new MyButtonInToolbar("1", game.mAssets.getSkinAdvanced()));
      menu3.addButton(new MyButtonInToolbar("2", game.mAssets.getSkinAdvanced()));
      menu3.addButton(new MyButtonInToolbar("3", game.mAssets.getSkinAdvanced()));
      menu3.addButton(new MyButtonInToolbar("4", game.mAssets.getSkinAdvanced()));
      stage.addActor(menu3);

      //menu3.setVisible(false);

      /*btnWindow.addListener(new InputListener() {
         public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            showMenu();
         }
      });*/
      btnShowMenu.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor) {
            //Global.logger.info("button changed");
            showTool1();
         }
      });
      btnHideMenu.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor) {
            //Global.logger.info("button changed");
            showTool2();
         }
      });
      multiplexer.addProcessor(stage);
      multiplexer.addProcessor(this);
      Gdx.input.setInputProcessor(multiplexer);


   }
   private void showTool1(){
      menu3.hideToolbar();
      menu2.showTopDown();
   }
   private void showTool2(){
      menu2.hideToolbar();
      menu3.showTopDown();
   }

   @Override
   public boolean keyDown(int keycode) {
      return false;
   }

   @Override
   public boolean keyUp(int keyCode){
      if(keyCode == Input.Keys.ESCAPE){
         game.setMainMenuScreen();
         return true;}
      return false;
   }

   @Override
   public boolean keyTyped(char character) {
      return false;
   }

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchDragged(int screenX, int screenY, int pointer) {
      return false;
   }

   @Override
   public boolean mouseMoved(int screenX, int screenY) {
      return false;
   }

   @Override
   public boolean scrolled(int amount) {
      return false;
   }

   @Override
   public void show() {
      Gdx.input.setInputProcessor(multiplexer);
      stage.draw();
      menu2.memoPosition();
      menu3.memoPosition();
   }
   public void renderUi(float delta) {


      stage.act(delta);
      stage.draw();


   }
   @Override
   public void resize(int width, int height) {
      stage.getViewport().update(width, height, true);


   }
   private void update(float delta) {

   }
   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 110, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


      update(delta);
      renderUi(delta);
      viewport.apply();
   }
}
