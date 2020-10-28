package dev.classes.gfx;

import dev.classes.Game;

public class ObjectMotionAnimation {
    public boolean ANIM_STOP = true;
    public boolean ENABLE = true;
    int counter = 0;

    private int anim_time, x_beg, x_end, y_beg, y_end,
    steps, cur_x, cur_y;
    private double x_step, y_step;
    private double int_part_of_x_step, int_part_of_y_step, x_counter = 0, y_counter = 0;


    public ObjectMotionAnimation(int anim_time, int x_beg, int y_beg, int x_end, int y_end){
        this.anim_time = anim_time;
        this.x_beg = x_beg;
        this.x_end = x_end;
        this.y_beg = y_beg;
        this.y_end = y_end;
        this.cur_x = x_beg;
        this.cur_y = y_beg;
        this.steps =  (int) Math.floor((double) anim_time / 1000.0 * (double) Game.fps);
        this.x_step = Math.abs(x_beg - x_end) / (double) steps;
        this.y_step = Math.abs(y_beg - y_end) / (double) steps;
        this.ANIM_STOP = false;

    }

    public void tick(){
        if(counter < steps) {

            //COUNTING CUR_X
            x_counter += x_step;
            if(x_counter >= 1.0){
                int_part_of_x_step = Math.floor(x_counter);
                x_counter -= int_part_of_x_step;
                if (x_beg > x_end) {
                    cur_x -= int_part_of_x_step;
                } else if (x_beg < x_end) {
                    cur_x += int_part_of_x_step;
                }

            }

            //COUNTING CUR_Y
            y_counter += y_step;
            if(y_counter >= 1.0) {
                int_part_of_y_step = Math.floor(y_counter);
                y_counter -= int_part_of_y_step;
                if (y_beg > y_end) {
                    cur_y -= int_part_of_y_step;
                } else if (y_beg < y_end) {
                    cur_y += int_part_of_y_step;
                }
            }
            counter++;
        } else {
            ANIM_STOP = true;
        }

    }

    public int getCur_x(){
        return this.cur_x;
    }

    public int getCur_y(){
        return  this.cur_y;
    }
}
