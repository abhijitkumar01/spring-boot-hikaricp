package com.example.springboot.controller;

import com.example.springboot.ddl.*;
import com.example.springboot.jdbc.*;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;

@RestController
public class TrailerController {

  private static final Logger logger = LogManager.getLogger(TrailerController.class);

  @Autowired
  private TrailerRepository repository;

  @GetMapping("/trailers")
  public @ResponseBody Iterable<Trailer> getAllTrailers() {
    logger.info("inside getAllTrailers");
    return repository.findAll();
  }

  @GetMapping("/trailers/trailer/{id}")
  public List<Trailer> retrieveTrailer(@PathVariable long id) {
    logger.info("retrieveTrailer {id}: " + id);
    List<Trailer> trailers = new ArrayList<>();
    repository.findById(id).ifPresent(x -> trailers.add(x));
    if(trailers.size() > 0) {
      logger.info("found trailer with id: " + id);
    } else {
      logger.error("trailer with id: " + id + " not found");
    }
    return trailers;
  }

  @DeleteMapping("/trailers/trailer/{id}")
  public ResponseEntity<Object> deleteTrailer(@PathVariable long id) {
    logger.info("delete trailer with id: " + id);
    Optional<Trailer> obj = repository.findById(id);
    if (obj.isPresent()) {
      repository.delete(obj.get());
      logger.info("deleted trailer " + id);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      logger.info("trailer does not exist");
      return new ResponseEntity<>("trailer not found", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/trailers/trailer/save")
  public ResponseEntity<Object> createTrailer(@RequestBody Trailer trailer) throws NoSuchAlgorithmException {
    logger.info("save new trailer: " + trailer);
    if(repository.findByName(trailer.getName()).size() == 0) {
      repository.save(new Trailer(trailer.getName()));
      Trailer newTrailer = repository.findByName(trailer.getName()).get(0);
      logger.info("Trailer created");
      return new ResponseEntity<>(newTrailer, HttpStatus.OK);
    } else {
      logger.info("trailer already exists. Do not create");
      return new ResponseEntity<>("trailer already exists", HttpStatus.BAD_REQUEST);
    }
  }

}
