package com.refactorizando.example.list.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

  private String name;

  private String surname;

  private String address;

}
