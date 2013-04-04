package mock.rest.test

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringJUnit4ClassRunner)
@WebAppConfiguration
@ContextConfiguration(loader = StandardWebContextLoader)
abstract class BaseRequestTest {
    @Autowired
    WebApplicationContext wac

    MockMvc mockMvc

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }
}
