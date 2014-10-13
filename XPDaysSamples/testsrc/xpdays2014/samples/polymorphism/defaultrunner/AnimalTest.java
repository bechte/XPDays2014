package xpdays2014.samples.polymorphism.defaultrunner;

import org.junit.Test;
import xpdays2014.samples.polymorphism.Animal;
import xpdays2014.samples.polymorphism.Cat;
import xpdays2014.samples.polymorphism.Dog;

import static org.junit.Assert.assertEquals;

public class AnimalTest {
  private Animal dog = new Dog();
  private Animal cat = new Cat();

  @Test
  public void dogBarkes() throws Exception {
    assertEquals("wuff wuff", dog.makeNoise());
  }

  @Test
  public void catMeows() throws Exception {
    assertEquals("meow", cat.makeNoise());
  }
}