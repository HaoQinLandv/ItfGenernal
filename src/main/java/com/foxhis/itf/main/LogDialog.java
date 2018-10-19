package com.foxhis.itf.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class LogDialog extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    private JScrollPane logScrollPane;  
    private JTextArea logTextArea;  
    
	public LogDialog() {
		
		// TODO Auto-generated constructor stub
		initComponents();
	}
	private void initComponents() {
	    this.setTitle("日志窗口");
        logScrollPane = new JScrollPane();  
        logTextArea = new JTextArea();  
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);  
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //logTextArea.setColumns(20);  
        //logTextArea.setRows(5);  
        logScrollPane.setViewportView(logTextArea);  
  
        GroupLayout layout = new GroupLayout(getContentPane());  
        this.getContentPane().setLayout(layout);  
        layout.setHorizontalGroup(  
                layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()).addComponent(logScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,(int)dim.getWidth()/2+200, Short.MAX_VALUE));  
        layout.setVerticalGroup(  
                layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()).addComponent(logScrollPane, GroupLayout.DEFAULT_SIZE, (int)dim.getHeight()/2, Short.MAX_VALUE));  
  
        pack();  
      
    }  
	
	
	public void initLog()
	{
		 File logFile = new File(System.getProperty("user.dir")+"\\logs\\foxerror.log");
		 Thread rthread = new Thread(new LogReader(logFile));
	     rthread.start();

	}
	public static void main(String[] args) {
		LogDialog logDemoFrame = new LogDialog();  
	    logDemoFrame.initLog();  
	    logDemoFrame.setVisible(true);  
 
	}
	
	class LogReader implements Runnable{

		private File logFile = null;
	    private long lastTimeFileSize = 0; // 上次文件大小

	    public LogReader(File logFile) {
	        this.logFile = logFile;
	        //lastTimeFileSize = logFile.length();
	    }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				 RandomAccessFile randomFile=null;
	            try {
	                randomFile = new RandomAccessFile(logFile, "r");
	                randomFile.seek(lastTimeFileSize);
	                String tmp = null;
	                
	                while ((tmp = randomFile.readLine()) != null) {
	                    logTextArea.append(new String(tmp.getBytes("iso-8859-1"), "gbk"));
	                    logTextArea.append("\n");
	                }
	                lastTimeFileSize = randomFile.length();
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
	            try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }

		}
		
	}

}
