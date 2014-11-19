package com.bme.bg.phrobeclient.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Data object
 */
@Entity
public class PhrobeDataObject {
    @Id
    @GeneratedValue
    public Long id;

    public String apiKey;

    public long timestamp;
    public int interval;
    public LocationPref location = new LocationPref();
    public GsmSingalPref signalStrength = new GsmSingalPref();

    public PhrobeDataObject() {
    }

    @Embeddable
    public static class LocationPref {
        public double accuracy;
        public double altitude;
        public double bearing;
        public double elapsedRealtimeNanos;
        public double latitude;
        public  double longitude;
        public String provider;
        public double speed;
        public long time;

        @Override
        public String toString() {
            return "LocationPref{" +
                    "accuracy=" + accuracy +
                    ", altitude=" + altitude +
                    ", bearing=" + bearing +
                    ", elapsedRealtimeNanos=" + elapsedRealtimeNanos +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", provider='" + provider + '\'' +
                    ", speed=" + speed +
                    ", time=" + time +
                    '}';
        }
    }

    @Embeddable
    public static class GsmSingalPref {
        public int dbm;
        public int signalStrength;

        @Override
        public String toString() {
            return "GsmSingalPref{" +
                    "dbm=" + dbm +
                    ", signalStrength=" + signalStrength +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PhrobeDataObject{" +
                "id=" + id +
                ", apiKey='" + apiKey + '\'' +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", location=" + location +
                ", signalStrength=" + signalStrength +
                '}';
    }
}
