package oy.interact.tira.factories;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.model.Coder;
import oy.interact.tira.student.QueueImplementation;
import oy.interact.tira.util.QueueInterface;

public class ArrayQueueFactory {

	private ArrayQueueFactory() {
		// empty
	}

	public static QueueInterface<Integer> createIntegerQueue() {
		return new QueueImplementation<Integer>();
	}

	public static QueueInterface<Integer> createIntegerQueue(int capacity) {
		return new QueueImplementation<Integer>(capacity);
	}

	public static QueueInterface<String> createStringQueue() {
		return new QueueImplementation<String>();
	}

	public static QueueInterface<String> createStringQueue(int capacity) {
		return new QueueImplementation<String>(capacity);
	}

	public static QueueInterface<Coder> createCoderQueue() {
		return new QueueImplementation<Coder>();
	}
}
