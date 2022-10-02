package ru.ivanov.transportation.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Cargo {

  @Id
  private final String id;
  private final String name;
  private final Type type;

  public enum Type {
    CAR, BIKE, FURNITURE, THING, SCRAP
  }
}
