package dev.classes.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageToggleButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;
    private boolean IsEnable = true;
    private boolean IsPressed = false;

    public UIImageToggleButton(int x, int y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    public UIImageToggleButton(int x, int y, String path, BufferedImage[] images, ClickListener clicker) {
        super(x, y, path);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        try {
            if (IsEnable) {
                if (IsPressed) {
                    g.drawImage(images[2], x, y, width, height, null);
                } else {
                    if (hovering) {
                        g.drawImage(images[1], x, y, width, height, null);
                        if (hovering && was_clicked) {
                            IsPressed = true;
                            g.drawImage(images[2], x, y, width, height, null);
                        }
                    } else
                        g.drawImage(images[0], x, y, width, height, null);
                }
            } else {
                g.drawImage(images[0], x, y, width, height, null);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
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
