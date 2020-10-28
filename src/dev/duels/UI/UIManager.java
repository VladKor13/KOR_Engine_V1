package dev.duels.UI;

import dev.duels.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private Handler handler;
    private ArrayList<Object> objects;

    public UIManager(Handler handler){
        this.handler = handler;
        objects = new ArrayList<Object>();
    }

    public void tick(){

        for(Object o : objects) {
            if(o instanceof RectangleUIImageButton){
                ((RectangleUIImageButton) o).tick();
            }
            if(o instanceof CustomUIImageButton){
                ((CustomUIImageButton) o).tick();
            }
        }
    }

    public void render(Graphics g){
        for(Object o : objects) {
            if(o instanceof RectangleUIImageButton){
                ((RectangleUIImageButton) o).render(g);
            }
            if(o instanceof CustomUIImageButton){
                ((CustomUIImageButton) o).render(g);
            }
        }
    }

    public void onMouseMove(MouseEvent e){
        for(Object o : objects) {
            if(o instanceof RectangleUIImageButton){
                ((RectangleUIImageButton) o).onMouseMove(e);
            }
            if(o instanceof CustomUIImageButton){
                ((CustomUIImageButton) o).onMouseMove(e);
            }
        }

    }

    public void onMouseRelease(MouseEvent e){
        for(Object o : objects) {
            if(o instanceof RectangleUIImageButton){
                ((RectangleUIImageButton) o).onMouseRelease(e);
            }
            if(o instanceof CustomUIImageButton){
                ((CustomUIImageButton) o).onMouseMove(e);
            }
        }

    }

    public void addObject(Object o){
        objects.add(o);
    }

    public void removeObject(Object o){
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }

}
