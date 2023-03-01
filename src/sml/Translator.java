package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 * <p>
 * * @author Kishen Nakrani
 * * @version 1.0
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel(); // getLabel method checks whether or not a label exists and returns if it does e.g returns f4 (without colon) (see further down)

                Instruction instruction = getInstruction(label); // getInstruction method retrieves the next instruction (see further down)
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size()); // add to machine instance any label at start of line to Hashmap to keep track of them
                    program.add(instruction); // add to machine instance: an instruction object that gets added to program (which is nothing but an arraylist of instructions)
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        if (line.isEmpty())
            return null;

        String opcode = scan(); // scan method defined further down. It returns the first word
        String opcodeClass =  "sml.instruction." + opcode.substring(0,1).toUpperCase() + opcode.substring(1).toLowerCase() + "Instruction";

        Class<?> ClassInstruction = Class.forName(opcodeClass);

        Instruction ins;
        String r = scan();


        if (ClassInstruction == OutInstruction.class) {
            Constructor<?> constructor = ClassInstruction.getConstructor(String.class, RegisterName.class);
            ins = (Instruction) constructor.newInstance(label, Register.valueOf(r));
        } else if (ClassInstruction == JnzInstruction.class) {
            Constructor<?> constructor = ClassInstruction.getConstructor(String.class, RegisterName.class, String.class);
            String s = scan();
            ins = (Instruction) constructor.newInstance(label, Register.valueOf(r), s);
        } else if (ClassInstruction == MovInstruction.class) {
            Constructor<?> constructor = ClassInstruction.getConstructor(String.class, RegisterName.class, int.class);
            int s = Integer.parseInt(scan());
            ins = (Instruction) constructor.newInstance(label, Register.valueOf(r), s);
        } else {
            Constructor<?> constructor = ClassInstruction.getConstructor(String.class, RegisterName.class, RegisterName.class);
            String s = scan();
            ins = (Instruction) constructor.newInstance(label, Register.valueOf(r), Register.valueOf(s));
        }
        return ins;
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}