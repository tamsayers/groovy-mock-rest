package mock.rest.controller

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import mock.rest.api.data.Resource
import mock.rest.api.data.Resources
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
@ContextConfiguration(classes = RestContentListRequestTest.ListContentConfig, inheritLocations = true)
class RestContentListRequestTest extends RequestTest {
    @Configuration
    @Profile('test')
    static class ListContentConfig {
        @Bean
        RestService restService() {
            def resources = new Resources([
                new Resource('url1', ['application/xml']),
                new Resource('url2', ['text/xml', 'text/html'])
            ])

            [getAll: { resources }] as RestService
        }
    }

    @Autowired
    RestService restService

    @Test
    void dataShouldBeAddedForTheGivenUrlAndType() {
        def content = 'content'
        def path =  '/content/list'

        mockMvc.perform(get(path).accept(MediaType.APPLICATION_JSON))
                .andExpect(Response.jsonPath('$.resources').value(hasSize(2)))
                .andExpect(Response.jsonPath('$.resources[0].url').value('url1'))
                .andExpect(Response.jsonPath('$.resources[1].types[1]').value('text/html'))
    }
}
