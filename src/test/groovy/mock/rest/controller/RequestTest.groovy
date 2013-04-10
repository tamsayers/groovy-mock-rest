package mock.rest.controller

import mock.rest.config.AppConfig

import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

//@RunWith(SpringJUnit4ClassRunner)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig)
@ActiveProfiles('test')
class RequestTest {
    @Autowired
    WebApplicationContext wac

    MockMvc mockMvc

    @Before
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }
}
