package mock.rest.api.service

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.Resources
import mock.rest.api.data.TypeContent

interface RestService {
    void addContent(Content content)

    TypeContent getContent(ContentCriteria resource)

    Resources getAll()
}
