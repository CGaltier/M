package com.cgaltier.mgame;


import com.badlogic.gdx.Game;
import com.cgaltier.mgame.Screens.GameScreen;
import com.cgaltier.mgame.Screens.MainMenuScreen;
import com.cgaltier.mgame.Screens.TestScreen;

public class MGame extends Game {//InputAdapter implements ApplicationListener {


   public MAssets mAssets;
   public GameScreen gameScreen;
   public MainMenuScreen mainMenuScreen;
   public TestScreen testScreen;
   public GameWorld gameWorld;

   @Override
	public void create () {
      mAssets = new MAssets();
      mAssets.loadAssets();
      gameWorld = new GameWorld();
      gameScreen = new GameScreen(this);
      mainMenuScreen = new MainMenuScreen(this);
      testScreen= new TestScreen(this);
      setScreen(mainMenuScreen);
      //setScreen(testScreen);

   }
   @Override
   public void dispose(){
      gameScreen.dispose();
      mainMenuScreen.dispose();
      mAssets.dispose();
   }


   public void setGameScreen() {
      this.setScreen(gameScreen);
   }
   public void setMainMenuScreen(){
      this.setScreen(mainMenuScreen);
   }
   public void setTestScreen(){
      this.setScreen(testScreen);
   }
}
