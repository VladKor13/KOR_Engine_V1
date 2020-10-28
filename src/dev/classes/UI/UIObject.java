package dev.classes.UI;

import dev.classes.gfx.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public abstract class UIObject {

    protected int x, y;
    protected int width, height;
    protected Object bounds;
    protected boolean hovering = false;
    protected boolean was_clicked = false;

    public UIObject(int x, int y, int width, int height){
        this.x= x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(x, y, width, height);
    }

    public UIObject(int x, int y, String path){
        this.x= x;
        this.y = y;
        this.width = ImageLoader.loadImage(path).getWidth();
        this.height = ImageLoader.loadImage(path).getHeight();

        bounds = getImageShadow(path);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if(bounds instanceof Rectangle){
            if(((Rectangle) bounds).contains(e.getX(),e.getY())){
                hovering = true;
            } else {
                hovering = false;
            }
        }
        if(bounds instanceof int[][]){
            if(contains(e.getX(),e.getY())){
                hovering = true;
            } else {
                hovering = false;
            }
        }

    }

    public void onMouseRelease(MouseEvent e){
        if(hovering){

            was_clicked = true;
            try{
                Thread.sleep(200);
            }catch (InterruptedException e1){
                e1.printStackTrace();
            }
            was_clicked = false;

            onClick();

        }

    }

//    CUSTOM BUTTONS FUNCTIONS
    protected int[][] getImageShadow(String path){

    URL url = ImageLoader.class.getResource(path);
    BufferedImage image;
    try {
        image = ImageIO.read(url);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }

    int[][] image_shadow = new int[image.getHeight()][image.getWidth()];
    this.height = image.getHeight();
    this.width = image.getWidth();

    int clr =  image.getRGB(0,0);
    int back_red = (clr & 0x00ff0000) >> 16,
            back_green = (clr & 0x0000ff00) >> 8,
            back_blue = clr & 0x000000ff;

    for(int tmp_y = 0; tmp_y < image.getHeight(); tmp_y++){
        for(int tmp_x = 0; tmp_x < image.getWidth(); tmp_x++){
            clr =  image.getRGB(tmp_x,tmp_y);

            int  red   = (clr & 0x00ff0000) >> 16;
            int  green = (clr & 0x0000ff00) >> 8;
            int  blue  =  clr & 0x000000ff;

            if(red == back_red && green == back_green && blue == back_blue){
                image_shadow[tmp_y][tmp_x] = 0;
            } else {
                image_shadow[tmp_y][tmp_x] = 1;
            }
        }
    }

    return image_shadow;
}

    protected boolean contains(int cur_x, int cur_y){
        if(cur_x < x || cur_x > x + width) return false;
        if(cur_y < y || cur_y > y + height) return false;
        try {
            if ( ( (int[][])bounds )[cur_y - y][cur_x - x] == 1) return true;
        } catch (ArrayIndexOutOfBoundsException e){
            return true;
        }
        return false;
    }


//    GETTERS AND SETTERS
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

}
