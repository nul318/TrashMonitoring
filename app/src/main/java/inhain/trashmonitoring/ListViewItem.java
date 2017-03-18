package inhain.trashmonitoring;

/**
 * Created by user on 2017-03-18.
 */

public class ListViewItem {
    private String Lid=null;
    private int Lwin=0;
    private int Llose=0;

    public String getLid(){return Lid;}
    public int getLwin(){return Lwin;}
    public int getLlose(){return Llose;}

    public ListViewItem(String Lid,int Lwin,int Llose) {
        this.Lid = Lid;
        this.Lwin = Lwin;
        this.Llose = Llose;
    }
}