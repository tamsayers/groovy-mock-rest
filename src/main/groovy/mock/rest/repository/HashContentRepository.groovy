package mock.rest.repository

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.Resource
import mock.rest.api.data.Resources
import mock.rest.api.repository.ContentRepository

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class HashContentRepository implements ContentRepository {
    @Value("#{new java.util.HashMap()}")
    Map dataStore

    def log = { text -> println text }

    void add(Content restContent) {
        if (!dataStore[restContent.url]) {
            dataStore[restContent.url] = [:]
        }

        dataStore[restContent.url]."$restContent.type" = restContent.content

        dataStore.keySet().each { log it }
    }

    String get(ContentCriteria resource) {
        dataStore[resource.url]?."$resource.type"
    }

    Resources all() {
        def resourceList = dataStore.collect { url, types ->
            new Resource(url: url.fullUrl, types: types.keySet().asList())
        }

        new Resources(list: resourceList)
    }
}

