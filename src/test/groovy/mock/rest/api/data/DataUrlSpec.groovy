package mock.rest.api.data

import spock.lang.Specification

class DataUrlSpec extends Specification {
    def "should return the full url"() {
        given:
        def dataUrl = new DataUrl('path', 'queryString')

        expect:
        dataUrl.fullUrl == 'path?queryString'
    }
}
