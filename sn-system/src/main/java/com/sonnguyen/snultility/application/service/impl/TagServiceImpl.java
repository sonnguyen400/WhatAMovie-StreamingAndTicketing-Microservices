package com.sonnguyen.snultility.application.service.impl;

import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.application.dto.request.TagAssignmentRequest;
import com.sonnguyen.snultility.application.dto.request.TagCreateOrUpdateRequest;
import com.sonnguyen.snultility.application.dto.response.TagDTO;
import com.sonnguyen.snultility.application.mapper.DTOMapper;
import com.sonnguyen.snultility.application.service.TagService;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.domain.Tag;
import com.sonnguyen.snultility.domain.cmd.TagAssignmentCmd;
import com.sonnguyen.snultility.domain.cmd.TagCreateOrUpdateCmd;
import com.sonnguyen.snultility.domain.repository.ContentTagRepository;
import com.sonnguyen.snultility.domain.repository.TagRepository;
import com.sonnguyen.snultility.infrastructure.persistence.entity.TagEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagServiceImpl implements TagService {
    DTOMapper dtoMapper;
    TagRepository tagRepository;
    ContentTagRepository tagContentRepository;

    public TagDTO createTag(TagCreateOrUpdateRequest request) {
        TagCreateOrUpdateCmd cmd = this.dtoMapper.from(request);
        Tag tag = new Tag(cmd);
        tagRepository.save(tag);
        return TagDTO.from(tag);
    }

    public List<Tag> findAllTagsByKey(FindAllTagByKeyRequest request){
        List<TagEntity> tagEntities = tagRepository.findAllByKey(request.getDomainId(), request.getDomainTypes());
    }

    public List<ContentTag> assignTags(TagAssignmentRequest request) {
        TagAssignmentCmd cmd = this.dtoMapper.from(request);
        Collection<Tag> tags = this.tagRepository.findAllById(cmd.getTagIds());
        Set<UUID> tagIds = tags.stream().map(Tag::getId).collect(Collectors.toSet());
        ArrayList<ContentTag> contentTags = new ArrayList<>(this.tagContentRepository.findByDomainIdAndDomainType(
                cmd.getDomainId(),
                cmd.getDomainType()
        ));
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
}
