package com.sonnguyen.sncatalogue.application.service.impl;

import com.sonnguyen.sncatalogue.application.service.PersonQueryService;
import lombok.AccessLevel;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonQueryServiceImpl implements PersonQueryService {
}
