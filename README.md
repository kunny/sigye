Sigye (시계; Clock)
=====

*Sample watchface application for Android Wear*

## About Sigye
**Sigye**, which means 'Clock' in Korean, is a sample Watchface app for Android Wear. `Watchface` is an exclusive component for Android Wear, which is similar to `Wallpaper` on Mobile(Phone, Tablet) platform.

Sigye provides simple watchface application which includes **essential** components for making watchfaces, so you can take Sigye as a start point for your own watchface app.

### Helper class from Sigye
#### ClockHandler
`ClockHandler` class notifies to you through `ClockHandler.ClockListener` as time changes, with interval of one minute.

Once you set a listener on the `Acticity` and call `ClockHandler.listen()`, the `ClockListener` will starts call `onTick(Calendar)` callback method in the `ClockHandler.ClockListener`.

After you are done with using `ClockHandler`, call `ClockHandler.stop()` to stop listening the time changes.

### Known Issues & Limitations
Since there are no official documentations for Watchface development, current implementation has following limitations :

1. No support for small notifications  
Most watchface uses entire area of the screen, so the 'Pending' notifications(which is not opened/removed by user) should not hide the watchface contents. However, currently there are no known flag/method to support 'Small' notification mode.  
Most of the default watchface app uses small notification mode, so the notification content hides only small area of the watchface.

2. No support for 'Ok google' text and Battery indicator  
There are no known method to set the alignment for these components. Battery indicator showed on the top leftmost area, and 'Ok google' text is showed on just below of the battery indicator.

### Sample Implementations
#### Simple Digital Sigye
![Simple Digital Sigye](https://github.com/kunny/sigye/raw/master/readme_assets/simple_digital_sigye.png)  
Simple Digital Sigye shows the current time with white on the black background.

## Steps to write your own Watchface app
### Step 1: Make new Android Wear Project
I beleive that I don't have to explain how to do this :)

### Step 2: Create an activity to be dispalyed as a Watchface
Once the activity has created, proceed following steps.

1. `android:allowEmbedded="true"` in `<activity>` tag
2. Set activity theme to `android:theme="@android:style/Theme.DeviceDefault.NoActionBar"`
3. Add following meta-data inside `<activity>` tag:

        <meta-data android:name="com.google.android.clockwork.home.preview"
                        android:resource="@drawable/[PREVIEW_DRAWABLE_RESOURCE]" />
4. Set activity's intent filter as:  

	    <intent-filter>
	        <action android:name="android.intent.action.MAIN" />
	        <category android:name="com.google.android.clockwork.home.category.HOME_BACKGROUND" />
	    </intent-filter>

### Step 3: Add following permission to Manifest:  

    <uses-permission android:name="com.google.android.permission.PROVIDE_BACKGROUND" />

It's all done! Now, All you have to do is make your own watchface design!

## License
	Copyright (c) 2014 Taeho Kim <jyte82@gmail.com>

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.


