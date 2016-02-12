package com.cgaltier.mgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cgaltier.mgame.MGame;
import com.cgaltier.mgame.MyShader;
import com.cgaltier.mgame.UIElements.HorizontalButtonGroup;
import com.cgaltier.mgame.UIElements.MyTextButton;
import com.cgaltier.mgame.UIElements.UIHumanResourceWidget;
import com.cgaltier.mgame.UIElements.UINaturalResourcesWidget;
import com.cgaltier.mgame.UIElements.UIProjectAdvancementWidget;
import com.cgaltier.mgame.UIElements.MyButtonInToolbar;
import com.cgaltier.mgame.Utils.Global;

/**
 * Created by Christian on 20/01/2016.
 */
public class TestScreen extends AbstractMScreen {
   public TextButton btnToolbar1;
   public TextButton btnToolbar2;
   public TextButton btnToolbar3;


   public Array<MyShader> shaders;
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
   HorizontalButtonGroup menu4;
   HorizontalButtonGroup menu5;
   HorizontalButtonGroup menu6;

   InputMultiplexer multiplexer ;
   Texture background ;
   FrameBuffer frameBuffer;

   int currentShader ;
   public TestScreen (MGame game){
      super(game);
      shaders = new Array <MyShader>();
      shaders.add(new MyShader("grayscale"));
      shaders.add(new MyShader("blur"));
      shaders.add(new MyShader("inverted"));
      shaders.add(new MyShader("sepia"));
      shaders.add(new MyShader("vignette"));
      shaders.add(new MyShader("videofilter"));
      shaders.add(new MyShader(null));
      currentShader = 0;

      frameBuffer = new FrameBuffer(Pixmap.Format.RGB888,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false);
      background = game.mAssets.getBackgroundImage();
      batch = new SpriteBatch();
      multiplexer = new InputMultiplexer();

      viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
      //stage = new Stage(viewport, batch );
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
      btnToolbar1 = new TextButton("Tool 1", game.mAssets.getSkinAdvanced());
      btnToolbar2 = new TextButton("Tool 2", game.mAssets.getSkinAdvanced());
      btnToolbar3 = new TextButton("Tool 3", game.mAssets.getSkinAdvanced());

      menu.addActor(btnToolbar1);
      menu.addActor(btnToolbar2);
      menu.addActor(btnToolbar3);
      stage.addActor(menu);

      menu2 = new HorizontalButtonGroup(game);
      menu2.setPosition(20, 100);
      menu2.space(2.0f);
      menu2.addButton(new MyButtonInToolbar("Text", game.mAssets.getSkinAdvanced(),true));
      menu2.addButton(new MyButtonInToolbar("lorem ipsum", game.mAssets.getSkinAdvanced(),true));
      menu2.addButton(new MyButtonInToolbar("cogito ergo", game.mAssets.getSkinAdvanced(),true));
      menu2.addButton(new MyButtonInToolbar("slurp", game.mAssets.getSkinAdvanced(),true));
      menu2.addButton(new MyButtonInToolbar("miam", game.mAssets.getSkinAdvanced(),true));
      menu2.addButton(new MyButtonInToolbar("gloups", game.mAssets.getSkinAdvanced(),true));
      menu2.addButton(new MyButtonInToolbar("Smurch", game.mAssets.getSkinAdvanced(),true));
      menu2.addButton(new MyButtonInToolbar("KIKOOULOL", game.mAssets.getSkinAdvanced(),true));

      stage.addActor(menu2);

      menu3 = new HorizontalButtonGroup(game);
      menu3.setPosition(20, 100);
      menu3.space(2.0f);
      menu3.addButton(new MyButtonInToolbar("1", game.mAssets.getSkinAdvanced(),true));
      menu3.addButton(new MyButtonInToolbar("2", game.mAssets.getSkinAdvanced(),true));
      menu3.addButton(new MyButtonInToolbar("3", game.mAssets.getSkinAdvanced(),true));
      menu3.addButton(new MyButtonInToolbar("4", game.mAssets.getSkinAdvanced(),true));
      stage.addActor(menu3);

      TextButton.TextButtonStyle style = game.mAssets.getSkinAdvanced().get("slant", TextButton.TextButtonStyle.class);

      menu4 = new HorizontalButtonGroup(game);
      menu4.setPosition(20, 100);
      menu4.space(-8.0f);
      menu4.addButton(new MyButtonInToolbar("Badoum", style,true));
      menu4.addButton(new MyButtonInToolbar("Bah", style,true));
      menu4.addButton(new MyButtonInToolbar("Padam", style,true));
      menu4.addButton(new MyButtonInToolbar("Poum", style,true));
      menu4.addButton(new MyButtonInToolbar("Tchaaa", style,true));
      menu4.addButton(new MyButtonInToolbar("Tsing-boum", style,true));
      stage.addActor(menu4);


      menu2.validate();//computes layout
      menu3.validate();//computes layout
      menu4.validate();//computes layout
      menu2.memoPosition();
      menu3.memoPosition();
      menu4.memoPosition();
      menu3.setVisible(false);
      menu4.setVisible(false);
      menu2.setOn(true);



      //menu3.setVisible(false);

      /*btnWindow.addListener(new InputListener() {
         public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            showMenu();
         }
      });*/
      btnToolbar1.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            //Global.logger.info("button changed");
            showTool2();
         }
         @Override
         public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor){
            Global.logger.info("Enter on button 1");
         }
         @Override
         public void exit (InputEvent event, float x, float y, int pointer, Actor toActor){
            Global.logger.info("Exit off button 1");
         }
      });
      btnToolbar2.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            //Global.logger.info("button changed");
            showTool3();
         }
         @Override
         public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor){
            Global.logger.info("Enter on button 2");
         }
         @Override
         public void exit (InputEvent event, float x, float y, int pointer, Actor toActor){
            Global.logger.info("Exit off button 2");
         }
      });
      btnToolbar3.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            //Global.logger.info("button changed");
            showTool4();
         }
         @Override
         public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor){
            Global.logger.info("Enter on button 3");
         }
         @Override
         public void exit (InputEvent event, float x, float y, int pointer, Actor toActor){
            Global.logger.info("Exit off button 3");
         }
      });
      multiplexer.addProcessor(stage);
      multiplexer.addProcessor(this);
      Gdx.input.setInputProcessor(multiplexer);


   }
   private void showTool2(){
      if ( !menu2.finishedAnimation() ||
           !menu3.finishedAnimation() ||
           !menu4.finishedAnimation() )
         return;
      menu2.setVisible(true);
      if (!menu2.isOn()){
         if (menu3.isOn())
            menu3.hideToolbar();
         if (menu4.isOn())
            menu4.hideToolbar();
      menu2.showTopDown();}
   }
   private void showTool3(){
      if ( !menu2.finishedAnimation() ||
      !menu3.finishedAnimation() ||
      !menu4.finishedAnimation() )
         return;
      menu3.setVisible(true);
      if (!menu3.isOn()){
         if (menu2.isOn())
            menu2.hideToolbar();
         if(menu4.isOn())
            menu4.hideToolbar();
      menu3.showTopDown();}
   }
   private void showTool4(){
      if ( !menu2.finishedAnimation() ||
      !menu3.finishedAnimation() ||
      !menu4.finishedAnimation() )
         return;
      menu4.setVisible(true);
      if (!menu4.isOn()){
         if(menu2.isOn())
            menu2.hideToolbar();
         if(menu3.isOn())
            menu3.hideToolbar();
         menu4.showTopDown();}
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
      if(keyCode == Input.Keys.F1){
         currentShader = Math.floorMod(currentShader+1,shaders.size);
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


/*
      menu4.memoPosition();
      menu5.memoPosition();
      menu6.memoPosition();
      menu4.showTopDown();
      menu5.showTopDown();
      menu6.showTopDown();*/
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

      frameBuffer.bind();

      //background and UI do not share the batch, it caused problem with the button pulse coloring
      batch.begin();
      batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
      batch.draw(background, 0, 0, 0, 0, background.getWidth(), background.getHeight());
      batch.end();

      update(delta);
      renderUi(delta);
      viewport.apply();

      frameBuffer.unbind();

      batch.setShader(shaders.get(currentShader).getProgram());
      shaders.get(currentShader).setResolutionUniform();
      batch.begin();

      //batch.draw(texture, x,y,width,height,srcX,srcY,srcWidth,srcHeight,flipX,flipY);
      batch.draw(frameBuffer.getColorBufferTexture(),
      0.0f, 0.0f,
      frameBuffer.getColorBufferTexture().getWidth(), frameBuffer.getColorBufferTexture().getHeight(),
      0, 0,
      frameBuffer.getColorBufferTexture().getWidth(), frameBuffer.getColorBufferTexture().getHeight(),false,true);


      batch.end();
      batch.setShader(null);
   }
   @Override
   public void dispose (){
      batch.dispose();
      frameBuffer.dispose();
      for (MyShader shader : shaders){
         shader.dispose();
      }
   }
}
