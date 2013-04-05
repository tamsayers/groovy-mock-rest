package mock.rest.controller

import javax.servlet.http.HttpServletRequest

import mock.rest.api.data.DataUrl
import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource
import mock.rest.api.service.RestService

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import spock.lang.Specification

class MockRestControllerSpec extends Specification {
    def mockDataKey = new DataUrl([:])
    def content = 'data'
    def contentType = MediaType.TEXT_XML.toString()
    def path = 'url'
    def queryString = 'a=b&c=d'

    RestService restService = Mock()
    MockRestController controller = new MockRestController(restService: restService)

    def "data should be added for the given url and content type"() {
        given:
        HttpEntity httpEntity = Mock()
        HttpHeaders headers = Mock()
        httpEntity.body >> content
        httpEntity.headers >> headers
        headers.get('Content-Type') >> [contentType]

        when:
        controller.add(mockDataKey, httpEntity)

        then:
        1 * restService.addContent(new RestContent(url: mockDataKey, type: contentType, content: content))
    }

    def "the url should be populated from the request data"() {
        given:
        HttpServletRequest request = Mock()
        request.queryString >> queryString
        request.requestURI >> path

        when:
        def result = controller.dataUrl(request)

        then:
        result == new DataUrl(path, queryString)
    }

    def "content should be got for the given url and content type"() {
        restService.getResource(new RestResource(url: mockDataKey, type: contentType)) >> content
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.TEXT_XML)

        when:
        def result = controller.get(mockDataKey, contentType)

        then:
        result == new ResponseEntity(content, headers, HttpStatus.OK)
    }

    def "a 404 should be returned on no content"() {
        restService.getResource(new RestResource(url: mockDataKey, type: contentType)) >> null

        when:
        def result = controller.get(mockDataKey, contentType)

        then:
        result == new ResponseEntity(HttpStatus.NOT_FOUND)
    }
}
