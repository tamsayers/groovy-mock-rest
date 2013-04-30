package mock.rest.service

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.Resources
import mock.rest.api.data.TypeContent
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
        def resource = new ContentCriteria(type: 'a')
        repository.get(resource) >> 'content'

        when:
        def result = service.getContent(resource)

        then:
        result == new TypeContent('a', content)
    }


    def "should retrieve content for one of the types from the repository"() {
        given:
        def expectedTypeContent = new TypeContent('c', 'content')
        def resource = new ContentCriteria(type: 'a,b,c,d,e,f')
        repository.get(resource.forTypes()[2]) >> expectedTypeContent.content

        when:
        def result = service.getContent(resource)

        then:
        result == expectedTypeContent
    }

    def "should retrieve content for one of the types from the repository2"() {
        given:
        def content = 'content'
        def resource = new ContentCriteria(type: 'a,b,c,d,e,f')
        repository.get(resource.forTypes()[2]) >> 'content'

        when:
        service.getContent(resource)

        then:
        6 * repository.get(_)
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
