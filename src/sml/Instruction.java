package sml;

// TODO: write a JavaDoc for the class. Done

/**
 * Represents a single abstract instruction in the language, which therefore must be extended by subclasses to be used
 *
 * @author Kishen Nakrani
 * @version 1.0
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	/**
	 * Gets the label for this instruction
	 * @return the label for this instruction
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Gets the opcode for this instruction
	 * @return the opcode for this instruction
	 */
	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	/**
	 * Gets the full label string if a label is not null
	 * @return blank String if label is null or appends ": " to the label otherwise
	 */
	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean?
	//       (Write a short explanation.). Done please see below:

	// Answer: Abstract methods mean that any non-abstract subclasses that extend class Instruction must implement that method i.e override
	// it and thus provide a useful overridden implementation of toString specific to that subclass.
	// If the method wasn't declared as abstract, the user wouldn't be forced to necessarily
	// override it in the non-abstract subclasses. It is a good way to ensure whoever extends class Instruction provides an overriden toString method.

	/**
	 * Output this instruction in String form
	 * @return the output of this instruction in useful String form
	 */
	@Override
	public abstract String toString();

	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine).
	// This is done below

	/**
	 * Override equals method to check if an instruction is equal to an Object
	 * @param o is the Object that this instruction is compared to
	 * @return true if this Instruction and the Object (o) are equal or false if this Instruction and the Object (o) are not equal
	 */
	@Override
	public abstract boolean equals(Object o);


	/**
	 * Override HashCode method for this instruction
	 * @return the hashCode for this instruction
	 */
	@Override
	public abstract int hashCode();
}
