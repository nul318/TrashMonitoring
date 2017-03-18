package inhain.trashmonitoring;

public class TrashCan {
    int idx;
    String address;
    String location;
    int percent;
    String color;

    TrashCan(int idx, String address, String location, int percent, String color) {
        this.idx=idx;
        this.address = address;
        this.location = location;
        this.percent = percent;
        this.color = color;

    }
}
