package project.pcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/proslediZahtev")
	public String proslediZahtev(@RequestBody Transakcija transakcija) {
		System.out.println("[PCC] PROSLEDIZAHTEV USAO");
		final String putanja = "https://localhost:" + transakcija.getPan().substring(0, 4) + "/placanje/proveriZahtev";
		return restTemplate.postForObject(putanja, transakcija, String.class);
	}

	@PostMapping("/proslediOdgovor")
	public String proslediOdgovor(@RequestBody RezultatTransakcije rezultatTransakcije) {
		System.out.println("[PCC] PROSLEDI ODGOVOR USAO");
		final String putanja = "https://localhost:" + rezultatTransakcije.getAcquirerSwiftCode()+ "/placanje/obradiIshodTransakcije";
		return restTemplate.postForObject(putanja, rezultatTransakcije, String.class);
	}

}
