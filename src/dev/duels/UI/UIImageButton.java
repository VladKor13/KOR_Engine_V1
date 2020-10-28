package dev.duels.UI;

import dev.duels.gfx.ObjectMotionAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;
    private boolean IsEnable = true;
    private ObjectMotionAnimation objectMotionAnimation;


    public UIImageButton(int x, int y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    public UIImageButton(int x, int y, String path, BufferedImage[] images, ClickListener clicker) {
        super(x, y, path);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {
        if(objectMotionAnimation != null){
            if(objectMotionAnimation.ANIM_STOP){
                objectMotionAnimation = null;
            } else {
                objectMotionAnimation.tick();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(objectMotionAnimation != null){
            this.setX(objectMotionAnimation.getCur_x());
            this.setY(objectMotionAnimation.getCur_y());
        }

        if(IsEnable) {
            if (hovering) {
                //HOVERING == TRUE
                g.drawImage(images[1], x, y, width, height, null);
                //PRESSED == TRUE
                if (hovering && was_clicked){
                    g.drawImage(images[2], x, y, width, height, null);
                }
            } else
                //HOVERING == FALSE
                g.drawImage(images[0], x, y, width, height, null);
        } else{
            //HOVERING == FALSE
            g.drawImage(images[0], x, y, width, height, null);
        }

    }

    @Override
    public void onClick() {
        if(IsEnable) {
            clicker.onClick();
        }

    }

    public void setEnable(boolean IsEnable){
        this.IsEnable = IsEnable;
    }

    public boolean getEnable(){
        return IsEnable;
    }

    public void setObjectMotionAnimation(ObjectMotionAnimation objectMotionAnimation){
        this.objectMotionAnimation = objectMotionAnimation;
    }
}
