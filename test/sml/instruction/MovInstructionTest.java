package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MovInstructionTest {
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
        Instruction instruction = new MovInstruction(null, EAX, 5);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        Instruction instruction = new MovInstruction(null, EAX, 5);
        Instruction instruction2 = new MovInstruction(null, EAX, 15);
        instruction.execute(machine);
        instruction2.execute(machine);
        Assertions.assertEquals(15, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringTest() {
        Instruction instruction = new MovInstruction(null, EAX, 5);
        instruction.execute(machine);
        String string1 = "mov EAX 5";
        String string2 = instruction.toString();
        Assertions.assertEquals(string1, string2);
    }

    @Test
    void EqualsMethodOverridenTrue(){
        Instruction instruction = new MovInstruction(null, EAX, 5);
        Instruction instruction2 = new MovInstruction(null, EAX, 5);
        instruction.execute(machine);
        Assertions.assertEquals(instruction, instruction2);
    }

    @Test
    void EqualsMethodOverridenFalse(){
        Instruction instruction = new MovInstruction(null, EAX, 5);
        Instruction instruction2 = new MovInstruction(null, EAX, 10);
        instruction.execute(machine);
        Assertions.assertNotEquals(instruction, instruction2);
    }
}