package com.sonnguyen.sncatalogue.presentation.rest;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.sncatalogue.application.dto.request.PersonCreateOrUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.PersonSearchRequest;
import com.sonnguyen.sncatalogue.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api")
public interface PersonController {

    @GetMapping("/v1/persons/{id}")
    Response<Person> getPersonById(@PathVariable UUID id);

    @PostMapping("/v1/persons")
    Response<Person> createPerson(@RequestBody PersonCreateOrUpdateRequest request);

    @PostMapping("/v1/persons/{id}")
    Response<Person> updatePersonById(@PathVariable UUID id, @RequestBody PersonCreateOrUpdateRequest request);

    @PostMapping("/v1/persons/{id}")
    Response<Void> deletePersonById(@PathVariable UUID id);

    @PostMapping("/v1/persons/search")
    PagingResponse<Person> searchPersons(@RequestBody PersonSearchRequest request);
}
