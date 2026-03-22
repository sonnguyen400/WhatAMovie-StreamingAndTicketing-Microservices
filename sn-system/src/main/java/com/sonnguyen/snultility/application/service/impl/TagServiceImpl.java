package com.sonnguyen.snultility.application.service.impl;

import com.sonnguyen.common.data.persistence.domain.repository.MessageLocaleRepository;
import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.application.dto.request.TagAssignmentRequest;
import com.sonnguyen.snultility.application.dto.request.TagCreateOrUpdateRequest;
import com.sonnguyen.snultility.application.dto.request.TagSearchRequest;
import com.sonnguyen.snultility.application.dto.response.TagDTO;
import com.sonnguyen.snultility.application.mapper.DTOMapper;
import com.sonnguyen.snultility.application.service.TagService;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.domain.Tag;
import com.sonnguyen.snultility.domain.cmd.TagAssignmentCmd;
import com.sonnguyen.snultility.domain.cmd.TagCreateOrUpdateCmd;
import com.sonnguyen.snultility.domain.query.TagSearchQuery;
import com.sonnguyen.snultility.domain.repository.ContentTagRepository;
import com.sonnguyen.snultility.domain.repository.TagRepository;
import com.sonnguyen.snultility.infrastructure.persistence.repository.TagEntityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagServiceImpl implements TagService {
    DTOMapper dtoMapper;
    TagRepository tagRepository;
    TagEntityRepository tagEntityRepository;
    ContentTagRepository tagContentRepository;
    MessageLocaleRepository messageLocaleRepository;

    @Override
    public Tag getTagById(UUID id) {
        return this.tagRepository.getById(id);
    }

    @Override
    public TagDTO createTag(TagCreateOrUpdateRequest request) {
        TagCreateOrUpdateCmd cmd = this.dtoMapper.from(request);
        Tag tag = new Tag(cmd);
        tagRepository.save(tag);
        return TagDTO.from(tag);
    }

    @Override
    public List<Tag> findAllTagsByKey(FindAllTagByKeyRequest request) {
        return this.tagRepository.findByKey(request);
    }

    @Override
    public List<ContentTag> assignTags(TagAssignmentRequest request) {
        TagAssignmentCmd cmd = this.dtoMapper.from(request);
        Collection<Tag> tags = this.tagRepository.findAllById(cmd.getTagIds());
        Set<UUID> tagIds = tags.stream().map(Tag::getId).collect(Collectors.toSet());
        ArrayList<ContentTag> contentTags = new ArrayList<>(this.tagContentRepository.findByDomainIdAndDomainType(cmd.getDomainId(), cmd.getDomainType()));
        for (ContentTag contentTag : contentTags) {
            contentTag.delete();
            if (tagIds.contains(contentTag.getTagId())) {
                tagIds.remove(contentTag.getTagId());
                contentTag.unDelete();
            }
        }
        for (UUID tagId : tagIds) {
            ContentTag newContentTag = new ContentTag(tagId, cmd.getDomainId(), cmd.getDomainType());
            contentTags.add(newContentTag);
        }
        this.tagContentRepository.saveAll(contentTags);
        return contentTags;
    }

    @Override
    public Tag updateById(UUID id, TagCreateOrUpdateRequest request) {
        Tag tag = this.tagRepository.getById(id);
        TagCreateOrUpdateCmd cmd = this.dtoMapper.from(request);
        tag.update(cmd);
        this.tagRepository.save(tag);
        return tag;
    }

    @Override
    public Collection<Tag> findAllTagsByIds(FindByIdsRequest request) {
        Collection<Tag> tags = this.tagRepository.findAllById(request.getIds());
        if (Objects.nonNull(request.getLocaleCode())) {
            this.messageLocaleRepository.enrichLocaleMessages(request.getLocaleCode(), DomainType.TAG, tags);
        }
        return tags;
    }

    @Override
    public PagingResponse<Tag> search(TagSearchRequest request) {
        TagSearchQuery tagSearchQuery = this.dtoMapper.from(request);
        Long count = this.tagEntityRepository.count(tagSearchQuery);
        if (count == 0) {
            return PagingResponse.of(new ArrayList<>(), 0L, request.getPageSize(), request.getPageIndex());
        }
        List<Tag> tagList = this.tagRepository.search(tagSearchQuery);
        return PagingResponse.of(tagList, count, request.getPageSize(), request.getPageIndex());
    }

    @Override
    public void deleteTagById(UUID id) {
        Tag tag = this.tagRepository.getById(id);
        tag.delete();
        this.tagRepository.save(tag);
    }
}
