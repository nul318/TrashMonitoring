package inhain.trashmonitoring;

import android.graphics.drawable.Drawable;

public class TrashCan {
    int idx;
    String address;
    String location;
    int percent;
    String color;
    Drawable draw;
    double locationX;
    double locationY;


<<<<<<< HEAD
    TrashCan(int idx, String address, String location, int percent, String color, Drawable draw) {
=======
    TrashCan(int idx, String address, String location, int percent, String color, Drawable draw, double X, double Y) {
>>>>>>> 9277e8ab5fd9f362ce72b748d63f42474e3e31b0
        this.idx=idx;
        this.address = address;
        this.location = location;
        this.percent = percent;
        this.color = color;
        this.draw = draw;
        this.locationX = X;
        this.locationY = Y;
    }
}
