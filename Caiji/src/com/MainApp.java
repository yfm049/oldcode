package com;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class MainApp {

	private JFrame frame;

	private JTextArea textArea;
	private static boolean isstart=false;
	private JComboBox comboBox;
	private CaijiMain cm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainApp window = new MainApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainApp.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		frame.setTitle("\u6570\u636E\u91C7\u96C6\u7A0B\u5E8F");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		final JButton button = new JButton("\u5F00\u59CB\u91C7\u96C6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isstart){
					caijithread cj=new caijithread();
					cj.start();
					isstart=true;
					button.setEnabled(false);
				}
				
			}
		});
		button.setBounds(339, 10, 93, 23);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 422, 213);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 319, 21);
		frame.getContentPane().add(comboBox);
		cm=new CaijiMain(textArea,comboBox);
		new GetUrlthread().start();
		
	}
	class GetUrlthread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			cm.GetUrl();
		}
		
	}
	class caijithread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			cm.GetPageurl();
		}
		
	}
}
