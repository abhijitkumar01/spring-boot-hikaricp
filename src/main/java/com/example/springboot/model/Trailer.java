package com.example.springboot.model;

import javax.persistence.*;
import javax.xml.bind.*;
import java.security.*;
import java.util.*;

@Entity
public class Trailer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private String nameMD5;

  public Trailer() {
    id = 0;
    name = "";
    nameMD5 = "";
  }

  public Trailer(String name) throws NoSuchAlgorithmException {
    this.name = name;
    setNameMd5();
  }

  public long getId() { return id; }

  public String getName() {
    return name;
  }

  public String getNameMD5() {
    return nameMD5;
  }

  public void setName(String name) throws NoSuchAlgorithmException {
    this.name = name;
    setNameMd5();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Trailer trailer = (Trailer) o;
    return id == trailer.id &&
            name.equals(trailer.name) &&
            nameMD5.equals(trailer.nameMD5);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, nameMD5);
  }

  @Override
  public String toString() {
    return "Trailer{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", nameMD5='" + nameMD5 + '\'' +
            '}';
  }

  public void setNameMd5() throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(this.name.getBytes());
    byte[] digest = md.digest();
    this.nameMD5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
  }
}
