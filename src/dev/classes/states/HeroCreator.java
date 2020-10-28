package dev.classes.states;

import dev.classes.Game;
import dev.classes.Handler;

import dev.classes.UI.UIImageButton;
import dev.classes.UI.UIManager;
import dev.classes.gfx.Assets;
import dev.classes.gfx.ImageLoader;

import java.awt.*;

public class HeroCreator extends State {

    private UIManager uiManager;
    private int SkillPtsLeft = 10, StrengthPts = 0, AgilityPts = 0, FortunePts = 0;
    private UIImageButton DoneButton;

    HeroCreator(final Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);


        //STRENGTH PLUS BUTTON
        uiManager.addObject(new UIImageButton(240, 207, 45, 49, Assets.plus_button, () -> {

            if(SkillPtsLeft > 0){
                SkillPtsLeft --;
                StrengthPts ++;
            }
        }));

        //AGILITY PLUS BUTTON
        uiManager.addObject(new UIImageButton(240, 300, 45, 49, Assets.plus_button, () -> {
            if(SkillPtsLeft > 0){
                SkillPtsLeft --;
                AgilityPts ++;
            }
        }));

        //FORTUNE PLUS BUTTON
        uiManager.addObject(new UIImageButton(240, 393, 45, 49, Assets.plus_button, () -> {
            if(SkillPtsLeft > 0){
                SkillPtsLeft --;
                FortunePts ++;
            }
        }));

        //STRENGTH MINUS BUTTON
        uiManager.addObject(new UIImageButton(305, 207, 45, 49, Assets.minus_button, () -> {
            if(StrengthPts > 0){
                SkillPtsLeft ++;
                StrengthPts --;
            }
        }));

        //AGILITY MINUS BUTTON
        uiManager.addObject(new UIImageButton(305, 300, 45, 49, Assets.minus_button, () -> {
            if(AgilityPts > 0){
                SkillPtsLeft ++;
                AgilityPts --;
            }
        }));

        //FORTUNE MINUS BUTTON
        uiManager.addObject(new UIImageButton(305, 393, 45, 49, Assets.minus_button, () -> {
            if(FortunePts > 0){
                SkillPtsLeft ++;
                FortunePts --;
            }
        }));

        //BACK BUTTON
        uiManager.addObject(new UIImageButton(35, 530, 192, 64, Assets.back_button, () -> {
            handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
            Game.mainMenuState = new MainMenuState(handler);
            State.setState(Game.mainMenuState);
        }));

        //DONE BUTTON
         DoneButton = new UIImageButton(285, 530, 192, 64, Assets.done_button, () -> {

         });
        uiManager.addObject(DoneButton);
    }

    @Override
    public void tick() {
        if(SkillPtsLeft > 0){
            DoneButton.setEnable(false);
            Assets.done_button[0] = ImageLoader.loadImage("/textures/DoneButtonPressedUnable.png");
        } else {
            DoneButton.setEnable(true);
            Assets.done_button[0] = ImageLoader.loadImage("/textures/DoneButton.png");
        }
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.RedNightBackGround,0,0, null);

        g.drawImage(Assets.SkillPtsLeftPanel, 35, 100, null);
        g.drawImage(Assets.StrengthPanel, 35, 207, null);
        g.drawImage(Assets.AgilityPanel, 35, 300, null);
        g.drawImage(Assets.FortunePanel, 35, 393, null);

        g.drawImage(Assets.NumPanel[SkillPtsLeft], 415, 113, null);
        g.drawImage(Assets.NumPanel[StrengthPts], 365, 212, null);
        g.drawImage(Assets.NumPanel[AgilityPts], 365, 305, null);
        g.drawImage(Assets.NumPanel[FortunePts], 365, 398, null);


        uiManager.render(g);
    }
}
