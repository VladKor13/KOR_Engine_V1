package dev.duels.states;

import dev.duels.Handler;

import dev.duels.UI.ClickListener;
import dev.duels.gfx.Assets;
import dev.duels.UI.UIImageButton;
import dev.duels.UI.UIManager;
import dev.duels.display.Display;

import java.awt.*;

public class ImgCheckState extends State {

    private UIManager uiManager;



    public ImgCheckState(final Handler handler)  {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        //TEST CUSTOM BUTTON
        uiManager.addObject(new UIImageButton(30, 30, "/textures/elementExplosive034.png",
                Assets.custom_btn, () -> {

                }));

    }

    @Override
    public void tick() {

        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Display.Red_Col);
        g.fillRect(0,0,512,640);


        uiManager.render(g);
    }



}
