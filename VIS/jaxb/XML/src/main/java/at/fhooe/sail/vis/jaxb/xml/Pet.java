package at.fhooe.sail.vis.jaxb.xml;

import jakarta.xml.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;

@XmlRootElement // root element of XML
@XmlAccessorType(XmlAccessType.FIELD) // access fields directly
public class Pet {

    @XmlElement(name = "name") // name of XML element
    String mName;

    @XmlAttribute(name = "nickname") // name of XML attribute
    String mNickname;

    @XmlElement(name = "birthday") // name of XML element
    @XmlSchemaType(name = "date")
    Date mBirthday;

    @XmlElement(name = "type") // name of XML element
    Type mType; // enum cat, dog, mour or bird

    @XmlElementWrapper(name = "vaccinations")
    @XmlElement(name = "vaccination")
    String[] mVaccinations;

    @XmlElement(name = "id") // name of XML element
    String mID;

    @XmlType(namespace = "at.fhooe.sail.vis.jaxb.xml")
    @XmlEnum
    public enum Type {
        CAT, DOG, MOUSE, BIRD
    }

    // Standardkonstruktor (erforderlich von JAXB)
    public Pet() {
    }

    public Pet(String name, String nickname, Date birthday, Type type, String[] vaccinations, String id) {
        mName = name;
        mNickname = nickname;
        mBirthday = birthday;
        mType = type;
        mVaccinations = vaccinations;
        mID = id;
    }

    @Override
    public String toString() {
        return "Pet: " +
                "name = " + mName +
                ", nickname = " + mNickname +
                ", birthday = " + mBirthday +
                ", type = " + mType +
                ", vaccinations = " + Arrays.toString(mVaccinations) +
                ", id = " + mID;
    }

}
