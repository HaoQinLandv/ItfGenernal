package com.foxhis.itf.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class LogFrame {

	public JFrame frame;

    private JScrollPane logScrollPane;  
    private JTextArea logTextArea;  
    public LogReader rthread ;
    
	  /**
     * 主窗口大小
     */
    public final static int MAIN_WINDOW_X = 240;
    public final static int MAIN_WINDOW_Y = 100;
    public final static int MAIN_WINDOW_WIDTH = 885;
    public final static int MAIN_WINDOW_HEIGHT = 636;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				LogFrame logFrame = new LogFrame();
				logFrame.initLog();
				logFrame.frame.setVisible(true);
			}
		});

	}
	
	public LogFrame() {
		// TODO Auto-generated constructor stub
		initialze();
		//initLog();
	}
	
	public void initialze()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		frame = new JFrame();
		frame.setTitle("日志");
		frame.setBounds(MAIN_WINDOW_X, MAIN_WINDOW_Y, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);  
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("logo.png")));
		JPanel mainPanel = new JPanel(true);
		Dimension maxDim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension defaultDim = new Dimension(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
		mainPanel.setPreferredSize(defaultDim);
		mainPanel.setMaximumSize(maxDim);
		mainPanel.setMinimumSize(defaultDim);
		mainPanel.setLayout(new BorderLayout());
		
		logTextArea =new JTextArea();
		/*logTextArea.setPreferredSize(defaultDim);
		logTextArea.setMaximumSize(maxDim);
		logTextArea.setMinimumSize(defaultDim);*/
		
		logScrollPane = new JScrollPane();
		logScrollPane.setViewportView(logTextArea);
		logScrollPane.setPreferredSize(defaultDim);
		logScrollPane.setMaximumSize(maxDim);
		logScrollPane.setMinimumSize(defaultDim);
		
		mainPanel.add(logScrollPane, BorderLayout.CENTER);
		frame.add(mainPanel);
		frame.pack();
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				rthread.setFlg(false);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
	}
	
	public void initLog()
	{
		 File logFile = new File(System.getProperty("user.dir")+"\\logs\\foxerror.log");
		 rthread = new LogReader(logFile);
		 rthread.setFlg(true);
	     rthread.start();

	}
	
	class LogReader extends Thread{

		private File logFile = null;
		private volatile boolean flg;
		
	    public boolean isFlg() {
			return flg;
		}

		public void setFlg(boolean flg) {
			this.flg = flg;
		}

		private long lastTimeFileSize = 0; // 上次文件大小

	    public LogReader(File logFile) {
	        this.logFile = logFile;
	        //lastTimeFileSize = logFile.length();
	    }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (flg) {
				 RandomAccessFile randomFile=null;
	            try {
	                randomFile = new RandomAccessFile(logFile, "r");
	                if(lastTimeFileSize==randomFile.length())
	                	continue;
	                randomFile.seek(lastTimeFileSize);
	                String tmp = null;
	                
	                while ((tmp = randomFile.readLine()) != null) {
	                    logTextArea.append(new String(tmp.getBytes("iso-8859-1"), "utf-8"));
	                    logTextArea.append("\n");
	                }
	                lastTimeFileSize = randomFile.length();
	                Thread.sleep(2000);
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            finally {
	            	try {
						randomFile.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }

		}
	}

}
