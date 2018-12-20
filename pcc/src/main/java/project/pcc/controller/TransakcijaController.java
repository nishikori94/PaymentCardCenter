package project.pcc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import project.pcc.model.RezultatTransakcije;
import project.pcc.model.Transakcija;

@RestController
@RequestMapping("/transakcija")
public class TransakcijaController {

	@PostMapping("/proslediZahtev")
	public void proslediZahtev(@RequestBody Transakcija transakcija) {
		System.out.println("[PCC] PROSLEDIZAHTEV USAO");
		final String putanja = "http://localhost:" + transakcija.getPan().substring(0, 4) + "/placanje/proveriZahtev";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(putanja, transakcija, Void.class);
	}

	@PostMapping("/proslediOdgovor")
	public void proslediOdgovor(@RequestBody RezultatTransakcije rezultatTransakcije) {
		System.out.println("[PCC] PROSLEDI ODGOVOR USAO");
		final String putanja = "http://localhost:" + rezultatTransakcije.getAcquirerSwiftCode()+ "/placanje/obradiIshodTransakcije";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(putanja, rezultatTransakcije, Void.class);
	}

}
