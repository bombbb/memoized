package net.sourcedestination.util;

import static org.junit.Assert.*;

import java.util.function.Function;

import net.sourcedestination.util.MemoizedFunction;

import org.junit.Test;

public class MemoizedFunctionTest {


	@Test
	public void testMemoize() {
		Function<Integer,Integer> f = new Function<Integer,Integer>() {

			public Integer apply(Integer x) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return x;
			}
			
		};
		
		MemoizedFunction<Integer, Integer> mf = MemoizedFunction.memoize(f);
		long time = System.currentTimeMillis();
		assertEquals((Integer)5, mf.apply(5));
		assertTrue(System.currentTimeMillis() >= time + 100);
		time = System.currentTimeMillis();
		assertEquals(mf.apply(5), (Integer)5);
		assertTrue(System.currentTimeMillis() < time + 100);
		time = System.currentTimeMillis();
		assertEquals(mf.apply(10), (Integer)10);
		assertTrue(System.currentTimeMillis() >= time + 100);
		time = System.currentTimeMillis();
		assertEquals(mf.apply(10), (Integer)10);
		assertTrue(System.currentTimeMillis() < time + 100);
	}

	
}
