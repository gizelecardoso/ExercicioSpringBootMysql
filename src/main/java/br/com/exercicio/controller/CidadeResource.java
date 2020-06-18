package br.com.exercicio.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.exercicio.model.beans.Cidade;
import br.com.exercicio.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	
	@GetMapping("/list")
	public List<Cidade> allCities(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/list/{letter}")
	public List<Cidade> allCitiesByInitialLetter(@PathVariable("letter") String letter){
		return cidadeRepository.findByInicialLetter(letter);
	}
	
	
	@GetMapping("/list/{latitude}/{longitude}")
	public List<Cidade> allCitiesByCoordinates(@PathVariable("latitude") Long latitude, @PathVariable("longitude") Long longitude){
		return cidadeRepository.findByCoord(latitude, longitude);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Cidade> saveCity(@RequestBody Cidade cidade, HttpServletResponse response){
		Cidade city = cidadeRepository.save(cidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(city.getId()).toUri();
		
		return ResponseEntity.created(uri).body(city);
	}

}
