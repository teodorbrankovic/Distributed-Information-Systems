package at.fhooe.sail.vis.rest.environmentrestclient;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "Sensors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sensors {

    @XmlElementWrapper(name = "sensorTypes")
    @XmlElement(name = "sensor")
    String[] mSensors;

    public Sensors() {
        mSensors = new String[1];
        mSensors[0] = "humidity";
    }

    public Sensors(String[] _sensors) {
        this.mSensors = _sensors;
    }


    @XmlElement
    public String[] getSensors() {
        return mSensors;
    }

    public void setSensors(String[] _sensors) {
        this.mSensors = _sensors;
    }

}
