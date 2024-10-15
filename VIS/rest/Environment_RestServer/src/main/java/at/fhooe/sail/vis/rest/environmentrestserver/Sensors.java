package at.fhooe.sail.vis.rest.environmentrestserver;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "Sensors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sensors {

    @XmlElement(name = "sensor")
    String[] mSensors;

    public Sensors() {
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
