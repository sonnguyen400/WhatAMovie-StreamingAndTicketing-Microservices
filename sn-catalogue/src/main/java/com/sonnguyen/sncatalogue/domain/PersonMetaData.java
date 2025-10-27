package com.sonnguyen.sncatalogue.domain;

import lombok.Value;

import java.util.List;

@Value
public class PersonMetaData {
    List<Person> children;
    Double height;
    Double weight;
    Person partner;
    List<String> awards;
    String education;
    List<Person> parents;
}
