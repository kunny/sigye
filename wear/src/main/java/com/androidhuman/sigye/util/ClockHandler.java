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

package com.androidhuman.sigye.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.Calendar;

/**
 * Created by kunny on 2014. 7. 13..
 */
public class ClockHandler {
    private Context context;
    private ClockListener listener;
    private Calendar calendar;

    private ClockHandler(){
        calendar = Calendar.getInstance();
    }

    public ClockHandler(Context context){
        this();
        this.context = context;
    }

    public ClockHandler(Context context, ClockListener listener){
        this(context);
        setClockListener(listener);
    }

    public void setClockListener(ClockListener listener){
        this.listener = listener;
    }

    public void listen(){
        IntentFilter timeTickFilter = new IntentFilter();
        timeTickFilter.addAction(Intent.ACTION_TIME_TICK);
        context.registerReceiver(timeTickReceiver, timeTickFilter);

        IntentFilter timeZoneFilter = new IntentFilter();
        timeZoneFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        context.registerReceiver(timeZoneReceiver, timeZoneFilter);
    }

    public void stop(){
        context.unregisterReceiver(timeTickReceiver);
        context.unregisterReceiver(timeZoneReceiver);
    }

    public void updateTime(){
        calendar.setTimeInMillis(System.currentTimeMillis());
        listener.onTick(calendar);
    }

    private BroadcastReceiver timeTickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateTime();
        }
    };

    private BroadcastReceiver timeZoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateTime();
        }
    };

    public static interface ClockListener{
        public void onTick(Calendar calendar);
    }

}
