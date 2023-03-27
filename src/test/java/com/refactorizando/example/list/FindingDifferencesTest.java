package com.refactorizando.example.list;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@Slf4j
public class FindingDifferencesTest {


  @Test
  public void given_two_list_when_get_difference_with_api_java_then_get_difference() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Amarillo", "Rojo", "Violeta", "Marrón", "Negro");

    final List expectedList = Arrays.asList("Azul", "Verde", "Gris");

    List<String> differences = new ArrayList<>(listA);
    differences.removeAll(listB);

    assertEquals(3, differences.size());
    assertEquals(differences, expectedList);

  }

  @Test
  public void given_two_list_when_get_difference_with_api_stream_then_get_difference() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Amarillo", "Rojo", "Violeta", "Marrón", "Negro");

    final List expectedList = Arrays.asList("Azul", "Verde", "Gris");

    List<String> differences = (List<String>) listA.stream()
        .filter(element -> !listB.contains(element))
        .collect(Collectors.toList());

    assertEquals(3, differences.size());
    assertEquals(differences, expectedList);
  }

  @Test
  public void given_two_list_when_get_difference_with_guava_then_get_difference() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Amarillo", "Rojo", "Violeta", "Marrón", "Negro");

    final List expectedList = Arrays.asList("Azul", "Verde", "Gris");

    List<String> differences = new ArrayList<>(Sets.difference(Sets.newHashSet(listA),
        Sets.newHashSet(listB)));

    assertEquals(3, differences.size());
    assertTrue(differences.size() == expectedList.size() &&
        differences.containsAll(expectedList) && expectedList.containsAll(differences));
  }

  @Test
  public void given_two_list_when_get_difference_with_apache_commons_then_get_difference() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Amarillo", "Rojo", "Violeta", "Marrón", "Negro");

    final List expectedList = Arrays.asList("Azul", "Verde", "Gris");

    List<String> differences = new ArrayList<>(CollectionUtils.removeAll(listA, listB));

    assertEquals(3, differences.size());
    assertTrue(differences.size() == expectedList.size() &&
        differences.containsAll(expectedList) && expectedList.containsAll(differences));
  }

  @Test
  public void given_two_list_when_get_difference_with_for_then_get_difference() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Amarillo", "Rojo", "Violeta", "Marrón", "Negro");

    final List expectedList = Arrays.asList("Azul", "Verde", "Gris");

    List<String> differences = new ArrayList<>();

    for (Object elementA : listA) {
      boolean equals = false;
      for (Object elementB : listB) {
        if (elementA.equals(elementB)) {
          equals = true;
        }
      }
      if (!equals) {
        differences.add((String) elementA);
      }
    }

    assertEquals(3, differences.size());
    assertEquals(differences, expectedList);

  }

  @Test
  public void given_two_list_when_get_difference_with_while_then_get_difference() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Amarillo", "Rojo", "Violeta", "Marrón", "Negro");

    final List expectedList = Arrays.asList("Azul", "Verde", "Gris");

    List<String> differences = new ArrayList<>();

    int i = 0;
    int j = 0;
    while (listA.size() > i) {
      boolean equals = false;
      while (!equals && listB.size() > j) {
        if (listA.get(i).equals(listB.get(j))) {
          equals = true;
        }
        j++;
      }
      if (!equals) {
        differences.add((String) listA.get(i));
      }
      i++;
    }

    assertEquals(3, differences.size());
    assertEquals(differences, expectedList);

  }

  @Test
  public void given_two_list_when_assert_then_return_false() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Rojo", "Azul", "Verde", "Gris", "Amarillo");

    assertFalse(listA.equals(listB));

  }

  @Test
  public void given_two_list_when_assert_with_apache_commons_then_return_true() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Rojo", "Azul", "Verde", "Gris", "Amarillo");

    assertTrue(CollectionUtils.isEqualCollection(listA, listB));
  }

  @Test
  public void given_two_list_when_assert_with_junit_then_return_true() {
    List listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
    List listB = Arrays.asList("Rojo", "Azul", "Verde", "Gris", "Amarillo");

    assertTrue(
        listA.size() == listB.size() && listA.containsAll(listB) && listB.containsAll(listA));
  }

    @Test
    public void given_two_list_when_assert_with_hamcrest_then_return_true() {
      List<String> listA = Arrays.asList("Amarillo", "Rojo", "Azul", "Verde", "Gris");
      List<String> listB = Arrays.asList("Rojo", "Azul", "Verde", "Gris", "Amarillo");
      assertThat(listA, Matchers.containsInAnyOrder(listB.toArray()));
    }

}
