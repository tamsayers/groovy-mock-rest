package mock.rest.service

import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource
import mock.rest.api.repository.ContentRepository;
import spock.lang.Specification

class RepositoryRestServiceSpec extends Specification {
    ContentRepository repository = Mock()
    RepositoryRestService service = new RepositoryRestService(repository: repository)

    def "should add content to the repository"() {
        given:
        def content = new RestContent([:])

        when:
        service.addContent(content)

        then:
        1 * repository.add(content)
    }

    def "should retrieve content from the repository"() {
        given:
        def content = 'content'
        def resource = new RestResource([:])
        repository.get(resource) >> 'content'

        when:
        def result = service.getResource(resource)

        then:
        result == content
    }
}
