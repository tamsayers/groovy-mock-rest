package mock.rest.api.repository

import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource

interface ContentRepository {
    void add(RestContent content)
    String get(RestResource resource)
}
