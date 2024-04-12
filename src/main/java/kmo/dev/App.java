package kmo.dev;

import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Min;
import kmo.dev.entity.Fruit;
import kmo.dev.entity.Product;
import kmo.dev.repository.ProductRepository;
import kmo.dev.service.ProductService;

import javax.xml.validation.Validator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

public class App {

    public static void main(final String[] args) throws Exception {
        final var productService = new ProductService();

        final var product1 = new Fruit(UUID.randomUUID(), "cherry", 1.99);
        final var product2 = new Fruit(UUID.randomUUID(), "grape", 0.99);

        final var productRepositoryInstance = ProductRepository.class.getDeclaredConstructor().newInstance();
        final var productRepositoryField = ProductService.class.getDeclaredField("productRepository");
        productRepositoryField.setAccessible(true);
        productRepositoryField.set(productService, productRepositoryInstance);

        productService.buy(product1);
        productService.buy(product2);

        System.out.println(STR."all products: \{productService.findAll()}");

        final var productModifierField = Product.class.getDeclaredField("modifier");
        productModifierField.setAccessible(true);

        System.out.println(STR."modifier product 1: \{productModifierField.get(product1)}");
        System.out.println(STR."modifier product 2: \{productModifierField.get(product2)}");

        final var productPriceField = Product.class.getDeclaredField("price");
        productPriceField.setAccessible(true);
        System.out.println(STR."annotation value: \{productPriceField.getDeclaredAnnotation(Min.class).value()}");

        System.out.println(STR."all declared fields (abstract class): \{Arrays.stream(Product.class.getDeclaredFields()).map(Field::getName).toList()}");
        System.out.println(STR."all fields (abstract class): \{Arrays.stream(Product.class.getFields()).map(Field::getName).toList()}");
        System.out.println(STR."all declared fields (implementation): \{Arrays.stream(Fruit.class.getDeclaredFields()).map(Field::getName).toList()}");
        System.out.println(STR."all fields (implementation): \{Arrays.stream(Fruit.class.getFields()).map(Field::getName).toList()}");
        System.out.println(STR."all declared classes (simple): \{Arrays.stream(Fruit.class.getDeclaredClasses()).map(Class::getSimpleName).toList()}");
        System.out.println(STR."all declared classes (full qualified): \{Arrays.stream(Fruit.class.getDeclaredClasses()).map(Class::getName).toList()}");
        System.out.println(STR."all declared methods (names): \{Arrays.stream(Fruit.class.getDeclaredMethods()).map(Method::getName).toList()}");
        System.out.println(STR."all declared methods (modifiers): \{Arrays.stream(Fruit.class.getDeclaredMethods()).map(Method::getModifiers).map(Modifier::toString).toList()}");
        System.out.println(STR."all declared methods (return type): \{Arrays.stream(Fruit.class.getDeclaredMethods()).map(Method::getReturnType).toList()}");

        final var fruitFlavourField = Fruit.class.getDeclaredField("flavour");
        final var flavourClass = Class.forName(STR."\{Fruit.class.getPackage().getName()}.Fruit$FLAVOUR").asSubclass(Enum.class);

        fruitFlavourField.setAccessible(true);
        fruitFlavourField.set(product1, Enum.valueOf(flavourClass, "SUPER_RAINBOW"));
        System.out.println(STR."classifier: \{product1.getClassifier()}");
    }

}
