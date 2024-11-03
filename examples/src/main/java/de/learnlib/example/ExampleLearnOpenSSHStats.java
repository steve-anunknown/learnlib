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
import de.learnlib.filter.statistic.oracle.MealyCounterOracle;
import de.learnlib.oracle.EquivalenceOracle.MealyEquivalenceOracle;
import de.learnlib.oracle.MembershipOracle.MealyMembershipOracle;
import de.learnlib.oracle.equivalence.MealyWMethodEQOracle;
import de.learnlib.oracle.membership.MealySimulatorOracle;
import de.learnlib.testsupport.example.mealy.ExampleOpenSSH;
import de.learnlib.testsupport.example.mealy.ExampleOpenSSH.Input;
import de.learnlib.testsupport.example.mealy.ExampleOpenSSH.Output;
import de.learnlib.util.Experiment.MealyExperiment;
import de.learnlib.util.statistic.SimpleProfiler;
import net.automatalib.alphabet.Alphabet;
import net.automatalib.automaton.transducer.MealyMachine;
import net.automatalib.serialization.dot.GraphDOT;
import net.automatalib.visualization.Visualization;

/**
 * This example shows the usage of a learning algorithm and an equivalence test as part of an experiment in order to
 * learn a simulated SUL (system under learning).
 */
@SuppressWarnings("PMD.SystemPrintln")
public final class ExampleLearnOpenSSHStats {

    private static final int EXPLORATION_DEPTH = 4;

    private ExampleLearnOpenSSHStats () {
        // prevent instantiation
    }

    public static void main(String[] args) throws IOException {

        // load DFA and alphabet
        ExampleOpenSSH opensshExample = ExampleOpenSSH.createExample();
        MealyMachine<?, Input, ?, Output> mealy = opensshExample.getReferenceAutomaton();
        Alphabet<Input> alphabet = opensshExample.getAlphabet();

        // create a membership oracle
        MealyMembershipOracle<Input, Output> sul = new MealySimulatorOracle<>(mealy);
        MealyCounterOracle<Input, Output> mqOracle = new MealyCounterOracle<>(sul);

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

        // profiling
        SimpleProfiler.logResults();

        // learning statistics
        System.out.println(experiment.getRounds().getSummary());
        System.out.println(mqOracle.getStatisticalData().getSummary());

        // model statistics
        System.out.println("States: " + result.size());
        System.out.println("Sigma: " + alphabet.size());

        Visualization.visualize(result, alphabet);
    }
}
