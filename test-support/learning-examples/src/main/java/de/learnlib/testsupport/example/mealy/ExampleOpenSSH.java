 /** This file was generated automatically by parse-dot.sh **/ 
package de.learnlib.testsupport.example.mealy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import de.learnlib.testsupport.example.DefaultLearningExample.DefaultMealyLearningExample;
import de.learnlib.testsupport.example.mealy.ExampleOpenSSH.Input;
import de.learnlib.testsupport.example.mealy.ExampleOpenSSH.Output;
import net.automatalib.alphabet.Alphabet;
import net.automatalib.alphabet.impl.Alphabets;
import net.automatalib.automaton.transducer.MutableMealyMachine;
import net.automatalib.automaton.transducer.impl.CompactMealy;
/**
 * This example encodes a small OpenSSH with a capacity of three elements and "push" and "pop" operations as Mealy
 * machine. Outputs are "ok", "empty" and "full".
 */
    public class ExampleOpenSSH extends DefaultMealyLearningExample<Input, Output> {

    public ExampleOpenSSH() {
        super(constructMachine());
    }

    public static CompactMealy<Input, Output> constructMachine() {
        return constructMachine(new CompactMealy<>(createInputAlphabet()));
    }

    /**
     * Construct and return a machine representation of this example.
     *
     * @param fm
     *         the output object to write the contents to
     * @param <S>
     *         state type
     * @param <T>
     *         transition type
     * @param <A>
     *         automaton type
     *
     * @return machine instance of the example
     */
	    public static <S, T, A extends MutableMealyMachine<S, ? super Input, T, ? super Output>> A constructMachine(A fm) {
        // @formatter:off
        // read the transitions from the file BitVise.dot
        String filePath = "/home/stefanos/Documents/NTUA/ModelLearning/learnlib/mealy-machines/ssh/open-ssh.dot";
        Map<String, S> states = new HashMap<>();
        states.computeIfAbsent("s0", arg -> fm.addInitialState());
        try (Scanner scanner = new Scanner(new File(filePath)))
        {
          while (scanner.hasNextLine())
          {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            if (parts.length == 4)
            {
              String from = parts[0];
              String to = parts[1];
              Input in;
              Output out;
              try
              {
                in = Input.valueOf(parts[2]);
                out = Output.valueOf(parts[3]);
              } catch (IllegalArgumentException e)
              {
                System.err.println("Invalid input/output label: " + line);
                continue;
              }
              states.computeIfAbsent(from, arg -> fm.addState());
              states.computeIfAbsent(to, arg -> fm.addState());
              fm.addTransition(states.get(from), in, states.get(to), out);
            }
          }
        } catch (FileNotFoundException e)
        {
          e.printStackTrace();
        }
        return fm;
        // @formatter:on
    }

    public static Alphabet<Input> createInputAlphabet() {
      return Alphabets.fromEnum(Input.class);
    }

    public static ExampleOpenSSH createExample() {
      return new ExampleOpenSSH();
    }
    public enum Input {
		I0,
		I1,
		I2,
		I3,
		I4,
		I5,
		I6,
		I7,
		I8,
		I9,
		I10,
		I11,
		I12,
		I13,
		I14,
		I15,
		I16,
		I17,
		I18,
		I19,
		I20,
		I21
,
    }
    public enum Output {
		O0,
		O1,
		O2,
		O3,
		O4,
		O5,
		O6,
		O7,
		O8,
		O9,
		O10,
		O11,
		O12,
		O13,
		O14,
		O15,
		O16,
		O17,
		O18
,
    }
}
