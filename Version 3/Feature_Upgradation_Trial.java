//import java.awt.EventQueue;
//import java.awt.Font;
//import java.io.IOException;
//import java.util.Timer;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//
//public class Feature_Upgradation_Trial extends JFrame {
//
//	private JPanel contentPane;
//
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Feature_Upgradation_Trial frame = new Feature_Upgradation_Trial();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	public Feature_Upgradation_Trial() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1067, 617);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setBounds(297, 169, 475, 225);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		
//		JLabel lblNewLabel = new JLabel("Welcome!!!");
//		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setBounds(0, 0, 475, 225);
//		panel.add(lblNewLabel);
//		Timer timer = new Timer();
//	    timer.setRepeats(false);
//	    timer.start();
//
//        lblNewLabel.setVisible(false);
//        JLabel lblNewLabel1 = new JLabel("Master");
//		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 40));
//		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel1.setBounds(0, 0, 475, 225);
//		panel.add(lblNewLabel1);
//		try {
//			Thread.sleep(5000);		
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
////        lblNewLabel1.setVisible(false);
//        
//	}
//}


/** 
 * Uses a SwingWorker to perform a time-consuming (and utterly fake) task.
 * LongTask.java is used by: SwingTimerDemo.java
 */
//package com.jcg;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingWorker;

public class Feature_Upgradation_Trial {
	
	private static final int TASK_LENGTH = 1000;
	private AtomicBoolean isStarted =  new AtomicBoolean(false);
	private AtomicBoolean isRunning = new AtomicBoolean(false);
	private AtomicBoolean isDone = new AtomicBoolean(false);
	private int lengthOfTask;
	private int current = 0;	
	private String statMessage;

	public Feature_Upgradation_Trial() {
		// Compute length of task...
		// In a real program, this would figure out
		// the number of bytes to read or whatever.
		lengthOfTask = TASK_LENGTH;
	}

	/**
	 * Called from SwingTimerDemo to start the task.
	 */
	public void go() {
		isRunning.set(true);
		if (!isStarted.get()) {
			isDone.set(false);
			isStarted.set(true);
			statMessage = null;
			current = 0;
			final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					// Fake a long task, making a random amount of progress every second.
					while (!isDone.get()) {
						if (isRunning.get()) {
							try {
								Thread.sleep(1000); // sleep for a second
								current += Math.random() * 100; // make some progress
								if (current >= lengthOfTask) {
									onDown();
									current = lengthOfTask;
								}
								statMessage = "Completed " + current + " out of " + lengthOfTask + ".";
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					return null;
				}
			};
			worker.execute();
		}
	}

	public void pause() {
		this.isRunning.set(false);
	}

	/**
	 * Called from SwingTimerDemo to find out how much work needs to be done.
	 */
	public int getLengthOfTask() {
		return lengthOfTask;
	}

	/**
	 * Called from SwingTimerDemo to find out how much has been done.
	 */
	public int getCurrent() {
		return current;
	}

	public void onDown() {
		isDone.set(true);
		isStarted.set(false);
		isRunning.set(false);
		statMessage = null;
	}

	/**
	 * Called from SwingTimerDemo to find out if the task has completed.
	 */
	public boolean isDone() {
		return isDone.get();
	}

	/**
	 * Returns the most recent status message, or null if there is no current
	 * status message.
	 */
	public String getMessage() {
		return statMessage;
	}

}
