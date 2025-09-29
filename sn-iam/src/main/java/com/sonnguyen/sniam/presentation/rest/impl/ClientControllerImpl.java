package com.sonnguyen.sniam.presentation.rest.impl;

import com.sonnguyen.sniam.application.service.ClientService;
import com.sonnguyen.sniam.presentation.rest.ClientController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class ClientControllerImpl implements ClientController {
    ClientService clientService;

    @Override
    public Map<String, Object> getJwks() {
        return this.clientService.getKey().toJSONObject();
    }
}
