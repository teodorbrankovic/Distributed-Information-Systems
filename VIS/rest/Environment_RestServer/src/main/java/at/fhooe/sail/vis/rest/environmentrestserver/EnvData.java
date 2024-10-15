package at.fhooe.sail.vis.rest.environmentrestserver;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "EnvData")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnvData implements Serializable {

    @XmlElement(name = "SensorName")
    String mSensorName;

    @XmlElement(name = "Timestamp")
    long mTimestamp;

    @XmlElementWrapper(name = "Values")
    @XmlElement(name = "Value")
    int[] mValues;

    public EnvData() {
    }

    public EnvData(String _sensorName, long _timestamp, int[] _values) {
        mSensorName = _sensorName;
        mTimestamp = _timestamp;
        mValues = _values;
    }

    public String getSensorName() {
        return mSensorName;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public int[] getValues() {
        return mValues;
    }

    @Override
    public String toString() {
        StringBuilder wendy = new StringBuilder();
        wendy.append("Sensor: " + mSensorName + " Timestamp: " + mTimestamp + " Values: ");
        for (int i = 0; i < mValues.length; i++) {
            wendy.append(mValues[i] + " ");
        }
        return wendy.toString();
    }
}
