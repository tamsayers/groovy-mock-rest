package mock.rest.service

import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource
import mock.rest.api.repository.ContentRepository
import mock.rest.api.service.RestService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepositoryRestService implements RestService {
    @Autowired
    ContentRepository repository

    void addContent(RestContent content) {
        repository.add(content)
    }

    String getResource(RestResource resource) {
        repository.get(resource)
    }
}
