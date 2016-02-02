/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.marchmadness.batch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.spring.marchmadness.domain.Bracket;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author Michael Minella
 */
public class DeDupingItemProcessor implements ItemProcessor<Bracket, Bracket> {

	private Map<Integer, Object> hashes = new ConcurrentHashMap<>();

	@Override
	public Bracket process(Bracket item) throws Exception {
		int curHash = item.hashCode();

		if(hashes.get(curHash) != null) {
			return null;
		}
		else {
			hashes.put(curHash, new Object());
			return item;
		}
	}
}
