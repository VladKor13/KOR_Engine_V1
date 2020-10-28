package dev.duels.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RectangleUIImageToggleButton extends RectangleUIObject {

    private BufferedImage[] images;
    private ClickListener clicker;
    private boolean IsEnable = true;
    private boolean IsPressed = false;

    public RectangleUIImageToggleButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(IsEnable) {
            if(IsPressed){
                g.drawImage(images[2], (int) x, (int) y, width, height, null);
            } else {
                if (hovering) {
                    g.drawImage(images[1], (int) x, (int) y, width, height, null);
                    if (hovering && was_clicked) {
                        IsPressed = true;
                        g.drawImage(images[2], (int) x, (int) y, width, height, null);
                    }
                } else
                    g.drawImage(images[0], (int) x, (int) y, width, height, null);
            }
        }
        else{
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {

        if(IsEnable) {
            IsPressed = true;
            clicker.onClick();
        }

    }

    public void setEnable(boolean IsEnable){
        this.IsEnable = IsEnable;
    }

    public void setPressed(boolean IsPressed){
        this.IsPressed = IsPressed;
    }

    public boolean isEnable(){
        return IsEnable;
    }

    public boolean getPressed(){
        return IsPressed;
    }
}
