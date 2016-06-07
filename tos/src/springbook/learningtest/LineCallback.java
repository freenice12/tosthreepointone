package springbook.learningtest;

public interface LineCallback<T> {
	public T doSomethingWithLine(String line, T value);
}
