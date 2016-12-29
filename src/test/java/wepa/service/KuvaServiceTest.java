package wepa.service;

import java.util.ArrayList;
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
import wepa.domain.Kuva;

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
    public void voiLisataJaLoytyy() throws Exception {

//        int sizeBefore = service.findAll().size();
        ArrayList<Long> lista = new ArrayList<>();
        service.findAll().stream().forEach((kuva) -> {
            lista.add(kuva.getId());
        });
//        MockMultipartFile multipartFile = new MockMultipartFile("file", "faketest.gif", "image/gif", "faketestgif".getBytes());
        MockMultipartFile multipartFilePng = new MockMultipartFile("file", "faketest.png", "image/png", "faketestpng".getBytes());

//        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pics").file(multipartFile)).andExpect(MockMvcResultMatchers.redirectedUrl("/pics"));
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pics").file(multipartFilePng)).andExpect(MockMvcResultMatchers.redirectedUrl("/pics"));

//        int sizeAfter = service.findAll().size();
        long id = -1l;
        for(Kuva kuva : service.findAll()) {
            if(!lista.contains(kuva.getId())) {
               id = kuva.getId();
               break;
            }
        }        
        assertFalse(id == -1l);
//        assertTrue(sizeAfter == sizeBefore + 1);
        assertNotNull(service.findOne(id));
        MvcResult tulos = mockMvc.perform(MockMvcRequestBuilders.get("/pics/+" + id + "/content")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertEquals("faketestpng", new String(tulos.getResponse().getContentAsByteArray()));
        //kontrolleri metodi palauttaa aina gifinä vaikkei olisi oikeasti
        assertEquals("image/gif", tulos.getResponse().getContentType());
    }
}
