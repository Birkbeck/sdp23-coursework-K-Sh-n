package sml;

import java.util.*;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

/**
 * Represents the Registers of the program stored as a Map with methods to add, remove and retrieve Registers and their corresponding values
 * The class overrides methods equals, hashCode and toString
 * @author Kishen Nakrani
 * @version 1.0
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
    }

    /**
     * Constructor creates an object and clears all values in Map to zero
     */
    public Registers() {
        clear(); // the class is final
    }

    /**
     * Clears all the values from this Register
     */
    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }

    /**
     * Override equals method to check if a Register object is equal to another Object
     * @param o is the Object that this Register object is compared to
     * @return true if this Register object and the Object (o) are equal or false if this Register object and the Object (o) are not equal
     */
    // TODO: use pattern matching for instanceof
    // https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof Registers r)) {
            return false;
        }
        return registers.equals(r.registers);
    }

    /**
     * Override HashCode method for this register
     * @return the hashCode for this register
     */
    @Override
    public int hashCode() {
        return registers.entrySet().stream()
                .mapToInt(register -> Objects.hash(register.getKey(), register.getValue()))
                .sum();
    }

    /**
     *
     * Output this instruction in String form
     * in form [Register = Integer, Register = Integer, ..., Register = Integer].
     * @return the output of this instruction in useful String form
     */
    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
