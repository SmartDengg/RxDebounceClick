package com.smartdengg.example;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;

/**
 * Created by SmartDengg on 2016/4/30.
 */
public class Entity {

    @SuppressLint("SimpleDateFormat") private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss·S");

    private long timeline;
    private String processInfo;

    public Entity(long timeline, String processInfo) {
        this.timeline = timeline;
        this.processInfo = processInfo;
    }

    @Override
    public String toString() {
        return "Trigger ⇢\n" + "    Timeline = '" + formatter.format(timeline) + "'\n    ProcessInfo = '" + processInfo + "'";
    }
}
