package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;

class JnzInstructionTest {
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
    void toStringTest() {
        Instruction instruction = new JnzInstruction(null, EAX, "f5");
        instruction.execute(machine);
        String string1 = "jnz EAX f5";
        String string2 = instruction.toString();
        Assertions.assertEquals(string1, string2);
    }
@Test
    void EqualsMethodOverridenTrue() {
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        Instruction instruction2 = new JnzInstruction(null, EAX, "f3");
        instruction.execute(machine);
        Assertions.assertEquals(instruction, instruction2);
    }


    @Test
    void EqualsMethodOverridenFalse() {
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        Instruction instruction2 = new JnzInstruction(null, EAX, "f4");
        instruction.execute(machine);
        Assertions.assertNotEquals(instruction, instruction2);
    }
}
