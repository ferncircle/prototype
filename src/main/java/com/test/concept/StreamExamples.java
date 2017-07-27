/**
 * 
 */
package com.test.concept;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream represents sequence of objects from a source and it doesn't actually store data. It forms pipeline of operations and it's not computed until some terminal
 * 	operation is invoked. It supports parallelism (stream.parallelstream()) to take advantage of multiple core cpu parallel operation. 
 * Operations in stream are divided into 2 categories and together they form pipeline:
 * 1) Intermediate: returns new stream and are lazy(not computed)
 * 		a) Stateful: may need to process entire input (distinct, sorted)
 * 		b) Stateless: (filter, map)
 * 2) Terminal: mostly produces result and pipeline is considered as consumed and can't be reused. e.g. foreach, sum, max, collect 
 *  *
 * e.g.
 * int sum = widgets.stream()
                      .filter(b -> b.getColor() == RED)  //returns stream, forming pipeline
                      .mapToInt(b -> b.getWeight()) //returns stream, forming pipeline
                      .sum();
                      
                      
   List<Integer> transactionsIds = 
    transactions.stream()
                .filter(t -> t.getType() == Transaction.GROCERY)
                .sorted(comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());
                      
                      
 */
public class StreamExamples {

	public void testVariousExamples(){
		//int[] a--> Integer[]
		Integer[] what = Arrays.stream(new int[]{2,4,2,54,12,4} ).boxed().toArray( Integer[]::new );
		System.out.println(Arrays.toString(what));
		//convert string to Character array
		Character[] ca="exampleString".chars().mapToObj(c->(char)c).toArray(Character[]::new);
		System.out.println(Arrays.toString(ca));
		
		StringBuffer buf=new StringBuffer();
		Arrays.stream(ca).forEach(c->buf.append(c));
		System.out.println(buf.toString());
	}
	public void testParallel(){
		//parallel
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		//get count of empty string
		long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println("count="+count);		
	}
	
	public void testSorted(){
		Random random=new Random();
		random.ints().limit(5).sorted().forEach(System.out::println);
	}
	
	public void arrayToStream(){
		String[] array = {"a", "b", "c", "d", "e"};
		
		Stream<String> stream=Arrays.stream(array);
		stream.forEach(x->System.out.print(x+" "));
		System.out.println();
		
		int[] intArray = {1, 2, 3, 4, 5};
		
		IntStream iStream=Arrays.stream(intArray);
		iStream.forEach(x->System.out.print(x+" "));
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		StreamExamples ex=new StreamExamples();
		ex.testParallel();
		ex.testSorted();
		ex.arrayToStream();
		ex.testVariousExamples();
		
	}

}
