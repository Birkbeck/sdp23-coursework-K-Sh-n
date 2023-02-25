package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class GeneralTests {
    private Machine machine;
    private Registers registers;
    private Labels labels;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        labels = machine.getLabels();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        labels = null;
    }

    @Test
    void executeAddMultiply() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new AddInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        instruction2.execute(machine);
        Assertions.assertEquals(66, machine.getRegisters().get(EAX));
    }

    @Test
    void executeAddSub() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        registers.set(EBP, 3);
        registers.set(EDI, 1);
        Instruction instruction = new AddInstruction(null, EAX, EBX);
        Instruction instruction2 = new SubInstruction("f3", EBP, EDI);
        Instruction instruction3 = new AddInstruction(null, EAX, EBP);


        instruction.execute(machine);
        instruction2.execute(machine);
        instruction3.execute(machine);
        Assertions.assertEquals(13, machine.getRegisters().get(EAX));
    }


}