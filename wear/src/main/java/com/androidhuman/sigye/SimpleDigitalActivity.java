/*
 * Copyright (c) 2014 Taeho Kim <jyte82@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidhuman.sigye;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

import com.androidhuman.sigye.util.ClockHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SimpleDigitalActivity extends Activity implements ClockHandler.ClockListener{

    private TextView tvTop;
    private TextView tvMiddle;
    private TextView tvBottom;

    private ClockHandler handler;

    private SimpleDateFormat topDateFormat = new SimpleDateFormat("a", Locale.getDefault());
    private SimpleDateFormat middleDateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
    private SimpleDateFormat bottomDateFormat = new SimpleDateFormat("EEEE, MM/dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_digital);

        handler = new ClockHandler(this, this);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                tvTop = (TextView) findViewById(R.id.tvTop);
                tvMiddle = (TextView) findViewById(R.id.tvMiddle);
                tvBottom = (TextView) findViewById(R.id.tvBottom);

                handler.updateTime();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.listen();
        if(tvBottom!=null){
            tvBottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Screen has dimmed, so let's hide the bottom area.
        tvBottom.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.stop();
    }

    @Override
    public void onTick(Calendar calendar) {
        tvTop.setText(topDateFormat.format(calendar.getTime()));
        tvMiddle.setText(middleDateFormat.format(calendar.getTime()));
        tvBottom.setText(bottomDateFormat.format(calendar.getTime()));
    }
}
