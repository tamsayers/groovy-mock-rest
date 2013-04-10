package mock.rest.api.service

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.Resources

interface RestService {
    void addContent(Content content)

    String getContent(ContentCriteria resource)

    Resources getAll()
}
