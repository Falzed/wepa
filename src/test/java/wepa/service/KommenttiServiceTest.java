package wepa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wepa.domain.Kommentti;
import wepa.domain.Kuva;
import wepa.repository.KommenttiRepository;
import wepa.repository.KuvaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KommenttiServiceTest {
    
    @Autowired
    private KommenttiRepository kommenttiRepository;
    
    @Autowired
    private KuvaRepository kuvaRepository;
    
    @Autowired
    private KommenttiService kommenttiService;
    
    
    @Test
    public void testPostingKommentti() {
//        Kuva kuva = new Kuva();
//        kuva = kuvaRepository.save(kuva);
//        Kommentti kommentti = kommenttiService.postKommentti(kuva.getId(), "Kommentin sisalto");
//        
//        Kuva retrievedKuva = kuvaRepository.findOne(kuva.getId());
//        Kommentti retrievedKommentti = kommenttiRepository.findOne(kommentti.getId());
//        
//        assertTrue(retrievedKuva.getKommentit().contains(retrievedKommentti));
//        assertEquals(retrievedKommentti.getKuva(), retrievedKuva);
    }
    
}
