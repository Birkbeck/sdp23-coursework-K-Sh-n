package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MulInstructionTest {
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
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-30, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringTest() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        String string1 = "mul EAX EBX";
        String string2 = instruction.toString();
        Assertions.assertEquals(string1, string2);
    }

    @Test
    void EqualsMethodOverridenTrue(){
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(instruction, instruction2);
    }

    @Test
    void EqualsMethodOverridenFalse(){
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, EAX, ECX);
        instruction.execute(machine);
        Assertions.assertNotEquals(instruction, instruction2);
    }
}