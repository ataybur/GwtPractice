package org.gwtproject.tutorial.server.lambda;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.gwtproject.tutorial.server.pojo.Context;
import org.gwtproject.tutorial.server.utils.ContextHelper;
import org.gwtproject.tutorial.server.utils.LineInfo;

public class LineInfoCollector implements Collector<LineInfo, ContextHelper, Optional<Context>> {

	@Override
	public BiConsumer<ContextHelper, LineInfo> accumulator() {
		return (ch, li) -> ch.applyLineInfo(li);
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.emptySet();
	}

	@Override
	public BinaryOperator<ContextHelper> combiner() {
		return null;
	}

	@Override
	public Function<ContextHelper, Optional<Context>> finisher() {
		return ContextHelper::toInstance;
	}

	@Override
	public Supplier<ContextHelper> supplier() {
		return ContextHelper::new;
	}

}
