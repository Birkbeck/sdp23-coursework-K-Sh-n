package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

import java.awt.*;
import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents a single Jump instruction in the language used.
 *
 * @author Kishen Nakrani
 * @version 1.0
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String newLabel;

    public static final String OP_CODE = "jnz";

    /**
     * @param label  optional label (can be null)
     * @param result Register containing the value. If non-zero a jump operation is performed
     * @param newLabel indicates where to jump to
     */
    public JnzInstruction(String label, RegisterName result, String newLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.newLabel = newLabel;
    }

    /**
     * Executes the instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return the new program counter
     * @throws NullPointerException if the instruction jumps to a label address does not exist
     */
    @Override
    public int execute(Machine m) throws NullPointerException{
        int registerValue = m.getRegisters().get(result);
        if (registerValue == 0){
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        return m.getLabels().getAddress(newLabel);

    }

    /**
     * Output this instruction in String form
     * @return the output of this instruction in useful String form
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + newLabel;
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
        if (!(o instanceof JnzInstruction jnzInstruction)){
            return false;
        }
        return result.equals(jnzInstruction.result) && newLabel.equals(jnzInstruction.newLabel);
    }

    /**
     * Override HashCode method for this instruction
     * @return the hashCode for this instruction
     */
    @Override
    public int hashCode() {
        return Objects.hash(result, newLabel);
    }
}
