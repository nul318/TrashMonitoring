package inhain.trashmonitoring;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.api.MapView.MapViewEventListener;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by DANBI on 2017-03-18.
 */

public class MapViewDialog extends Activity
        implements MapViewEventListener, MapView.POIItemEventListener{

    private TextView txtProgress;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();
    MapView mapView;

    double x;
    double y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        setContentView(R.layout.map_view);


        mapView = new MapView(MapViewDialog.this);
        mapView.setDaumMapApiKey("1339174b547c86283d915e6ee1a4bff8");
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map);
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener(this); // this에 MapView.MapViewEventListener 구현.
        mapView.setPOIItemEventListener(this);


















        TextView txt_address = (TextView) findViewById(R.id.Title);
        TextView txt_sub_title = (TextView) findViewById(R.id.sub_title);


        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        String location = intent.getStringExtra("location");
        final int percent = intent.getIntExtra("percent",0);
        x = intent.getDoubleExtra("x", 0.0);
        y = intent.getDoubleExtra("y", 0.0);


        txt_address.setText(address);
        txt_sub_title.setText(location);









        txtProgress = (TextView) findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= percent) { // 여기서 30말고 퍼센트 게이지변수 데려오면 됨!
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            txtProgress.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(x, y), true);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Trash Can");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(x ,y));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}


