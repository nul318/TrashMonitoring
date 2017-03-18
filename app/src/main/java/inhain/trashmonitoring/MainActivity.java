package inhain.trashmonitoring;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static String SERVER_IP = "52.79.189.195";
    public static int SERVER_PORT = 19999;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    public String trash_can_num = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

















        ArrayList<TrashCan> list_component = new ArrayList<>();



        list_component.add(new TrashCan(0,"서울 고시텔","20m 이내",52,"#FFFFFF"));
        list_component.add(new TrashCan(1,"관악 실버케어","100m 이내",52,"#FFFFFF"));
        list_component.add(new TrashCan(2,"럭키빌 고시원","10m 이내",52,"#FFFFFF"));
        list_component.add(new TrashCan(3,"휴 레지던스","50m 이내",52,"#FFFFFF"));
        list_component.add(new TrashCan(4,"실버 사거리","10m 이내",52,"#FFFFFF"));
        list_component.add(new TrashCan(5,"인하대 후문","30m 이내",52,"#FFFFFF"));
        list_component.add(new TrashCan(6,"부천대 앞","40m 이내",52,"#FFFFFF"));


        myAdapter Adapter = new myAdapter(getApplicationContext(), R.layout.item, list_component);
        ListView list = (ListView) findViewById(R.id.trash_list);

        list.setAdapter(Adapter);





        new Thread(new Runnable() {
            public void run() {
                connect();
            }
        }
        ).start();






    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
        }
    }

    public void connect() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            while (socket != null) {
                if (socket.isConnected()) {
                    output.writeUTF("p`1`1`스마트폰 연결`");
                    output.flush();
                    break;
                }
            }
            MessageReciver messageReceiver = new MessageReciver();
            messageReceiver.start();
        } catch (Exception e) {
            System.out.println("서버에 접속할 수 없습니다.");
            this.finish();
        }
    }


    public class MessageReciver extends Thread {
        public void run() {
            try {
                String received;
                while ((received = input.readUTF()) != null) {
                    final String[] buffer = received.split("`");
                    switch (buffer[0].charAt(0)) {
                        case 'n':
//                            chatMessage = buffer[1]; //입장
//                            System.out.println(buffer[1]);
                            break;
                        case 'c':
                            trash_can_num = buffer[1];
                            System.out.println(trash_can_num + "번 쓰레기통에 투입");
                            break;
                        case 'x':
//                            chatMessage = buffer[1]; //퇴장
                            break;
                    }

                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }






    public class myAdapter extends BaseAdapter {
        Context con;
        LayoutInflater inflater;
        ArrayList<TrashCan> components_list;
        int layout;
        myAdapter(Context context, int layout, ArrayList<TrashCan> components_list) {
            con = context;
            this.layout = layout;
            this.components_list = components_list;
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // 멤버변수 초기화
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return components_list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return components_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if (null == convertView) {
                convertView = inflater.inflate(layout, parent, false);
            }

            TextView address = (TextView) convertView.findViewById(R.id.address);
            TextView location = (TextView) convertView.findViewById(R.id.location);
            TextView percent = (TextView) convertView.findViewById(R.id.percent);

            address.setText(components_list.get(position).address);
            location.setText(components_list.get(position).location);
            percent.setText(String.valueOf(components_list.get(position).percent) + "%");



            return convertView;
        }
    }
}
