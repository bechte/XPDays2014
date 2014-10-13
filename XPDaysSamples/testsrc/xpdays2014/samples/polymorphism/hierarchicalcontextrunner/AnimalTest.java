package xpdays2014.samples.polymorphism.hierarchicalcontextrunner;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import xpdays2014.samples.polymorphism.Animal;
import xpdays2014.samples.polymorphism.Cat;
import xpdays2014.samples.polymorphism.Dog;

import static org.junit.Assert.assertEquals;

@RunWith(HierarchicalContextRunner.class)
public class AnimalTest {
  private Animal animal;

  public class DogContext {
    @Before
    public void createDog() {
      animal = new Dog();
    }

    @Test
    public void dogBarkes() throws Exception {
      assertEquals("wuff wuff", animal.makeNoise());
    }
  }

  public class CatContext {
    @Before
    public void createCat() {
      animal = new Cat();
    }

    @Test
    public void catMeows() throws Exception {
      assertEquals("meow", animal.makeNoise());
    }
  }
}