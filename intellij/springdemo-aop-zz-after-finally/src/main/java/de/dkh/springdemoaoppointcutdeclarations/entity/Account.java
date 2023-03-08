package de.dkh.springdemoaoppointcutdeclarations.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Account {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String level;
}
