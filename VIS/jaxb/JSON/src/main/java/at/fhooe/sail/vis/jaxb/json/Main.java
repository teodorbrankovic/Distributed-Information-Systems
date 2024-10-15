package at.fhooe.sail.vis.jaxb.json;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

public class Main {


    public static void main(String[] args) throws JAXBException {

        Pet pet = new Pet("Thomas", "Tom", new Date(1940, 2, 10), Pet.Type.CAT,
                new String[]{"cat flu", "feline distemper", "rabies", "leucosis"}, "123456789");

        // JAXBContext using MOXy
        JAXBContext jc = JAXBContext.newInstance(Pet.class);

        // create Marshaller
        Marshaller m = jc.createMarshaller();
        m.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // serialize to JSON
        StringWriter sw = new StringWriter();
        m.marshal(pet, sw);

        String data = sw.toString(); // JSON string
        System.out.println(data);

        // deserialize from JSON
        Unmarshaller um = jc.createUnmarshaller();
        um.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);


        Pet pet2 = (Pet) um.unmarshal(new StringReader(data));

        System.out.println();
        System.out.println("Task 3.1.d - PART B");
        System.out.println();
        System.out.println("----jaxb/JSON----");
        System.out.println(pet2);
    }
}