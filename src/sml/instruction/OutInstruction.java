package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Represents a single Out instruction in the language used.
 *
 * @author Kishen Nakrani
 * @version 1.0
 */

public class OutInstruction extends Instruction {
    private final RegisterName result;

    public static final String OP_CODE = "out";

    /**
     * @param label  optional label (can be null)
     * @param result Register containing the value that is output
     */
    public OutInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
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
        System.out.println(value1);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * Output this instruction in String form
     * @return the output of this instruction in useful String form
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
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
        if (!(o instanceof OutInstruction outInstruction)){
            return false;
        }
        return result.equals(outInstruction.result);
    }

    /**
     * Override HashCode method for this instruction
     * @return the hashCode for this instruction
     */
    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
