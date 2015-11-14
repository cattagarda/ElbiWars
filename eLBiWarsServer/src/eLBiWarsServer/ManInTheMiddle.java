package eLBiWarsServer;

import java.awt.EventQueue;

import org.jfree.chart.demo.Graph;
import org.jfree.ui.RefineryUtilities;

public class ManInTheMiddle {

	public static void main (String[] args){
		Thread starter = new Thread(new ServerStart(1984));
        starter.start();
        
        
        System.out.println("Harooo~\n");
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frmServer.setVisible(true);
					
					Graph demo = new Graph("Data packets recieved from client");
			        demo.pack();
			        RefineryUtilities.centerFrameOnScreen(demo);
			        demo.setVisible(true);

			        
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	
}

