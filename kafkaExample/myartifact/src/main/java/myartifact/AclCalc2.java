package myartifact;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AclCalc2 {
	
	private static ThreadPoolExecutor executor = null;
	
	public void threadsTest() {
		executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(8);
		for (int i=0;i<30;i++) {
			AclCalc aclCalc =new AclCalc(""+i);
			executor.execute(aclCalc);
		}
	}
	
	public  class AclCalc implements Runnable{
		String routerName = null;
		public AclCalc(String routerName) {
			if (this.routerName!=null) {
				System.out.println("ERRRRRROR " + this.routerName);
			}
			this.routerName = routerName;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			System.out.println("DO " + routerName);
		}
	}	
	
}	