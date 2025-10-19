package com.sonnguyen.common.data.mongo.mapper;

import java.util.Collection;
import java.util.List;

public interface DocumentMapper<DOM, DOC> {
    DOM toDomain(DOC document);

    DOC toDocument(DOM domain);

    List<DOM> toDomain(Collection<DOC> documentList);

    List<DOC> toDocument(Collection<DOM> domainList);
}
