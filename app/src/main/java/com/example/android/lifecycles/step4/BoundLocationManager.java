package com.example.android.lifecycles.step4;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;


public class BoundLocationManager implements DefaultLifecycleObserver {
    public static void bindLocationListenerIn(LifecycleOwner lifecycleOwner,
                                              LocationListener listener, Context context) {
        new BoundLocationListener(lifecycleOwner, listener, context);
    }

    @SuppressWarnings("MissingPermission")
    static class BoundLocationListener implements LifecycleObserver {
        private final Context mContext;
        private LocationManager mLocationManager;
        private final LocationListener mListener;

        public BoundLocationListener(LifecycleOwner lifecycleOwner,
                                     LocationListener listener, Context context) {
            mContext = context;
            mListener = listener;
            lifecycleOwner.getLifecycle().addObserver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void addLocationListener() {
            mLocationManager =
                    (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mListener);
            Log.d("BoundLocationMgr", "Listener added");

            Location lastLocation = mLocationManager.getLastKnownLocation(
                    LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                mListener.onLocationChanged(lastLocation);
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void removeLocationListener() {
            if (mLocationManager == null) {
                return;
            }
            mLocationManager.removeUpdates(mListener);
            mLocationManager = null;
            Log.d("BoundLocationMgr", "Listener removed");
        }
    }
}
