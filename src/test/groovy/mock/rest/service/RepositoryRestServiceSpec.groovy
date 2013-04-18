package mock.rest.service

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.Resources
import mock.rest.api.repository.ContentRepository
import spock.lang.Specification

class RepositoryRestServiceSpec extends Specification {
    ContentRepository repository = Mock()
    RepositoryRestService service = new RepositoryRestService(repository: repository)

    def "should add content to the repository"() {
        given:
        def content = new Content([:])

        when:
        service.addContent(content)

        then:
        1 * repository.add(content)
    }

    def "should retrieve content from the repository"() {
        given:
        def content = 'content'
        def resource = new ContentCriteria([:])
        repository.get(resource) >> 'content'

        when:
        def result = service.getContent(resource)

        then:
        result == content
    }

    def "should retrieve the resources from the repository"() {
        given:
        Resources resources = new Resources([:])
        repository.all() >> resources

        when:
        def result = service.getAll()

        then:
        result == resources
    }
}
