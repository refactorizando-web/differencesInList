package com.refactorizando.example.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.refactorizando.example.list.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
public class FindElementTest {

  List<User> users = new ArrayList<>();

  @BeforeEach
  public void setup(){

    users = Arrays.asList(User.builder().name("Pepe").build(),
        User.builder().name("Josh").build(), User.builder().name("Sarah").build());
  }
  @Test
  public void given_a_string_list_when_find_amarillo_then_return_element() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");

    int index = listA.indexOf("Amarillo");

    assertEquals("Amarillo", listA.get(index));

  }

  @ParameterizedTest
  @ValueSource(strings = {"Pepe"})
  public void given_a_name_when_find_name_in_list_then_return_user(String userName) {

    List<User> users = Arrays.asList(User.builder().name("Pepe").build(),
        User.builder().name("Josh").build(), User.builder().name("Sarah").build());

    User userFound = null;

    for (User user : users) {
      if (user.getName().equalsIgnoreCase(userName)) {
        userFound = user;
        return;
      }
    }

    assertEquals(userFound, users.get(0));
  }

  @ParameterizedTest
  @ValueSource(strings = {"Pepe"})
  public void given_a_name_when_find_name_in_list_with_iterator_then_return_user(String userName) {

    List<User> users = Arrays.asList(User.builder().name("Pepe").build(),
        User.builder().name("Josh").build(), User.builder().name("Sarah").build());

    User userFound = null;

    Iterator<User> iterator = users.iterator();

    boolean cont = true;

    while (iterator.hasNext() && cont) {

      User user = iterator.next();

      if (user.getName().equals(userName)) {

        userFound = user;
        cont = false;
      }
    }

    assertEquals(userFound, users.get(0));
  }

  @ParameterizedTest
  @ValueSource(strings = {"Sarah"})
  public void given_a_name_when_find_name_in_list_with_java_stream_then_return_user(
      String userName) {

    List<User> users = Arrays.asList(User.builder().name("Pepe").build(),
        User.builder().name("Josh").build(), User.builder().name("Sarah").build());

    User userFound = users.stream()
        .filter(user -> user.getName().equalsIgnoreCase(userName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException());

    assertEquals(userFound, users.get(2));
  }

  @ParameterizedTest
  @ValueSource(strings = {"Pepe"})
  public void given_a_name_when_find_name_in_list_with_java_stream_find_any_then_return_user(
      String userName) {

    List<User> users = Arrays.asList(User.builder().name("Pepe").build(),
        User.builder().name("Josh").build(), User.builder().name("Sarah").build());

    User userFound = users.stream()
        .filter(user -> user.getName().equalsIgnoreCase(userName))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException());

    assertEquals(userFound, users.get(0));
  }

  //Remove comment to use with apache commons
 /* @ParameterizedTest
  @ValueSource(strings = {"Pepe"})
  public void given_a_name_when_find_name_in_list_with_apache_commons_then_return_user(
      String userName) {

    List<User> users = Arrays.asList(User.builder().name("Pepe").build(),
        User.builder().name("Josh").build(), User.builder().name("Sarah").build());

    User userFound = IterableUtils.find(users,
        new Predicate<User>() {
          public boolean evaluate(User user) {
            return userName.equals(user.getName());
          }
        });

    assertEquals(userFound, users.get(0));
  }*/

  @ParameterizedTest
  @ValueSource(strings = {"Pepe"})
  public void given_a_name_when_find_name_in_list_with_guava_then_return_user(
      String userName) {

    User userFound = Iterables.tryFind(users,
        user -> userName.equals(user.getName())).get();

    assertEquals(userFound, users.get(0));
  }


}
