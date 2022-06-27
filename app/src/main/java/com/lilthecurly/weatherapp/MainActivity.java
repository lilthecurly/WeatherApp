package com.lilthecurly.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.gridlayout.widget.GridLayout;
import android.Manifest;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView city, temperature, celsius, weather, feels_like, humidity, pressure, wind, pop, cloudiness;
    private TextView pressure1, wind1, pop1, cloudiness1;
    private TextView scroll1_1, scroll1_2, scroll1_3, scroll1_4, scroll1_5, scroll1_6, scroll1_7, scroll1_8;
    private TextView scroll3_1, scroll3_2, scroll3_3, scroll3_4, scroll3_5, scroll3_6, scroll3_7, scroll3_8;
    private ImageView scroll2_1, scroll2_2, scroll2_3, scroll2_4, scroll2_5, scroll2_6, scroll2_7, scroll2_8;
    private TextView dayTime;
    private HorizontalScrollView horizontalScrollView;
    private GridLayout gridLayout;
    ImageView img;
    Bitmap bitmap;
    Double Longitude;
    Double Latitude;
    String URLIMAGE;

    String a = "01d";
    String b = "01n";
    String c = "02d";
    String d = "02n";
    String e = "03d";
    String f = "03n";
    String g = "04d";
    String h = "04n";
    String i = "09d";
    String j = "09n";
    String k = "10d";
    String l = "10n";
    String m = "11d";
    String n = "11n";
    String o = "13d";
    String p = "13n";
    String q = "50d";
    String r = "50d";

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        Date dateNow = new Date();
        String time = simpleDateFormat.format(dateNow);
        int TIME = Integer.parseInt(time);

        if (TIME >= 20 || TIME <= 3) {
            setContentView(R.layout.activity_night);
            Intent intent = new Intent(this, Night_Activity.class);
            startActivity(intent);
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        permissioncheck();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            init();
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Longitude = location.getLongitude();
            Latitude = location.getLatitude();
            String key = "6aa5476efdccd8783abd436c24e3a095";
            String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + Latitude + "&lon=" + Longitude + "&appid=" + key + "&units=metric";
            new GetURLData().execute(url);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void permissioncheck() {
        while (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
    }

    @Override
    public void onBackPressed() {}

    @SuppressLint("StaticFieldLeak")
    public class GetImageFromURL extends AsyncTask < String, Void, Bitmap > {

        ImageView imgView;

        public GetImageFromURL(ImageView imageView) {
            this.imgView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String...url) {
            String urldisplay = url[0];
            bitmap = null;
            try {
                InputStream srt = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        img = (ImageView) findViewById(R.id.imageView);
        gridLayout = findViewById(R.id.gridLayout);
        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        dayTime = findViewById(R.id.daytime);
        scroll1_1 = findViewById(R.id.scroll1_1);
        scroll1_2 = findViewById(R.id.scroll1_2);
        scroll1_3 = findViewById(R.id.scroll1_3);
        scroll1_4 = findViewById(R.id.scroll1_4);
        scroll1_5 = findViewById(R.id.scroll1_5);
        scroll1_6 = findViewById(R.id.scroll1_6);
        scroll1_7 = findViewById(R.id.scroll1_7);
        scroll1_8 = findViewById(R.id.scroll1_8);
        scroll2_1 = findViewById(R.id.scroll2_1);
        scroll2_2 = findViewById(R.id.scroll2_2);
        scroll2_3 = findViewById(R.id.scroll2_3);
        scroll2_4 = findViewById(R.id.scroll2_4);
        scroll2_5 = findViewById(R.id.scroll2_5);
        scroll2_6 = findViewById(R.id.scroll2_6);
        scroll2_7 = findViewById(R.id.scroll2_7);
        scroll2_8 = findViewById(R.id.scroll2_8);
        scroll3_1 = findViewById(R.id.scroll3_1);
        scroll3_2 = findViewById(R.id.scroll3_2);
        scroll3_3 = findViewById(R.id.scroll3_3);
        scroll3_4 = findViewById(R.id.scroll3_4);
        scroll3_5 = findViewById(R.id.scroll3_5);
        scroll3_6 = findViewById(R.id.scroll3_6);
        scroll3_7 = findViewById(R.id.scroll3_7);
        scroll3_8 = findViewById(R.id.scroll3_8);
        city = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        weather = findViewById(R.id.weather);
        celsius = findViewById(R.id.celsius);
        cloudiness = findViewById(R.id.cloudiness);
        cloudiness1 = findViewById(R.id.cloudiness1);
        pressure = findViewById(R.id.pressure);
        pressure1 = findViewById(R.id.pressure1);
        pop = findViewById(R.id.pop);
        pop1 = findViewById(R.id.pop1);
        wind = findViewById(R.id.wind);
        wind1 = findViewById(R.id.wind1);
        humidity = findViewById(R.id.humidity);
        feels_like = findViewById(R.id.feels_like);
    }

    @SuppressLint("StaticFieldLeak")
    class GetURLData extends AsyncTask < String, String, String > {

        @Override
        protected String doInBackground(String...strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                return buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();

                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        public void setImage(String icon, ImageView scroll) {
            if (icon.equals(a)) {
                URLIMAGE = "https://i.ibb.co/M2Yh3yP/weather-icon-01.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(b)) {
                URLIMAGE = "https://i.ibb.co/PjLJbBB/weather-icon-05.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(c)) {
                URLIMAGE = "https://i.ibb.co/qFZySxN/weather-icon-17.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(d)) {
                URLIMAGE = "https://i.ibb.co/b7hpv2Y/weather-icon-18.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);

            } else if (icon.equals(e) || icon.equals(f)) {
                URLIMAGE = "https://i.ibb.co/93Z9cgr/weather-icon-16.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(g) || icon.equals(h)) {
                URLIMAGE = "https://i.ibb.co/93Z9cgr/weather-icon-16.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(i) || icon.equals(j)) {
                URLIMAGE = "https://i.ibb.co/47QsgyG/weather-icon-36.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(k)) {
                URLIMAGE = "https://i.ibb.co/r5G595P/weather-icon-20.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(l)) {
                URLIMAGE = "https://i.ibb.co/Lg6sBxX/weather-icon-21.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(m) || icon.equals(n)) {
                URLIMAGE = "https://i.ibb.co/QnCDyGS/weather-icon-28.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(o) || icon.equals(p)) {
                URLIMAGE = "https://i.ibb.co/9T0405P/weather-icon-68.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            } else if (icon.equals(q) || icon.equals(r)) {
                URLIMAGE = "https://i.ibb.co/gJNGtc9/weather-icon-39.png";
                new GetImageFromURL(scroll).execute(URLIMAGE);
            }
        }

        @SuppressLint({
                "SetTextI18n",
                "ResourceAsColor"
        })
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                city.setText(jsonObject.getJSONObject("city").getString("name"));

                int temp = (int) jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
                temperature.setText(String.valueOf(temp));

                int colorFrom = getResources().getColor(R.color.purple);
                int colorTo = getResources().getColor(R.color.deepblue);
                int colorTo1 = getResources().getColor(R.color.green);
                int colorTo2 = getResources().getColor(R.color.purple);
                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo, colorTo1, colorTo2);
                colorAnimation.setDuration(30000);
                colorAnimation.setRepeatCount(1000000);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        temperature.setTextColor((int) animator.getAnimatedValue());
                        celsius.setTextColor((int) animator.getAnimatedValue());
                        city.setTextColor((int) animator.getAnimatedValue());
                        dayTime.setTextColor((int) animator.getAnimatedValue());
                        feels_like.setTextColor((int) animator.getAnimatedValue());
                        humidity.setTextColor((int) animator.getAnimatedValue());
                        weather.setTextColor((int) animator.getAnimatedValue());
                        pressure.setTextColor((int) animator.getAnimatedValue());
                        pressure1.setTextColor((int) animator.getAnimatedValue());
                        wind.setTextColor((int) animator.getAnimatedValue());
                        wind1.setTextColor((int) animator.getAnimatedValue());
                        pop.setTextColor((int) animator.getAnimatedValue());
                        pop1.setTextColor((int) animator.getAnimatedValue());
                        cloudiness.setTextColor((int) animator.getAnimatedValue());
                        cloudiness1.setTextColor((int) animator.getAnimatedValue());
                    }

                });

                colorAnimation.start();

                int ghost = getResources().getColor(R.color.ghost);

                int blackghost = getResources().getColor(R.color.blackghost);

                gridLayout.setBackgroundColor(ghost);

                horizontalScrollView.setBackgroundColor(blackghost);

                weather.setText(jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description"));

                celsius.setText("°C");

                int feels = (int) jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("feels_like");
                feels_like.setText("Feels like : " + feels);

                humidity.setText("Humidity : " + jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("humidity") + "%");

                pressure.setText(jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("pressure") + " hPa");

                pressure1.setText("Atm. Pressure");

                wind.setText(jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getDouble("speed") + " m/s");

                wind1.setText("Wind speed");

                double POP = (jsonObject.getJSONArray("list").getJSONObject(0).getDouble("pop") * 100);
                int PoP = ((int) POP);
                pop.setText(PoP + "%");

                pop1.setText("Probability of precipitation");

                cloudiness.setText(jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("clouds").getInt("all") + "%");

                cloudiness1.setText("Cloudiness");

                String ico = (jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("icon"));

                img = (ImageView) findViewById(R.id.imageView);

                if (ico.equals(a)) {
                    URLIMAGE = "https://i.ibb.co/hg5J3jf/sun.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(b)) {
                    URLIMAGE = "https://i.ibb.co/SR2tJjs/crescent-moon.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(c)) {
                    URLIMAGE = "https://i.ibb.co/6HFsJ61/sunclouds.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(d)) {
                    URLIMAGE = "https://i.ibb.co/KX16QMB/night.png";
                    new GetImageFromURL(img).execute(URLIMAGE);

                } else if (ico.equals(e) || ico.equals(f)) {
                    URLIMAGE = "https://i.ibb.co/Sxdjpf2/cloud-computing.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(g) || ico.equals(h)) {
                    URLIMAGE = "https://i.ibb.co/Qcrrw66/clouds.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(i) || ico.equals(j)) {
                    URLIMAGE = "https://i.ibb.co/xj7QWJJ/rain.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(k)) {
                    URLIMAGE = "https://i.ibb.co/6PSW1q2/rainy-day.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(l)) {
                    URLIMAGE = "https://i.ibb.co/0FsVmMy/rainy-night.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(m) || ico.equals(n)) {
                    URLIMAGE = "https://i.ibb.co/ym2qMwJ/thunderstorm.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(o) || ico.equals(p)) {
                    URLIMAGE = "https://i.ibb.co/YNnR922/snowflake.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                } else if (ico.equals(q) || ico.equals(r)) {
                    URLIMAGE = "https://i.ibb.co/QX96pVm/fog.png";
                    new GetImageFromURL(img).execute(URLIMAGE);
                }
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                Date dateNow = new Date();
                String time = simpleDateFormat.format(dateNow);
                int TIME = Integer.parseInt(time);

                if (TIME >= 0 && TIME <= 3) {
                    String time1 = "03:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("06:00");
                    scroll1_3.setText("09:00");
                    scroll1_4.setText("12:00");
                    scroll1_5.setText("15:00");
                    scroll1_6.setText("18:00");
                    scroll1_7.setText("21:00");
                    scroll1_8.setText("00:00");
                } else if (TIME >= 3 && TIME <= 6) {
                    String time1 = "06:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("09:00");
                    scroll1_3.setText("12:00");
                    scroll1_4.setText("15:00");
                    scroll1_5.setText("18:00");
                    scroll1_6.setText("21:00");
                    scroll1_7.setText("00:00");
                    scroll1_8.setText("03:00");
                } else if (TIME >= 6 && TIME <= 9) {
                    String time1 = "09:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("12:00");
                    scroll1_3.setText("15:00");
                    scroll1_4.setText("18:00");
                    scroll1_5.setText("21:00");
                    scroll1_6.setText("00:00");
                    scroll1_7.setText("03:00");
                    scroll1_8.setText("06:00");
                } else if (TIME >= 9 && TIME <= 12) {
                    String time1 = "12:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("15:00");
                    scroll1_3.setText("18:00");
                    scroll1_4.setText("21:00");
                    scroll1_5.setText("00:00");
                    scroll1_6.setText("03:00");
                    scroll1_7.setText("06:00");
                    scroll1_8.setText("09:00");
                } else if (TIME >= 12 && TIME <= 15) {
                    String time1 = "15:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("18:00");
                    scroll1_3.setText("21:00");
                    scroll1_4.setText("00:00");
                    scroll1_5.setText("03:00");
                    scroll1_6.setText("06:00");
                    scroll1_7.setText("09:00");
                    scroll1_8.setText("12:00");
                } else if (TIME >= 15 && TIME <= 18) {
                    String time1 = "18:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("21:00");
                    scroll1_3.setText("00:00");
                    scroll1_4.setText("03:00");
                    scroll1_5.setText("06:00");
                    scroll1_6.setText("09:00");
                    scroll1_7.setText("12:00");
                    scroll1_8.setText("15:00");
                } else if (TIME >= 18 && TIME <= 21) {
                    String time1 = "21:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("00:00");
                    scroll1_3.setText("03:00");
                    scroll1_4.setText("06:00");
                    scroll1_5.setText("09:00");
                    scroll1_6.setText("12:00");
                    scroll1_7.setText("15:00");
                    scroll1_8.setText("18:00");
                } else if (TIME >= 21) {
                    String time1 = "00:00";
                    scroll1_1.setText(time1);
                    scroll1_2.setText("03:00");
                    scroll1_3.setText("06:00");
                    scroll1_4.setText("09:00");
                    scroll1_5.setText("12:00");
                    scroll1_6.setText("15:00");
                    scroll1_7.setText("18:00");
                    scroll1_8.setText("21:00");
                }

                String ico1 = (jsonObject.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("icon"));
                String ico2 = (jsonObject.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("icon"));
                String ico3 = (jsonObject.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("icon"));
                String ico4 = (jsonObject.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("icon"));
                String ico5 = (jsonObject.getJSONArray("list").getJSONObject(5).getJSONArray("weather").getJSONObject(0).getString("icon"));
                String ico6 = (jsonObject.getJSONArray("list").getJSONObject(6).getJSONArray("weather").getJSONObject(0).getString("icon"));
                String ico7 = (jsonObject.getJSONArray("list").getJSONObject(7).getJSONArray("weather").getJSONObject(0).getString("icon"));
                String ico8 = (jsonObject.getJSONArray("list").getJSONObject(8).getJSONArray("weather").getJSONObject(0).getString("icon"));

                setImage(ico1, scroll2_1);
                setImage(ico2, scroll2_2);
                setImage(ico3, scroll2_3);
                setImage(ico4, scroll2_4);
                setImage(ico5, scroll2_5);
                setImage(ico6, scroll2_6);
                setImage(ico7, scroll2_7);
                setImage(ico8, scroll2_8);

                int temp1 = (int) jsonObject.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp");
                scroll3_1.setText(temp1 + "°C");

                int temp2 = (int) jsonObject.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp");
                scroll3_2.setText(temp2 + "°C");

                int temp3 = (int) jsonObject.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp");
                scroll3_3.setText(temp3 + "°C");

                int temp4 = (int) jsonObject.getJSONArray("list").getJSONObject(4).getJSONObject("main").getDouble("temp");
                scroll3_4.setText(temp4 + "°C");

                int temp5 = (int) jsonObject.getJSONArray("list").getJSONObject(5).getJSONObject("main").getDouble("temp");
                scroll3_5.setText(temp5 + "°C");

                int temp6 = (int) jsonObject.getJSONArray("list").getJSONObject(6).getJSONObject("main").getDouble("temp");
                scroll3_6.setText(temp6 + "°C");

                int temp7 = (int) jsonObject.getJSONArray("list").getJSONObject(7).getJSONObject("main").getDouble("temp");
                scroll3_7.setText(temp7 + "°C");

                int temp8 = (int) jsonObject.getJSONArray("list").getJSONObject(8).getJSONObject("main").getDouble("temp");
                scroll3_8.setText(temp8 + "°C");

                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormatday = new SimpleDateFormat("E, dd.MM.yyyy", Locale.US);
                Date datetoday = new Date();
                String timenow = simpleDateFormatday.format(datetoday);
                dayTime.setText(timenow);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}