package mock.rest.controller

import javax.servlet.http.HttpServletRequest

import mock.rest.api.data.Content
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.DataUrl
import mock.rest.api.data.Resources
import mock.rest.api.service.RestService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping('/content')
class RestContentController {
    private static def log = { println it }

    @Autowired
    RestService restService

    @ModelAttribute
    DataUrl dataUrl(HttpServletRequest request) {
        new DataUrl(request.requestURI, request.queryString)
    }

    @RequestMapping(value = '/**', method = RequestMethod.POST)
    @ResponseBody
    void add(@ModelAttribute DataUrl url, HttpEntity<String> httpEntity) {
        def contentType = httpEntity.headers.'Content-Type'[0]

        restService.addContent(new Content(url, contentType, httpEntity.body))
    }

    @RequestMapping(value = '/**', method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<String> get(@ModelAttribute DataUrl url, @RequestHeader('Accept') String accepts) {
        def content = restService.getContent(new ContentCriteria(url, accepts))
        if (content) {
            def httpHeaders = new HttpHeaders()
            httpHeaders.contentType = MediaType.valueOf(accepts)

            new ResponseEntity<String>(content, httpHeaders, HttpStatus.OK)
        } else {
            new ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = '/list', method = RequestMethod.GET)
    @ResponseBody
    Resources list() {
        restService.getAll()
    }
}
