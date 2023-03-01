package sml;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    /**
     * Initialises the system and executes the program.
     *
     * @param args name of the file containing the program text.
     */
    public static void main(String... args) {
        if (args.length != 1) {
            System.err.println("Incorrect number of arguments - Machine <file> - required");
            System.exit(-1);
        }

        try {
            Translator t = new Translator(args[0]);
            Machine m = new Machine(new Registers());
            t.readAndTranslate(m.getLabels(), m.getProgram());

            System.out.println("Here is the program; it has " + m.getProgram().size() + " instructions.");
            System.out.println(m);

            System.out.println("Beginning program execution.");
            m.execute();
            System.out.println("Ending program execution.");

            System.out.println("Values of registers at program termination:" + m.getRegisters() + ".");


        } catch (IOException e) {
            System.out.println("Error reading the program from " + args[0]);
        } catch (ArithmeticException ae) {
            System.out.println("Error! .sml file has an error with Arithmetic, please check if you have a divide instriction that divides by zero");
        } catch (NullPointerException np) {
            System.out.println("Error! .sml file has a jnz instruction that refers to a label that doesn't exist. Please check");
        } catch (InvocationTargetException e) {
            System.out.println("Error! The method you have invoked has thrown an exception");
        } catch (NoSuchMethodException e) {
            System.out.println("Error! That constructor does not exist");
        } catch (InstantiationException e) {
            System.out.println("Error! The specified class object you are trying to create cannot be instantiated");
        } catch (IllegalAccessException e) {
            System.out.println("Error! You do not have access to the constructor you are trying to call");
        } catch (ClassNotFoundException e) {
            System.out.println("Error! The Instruction you are trying to process has not been found");
        }
    }
}
