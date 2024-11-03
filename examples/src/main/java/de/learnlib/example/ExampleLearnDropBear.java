/* Copyright (C) 2013-2024 TU Dortmund University
 * This file is part of LearnLib, http://www.learnlib.de/.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.learnlib.example;

import java.io.IOException;

import de.learnlib.acex.AcexAnalyzers;
import de.learnlib.algorithm.LearningAlgorithm.MealyLearner;
import de.learnlib.algorithm.ttt.mealy.TTTLearnerMealy;
import de.learnlib.oracle.EquivalenceOracle.MealyEquivalenceOracle;
import de.learnlib.oracle.MembershipOracle.MealyMembershipOracle;
import de.learnlib.oracle.OmegaMembershipOracle.MealyOmegaMembershipOracle;
import de.learnlib.oracle.equivalence.MealyWMethodEQOracle;
import de.learnlib.oracle.membership.SimulatorOmegaOracle.MealySimulatorOmegaOracle;
import de.learnlib.testsupport.example.mealy.ExampleDropBear;
import de.learnlib.testsupport.example.mealy.ExampleDropBear.Input;
import de.learnlib.testsupport.example.mealy.ExampleDropBear.Output;
import de.learnlib.util.Experiment.MealyExperiment;
import net.automatalib.alphabet.Alphabet;
import net.automatalib.automaton.transducer.MealyMachine;
import net.automatalib.serialization.dot.GraphDOT;
import net.automatalib.visualization.Visualization;

/**
 * This example shows the usage of a learning algorithm and an equivalence test as part of an experiment in order to
 * learn a simulated SUL (system under learning).
 */
@SuppressWarnings("PMD.SystemPrintln")
public final class ExampleLearnDropBear {

    private static final int EXPLORATION_DEPTH = 4;

    private ExampleLearnDropBear () {
        // prevent instantiation
    }

    public static void main(String[] args) throws IOException {

        // load DFA and alphabet
        ExampleDropBear tcpExample = ExampleDropBear.createExample();
        MealyMachine<?, Input, ?, Output> mealy = tcpExample.getReferenceAutomaton();
        Alphabet<Input> alphabet = tcpExample.getAlphabet();

        // create an omega membership oracle
        MealyOmegaMembershipOracle<?, Input, Output> omqOracle = new MealySimulatorOmegaOracle<>(mealy);

        // create a regular membership oracle
        MealyMembershipOracle<Input, Output> mqOracle = omqOracle.getMembershipOracle();
        MealyLearner<Input, Output> learner = new TTTLearnerMealy<>(alphabet, mqOracle, AcexAnalyzers.LINEAR_FWD);

        MealyEquivalenceOracle<Input, Output> wMethod = new MealyWMethodEQOracle<>(mqOracle, EXPLORATION_DEPTH);

        MealyExperiment<Input, Output> experiment = new MealyExperiment<>(learner, wMethod, alphabet);

        experiment.setProfile(true);
        experiment.setLogModels(true);
        experiment.run();

        MealyMachine<?, Input, ?, Output> result = experiment.getFinalHypothesis();

        System.out.println();
        System.out.println("Model: ");
        GraphDOT.write(result, alphabet, System.out); // may throw IOException!

        Visualization.visualize(result, alphabet);
    }
}
