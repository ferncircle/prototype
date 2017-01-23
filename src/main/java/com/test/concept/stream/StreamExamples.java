/**
 * 
 */
package com.test.concept.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Stream represents sequence of objects from a source, supports functional style operations such map-reduce transformations. 
 * Operations are divided into 2 categories and together they form pipeline:
 * 1) Intermediate: returns new stream and are lazy
 * 		a) Stateful: may need to process entire input (distinct, sorted)
 * 		b) Stateless: (filter, map)
 * 2) Terminal: mostly produces result and pipeline is considered as consumed and can't be reused. e.g. foreach, sum, max *  
 * 
   Features:
   1) No storage: It's not a data structure but depends on base data source such as collection, array, I/O channel
   2) Functional in nature, produces intermediate result but doesn't modify source
   3) Supports parallelism 
 *
 * e.g.
 * int sum = widgets.stream()
                      .filter(b -> b.getColor() == RED)
                      .mapToInt(b -> b.getWeight())
                      .sum();
                      
 */
public class StreamExamples {

	public void testParallel(){
		//parallel
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		//get count of empty string
		long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println("count="+count);		
	}
	
	public void testSorted(){
		Random random=new Random();
		random.ints().limit(20).sorted().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		StreamExamples ex=new StreamExamples();
		ex.testParallel();
		ex.testSorted();
	}

}
