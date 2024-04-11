package kmo.dev.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Random;

@Getter
@ToString
@EqualsAndHashCode
public abstract class Product {

    private final int modifier = new Random().nextInt(10) + 1;

    @NotNull
    private final String id;

    @NotBlank
    private final String name;

    @Min(1)
    private final double price;

    public Product(@NotNull String id, @NotBlank String name, @Min(1) double price) {
        this.id = id;
        this.name = name;
        this.price = modifier*price;
    }
}
