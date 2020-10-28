package dev.duels.states;

import dev.duels.Game;
import dev.duels.Handler;
import dev.duels.UI.ClickListener;
import dev.duels.UI.UIImageButton;
import dev.duels.UI.UIManager;
import dev.duels.gfx.Assets;

import java.awt.*;


public class MainMenuState extends State{

    private UIManager uiManager;
    private UIImageButton continue_Button;

    public MainMenuState(final Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        //START BUTTON
        uiManager.addObject(new UIImageButton(192, 100, 128, 64, Assets.start_button, () -> {
            handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
            Game.heroCreatorState = new HeroCreator(handler);
            State.setState(Game.heroCreatorState);
        }));

        //CONTINUE BUTTON
        continue_Button = new UIImageButton(192, 200, 128, 64, Assets.continue_button, new ClickListener() {
            @Override
            public void onClick() {
            }
        });
        uiManager.addObject(continue_Button);

        //OPTIONS BUTTON
        uiManager.addObject(new UIImageButton(192, 300, 128, 64, Assets.options_button, new ClickListener() {
            @Override
            public void onClick() {

            }
        }));

        //EXIT BUTTON
        uiManager.addObject(new UIImageButton(192, 400, 128, 64, Assets.exit_button, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().stop();
            }
        }));




    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.MenuBackGround,0,0, null);
        uiManager.render(g);
    }
}
