package inhain.trashmonitoring;

import android.graphics.drawable.Drawable;

public class TrashCan {
    int idx;
    String address;
    String location;
    int percent;

   String color;
    static String color2;
    Drawable draw;

    TrashCan(int idx, String address, String location, int percent, String color, String color2, Drawable draw) {
        this.idx=idx;
        this.address = address;
        this.location = location;
        this.percent = percent;
        this.color = color;
        this.color2 = color2;
        this.draw = draw;
    }
}
