package mock.rest.controller

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import mock.rest.api.data.ContentCriteria
import mock.rest.api.data.DataUrl
import mock.rest.api.service.RestService

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.result.MockMvcResultMatchers as Response

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = RestContentGetRequestTest.GetContentConfig, inheritLocations = true)
public class RestContentGetRequestTest extends RequestTest {
    static def expectedCriteria
    static def expectedContent

    @Configuration
    @Profile('test')
    static class GetContentConfig {
        @Bean
        RestService restService() {
            [getContent: { ContentCriteria criteria -> if (criteria == expectedCriteria) expectedContent }] as RestService
        }
    }

    @Autowired
    RestService restService

    @Test
    void dataShouldBeAddedForTheGivenUrlAndType() {
        def content = 'content'
        def path =  '/content/my/url'
        def mediaType = MediaType.APPLICATION_XML

        expectedCriteria = new ContentCriteria(new DataUrl(path: path), mediaType.toString())
        expectedContent = 'content'

        mockMvc.perform(get(path).accept(mediaType))
                .andExpect(Response.content().string(expectedContent))
                .andExpect(Response.content().contentType(mediaType))
    }

    @Test
    void dataShouldBeAddedForTheGivenUrlQueryStringAndType() {
        def content = 'content'
        def path =  '/content/my/url'
        def mediaType = MediaType.APPLICATION_XML

        expectedCriteria = new ContentCriteria(new DataUrl(path: path), mediaType.toString())
        expectedContent = 'content'

        mockMvc.perform(get(path).accept(mediaType))
                .andExpect(Response.content().string(expectedContent))
                .andExpect(Response.content().contentType(mediaType))
    }
}
