package mock.rest.repository

import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource
import mock.rest.api.repository.ContentRepository

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class HashContentRepository implements ContentRepository {
    @Value("#{new java.util.HashMap()}")
    Map dataStore

    def log = { text -> println text }

    void add(RestContent restContent) {
        if (!dataStore[restContent.url]) {
            dataStore[restContent.url] = [:]
        }

        dataStore[restContent.url]."$restContent.type" = restContent.content
    }

    String get(RestResource resource) {
        dataStore[resource.url]?."$resource.type"
    }
}
