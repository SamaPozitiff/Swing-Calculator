package org.example.util;

import org.example.entity.Operations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


public class XmlUtils {

    /**
     *Производит маршаллинг объекта Operations в XML файл
     */
    public static void marshall(Operations object) {
        try {
            JAXBContext context = JAXBContext.newInstance(Operations.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String fileName = "exp";
            int number = 1;
            String format = ".XML";
            while (isFileExists("src/main/resources/" + fileName + number + format)) {
                number++;
            }
            marshaller.marshal(object, new File("src/main/resources/" + fileName + number + format));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isFileExists(String filePath) {
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }
}
