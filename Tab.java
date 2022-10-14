package Swing;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Tab extends JFrame implements ActionListener {
	JTabbedPane tab = new JTabbedPane();
	JPanel panel1, panel2, panel3;
	Container c;
	Container c1, c2;
	JLabel fname, lname, regno, email, mobile, gender, address;
	JLabel s, s1, s2, s3;
	JLabel username, pwd;
	JTextField t1, t2, t3, t4, t5;
	JTextField text1, text2, tsearch;
	JRadioButton r1, r2;
	JButton login, submit, searchb;
	JTextArea area, ta;
	JLabel search;
	ButtonGroup bg;
	String reg_no, file_name;

	public Tab() {
		// Creating Panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		// panel1
		panel1.setLayout(new GridLayout(10, 2));

		fname = new JLabel("First Name :");
		lname = new JLabel("Last Name:");
		regno = new JLabel("Registration Number :");
		email = new JLabel("Email :");
		mobile = new JLabel("Mobile Number :");
		gender = new JLabel("Gender :");
		address = new JLabel("Address :");
		// labels for spacing purpose
		s = new JLabel();
		s1 = new JLabel();
		s2 = new JLabel();
		s3 = new JLabel();

		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);
		t4 = new JTextField(20);
		t5 = new JTextField(20);

		r1 = new JRadioButton("Male");
		r2 = new JRadioButton("Female");
		area = new JTextArea();

		submit = new JButton("Submit");
		submit.setBounds(100, 110, 90, 25);
		// button grouping
		bg = new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		// adding components to panel1
		panel1.add(fname);
		panel1.add(t1);
		panel1.add(lname);
		panel1.add(t2);
		panel1.add(regno);
		panel1.add(t3);
		panel1.add(email);
		panel1.add(t4);
		panel1.add(mobile);
		panel1.add(t5);
		panel1.add(gender);
		panel1.add(r1);
		panel1.add(s);
		panel1.add(r2);
		panel1.add(address);
		panel1.add(area);
		panel1.add(s1);
		panel1.add(s2);
		panel1.add(s3);
		panel1.add(submit);

		// panel2
		panel2.setLayout(null);

		username = new JLabel("User Name :");
		username.setBounds(100, 8, 70, 20);

		text1 = new JTextField();
		text1.setBounds(100, 28, 193, 28);

		pwd = new JLabel("Password :");
		pwd.setBounds(100, 55, 70, 20);

		text2 = new JTextField();
		text2.setBounds(100, 75, 193, 28);

		login = new JButton("Login");
		login.setBounds(100, 110, 90, 25);
		// adding components to panel2
		panel2.add(username);
		panel2.add(text1);
		panel2.add(pwd);
		panel2.add(text2);
		panel2.add(login);

		// panel3
		panel3.setLayout(null);
		search = new JLabel("Enter The Name Of The Student You Want To Search");
		search.setBounds(90, 20, 500, 40);

		tsearch = new JTextField(20);
		tsearch.setBounds(140, 80, 200, 30);
		searchb = new JButton("Search");
		searchb.setBounds(180, 150, 100, 30);
		// adding components to panel3
		panel3.add(search);
		panel3.add(tsearch);
		panel3.add(searchb);
		// container to add tab
		c = getContentPane();
		c.setLayout(new GridLayout(2, 2));
		// adding panels to tab
		tab.addTab("Registration", panel1);
		tab.addTab("LOGIN", panel2);
		tab.addTab("SEARCH", panel3);
		c.add(tab);
		// text area(ta) to display messages
		ta = new JTextArea();
		c.add(ta);
		ta.setEditable(false);
		// ActionListener
		submit.addActionListener(this);
		login.addActionListener(this);
		searchb.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Registration
		if (e.getSource() == submit) {

			String data1, data2, data3, data4;

			data1 = "NAME : " + t1.getText() + " " + t2.getText() + "\n" + "REGISTRATION NUMBER : " + t3.getText()
					+ "\n" + "EMAIL : " + t4.getText() + "\n" + "MOBILE : " + t5.getText() + "\n";
			if (r1.isSelected()) {
				data2 = "GENDER : Male";
			} else {
				data2 = "GENDER : Female";
			}

			data3 = "\n" + "ADDRESS :" + area.getText();

			reg_no = t3.getText().toUpperCase();
			file_name = "D:\\student_info\\" + reg_no + ".txt";

			if (t1.getText().equals("") || t3.getText().equals("") || t4.getText().equals("")
					|| t5.getText().equals("")) {
				ta.setText("\n\n\tAll the above fields are mandatory please fill them!!");
			} else {
				Random random = new Random();
				int p = random.nextInt(1000000);
				String password = "@" + p;
				data4 = "\nPASSWORD : " + password;

				ta.setText("\n\n\t\tRegistartion Successfull!" + "\n\t\tPassword is : " + password);
				try {
					File file = new File(file_name);
					if (file.createNewFile()) {
						FileWriter writer = new FileWriter(file);
						writer.write(data1 + data2 + data3 + data4);
						writer.close();
					} else {
						ta.setText("\n\n\t\tUser already exists!");
					}
				} catch (IOException e1) {
					ta.setText("Something went wrong Try again");
				}
			}
			// validation
			if (e.getSource() == login) {
				String user_name, line, file_check, pass = null;
				String pass_check;
				String arr[] = new String[5];
				user_name = text1.getText().toUpperCase();
				pass_check = text2.getText();
				file_check = "D:\\student_info\\" + user_name + ".txt";

				try {
					BufferedReader br = new BufferedReader(new FileReader(file_check));
					while ((line = br.readLine()) != null) {
						pass = line;
					}
					arr = pass.split(" : ");

					if (pass_check.equals(arr[1])) {
						ta.setText("\n\n\t\tLogin Successfull!!");
					} else {
						ta.setText("\n\n\t\tInvalid password!");
					}
				} catch (FileNotFoundException e1) {
					ta.setText("\n\n\t\tEnter the valid Username!");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			// Searching
			if (e.getSource() == searchb) {
				String search_name = tsearch.getText();
				String name1 = null, start_line = null;
				String names = null;

				String path = "D:\\student_info\\";
				File directory = new File(path);
				File filesList[] = directory.listFiles();
				String arr_name[] = new String[5];

				for (File f : filesList) {
					if (f.isFile()) {
						name1 = f.getName();
						String fn = path + name1;
						try {
							BufferedReader bs = new BufferedReader(new FileReader(fn));
							start_line = bs.readLine();
							arr_name = start_line.split(" : ");
							names = arr_name[1];
							String data = arr_name[1], ln;
							if (names.equalsIgnoreCase(search_name)) {
								while ((ln = bs.readLine()) != null) {
									data += "\n" + ln;
								}
								ta.setText(data);
							}

						} catch (FileNotFoundException e1) {
							ta.setText("Enter the valid Student name");
						} catch (IOException e1) {
							ta.setText("Error!");
							;
						}
					}
				}
			}
		}
	}

}
