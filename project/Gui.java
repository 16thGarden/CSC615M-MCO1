import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame{
	private JLabel header = new JLabel("Text Cleaner", SwingConstants.CENTER);
	private JLabel inputLabel = new JLabel("Input file name: ");
	private JLabel outputLabel = new JLabel("Output file name: ");
	private JTextField inputTextField = new JTextField("raw_600Kwords");
	private JTextField outputTextField = new JTextField("output_600Kwords");

	private JLabel configHeader = new JLabel("Include the following:", SwingConstants.CENTER);
	
	private JCheckBox configWatchdog = new JCheckBox("Watchdog announcements");
	private JCheckBox configProfile = new JCheckBox("Profile information");
	private JCheckBox configServerSwitch = new JCheckBox("Server switching");
	private JCheckBox configServerRestart = new JCheckBox("Server restarts");
	private JCheckBox configMessageDividers = new JCheckBox("Message dividers");
	private JCheckBox configAuction = new JCheckBox("Auction messages");
	private JCheckBox configBazaar = new JCheckBox("Bazaar messages");
	private JCheckBox configSkyHanni = new JCheckBox("SkyHanni mod messages");
	private JCheckBox configNEU = new JCheckBox("NEU mod messages");
	private JCheckBox configParty = new JCheckBox("Party chat messages");
	private JCheckBox configGuild = new JCheckBox("Guild chat messages");
	private JCheckBox configCoop = new JCheckBox("Co-op chat messages");

	private JCheckBox filterMode = new JCheckBox("Filter chat mode");
	private JRadioButton publicChat = new JRadioButton("Public Chat");
	private JRadioButton partyChat = new JRadioButton("Party Chat");
	private JRadioButton guildChat = new JRadioButton("Guild Chat");
	private JRadioButton coopChat = new JRadioButton("Co-op Chat");
	private ButtonGroup filterModeGroup = new ButtonGroup();
	private JCheckBox csvMode = new JCheckBox("Save as CSV");

	private JButton clean = new JButton("Clean text");
	private JButton save = new JButton("Save to file");
	private JLabel status = new JLabel("Waiting for action...", SwingConstants.CENTER);
	
	public Gui() {
		super("CSC615M Text Cleaning");
		JPanel panel = new JPanel(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);

		header.setBounds(50, 50, 700, 30);
		inputLabel.setBounds(50, 100, 170, 30);
		outputLabel.setBounds(50, 150, 170, 30);
		inputTextField.setBounds(200, 100, 550, 30);
		outputTextField.setBounds(200, 150, 550, 30);
		Font font = new Font("Consolas", Font.PLAIN, 18);
		inputTextField.setFont(font);
		outputTextField.setFont(font);

		configHeader.setBounds(50, 200, 700, 30);
		
		configWatchdog.setBounds(50, 250, 200, 30);
		configProfile.setBounds(300, 250, 200, 30);
		configServerSwitch.setBounds(550, 250, 200, 30);
		configServerRestart.setBounds(50, 275, 200, 30);
		configMessageDividers.setBounds(300, 275, 200, 30);
		configAuction.setBounds(550, 275, 200, 30);
		configBazaar.setBounds(50, 300, 200, 30);
		configSkyHanni.setBounds(300, 300, 200, 30);
		configNEU.setBounds(550, 300, 200, 30);
		configParty.setBounds(50, 325, 200, 30);
		configGuild.setBounds(300, 325, 200, 30);
		configCoop.setBounds(550, 325, 200, 30);
		boolean[] defaultConfig = {
			false,
			true,
			true,
			false,
			false,
			true,
			true,
			false,
			false,
			true,
			true,
			true,
		};
		configWatchdog.setSelected(defaultConfig[0]);
		configProfile.setSelected(defaultConfig[1]);
		configServerSwitch.setSelected(defaultConfig[2]);
		configServerRestart.setSelected(defaultConfig[3]);
		configMessageDividers.setSelected(defaultConfig[4]);
		configAuction.setSelected(defaultConfig[5]);
		configBazaar.setSelected(defaultConfig[6]);
		configSkyHanni.setSelected(defaultConfig[7]);
		configNEU.setSelected(defaultConfig[8]);
		configParty.setSelected(defaultConfig[9]);
		configGuild.setSelected(defaultConfig[10]);
		configCoop.setSelected(defaultConfig[11]);
		
		filterMode.setBounds(325, 400, 150, 30);
		publicChat.setBounds(200, 450, 100, 30);
		partyChat.setBounds(300, 450, 100, 30);
		guildChat.setBounds(400, 450, 100, 30);
		coopChat.setBounds(500, 450, 100, 30);
		csvMode.setBounds(325, 475, 150, 30);

		filterModeGroup.add(publicChat);
		filterModeGroup.add(partyChat);
		filterModeGroup.add(guildChat);
		filterModeGroup.add(coopChat);
		setFilterModeEnabled(false);

		clean.setBounds(100, 650, 250, 30);
		save.setBounds(450, 650, 250, 30);
		setEnabledSaveButton(false);
		
		status.setBounds(50, 600, 700, 30);

		panel.add(header);
		panel.add(inputLabel);
		panel.add(outputLabel);
		panel.add(inputTextField);
		panel.add(outputTextField);

		panel.add(configHeader);
		
		panel.add(configWatchdog);
		panel.add(configProfile);
		panel.add(configServerSwitch);
		panel.add(configServerRestart);
		panel.add(configMessageDividers);
		panel.add(configAuction);
		panel.add(configBazaar);
		panel.add(configSkyHanni);
		panel.add(configNEU);
		panel.add(configParty);
		panel.add(configGuild);
		panel.add(configCoop);
		
		panel.add(filterMode);
		panel.add(publicChat);
		panel.add(partyChat);
		panel.add(guildChat);
		panel.add(coopChat);
		panel.add(csvMode);
		
		panel.add(clean);
		panel.add(save);
		panel.add(status);
		
		add(panel);
		setVisible(true);
		
		addFilterModeListener(new FilterModeListener());
	}

	public String getInputTextField() {
		return inputTextField.getText();
	};

	public String getOutputTextField() {
		return outputTextField.getText();
	};
	
	public void setConfigEnabled(boolean enable) {
		configWatchdog.setEnabled(enable);
		configProfile.setEnabled(enable);
		configServerSwitch.setEnabled(enable);
		configServerRestart.setEnabled(enable);
		configMessageDividers.setEnabled(enable);
		configAuction.setEnabled(enable);
		configBazaar.setEnabled(enable);
		configSkyHanni.setEnabled(enable);
		configNEU.setEnabled(enable);
		configParty.setEnabled(enable);
		configGuild.setEnabled(enable);
		configCoop.setEnabled(enable);
	}
	
	public boolean[] getConfig() {
		boolean[] config = {
			configWatchdog.isSelected(),
			configProfile.isSelected(),
			configServerSwitch.isSelected(),
			configServerRestart.isSelected(),
			configMessageDividers.isSelected(),
			configAuction.isSelected(),
			configBazaar.isSelected(),
			configSkyHanni.isSelected(),
			configNEU.isSelected(),
			configParty.isSelected(),
			configGuild.isSelected(),
			configCoop.isSelected()
		};
		
		return config;
	}
	
	public boolean getFilterModeEnabled() {
		return filterMode.isSelected();
	}
	
	public void setFilterModeEnabled(boolean enable) {
		publicChat.setEnabled(enable);
		partyChat.setEnabled(enable);
		guildChat.setEnabled(enable);
		coopChat.setEnabled(enable);
		csvMode.setEnabled(enable);
	}
	
	public int getFilterConfig() {
		if (publicChat.isSelected()) {
			return 0;
		} else if (partyChat.isSelected()) {
			return 1;
		} else if (guildChat.isSelected()) {
			return 2;
		} else if (coopChat.isSelected()) {
			return 3;
		}
		
		return -1;
	}
	
	public boolean getCsvModeEnabled() {
		return csvMode.isSelected();
	}
	
	public void setEnabledCleanButton(boolean enable) {
		clean.setEnabled(enable);
	}
	
	public void setEnabledSaveButton(boolean enable) {
		save.setEnabled(enable);
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void setStatus(String s) {
		status.setText(s);
	}

	public void addCleanListener(ActionListener listenForCleanButton) {
		clean.addActionListener(listenForCleanButton);
	}

	public void addSaveListener(ActionListener listenForSaveButton) {
		save.addActionListener(listenForSaveButton);
	}
	
	public void addFilterModeListener(ActionListener listenForFilterMode) {
		filterMode.addActionListener(listenForFilterMode);
	}
	
	class FilterModeListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {			
			setConfigEnabled(!filterMode.isSelected());
			setFilterModeEnabled(filterMode.isSelected());
		}
	}
}