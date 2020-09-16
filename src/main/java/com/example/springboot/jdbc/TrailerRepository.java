package com.example.springboot.jdbc;

import com.example.springboot.ddl.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface TrailerRepository extends CrudRepository<Trailer, Long> {
  List<Trailer> findByName(String name);
}
