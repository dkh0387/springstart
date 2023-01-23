package de.dkh.springdemo.mvc;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Student {

    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String country;
    @Getter
    private Set<Country> countries = Arrays.stream(Country.values()).collect(Collectors.toSet());
    @Getter
    @Setter
    private String language;
    @Getter
    private Set<ProgLanguage> languages = Arrays.stream(ProgLanguage.values()).collect(Collectors.toSet());

    /**
     * Inner Enum for drop down data binding in {@code student-form.jsp}.
     */
    enum Country {
        GERMANY("Germany"), BRAZIL("Brazil"), USA("USA"), JAPAN("Japan");

        @Getter
        private final String label;

        Country(String label) {
            this.label = label;
        }

    }

    /**
     * Inner Enum for radios data binding in {@code student-form.jsp}.
     */
    enum ProgLanguage {
        JAVA("Java"), C("C#"), PHP("PHP"), PYTHON("Python");

        @Getter
        private final String label;

        ProgLanguage(String label) {
            this.label = label;
        }

    }

}
