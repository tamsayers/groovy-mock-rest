package mock.rest.api.data

import spock.lang.Specification

class ContentCriteriaSpec extends Specification {
    def "should return criteria list for held types"() {
        given:
        def dataUrl = new DataUrl([:])
        def criteria = new ContentCriteria(url: dataUrl, type: 'a, b, c')

        when:
        def result = criteria.forTypes()

        then:
        result == [
            new ContentCriteria(url: dataUrl, type: 'a'),
            new ContentCriteria(url: dataUrl, type: 'b'),
            new ContentCriteria(url: dataUrl, type: 'c')
        ]
    }
}
