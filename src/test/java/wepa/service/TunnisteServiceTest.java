package wepa.service;

import javax.transaction.Transactional;
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
import wepa.domain.Tunniste;
import wepa.repository.KommenttiRepository;
import wepa.repository.KuvaRepository;
import wepa.repository.TunnisteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TunnisteServiceTest {
    
    @Autowired
    private TunnisteService tunnisteService;
    
    @Autowired
    private KuvaRepository kuvaRepository;
    
    @Autowired
    private KuvaService kuvaService;
    
    @Autowired
    private TunnisteRepository tunnisteRepository;
    
    
    //Luodaan uusi tagi ja testataan että se löytyy tunnisteista.
    //Seuraavaksi poistetaan kyseinen tagi ja varmistetaan ettei se enää ole olemasa
    @Test
    public void testAddAndDeleteTag() {
            Tunniste tunniste = new Tunniste();
            tunniste.setNimi("Testi");
            tunnisteService.addTag(tunniste);
            
            Tunniste haettuTunniste = tunnisteService.findByNimi("Testi");
            assertEquals(tunniste, haettuTunniste);
            
            tunnisteService.delete(tunniste.getId());
            
            haettuTunniste = tunnisteService.findByNimi("Testi");
            assertEquals(null, haettuTunniste);

    }


    //Luodaan uusi tagi, liitetään se kuvaan ja testataan että se löytyy kuvasta
    //Seuraavaksi se poistetaan kuvasta ja testataan ettei se enää ole liitettynä
    @Test
    @Transactional
    public void testLisaaJaPoistaTunnisteKuvasta(){
        Tunniste tunniste = new Tunniste();
        tunniste.setNimi("KuvaTesti");
        Kuva kuva = new Kuva();
        kuvaRepository.save(kuva);

        tunnisteService.addTag(tunniste);

        tunnisteService.lisaatunnisteKuvaan(tunniste.getId(), kuva.getId());

        assertTrue(kuvaService.getKuvanTunnisteet(kuva.getId()).contains(tunniste));

        tunnisteService.poistaTunnisteKuvasta(tunniste.getId(), kuva.getId());

        assertFalse(kuvaService.getKuvanTunnisteet(kuva.getId()).contains(tunniste));


    }
    
}