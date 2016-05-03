package com.test;

public class ArrayElementsOccurence {

	public static void main(String[] args) {
		int[] A={5,4,2,4,3,3};

		for(int i=0;i<A.length;i++){
			//A[i]=A[i]+(i%A.length)*A.length;
			A[A[i]]=A[A[i]]+(A[i]%A.length)*A.length;
		}
		for(int i=0;i<A.length;i++){
			System.out.print(A[i]+" ");
		}
	}

}
