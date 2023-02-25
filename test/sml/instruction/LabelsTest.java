package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;

class LabelsTest {
    private Machine machine;
    private Registers registers;
    private Labels newLabel;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        newLabel = new Labels();
        newLabel.addLabel("f3",0);
        newLabel.addLabel("g3",1);
        newLabel.addLabel("h3",2);
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        newLabel = null;
    }

    @Test
    void LabelshouldThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> newLabel.getAddress("z10") );
    }

    @Test
    void LabelshouldPrintOutFormatted() {
        Assertions.assertEquals("[f3 -> 0, g3 -> 1, h3 -> 2]", newLabel.toString());
    }


    // need to think about how to test for Dupelabels if the system exits

    void addDupeLabel() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        String expected = "Error! There is atleast one duplicate label, please double check the .sml file";
        newLabel.addLabel("f3",5);

        String actual = outputStream.toString().trim();

        Assertions.assertEquals(expected,actual);
    }
}