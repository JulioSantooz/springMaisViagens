package br.com.maisViagens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maisViagens.model.Feedback;
import br.com.maisViagens.repository.FeedbackRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FeedbackController {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@RequestMapping(value="/olamundo/{descricao}", method = RequestMethod.GET) /*Mapeia a url*/
	@ResponseStatus(HttpStatus.OK)
	public String olaMundo(@PathVariable String descricao) {
		
		Feedback feedback = new Feedback();
		feedback.setDescricao(descricao);
		
		feedbackRepository.save(feedback);
		
		return "Olá mundo " + descricao;
	}
	
	@GetMapping(value = "listatodos")/*Metodo GET (Primerio metodo da API - Lista todos os feedbacks)*/
	@ResponseBody/*Retorna os dados ao corpo da resposta*/
	public ResponseEntity<List<Feedback>> litaFeedback() {
		
		List<Feedback> feedbacks = feedbackRepository.findAll();/*Executa a consulta no banco de dados*/
		
		return new ResponseEntity<List<Feedback>>(feedbacks, HttpStatus.OK);/*Retorna a lista em json*/
		
	}
	
	@PostMapping(value = "salvar") /*Metodo POST*/
	@ResponseBody /*Descricao da resposta*/
	public ResponseEntity<Feedback> salvar(@RequestBody Feedback feedback) { /*Recebe os dados para salvar*/
		
		Feedback feedb = feedbackRepository.save(feedback);
		
		return new ResponseEntity<Feedback>(feedb, HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "atualizar") /*Metodo PUT*/
	@ResponseBody /*Descricao da resposta*/
	public ResponseEntity<?> atualizar(@RequestBody Feedback feedback) { /*Recebe os dados para salvar*/
		
		if(feedback.getId_feedback() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
		}
		
		Feedback feedb = feedbackRepository.saveAndFlush(feedback);
		
		return new ResponseEntity<Feedback>(feedb, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "delete") /*Metodo DELETE*/
	@ResponseBody /*Descricao da resposta*/
	public ResponseEntity<String> delete(@RequestParam Long idFeedback) { /*Recebe os dados para salvar*/
		
		feedbackRepository.deleteById(idFeedback);
		
		return new ResponseEntity<String>("Feedback deletado com sucesso!", HttpStatus.OK);
		
	}
	
	@GetMapping(value = "buscarfeedid") /*Metodo GET por id*/
	@ResponseBody /*Descricao da resposta*/
	public ResponseEntity<Feedback> buscarfeedid(@RequestParam(name = "id_feedback") Long id_feedback) { /*Recebe os dados para consultar*/
		
		Feedback feedback = feedbackRepository.findById(id_feedback).get();
		
		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "buscarPorDescricao") /*Metodo GET por descricao*/
	@ResponseBody /*Descricao da resposta*/
	public ResponseEntity<List<Feedback>> buscarPorDescricao(@RequestParam(name = "descricao") String descricao) { /*Recebe os dados para consultar*/
		
		List<Feedback> feedback = feedbackRepository.buscarPorDescricao(descricao.trim().toUpperCase());
		
		return new ResponseEntity<List<Feedback>>(feedback, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "buscarPorData") /*Metodo GET por data*/
	@ResponseBody /*Descricao da resposta*/
	public ResponseEntity<List<Feedback>> buscarPorData(@RequestParam(name = "data") String data) { /*Recebe os dados para consultar*/
		
		List<Feedback> feedback = feedbackRepository.buscarPorData(data.trim().toUpperCase());
		
		return new ResponseEntity<List<Feedback>>(feedback, HttpStatus.OK);
		
	}
	
	
}
