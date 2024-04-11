package kmo.dev.entity;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import static java.lang.StringTemplate.STR;

public class Fruit extends Product {

    private FLAVOUR flavour = FLAVOUR.SWEET;
    public String serialVersionGuid = UUID.randomUUID().toString();

    public Fruit(@NotNull UUID id, @NotBlank String name, @Min(1) double price) {
        super(id.toString(), name, price);
    }

    public Fruit(@NotNull UUID id, @NotBlank String name, @Min(1) double price, final String flavour) throws ConstraintViolationException {
        super(id.toString(), name, price);
        this.flavour = FLAVOUR.valueOf(flavour);
    }

    public String getClassifier() {
        return STR."\{flavour} \{super.getName()}";
    }

    enum FLAVOUR {
        SWEET,
        BITTER,
        SUPER_RAINBOW
    }
}
