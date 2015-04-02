package zhuceji;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class zcj {

	private JFrame frame;
	private JTextField zcm;
	private JTextField xlh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					zcj window = new zcj();
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
	public zcj() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6CE8\u518C\u673A");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6CE8\u518C\u7801\uFF1A");
		label.setBounds(10, 30, 54, 15);
		frame.getContentPane().add(label);
		
		zcm = new JTextField();
		zcm.setBounds(54, 27, 282, 21);
		frame.getContentPane().add(zcm);
		zcm.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5E8F\u5217\u53F7\uFF1A");
		label_1.setBounds(10, 55, 54, 15);
		frame.getContentPane().add(label_1);
		
		xlh = new JTextField();
		xlh.setBounds(54, 52, 282, 21);
		frame.getContentPane().add(xlh);
		xlh.setColumns(10);
		
		JButton btnNewButton = new JButton("\u751F\u6210");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String zm=zcm.getText();
				String zc=SimpleCrypto.GetMD5Code(zm);
				System.out.println(zc+"---------"+zm);
				if(zc.length()>=32){
					xlh.setText(zc.substring(27,32));
				}
				
				
			}
		});
		btnNewButton.setBounds(341, 26, 93, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
