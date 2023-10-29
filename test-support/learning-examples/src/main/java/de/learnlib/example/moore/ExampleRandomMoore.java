/* Copyright (C) 2013-2023 TU Dortmund
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
package de.learnlib.example.moore;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import de.learnlib.example.DefaultLearningExample.DefaultMooreLearningExample;
import net.automatalib.alphabet.Alphabet;
import net.automatalib.automaton.transducer.CompactMoore;
import net.automatalib.util.automaton.random.RandomAutomata;

public class ExampleRandomMoore<I, O> extends DefaultMooreLearningExample<I, O> {

    @SafeVarargs
    public ExampleRandomMoore(Random random, Alphabet<I> alphabet, int size, O... outputs) {
        super(RandomAutomata.randomDeterministic(random,
                                                 size,
                                                 alphabet,
                                                 Arrays.asList(outputs),
                                                 Collections.emptyList(),
                                                 new CompactMoore<>(alphabet)));
    }

    @SafeVarargs
    public static <I, O> ExampleRandomMoore<I, O> createExample(Random random,
                                                                Alphabet<I> alphabet,
                                                                int size,
                                                                O... outputs) {
        return new ExampleRandomMoore<>(random, alphabet, size, outputs);
    }

}
