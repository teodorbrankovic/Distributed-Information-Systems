package at.fhooe.sail.vis.rest.environmentrestserver;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "ListData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListData {

    @XmlElementWrapper(name = "EnvDatas")
    @XmlElement(name = "EnvData")
    EnvData[] mEnvData;

    @XmlElement(name = "size")
    int mSize;

    public ListData() {
        mEnvData = new EnvData[1];
    }

    public ListData(int _size) {
        mEnvData = new EnvData[_size];
    }

    public void setEnvData(EnvData _data) {
        mEnvData[mSize] = _data;
        mSize++;
    }
}
