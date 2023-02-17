package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author Kishen Nakrani
 * @version 1.0
 */

public class OutInstruction extends Instruction {
    private final RegisterName result;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result); // gets the value stored in the register
        System.out.println(value1); // prints out value1 per README "Print the contents of register s on the console"
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
