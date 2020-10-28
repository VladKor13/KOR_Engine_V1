package dev.duels.UI;

import dev.duels.gfx.ImageLoader;
import org.w3c.dom.ls.LSOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class CustomUIImageButton {


    private ClickListener clicker;
    private boolean IsEnable = true;
    private int x, y;
    private boolean hovering = false;
    private boolean was_clicked = false;
    private int[][] imageShadow;
    private int width = 0, height = 0;
    private String path;


    public CustomUIImageButton(int x, int y, ClickListener clicker, String path) {
        this.x= x;
        this.y = y;
        this.path = path;
        this.clicker = clicker;
        this.imageShadow = getImageShadow(path);
    }


    public void tick() {

    }

    public void render(Graphics g) {
        if(IsEnable) {
            if (hovering) {
                //HOVERING == TRUE
                g.setColor(Color.BLUE);
                g.fillRect(x + 150,y + 150,50,50);


                if (hovering && was_clicked){
                    //PRESSED == TRUE
                    //g.drawImage(images[2], x, y, null);
                }
            } else {
                //HOVERING == FALSE
                g.drawImage(ImageLoader.loadImage(path), x, y,  null);
            }
        } else{
            //HOVERING == FALSE
            g.drawImage(ImageLoader.loadImage(path), x, y,  null);
        }
    }


    public void onClick() {
        if(IsEnable) {
            clicker.onClick();
        }
    }

    public void onMouseMove(MouseEvent e){
        if(contains(e.getX(),e.getY())) {
            hovering = true;
        } else {
            hovering = false;
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
            if (imageShadow[cur_y - y][cur_x - x] == 1) return true;
        } catch (ArrayIndexOutOfBoundsException e){
            return true;
        }
        return false;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public void setEnable(boolean IsEnable){
        this.IsEnable = IsEnable;
    }

    public boolean getEnable(){
        return IsEnable;
    }

}
