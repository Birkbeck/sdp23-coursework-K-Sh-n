package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents a single Subtraction instruction in the language used.
 *
 * @author Kishen Nakrani
 * @version 1.0
 */

public class SubInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";

    /**
     * @param label  optional label (can be null)
     * @param result Register containing an operand and where the final result of the Subtraction is stored
     * @param source Register containing an operand
     */
    public SubInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * Executes the instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return NORMAL_PROGRAM_COUNTER_UPDATE to indicate that the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 - value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * Output this instruction in String form
     * @return the output of this instruction in useful String form
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }


    /**
     * Override equals method to check if an instruction is equal to an Object
     * @param o is the Object that this instruction is compared to
     * @return true if this Instruction and the Object (o) are equal or false if this Instruction and the Object (o) are not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof SubInstruction subInstruction)){
            return false;
        }
        return result.equals(subInstruction.result) && source.equals(subInstruction.source);
    }

    /**
     * Override HashCode method for this instruction
     * @return the hashCode for this instruction
     */
    @Override
    public int hashCode() {
        return Objects.hash(result, source);
    }
}
