package com.magicwind.android.fitness_club.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.magicwind.android.fitness_club.R;

import java.net.URL;
import java.util.Date;

public class class_details extends AppCompatActivity{
    private SurfaceView view1;
    private MediaPlayer player;
    private SurfaceHolder holder;

    //地图
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    private LocationManager locationManager;
    private String provider;

    //自定义定位图标相关
    private BitmapDescriptor bitmapDescriptor;
    private String videochose;

    private static final int REQUEST_LOCATION_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        videochose = intent.getStringExtra("videoname");
        setContentView(R.layout.activity_detail);


        getSupportActionBar().hide();
        String url = "";
        Log.d("开始了",videochose);
        if(videochose.equals("1")){
            url ="android.resource://" + getPackageName() + "/"+ R.raw.a;
        }else{
            url ="android.resource://" + getPackageName() + "/"+ R.raw.b;
        }



        Log.d("开始了",url);
        VideoView videoView=findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus();
        videoView.start();

        map();
        initLocation();
        add_mark();
    }

    //拨号的功能
    public void dailing(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + "13311385789");
        intent.setData(data);
        startActivity(intent);
    }

    //发信息的功能
    public void sendSMS(View view)
    {
        Uri smsToUri = Uri.parse("smsto:"+"13311385789");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("盛永坤", "你好啊！");
        startActivity(intent);
    }

    //发信息的功能
    public void sendEmail(View view)
    {
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:16301073@bjtu.edu.cn"));
        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
        startActivity(data);
    }

    public void map(){
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        // 位置管理
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //经度纬度的标准
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        //Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(class_details.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            // Initialize the location fields
            if (location != null) {
                System.out.println("Provider " + provider + " has been selected.");
            }
        }
        initLocation();
        add_mark();
    }
    //初始化地图
    private void initLocation(){
        //声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
        //可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
        //可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
        //可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
        //可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
        //可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
        //locationClient.start();
        //初始化图标
    }

    //在地图上标点
    private void add_mark(){
        //定义Maker坐标点

        LatLng point = new LatLng(39.957013, 116.34962);

        //构建Marker图标

        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.pin24);

        //构建MarkerOption，用于在地图上添加Marker

        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);

        //在地图上添加Marker，并显示

        mBaiduMap.addOverlay(option);
        //构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .fontSize(48)
                .text("所在地")
                .rotate(0)
                .position(point);

        //在地图上添加该文字对象并显示
        mBaiduMap.addOverlay(textOption);
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(18)
                .build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(mMapStatus);;
        mBaiduMap.setMapStatus(msu);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(class_details.this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( class_details.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
