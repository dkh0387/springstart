package de.dkh.jacksondatabindingjsondemo01procesingjson.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;

/**
 * Example of converting JSON to POJO using jackson.
 */
public class Driver {

    public static void main(String[] args) {

        try {

            // create a jackson mapper
            ObjectMapper objectMapper = new ObjectMapper();

            // read JSON file and convert to POJO
            Student student = objectMapper.readValue(new File("src/main/resources/data/sample-lite.json"), Student.class);

            System.out.println("Student: " + student.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
