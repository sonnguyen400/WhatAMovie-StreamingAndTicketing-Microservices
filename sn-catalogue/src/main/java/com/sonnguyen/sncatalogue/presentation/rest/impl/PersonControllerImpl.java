package com.sonnguyen.sncatalogue.presentation.rest.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.sncatalogue.application.dto.request.PersonCreateOrUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.PersonSearchRequest;
import com.sonnguyen.sncatalogue.application.service.PersonService;
import com.sonnguyen.sncatalogue.domain.Person;
import com.sonnguyen.sncatalogue.presentation.rest.PersonController;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PersonControllerImpl implements PersonController {
    PersonService personService;

    @Override
    public Response<Person> getPersonById(UUID id) {
        return Response.of(personService.getPersonById(id));
    }

    @Override
    public Response<Person> createPerson(PersonCreateOrUpdateRequest request) {
        return Response.of(personService.createPerson(request));
    }

    @Override
    public Response<Person> updatePersonById(@PathVariable UUID id, @RequestBody PersonCreateOrUpdateRequest request) {
        return Response.of(personService.updatePersonById(id, request));
    }

    @Override
    public Response<Void> deletePersonById(@PathVariable UUID id) {
        personService.deletePersonById(id);
        return Response.ok();
    }

    @Override
    public PagingResponse<Person> searchPersons(@RequestBody PersonSearchRequest request) {
        return personService.searchPersons(request);
    }
}
