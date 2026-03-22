package com.sonnguyen.snultility.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.common.data.persistence.entity.MessageLocaleEntity;
import com.sonnguyen.common.data.persistence.entityrepository.MessageLocaleEntityRepository;
import com.sonnguyen.common.data.persistence.mapper.MessageLocaleEntityMapper;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.domain.Tag;
import com.sonnguyen.snultility.domain.query.TagSearchQuery;
import com.sonnguyen.snultility.domain.repository.TagRepository;
import com.sonnguyen.snultility.infrastructure.mapper.ContentTagEntityMapper;
import com.sonnguyen.snultility.infrastructure.mapper.TagEntityMapper;
import com.sonnguyen.snultility.infrastructure.persistence.entity.ContentTagEntity;
import com.sonnguyen.snultility.infrastructure.persistence.entity.TagEntity;
import com.sonnguyen.snultility.infrastructure.persistence.repository.ContentTagEntityRepository;
import com.sonnguyen.snultility.infrastructure.persistence.repository.TagEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class TagRepositoryRepositoryImpl extends AbstractDomainRepository<Tag, TagEntity, UUID>
        implements TagRepository {
    private final TagEntityRepository tagEntityRepository;
    private final ContentTagEntityRepository contentTagEntityRepository;
    private final ContentTagEntityMapper contentTagEntityMapper;
    private final MessageLocaleEntityRepository localeEntityRepository;
    private final MessageLocaleEntityMapper messageLocaleEntityMapper;

    public TagRepositoryRepositoryImpl(TagEntityRepository tagEntityRepository,
                                       TagEntityMapper tagEntityMapper,
                                       ContentTagEntityRepository contentTagEntityRepository,
                                       ContentTagEntityMapper contentTagEntityMapper,
                                       MessageLocaleEntityRepository localeEntityRepository,
                                       MessageLocaleEntityMapper messageLocaleEntityMapper) {
        super(tagEntityRepository, tagEntityMapper);
        this.tagEntityRepository = tagEntityRepository;
        this.contentTagEntityRepository = contentTagEntityRepository;
        this.contentTagEntityMapper = contentTagEntityMapper;
        this.localeEntityRepository = localeEntityRepository;
        this.messageLocaleEntityMapper = messageLocaleEntityMapper;
    }

    @Override
    public Collection<Tag> saveAll(Collection<Tag> domains) {
        List<TagEntity> tagEntityList = this.mapper.toEntity(domains);
        List<MessageLocale> messageLocales = domains.stream().map(Tag::getMessageLocales)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .toList();
        List<MessageLocaleEntity> localeEntities = this.messageLocaleEntityMapper.toEntity(messageLocales);
        List<ContentTag> contentTags = domains.stream().map(Tag::getContents)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .toList();
        List<ContentTagEntity> contentTagEntities = this.contentTagEntityMapper.toEntity(contentTags);
        this.contentTagEntityRepository.saveAll(contentTagEntities);
        this.localeEntityRepository.saveAll(localeEntities);
        this.tagEntityRepository.saveAll(tagEntityList);
        return domains;
    }

    @Override
    public List<Tag> findByKey(FindAllTagByKeyRequest request) {
        return this.mapper.toDomain(this.tagEntityRepository.findByKey(request));
    }

    @Override
    public List<Tag> search(TagSearchQuery tagSearchQuery) {
        return this.mapper.toDomain(this.tagEntityRepository.search(tagSearchQuery));
    }

    @Override
    public Collection<Tag> enrichAll(Collection<Tag> domains) {
        List<UUID> tagIds = domains.stream().map(Tag::getId).toList();
        Map<UUID, List<ContentTag>> contentTagMap = new HashMap<>();
        Map<UUID, List<MessageLocale>> localeMap = Map.of();
        CompletableFuture<Void> mapContentTagFuture = CompletableFuture.supplyAsync(() -> {
            List<ContentTagEntity> contentTagEntities = this.contentTagEntityRepository
                    .findAllByTagIdIn(tagIds);
            return this.contentTagEntityMapper.toDomain(contentTagEntities).stream()
                    .collect(Collectors.groupingBy(ContentTag::getTagId));
        }).thenAccept(contentTagMap::putAll);
        CompletableFuture<Void> mapLocaleFuture = CompletableFuture.supplyAsync(() -> {
            List<MessageLocaleEntity> localeEntities = this.localeEntityRepository
                    .findAllByDomainIdAndDomainType(DomainType.TAG, tagIds);
            return this.messageLocaleEntityMapper
                    .toDomain(localeEntities)
                    .stream()
                    .collect(Collectors.groupingBy(MessageLocale::getDomainId));
        }).thenAccept(localeMap::putAll);
        CompletableFuture.allOf(mapContentTagFuture, mapLocaleFuture).join();

        for (Tag domain : domains) {
            List<ContentTag> relatedContentTags = contentTagMap.getOrDefault(domain.getId(), List.of());
            domain.enrichContentTags(relatedContentTags);
            List<MessageLocale> relatedLocales = localeMap.getOrDefault(domain.getId(), List.of());
            domain.enrichMessageLocale(relatedLocales);
        }
        return domains;
    }
}
