package com.test;

public class MySet<T> {

	private int size;

	private Entry[] items=new Entry[10];	

	private class Entry<T>{
		T data;
		Entry<T> next; 		

		public Entry(T data) {
			super();
			this.data = data;
		}
	}

	private int getBucketIndex(T data){
		int hashCode=Math.abs(data.hashCode());

		return hashCode%items.length;
	}
	
	public int size(){
		return size;
	}
	
	public void add(T data){
		if(data!=null){
			int index=getBucketIndex(data);
			if(!contains(data)){
				Entry<T> entry=new Entry<T>(data);
				entry.next=items[index];
				items[index]=entry;
				size++;
			}
				
		}
	}

	public void remove(T data){
		if(data!=null){
			boolean itemRemoved=false;
			int index=getBucketIndex(data);
			Entry<T> prev=items[index];
			Entry<T> currentItem=items[index];
			if(currentItem.data.equals(data)){
				items[index]=currentItem.next;
				itemRemoved=true;
				
			}else{
				while(currentItem!=null){
					if(currentItem.data.equals(data)){
						prev.next=currentItem.next;
						itemRemoved=true;
						break;
					}
					prev=currentItem;
					currentItem=currentItem.next;
				}
			}
			if(itemRemoved)
				size--;
			
		}
	}

	public boolean contains(T data){
		boolean contains=false;
		if(data!=null){
			int index=getBucketIndex(data);
			Entry<T> currentItem=items[index];
			while(currentItem!=null){
				if(currentItem.data.equals(data)){
					contains=true;
					break;
				}
				currentItem=currentItem.next;
			}
		}
		return contains;
	}

}
