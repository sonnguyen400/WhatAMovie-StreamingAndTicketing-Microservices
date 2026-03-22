package com.sonnguyen.common.whatamovieclient.client;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.whatamovieclient.fallbackfactory.SystemClientFallbackFactory;
import com.sonnguyen.common.whatamoviemodel.application.request.TagAssignmentRequest;
import com.sonnguyen.common.whatamoviemodel.domain.ContentTag;
import com.sonnguyen.common.whatamoviemodel.domain.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

@FeignClient(
        name = "system-client",
        url = "${whatamovie.service.system.url}",
        fallbackFactory = SystemClientFallbackFactory.class)
public interface SystemClient {
    @PostMapping("/api/v1/tags/find-by-ids")
    Response<Collection<Tag>> findAllTagsByIds(@RequestBody FindByIdsRequest findByIdsRequest);

    @PostMapping("/api/v1/tags/assign-to-contents")
    Response<List<ContentTag>> assignTags(TagAssignmentRequest request);
}
