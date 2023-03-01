package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

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
    void NullPointerExceptionTest() {
        registers.set(EAX, 48);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        Assertions.assertThrows(NullPointerException.class, () -> instruction.execute(machine));
    }

    @Test
    void noJumpTest() {
        registers.set(EAX, 0);
        registers.set(EBX, 5);
        Instruction instruction = new JnzInstruction(null, EAX, "f5");
        Assertions.assertEquals(-1, instruction.execute(machine));
    }

    @Test
    void JumpTest() {
        registers.set(EAX, 15);
        machine.getLabels().addLabel("f5",0);
        Instruction instruction2 = new JnzInstruction(null, EAX, "f5");
        Assertions.assertEquals(0, instruction2.execute(machine));
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
