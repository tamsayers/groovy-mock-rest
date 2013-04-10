package mock.rest.service

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.Resources
import mock.rest.api.repository.ContentRepository
import mock.rest.api.service.RestService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepositoryRestService implements RestService {
    @Autowired
    ContentRepository repository

    void addContent(Content content) {
        repository.add(content)
    }

    String getContent(ContentCriteria resource) {
        repository.get(resource)
    }

    Resources getAll() {
        repository.all()
    }
}
