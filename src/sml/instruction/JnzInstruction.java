package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

import java.awt.*;
import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author Kishen Nakrani
 * @version 1.0
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String newLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String newLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.newLabel = newLabel;
    }

    @Override
    public int execute(Machine m) throws NullPointerException{
        int registerValue = m.getRegisters().get(result);
        if (registerValue == 0){
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        return m.getLabels().getAddress(newLabel);

    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + newLabel;
    }


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

    @Override
    public int hashCode() {
        return Objects.hash(result, newLabel);
    }
}
