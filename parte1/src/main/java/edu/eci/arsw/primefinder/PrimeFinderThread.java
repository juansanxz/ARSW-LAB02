package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	Object lock;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public PrimeFinderThread(int a, int b, Object lock) {
		super();
		this.a = a;
		this.b = b;
		this.lock = lock;
	}

	public void run(){
		long start = System.currentTimeMillis();
		long current;
		for (int i=a;i<=b;i++){
			current = System.currentTimeMillis();

			if (current - start >= 5000){
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			} else if (isPrime(i)){
				primes.add(i);
				System.out.println(i);
			}
		}
		
		
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	
	
}
