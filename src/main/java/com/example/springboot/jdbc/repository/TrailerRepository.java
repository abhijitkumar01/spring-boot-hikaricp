package com.example.springboot.jdbc.repository;

import com.example.springboot.model.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface TrailerRepository extends CrudRepository<Trailer, Long> {
  List<Trailer> findByName(String name);
}
