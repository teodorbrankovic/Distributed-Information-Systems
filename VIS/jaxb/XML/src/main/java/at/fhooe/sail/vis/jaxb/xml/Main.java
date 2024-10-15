package at.fhooe.sail.vis.jaxb.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

public class Main {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance(Pet.class);

        // create new pet
        Pet pet = new Pet("Thomas", "Tom", new Date(1940, 2, 10), Pet.Type.CAT,
                new String[]{"cat flu", "feline distemper", "rabies", "leucosis"}, "123456789");

        // marshal pet to XML
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        StringWriter sw = new StringWriter(); // write to string
        m.marshal(pet, sw); // marshal to string

        String data = sw.getBuffer().toString();
        System.out.println(data);

        // unmarshal XML to pet
        Unmarshaller um = jc.createUnmarshaller();
        Pet pet2 = (Pet) um.unmarshal(new StringReader(data));

        // print pet
        System.out.println();
        System.out.println("Task 3.1.d - PART A");
        System.out.println();
        System.out.println("----jaxb/XML----");
        System.out.println(pet2.toString());

    }
}