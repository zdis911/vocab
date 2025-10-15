package com.example.app.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Vocab;
import com.example.app.domain.VocabType;
import com.example.app.mapper.VocabMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vocabs")
public class VocabController {
	
	private final VocabMapper mapper;
	
	// get word list
	@GetMapping
	public ResponseEntity<List<Vocab>> getVocabs(){
		List<Vocab> vocabs = mapper.selectAll();
		return new ResponseEntity<>(vocabs,HttpStatus.OK);
	}
	
	// get word by id
	@GetMapping("/{id}")
	public ResponseEntity<Vocab> getVocabById(@PathVariable int id){
		Vocab vocab = mapper.selectById(id);
		HttpStatus status = vocab==null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<>(vocab,status);
	}
	
	// get word by type
	@GetMapping("/type/{typeId}")
	public ResponseEntity<List<Vocab>> getVocabsByType(
			@PathVariable int typeId
			){
		List<Vocab> vocabs = mapper.selectByType(typeId);
		return new ResponseEntity<>(vocabs,HttpStatus.OK);
	}
	
	// get typeList
	@GetMapping("/types")
	public ResponseEntity<List<VocabType>> getVocabTypes(){
		List<VocabType> vocabTypes = mapper.selectAllTypes();
		return new ResponseEntity<>(vocabTypes,HttpStatus.OK);
	}
	
	// add word
	@PostMapping
	public ResponseEntity<String> addVocab(
			@RequestBody @Valid Vocab vocab,Errors errors
			){
		if(errors.hasErrors()) {
			return new ResponseEntity<>("failed to add vocab",HttpStatus.BAD_REQUEST);
		}
		
		mapper.insert(vocab);
		return new ResponseEntity<>("succeeded to add vocab",HttpStatus.OK);
		
	}
	
	@PutMapping
	public ResponseEntity<String> updateVocab(
		@RequestBody @Valid Vocab vocab,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<>("failed to update vocab",HttpStatus.BAD_REQUEST);
		}
		mapper.update(vocab);
		return new ResponseEntity<>("succeeded to update vocab",HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVocab(
			@PathVariable int id
			){
		mapper.delete(id);
		return new ResponseEntity<>("succeeded to delete vocab",HttpStatus.OK);
	}
	
}
