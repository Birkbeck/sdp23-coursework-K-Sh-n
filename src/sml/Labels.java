package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

/**
 * * @author Kishen Nakrani
 * * @version 1.0
 */
public final class Labels {
    private final Map<String, Integer> labels = new HashMap<>();

    /**
     * Adds a label with the associated address to the map.
     *
     * @param label   the label
     * @param address the address the label refers to
     */
    public void addLabel(String label, int address) {
        Objects.requireNonNull(label);
        // TODO: Add a check that there are no label duplicates.


        if (!labels.containsKey(label)) {
            labels.put(label, address);
        } else {
            System.err.println(" Error! There is atleast one duplicate label, please double check the .sml file ");
            System.exit(-1);
        }
    }


    /**
     * Returns the address associated with the label.
     *
     * @param label the label
     * @return the address the label refers to
     */
    public int getAddress(String label) throws NullPointerException {
        // TODO: Where can NullPointerException be thrown here?
        //       (Write an explanation.)
        //       Add code to deal with non-existent labels.

        // A NullPointerException can be thrown if in the labels map, a label, address key value pair doesnt exist
        // i.e if you want to get the address of label f4, but this isn't present in the map, then a NullPointerException
        // would be thrown. The method getAddress is called in the JnzInstruction Class
        return labels.get(label);
    }

    /**
     * representation of this instance,
     * in the form "[label -> address, label -> address, ..., label -> address]"
     *
     * @return the string representation of the labels map
     */
    @Override
    public String toString() {
        // TODO: Implement the method using the Stream API (see also class Registers).
        return labels.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " -> " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Labels labels1 = (Labels) o;
        return labels.equals(labels1.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labels);
    }

    /**
     * Removes the labels
     */
    public void reset() {
        labels.clear();
    }
}
