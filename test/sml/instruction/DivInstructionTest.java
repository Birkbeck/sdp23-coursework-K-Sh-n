package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class DivInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -48);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-8, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(EAX, -48);
        registers.set(EBX, -6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(8, machine.getRegisters().get(EAX));
    }

    @Test
    void printOut() {
        registers.set(EAX, 60);
        registers.set(EBX, 12);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        String string1 = "div EAX EBX";
        String string2 = instruction.toString();
        Assertions.assertEquals(string1, string2);
    }
}