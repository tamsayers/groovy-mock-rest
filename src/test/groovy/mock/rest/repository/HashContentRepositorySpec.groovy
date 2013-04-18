package mock.rest.repository

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.DataUrl
import spock.lang.Specification

class HashContentRepositorySpec extends Specification {
    DataUrl dataUrl = new DataUrl('url', 'queryString')
    def type = 'type'
    def content = 'content'
    def dataStore = [:]

    HashContentRepository repository = new HashContentRepository(dataStore: dataStore)

    def "should add content"() {
        given:
        def restContent = new Content(dataUrl, type, content)

        when:
        repository.add(restContent)

        then:
        dataStore[dataUrl][type] == content
    }

    def "should get the content"() {
        given:
        dataStore[dataUrl] = [type: content]

        when:
        def result = repository.get(new ContentCriteria(dataUrl, type))

        then:
        result == content
    }

    def "should get null if no content"() {
        when:
        def result = repository.get(new ContentCriteria(dataUrl, type))

        then:
        result == null
    }

    def "should get all the resources"() {
        given:
        dataStore[dataUrl] = [type: content, 'otherType': 'otherContent']
        dataStore[new DataUrl([:])] = [type: content]

        when:
        def resources = repository.all().list

        then:
        resources.size() == 2
        resources[0].url == dataUrl.fullUrl
        resources[0].types[0] == type
        resources[0].types[1] == 'otherType'
    }
}
