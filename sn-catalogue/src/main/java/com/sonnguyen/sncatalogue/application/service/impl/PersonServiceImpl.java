package com.sonnguyen.sncatalogue.application.service.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.sncatalogue.application.dto.mapper.CatalogueCommandMapper;
import com.sonnguyen.sncatalogue.application.dto.request.PersonCreateOrUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.PersonSearchRequest;
import com.sonnguyen.sncatalogue.application.service.PersonService;
import com.sonnguyen.sncatalogue.domain.Person;
import com.sonnguyen.sncatalogue.domain.command.PersonCreateUpdateCmd;
import com.sonnguyen.sncatalogue.domain.query.PersonSearchQuery;
import com.sonnguyen.sncatalogue.domain.repository.PersonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonServiceImpl implements PersonService {
    PersonRepository personRepository;
    CatalogueCommandMapper catalogueCommandMapper;

    @Override
    public Person createPerson(PersonCreateOrUpdateRequest request) {
        PersonCreateUpdateCmd cmd = this.catalogueCommandMapper.from(request);
        Person person = new Person(cmd);
        this.personRepository.save(person);
        return person;
    }

    @Override
    public Person updatePersonById(UUID id, PersonCreateOrUpdateRequest request) {
        PersonCreateUpdateCmd cmd = this.catalogueCommandMapper.from(request);
        Person person = this.personRepository.getById(id);
        person.update(cmd);
        this.personRepository.save(person);
        return person;
    }

    @Override
    public void deletePersonById(UUID id) {
        Person person = this.personRepository.getById(id);
        person.delete();
        this.personRepository.save(person);
    }

    @Override
    public PagingResponse<Person> searchPersons(PersonSearchRequest request) {
        PersonSearchQuery query = this.catalogueCommandMapper.from(request);
        Long count = this.personRepository.count(query);
        if (count == 0) {
            return PagingResponse.of(List.of(), 0L, query.getPageSize(), query.getPageIndex());
        }
        List<Person> personList = this.personRepository.search(query);
        return PagingResponse.of(personList, count, query.getPageSize(), query.getPageIndex());
    }

    @Override
    public Person getPersonById(UUID id) {
        return this.personRepository.getById(id);
    }
}
