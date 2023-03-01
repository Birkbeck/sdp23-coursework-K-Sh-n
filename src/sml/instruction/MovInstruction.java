package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a single Move instruction in the language used.
 *
 * @author Kishen Nakrani
 * @version 1.0
 */

public class MovInstruction extends Instruction {
    private final RegisterName source;
    private final int number;


    public static final String OP_CODE = "mov";

    /**
     * @param label  optional label (can be null)
     * @param source Register which is loaded with an integer value
     * @param number is the value loaded to the Register
     */
    public MovInstruction(String label, RegisterName source, int number) {
        super(label, OP_CODE);
        this.source = source;
        this.number = number;
    }

    /**
     * Executes the instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return NORMAL_PROGRAM_COUNTER_UPDATE to indicate that the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(source, number);
        return NORMAL_PROGRAM_COUNTER_UPDATE; // NORMAL means move onto the next instruction like normal, i.e no jumps to another address
    }

    /**
     * Override equals method to check if an instruction is equal to an Object
     * @param o is the Object that this instruction is compared to
     * @return true if this Instruction and the Object (o) are equal or false if this Instruction and the Object (o) are not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovInstruction that)) return false;
        return number == that.number && source.equals(that.source);
    }

    /**
     * Override HashCode method for this instruction
     * @return the hashCode for this instruction
     */
    @Override
    public int hashCode() {
        return Objects.hash(source, number);
    }

    /**
     * Output this instruction in String form
     * @return the output of this instruction in useful String form
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + number ;
    }

}
