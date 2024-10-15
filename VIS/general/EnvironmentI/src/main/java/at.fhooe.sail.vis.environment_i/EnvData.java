package at.fhooe.sail.vis.environment_i;

import java.io.Serializable;

/**
 * The EnvData class represents environmental data collected from a sensor.
 * It encapsulates the sensor's name, the timestamp of the data collection,
 * and an array of values recorded by the sensor.
 */
public class EnvData implements Serializable {
    private String mSensorName;
    private long mTimestamp;
    private int[] mValues;

    /**
     * Constructs a new EnvData object with specified sensor name, timestamp, and values.
     *
     * @param _sensorName The name of the sensor.
     * @param _timestamp  The timestamp of the data collection.
     * @param _values     The recorded values from the sensor.
     */
    public EnvData(String _sensorName, long _timestamp, int[] _values) {
        mSensorName = _sensorName;
        mTimestamp = _timestamp;
        mValues = _values;
    }

    /**
     * Default constructor for EnvData.
     */
    public EnvData() {
    }

    /**
     * Retrieves the name of the sensor.
     *
     * @return The name of the sensor.
     */
    public String getSensorName() {
        return mSensorName;
    }

    /**
     * Returns a string representation of the EnvData object.
     *
     * @return A string containing the sensor name, timestamp, and sensor values.
     */
    public String toString() {
        String s = "Sensor: " + mSensorName + " ";
        s += "Timestamp: " + mTimestamp + " ";
        s += "Values: ";
        for (int i = 0; i < mValues.length; i++) {
            s += mValues[i] + " ";
        }
        s += "\n";
        return s;
    }

    /**
     * Sets the name of the sensor.
     */
    public String getmSensorName() {
        return mSensorName;
    }

    /**
     * Retrieves the timestamp of the data collection.
     *
     * @return The timestamp of the data collection.
     */
    public long getmTimestamp() {
        return mTimestamp;
    }

    /**
     * Retrieves the recorded values from the sensor.
     *
     * @return An array of integers representing the sensor's recorded values.
     */
    public int[] getmValues() {
        return mValues;
    }
}


