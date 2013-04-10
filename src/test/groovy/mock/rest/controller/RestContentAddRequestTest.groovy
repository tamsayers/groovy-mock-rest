package mock.rest.controller

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import mock.rest.api.data.Content
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

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = RestContentAddRequestTest.AddContentConfig, inheritLocations = true)
class RestContentAddRequestTest extends RequestTest {
    static def added

    @Configuration
    @Profile('test')
    static class AddContentConfig {
        @Bean
        RestService restService() {
            [addContent: { Content restData -> added = restData }] as RestService
        }
    }

    @Autowired
    RestService restService

    @Test
    void dataShouldBeAddedForTheGivenUrlAndType() {
        def content = 'content'
        def path =  '/content/my/url'
        def mediaType = MediaType.APPLICATION_XML

        mockMvc.perform(post(path).contentType(mediaType).content(content))

        def url = new DataUrl(path: path)

        assert added == new Content(url, mediaType.toString(), content)
    }
}
