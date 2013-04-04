package mock.rest.api.service

import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource


interface RestService {
    void addContent(RestContent content)

    String getResource(RestResource resource)
}
