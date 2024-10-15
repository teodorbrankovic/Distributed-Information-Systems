package at.fhooe.sail.vis.jaxb.json;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@XmlRootElement // root element of XML
@XmlAccessorType(XmlAccessType.FIELD) // access fields directly
@XmlType(propOrder = {"mName", "mNickname", "mBirthday", "mType", "mVaccinations", "mID"})
public class Pet {


    String mName;

    @XmlAttribute(name = "nickname") // name of XML attribute
    String mNickname;

    Date mBirthday;

    Type mType; // enum cat, dog, mour or bird

    String[] mVaccinations;

    String mID;

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
