/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.worksap.global;

/**
 *
 * @author ming
 */
public class ImmutableQueueTest {

    public static void main(String[] args)
    {

	ImmutableQueue<Integer> dataInteger = new ImmutableQueue<>();
	ImmutableQueue<Integer> dataInteger2 = new ImmutableQueue<>();
	ImmutableQueue<Integer> dataInteger3 = new ImmutableQueue<>();
//	ImmutableQueue<Double> dataDouble = new ImmutableQueue<>();
	int num = 0;
	int max = 10;
	long current = System.currentTimeMillis();

	while (++num <= max)
	{
	    int value = (int) (Math.random() * 100);
	    System.out.print(value + " ");
	    dataInteger = dataInteger.enqueue(value);
	}
	
	System.out.println("");
	System.out.println("Time for enqueue " + max + " randome integer "
			   + (System.currentTimeMillis() - current) + " miliseconds");

	
//	while (--num > 0)
//	{
//	    System.out.print(dataInteger.peek() + " ");
//	    dataInteger = dataInteger.dequeue();
//	}
//
//	System.out.println("Time for dequeue " + max + " randome integer "
//			   + (System.currentTimeMillis() - current) + " miliseconds");
	
//	System.out.println("");
//	System.out.println("size: " + dataInteger.size());
//	while (dataInteger.size() > 0) {
//	    System.out.print(dataInteger.peek() +" ");
//	    dataInteger = dataInteger.dequeue();
//	}
	
	System.out.println("");
	dataInteger2 = dataInteger.enqueue(54);
	while (dataInteger2.size() > 0) {
	    System.out.print(dataInteger2.peek() +" ");
	    dataInteger2 = dataInteger2.dequeue();
	}

	System.out.println("");
	dataInteger3 = dataInteger.dequeue();
	while (dataInteger3.size() > 0) {
	    System.out.print(dataInteger3.peek() +" ");
	    dataInteger3 = dataInteger3.dequeue();
	}
    }

}