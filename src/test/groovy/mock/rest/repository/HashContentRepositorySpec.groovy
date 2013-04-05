package mock.rest.repository

import mock.rest.api.data.DataUrl
import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource
import spock.lang.Specification

class HashContentRepositorySpec extends Specification {
    def url = new DataUrl([:])
    def type = 'type'
    def content = 'content'
    def dataStore = [:]

    HashContentRepository repository = new HashContentRepository(dataStore: dataStore)

    def "should add content"() {
        given:
        def restContent = new RestContent(url, type, content)

        when:
        repository.add(restContent)

        then:
        dataStore[url][type] == content
    }

    def "should get the content"() {
        given:
        dataStore[url] = [type: content]

        when:
        def result = repository.get(new RestResource(url, type))

        then:
        result == content
    }

    def "should get null if no content"() {
        when:
        def result = repository.get(new RestResource(url, type))

        then:
        result == null
    }
}
