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
import wepa.domain.Kuva;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TykkaysServiceTest {

	@Autowired
	private TykkaysService tykkaysService;


	//Luodaan uusi kuva ilman sisältöä, tykätään siitä ja tarkistetaan että kuvalla on tykkäys
	//Seuraavaksi tykkäys poistetaan ja tarkistetaan ettei kuvalla ole enää tykkäyksiä
	@Test
	@Transactional
	public void testTallennaJaPoistaTykkays(){
		Kuva kuva = new Kuva();
		tykkaysService.tallennaTykkays(kuva);
		assertFalse(kuva.getTykkaykset().isEmpty());

		tykkaysService.poistaTykkays(kuva);
		assertTrue(kuva.getTykkaykset().isEmpty());

		
	}   

	//Luodaan uusi kuva ilman sisältöä, tykätään siitä ja tarkistetaan että tykkäyksien määrä on 1
	@Test
	@Transactional
	public void testTykkayksia(){
		Kuva kuva = new Kuva();
		tykkaysService.tallennaTykkays(kuva);
		int koko = tykkaysService.tykkayksia(kuva.getId());
		assertEquals(koko, 1);
	} 
}
