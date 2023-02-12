package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * @author Kishen Nakrani
 * @version 1.0
 */

public class MovInstruction extends Instruction {
    private final RegisterName source;
    private final int number;


    public static final String MOV_OP_CODE = "mov";

    public MovInstruction(String label, RegisterName source, int number) {
        super(label, MOV_OP_CODE);
        this.source = source;
        this.number = number;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(source, number);
        return NORMAL_PROGRAM_COUNTER_UPDATE; // NORMAL means move onto the next instruction like normal, i.e no jumps to another address
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovInstruction that)) return false;
        return number == that.number && source.equals(that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, number);
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + number ;
    }

}
