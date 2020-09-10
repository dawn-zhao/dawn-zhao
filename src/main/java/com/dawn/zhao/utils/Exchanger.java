package com.dawn.zhao.utils;

public interface Exchanger {

	<E> void pull(String begin, String end, Class<E> entityType, String entityDir) throws Exception;

}
