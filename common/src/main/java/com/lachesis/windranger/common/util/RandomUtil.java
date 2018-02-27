package com.lachesis.windranger.common.util;

import java.util.Random;

public class RandomUtil {

	private static final Random rng = new Random();

	public static long nextLong(long n) {  


		// error checking and 2^x checking removed for simplicity.  
		long bits, val;  

		do {  
			bits = (rng.nextLong() << 1) >>> 1;  
			val = bits % n;  
		} while (bits-val+(n-1) < 0L);  
		return val;  
	}
}
