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
package de.learnlib.algorithm.procedural.adapter.mealy;

import java.util.List;

import de.learnlib.AccessSequenceTransformer;
import de.learnlib.algorithm.oml.lstar.OptimalLStarMealy;
import de.learnlib.oracle.MembershipOracle;
import net.automatalib.alphabet.Alphabet;
import net.automatalib.word.Word;

/**
 * Adapter for using {@link OptimalLStarMealy} as a procedural learner.
 *
 * @param <I>
 *         input symbol type
 * @param <O>
 *         output symbol type
 */
public class OptimalLStarAdapterMealy<I, O> extends OptimalLStarMealy<I, O> implements AccessSequenceTransformer<I> {

    public OptimalLStarAdapterMealy(Alphabet<I> alphabet, MembershipOracle<I, Word<O>> oracle) {
        super(alphabet, oracle);
    }

    @Override
    public Word<I> transformAccessSequence(Word<I> word) {
        final List<Word<O>> row = super.rowForState(word);
        final List<Word<I>> shortPrefixes = super.getShortPrefixes(row);

        assert shortPrefixes.size() == 1;

        return shortPrefixes.get(0);
    }
}
