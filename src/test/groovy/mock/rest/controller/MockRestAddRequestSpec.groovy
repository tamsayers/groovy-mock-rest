package mock.rest.controller

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import mock.rest.api.data.DataUrl
import mock.rest.api.data.RestContent
import mock.rest.api.data.RestResource
import mock.rest.api.service.RestService
import mock.rest.config.AppConfig

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner)
@WebAppConfiguration
@ContextConfiguration(classes = [
    AppConfig, MockRestAddRequestSpec.MockRestAddConfig
], inheritLocations = true)
class MockRestAddRequestSpec {
    @Configuration
    static class MockRestAddConfig {
        @Bean
        RestService restService() {
            new RestService() {
                        def added
                        public void addContent(RestContent restData) {
                            added = restData
                        }

                        public String getResource(RestResource resource) {
                            ''
                        }
                    }
        }
    }

    @Autowired
    RestService restService
    @Autowired
    WebApplicationContext wac

    MockMvc mockMvc

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    public void dataShouldbeaddedforthegivenurlandtype() {
        def content = 'content'
        def path =  '/mock/my/url'
        def mediaType = MediaType.APPLICATION_XML

        mockMvc.perform(post(path).contentType(mediaType).content(content))

        def url = new DataUrl(path: path)

        assert restService.added == new RestContent(url, mediaType.toString(), content)
    }
}
