package mock.rest.api.repository

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.Resources

interface ContentRepository {
    void add(Content content)
    String get(ContentCriteria resource)
    Resources all()
}
