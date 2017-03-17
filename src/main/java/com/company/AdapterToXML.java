package com.company;

import com.company.User.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdapterToXML {

    public void userToXML(List<User> users, String path) throws IOException, JAXBException {
        FileWriter fileWriter = new FileWriter(path);
        for (User u:
             users) {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller jaxbMarshaller =jaxbContext.createMarshaller();

            jaxbMarshaller.marshal(u, fileWriter);
            jaxbMarshaller.marshal(u, System.out);
        }
    }
}
