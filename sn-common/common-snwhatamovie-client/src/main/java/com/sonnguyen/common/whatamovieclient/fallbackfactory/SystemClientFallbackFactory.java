package com.sonnguyen.common.whatamovieclient.fallbackfactory;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.whatamovieclient.client.SystemClient;
import com.sonnguyen.common.whatamoviemodel.application.request.TagAssignmentRequest;
import com.sonnguyen.common.whatamoviemodel.domain.ContentTag;
import com.sonnguyen.common.whatamoviemodel.domain.Tag;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SystemClientFallbackFactory implements FallbackFactory<SystemClient> {

    @Override
    public SystemClient create(Throwable cause) {
        return new SystemClientFallbackHandler(cause);
    }

    private record SystemClientFallbackHandler(Throwable cause) implements SystemClient {

        @Override
        public Response<Collection<Tag>> findAllTagsByIds(FindByIdsRequest findByIdsRequest) {
            return Response.fail();
        }

        @Override
        public Response<List<ContentTag>> assignTags(TagAssignmentRequest request) {
            return Response.fail();
        }
    }
}
