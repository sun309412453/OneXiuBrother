package com.zykj.onexiubrother.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zykj.onexiubrother.R;
import com.zykj.onexiubrother.utils.Y;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_DiZhiMap extends Activity {
    @Bind(R.id.et_map)
    EditText etMap;
    @Bind(R.id.map_ok)
    Button mapOk;
    @Bind(R.id.bmapView)
    MapView bmapView;
    private BaiduMap baiduMap;
    LocationClient mclient;
    private LatLng latLng;
    private Boolean diyici = true;
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//初始化百度地图上下文
        setContentView(R.layout.dizhi_map);
        ButterKnife.bind(this);
        baiduMap = bmapView.getMap();
        chushihua();
        dianji();

    }

    public void dianji() {
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                GeoCoder geoCoder = GeoCoder.newInstance();
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                        OverlayOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.tubiao)).position(geoCodeResult.getLocation());
                        baiduMap.addOverlay(options);
                    }

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        OverlayOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.tubiao)).position(reverseGeoCodeResult.getLocation());
                        baiduMap.addOverlay(options);
                        etMap.setText(reverseGeoCodeResult.getAddress());
                    }
                });
            }

            @Override   //兴趣点
            public boolean onMapPoiClick(MapPoi mapPoi) {
                GeoCoder geoCoder = GeoCoder.newInstance();
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(mapPoi.getPosition()));
                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                        OverlayOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.tubiao)).position(geoCodeResult.getLocation());
                        baiduMap.addOverlay(options);
                    }

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        OverlayOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.tubiao)).position(reverseGeoCodeResult.getLocation());
                        baiduMap.addOverlay(options);
                        etMap.setText(reverseGeoCodeResult.getAddress());
                    }
                });
                return false;
            }
        });
    }

    public void chushihua() {
        baiduMap.clear();
        mclient = new LocationClient(this);//初始化LocationClient类
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setCoorType("bd09ll");//设置经纬度为某地图的经纬度   例：bd09ll（不是1）为百度经纬度
        locationClientOption.setScanSpan(40000000);//每几秒定位一次
        locationClientOption.setOpenGps(true);//是否支持GPS
        locationClientOption.setIsNeedAddress(true);//是否成功后返回地址信息
        mclient.setLocOption(locationClientOption);//把locationClientOption设置在mclient中
        mclient.registerLocationListener(new BDLocationListener() {//添加注册事件
            @Override
            public void onReceiveLocation(final BDLocation bdLocation) {//定位成功后调用此函数
                if (bdLocation == null || bmapView == null) {
                    return;
                }
                etMap.post(new Runnable() {
                    @Override
                    public void run() {
                        etMap.setText(bdLocation.getAddrStr());
                    }
                });
                Y.ADDRESS.setCity_code(bdLocation.getAddress().cityCode);
                Y.ADDRESS.setCity_name(bdLocation.getAddress().city);
                Y.ADDRESS.setLat(bdLocation.getLatitude()+"");
                Y.ADDRESS.setLon(bdLocation.getLongitude()+"");
                Y.ADDRESS.setRegion(bdLocation.getAddress().district);
                city=bdLocation.getAddress().city;
                MyLocationData myLocationData = new MyLocationData.Builder()//创建MyLocationData.Builder()类来设置定位的准确度
                        .latitude(bdLocation.getLatitude()) //经纬度
                        .longitude(bdLocation.getLongitude())//经纬度
                        .accuracy(bdLocation.getRadius())//半径
                        .build();
                baiduMap.setMyLocationData(myLocationData);//把设置好的信息设置在baiduMap中
                latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());//把定位的经纬度提出来，方便调用
                if (diyici) {//设置只有第一次定位跳转到自己的位置
                    baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(latLng).zoom(21).build()));
                    diyici = false;
                }

                //设置图像坐标
                OverlayOptions overlayOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.tubiao)).position(latLng);
                baiduMap.addOverlay(overlayOptions);
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {
                //兴趣点信息
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mclient.isStarted()) {
            baiduMap.setMyLocationEnabled(true);//开启定位层
            mclient.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        baiduMap.setMyLocationEnabled(false);//关闭定位层
        mclient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmapView.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//创建系统菜单
        menu.add("点我找自己");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//设置系统菜单的点击事件
        if ("点我找自己" == item.toString()) {
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(mapStatusUpdate);
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.map_ok)
    public void onViewClicked() {
        final String dizhi = etMap.getText().toString();
        if (TextUtils.isEmpty(dizhi)) {
            Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            GeoCoder geoCoder = GeoCoder.newInstance();
            geoCoder.geocode(new GeoCodeOption().address(dizhi).city(Y.USER.getCity()));
            geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                    if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        Toast.makeText(Activity_DiZhiMap.this, "输入地址不合法", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                        baiduMap.animateMapStatus(mapStatusUpdate);
                        Intent intent =getIntent();
                        intent.putExtra("dizhi",etMap.getText().toString());
                        Activity_DiZhiMap.this.setResult(1,intent);
                        finish();
                    }
                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                }
            });
        }
    }
}
