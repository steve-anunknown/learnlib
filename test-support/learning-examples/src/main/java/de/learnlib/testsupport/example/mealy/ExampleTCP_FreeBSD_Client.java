 /** This file was generated automatically by parse-dot.sh **/ 
package de.learnlib.testsupport.example.mealy;

import de.learnlib.testsupport.example.DefaultLearningExample.DefaultMealyLearningExample;
import de.learnlib.testsupport.example.mealy.ExampleTCP_FreeBSD_Client.Input;
import de.learnlib.testsupport.example.mealy.ExampleTCP_FreeBSD_Client.Output;
import net.automatalib.alphabet.Alphabet;
import net.automatalib.alphabet.impl.Alphabets;
import net.automatalib.automaton.transducer.MutableMealyMachine;
import net.automatalib.automaton.transducer.impl.CompactMealy;
import net.automatalib.util.automaton.builder.AutomatonBuilders;
import net.automatalib.util.automaton.builder.MealyBuilder;

/**
 * This example encodes a small TCP_FreeBSD_Client with a capacity of three elements and "push" and "pop" operations as Mealy
 * machine. Outputs are "ok", "empty" and "full".
 */
    public class ExampleTCP_FreeBSD_Client extends DefaultMealyLearningExample<Input, Output> {

    public ExampleTCP_FreeBSD_Client() {
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
        return AutomatonBuilders.forMealy(fm)
                .withInitial("s0")
                .from("s0")
	                .on(Input.I1).withOutput(Output.O11).loop()
	                .on(Input.I3).withOutput(Output.O11).to("s1")
	                .on(Input.I4).withOutput(Output.O10).to("s2")
	                .on(Input.I0).withOutput(Output.O9).loop()
	                .on(Input.I8).withOutput(Output.O9).loop()
	                .on(Input.I7).withOutput(Output.O11).loop()
	                .on(Input.I2).withOutput(Output.O9).loop()
	                .on(Input.I5).withOutput(Output.O9).loop()
	                .on(Input.I9).withOutput(Output.O5).loop()
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s1")
	                .on(Input.I1).withOutput(Output.O11).loop()
	                .on(Input.I3).withOutput(Output.O11).loop()
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O9).loop()
	                .on(Input.I8).withOutput(Output.O9).loop()
	                .on(Input.I7).withOutput(Output.O11).loop()
	                .on(Input.I2).withOutput(Output.O9).loop()
	                .on(Input.I5).withOutput(Output.O9).loop()
	                .on(Input.I9).withOutput(Output.O5).loop()
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s2")
	                .on(Input.I1).withOutput(Output.O11).to("s1")
	                .on(Input.I3).withOutput(Output.O11).to("s4")
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O11).loop()
	                .on(Input.I8).withOutput(Output.O3).to("s3")
	                .on(Input.I7).withOutput(Output.O11).to("s5")
	                .on(Input.I2).withOutput(Output.O11).loop()
	                .on(Input.I5).withOutput(Output.O11).loop()
	                .on(Input.I9).withOutput(Output.O6).to("s6")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s3")
	                .on(Input.I1).withOutput(Output.O11).to("s1")
	                .on(Input.I3).withOutput(Output.O0).to("s8")
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O3).loop()
	                .on(Input.I8).withOutput(Output.O4).to("s1")
	                .on(Input.I7).withOutput(Output.O11).to("s1")
	                .on(Input.I2).withOutput(Output.O11).loop()
	                .on(Input.I5).withOutput(Output.O3).to("s7")
	                .on(Input.I9).withOutput(Output.O4).to("s1")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s4")
	                .on(Input.I1).withOutput(Output.O11).to("s1")
	                .on(Input.I3).withOutput(Output.O11).loop()
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O8).to("s1")
	                .on(Input.I8).withOutput(Output.O8).loop()
	                .on(Input.I7).withOutput(Output.O11).to("s1")
	                .on(Input.I2).withOutput(Output.O8).to("s1")
	                .on(Input.I5).withOutput(Output.O8).to("s1")
	                .on(Input.I9).withOutput(Output.O5).to("s1")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s5")
	                .on(Input.I1).withOutput(Output.O11).loop()
	                .on(Input.I3).withOutput(Output.O11).to("s1")
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O9).loop()
	                .on(Input.I8).withOutput(Output.O9).loop()
	                .on(Input.I7).withOutput(Output.O11).loop()
	                .on(Input.I2).withOutput(Output.O9).loop()
	                .on(Input.I5).withOutput(Output.O9).loop()
	                .on(Input.I9).withOutput(Output.O7).to("s6")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s6")
	                .on(Input.I1).withOutput(Output.O11).to("s1")
	                .on(Input.I3).withOutput(Output.O11).to("s4")
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O3).to("s3")
	                .on(Input.I8).withOutput(Output.O4).to("s1")
	                .on(Input.I7).withOutput(Output.O11).to("s1")
	                .on(Input.I2).withOutput(Output.O11).to("s3")
	                .on(Input.I5).withOutput(Output.O3).to("s7")
	                .on(Input.I9).withOutput(Output.O4).to("s1")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s7")
	                .on(Input.I1).withOutput(Output.O11).to("s1")
	                .on(Input.I3).withOutput(Output.O0).to("s9")
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O3).loop()
	                .on(Input.I8).withOutput(Output.O4).to("s1")
	                .on(Input.I7).withOutput(Output.O11).to("s1")
	                .on(Input.I2).withOutput(Output.O11).loop()
	                .on(Input.I5).withOutput(Output.O3).loop()
	                .on(Input.I9).withOutput(Output.O4).to("s1")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s8")
	                .on(Input.I1).withOutput(Output.O11).to("s1")
	                .on(Input.I3).withOutput(Output.O11).loop()
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O8).to("s1")
	                .on(Input.I8).withOutput(Output.O4).to("s1")
	                .on(Input.I7).withOutput(Output.O11).to("s1")
	                .on(Input.I2).withOutput(Output.O11).loop()
	                .on(Input.I5).withOutput(Output.O3).to("s10")
	                .on(Input.I9).withOutput(Output.O4).to("s1")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s9")
	                .on(Input.I1).withOutput(Output.O11).to("s1")
	                .on(Input.I3).withOutput(Output.O11).loop()
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O8).to("s1")
	                .on(Input.I8).withOutput(Output.O4).to("s1")
	                .on(Input.I7).withOutput(Output.O11).to("s1")
	                .on(Input.I2).withOutput(Output.O11).to("s4")
	                .on(Input.I5).withOutput(Output.O11).to("s4")
	                .on(Input.I9).withOutput(Output.O4).to("s1")
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s10")
	                .on(Input.I1).withOutput(Output.O11).to("s11")
	                .on(Input.I3).withOutput(Output.O11).loop()
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O2).loop()
	                .on(Input.I8).withOutput(Output.O2).loop()
	                .on(Input.I7).withOutput(Output.O11).to("s11")
	                .on(Input.I2).withOutput(Output.O11).loop()
	                .on(Input.I5).withOutput(Output.O2).loop()
	                .on(Input.I9).withOutput(Output.O11).loop()
	                .on(Input.I6).withOutput(Output.O11).loop()
                .from("s11")
	                .on(Input.I1).withOutput(Output.O11).loop()
	                .on(Input.I3).withOutput(Output.O11).loop()
	                .on(Input.I4).withOutput(Output.O11).loop()
	                .on(Input.I0).withOutput(Output.O1).loop()
	                .on(Input.I8).withOutput(Output.O1).loop()
	                .on(Input.I7).withOutput(Output.O11).loop()
	                .on(Input.I2).withOutput(Output.O1).loop()
	                .on(Input.I5).withOutput(Output.O1).loop()
	                .on(Input.I9).withOutput(Output.O5).to("s1")
	                .on(Input.I6).withOutput(Output.O11).loop()
                    .create();
        // @formatter:on
    }

    public static Alphabet<Input> createInputAlphabet() {
      return Alphabets.fromEnum(Input.class);
    }

    public static ExampleTCP_FreeBSD_Client createExample() {
      return new ExampleTCP_FreeBSD_Client();
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
		I9
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
		O11
,
    }
}
