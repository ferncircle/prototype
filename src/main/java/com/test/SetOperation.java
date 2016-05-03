package com.test;

import java.util.HashSet;
import java.util.Set;

public class SetOperation {

	public static void main(String[] args) {
		Set<Long> requestedSubscriptionSet=new HashSet<Long>();
		Set<Long> existingSubscriptionSet=new HashSet<Long>();
		
		
		requestedSubscriptionSet.add(1L);
		requestedSubscriptionSet.add(7L);
		requestedSubscriptionSet.add(5L);
		
		
		existingSubscriptionSet.add(2L);
		existingSubscriptionSet.add(8L);
		existingSubscriptionSet.add(5L);

		Set<Long> originalRequested=new HashSet<Long>(requestedSubscriptionSet);
		requestedSubscriptionSet.retainAll(existingSubscriptionSet); //intersection
		
		originalRequested.removeAll(requestedSubscriptionSet); //remove common
		existingSubscriptionSet.removeAll(requestedSubscriptionSet);
		
		System.out.println("subscribe="+originalRequested);
		System.out.println("unsubscribe="+existingSubscriptionSet);

	}

}
