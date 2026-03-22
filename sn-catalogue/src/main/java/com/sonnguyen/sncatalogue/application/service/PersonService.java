package com.sonnguyen.sncatalogue.application.service;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.sncatalogue.application.dto.request.PersonCreateOrUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.PersonSearchRequest;
import com.sonnguyen.sncatalogue.domain.Person;

import java.util.UUID;

public interface PersonService {
    Person createPerson(PersonCreateOrUpdateRequest request);

    Person updatePersonById(UUID id, PersonCreateOrUpdateRequest request);

    void deletePersonById(UUID id);

    PagingResponse<Person> searchPersons(PersonSearchRequest request);

    Person getPersonById(UUID id);
}
