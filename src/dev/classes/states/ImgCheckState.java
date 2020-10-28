package dev.classes.states;

import dev.classes.Handler;

import dev.classes.gfx.Assets;
import dev.classes.UI.UIImageButton;
import dev.classes.UI.UIManager;
import dev.classes.gfx.ObjectMotionAnimation;

import java.awt.*;

public class ImgCheckState extends State {

    private UIManager uiManager;
    private UIImageButton test_btn;
    private ObjectMotionAnimation test_btn_motin_anim;

    public ImgCheckState(final Handler handler)  {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        //TEST CUSTOM BUTTON
        test_btn = new UIImageButton(0, 0, "/textures/elementExplosive034.png",
                Assets.custom_btn, () -> {
                   test_btn_motin_anim = new ObjectMotionAnimation(5000, 0, 0,
                           250, 250);
                   test_btn.setObjectMotionAnimation(test_btn_motin_anim);

                });
        uiManager.addObject(test_btn);

    }

    @Override
    public void tick() {
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,512,640);

        uiManager.render(g);
    }



}
