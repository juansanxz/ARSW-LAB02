package edu.eci.arsw.primefinder;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Object lock = 24;
		//PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);

		for(int i=0; i<3; i++){
			PrimeFinderThread pft=new PrimeFinderThread(i*10000000, i*10000000 + 10000000, lock);
			pft.start();
		}

		boolean running = true;

		while (running){
			//running = false;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			Scanner scanner = new Scanner(System.in);
			System.out.println("Para seguir buscando los números primos en el intervalo dado, presione enter.");
			String letter = scanner.nextLine();
			if (!letter.equals("")) {
				running = false;

			} else {
				synchronized (lock) {
					lock.notifyAll();
				}
			}
		}
		System.out.println("Pacccccccccccccccccccccccr.");
		//pft.start();
	}
	
}
