package wepa.service;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KuvaServiceTest {

    private MockMvc mockMvc;
    @Autowired
    KuvaService service;
    @Autowired
    WebApplicationContext webAppContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void voiLisataJaLoytyy() throws Exception{
        MockMultipartFile multipartFile = new MockMultipartFile("file", "faketest.gif", "image/gif", "faketestgif".getBytes());
        MockMultipartFile multipartFilePng = new MockMultipartFile("file", "faketest.png", "image/png", "faketestpng".getBytes());
        
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pics").file(multipartFile)).andExpect(MockMvcResultMatchers.redirectedUrl("/pics"));
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pics").file(multipartFilePng)).andExpect(MockMvcResultMatchers.redirectedUrl("/pics"));
        
        int idOfLast = service.findAll().size();
        
        MvcResult tulos = mockMvc.perform(MockMvcRequestBuilders.get("/pics/+"+idOfLast+"/content")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertEquals("faketestpng", new String(tulos.getResponse().getContentAsByteArray()));
        assertEquals("image/gif", tulos.getResponse().getContentType());
    }
}
