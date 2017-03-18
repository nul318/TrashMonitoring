package inhain.trashmonitoring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-18.
 */

public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ListViewItem> data;
    private int layout;

    public ListViewAdapter(Context context, int layout, ArrayList<ListViewItem> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
    }
    @Override
    public int getCount(){return data.size();}
    @Override
    public String getItem(int position){return data.get(position).getLid();}
    @Override
    public long getItemId(int position){return position;}
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null) {
            convertView = inflater.inflate(layout, parent, false);
        }
//        TextView lid = ViewHolderHelper.get(convertView,R.id.lid);
//        TextView lwin = ViewHolderHelper.get(convertView,R.id.lwin);
//        TextView llose = ViewHolderHelper.get(convertView,R.id.llose);

        final ListViewItem listviewitem=data.get(position);
//        lid.setText(listviewitem.getLid());
//        lwin.setText(Integer.toString(listviewitem.getLwin()));
//        llose.setText(Integer.toString(listviewitem.getLlose()));

        return convertView;
    }
}
