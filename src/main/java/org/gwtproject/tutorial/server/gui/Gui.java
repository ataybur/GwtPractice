package org.gwtproject.tutorial.server.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.gwtproject.tutorial.server.main.AppServiceImpl;

public class Gui extends JFrame {

	private static final long serialVersionUID = 5422603205441353041L;

	private AppServiceImpl app;

	public Gui() {
		super("Survival Game");
		app = new AppServiceImpl();
		setSize(900, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton inputButton = new JButton("INPUT");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				int option = chooser.showOpenDialog(inputButton);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
//					app.load(file);
				}
			}
		});

		JButton outputButton = new JButton("OUTPUT");
		outputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				int option = chooser.showOpenDialog(inputButton);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
//					app.write(file);
				}
			}
		});

		JButton runButton = new JButton("RUN");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				app.run();
			}
		});
		c.add(inputButton);
		c.add(runButton);
		c.add(outputButton);
	}

}
