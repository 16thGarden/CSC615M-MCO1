import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class Manager {
    private Gui gui;
    private Cleaner cleaner;
	
    public Manager(Gui gui, Cleaner cleaner) {
        this.gui = gui;
        this.cleaner = cleaner;
    
        this.gui.addCleanListener(new CleanListener());
		this.gui.addSaveListener(new SaveListener());
    }
	
	private boolean readFile(String fileName) {
		ArrayList<String> text = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"))) {
            String line;
			int x = 0;
            while ((line = br.readLine()) != null) {
				text.add(line);
				x++;
            }
			
			cleaner.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
			return false;
        }
		
		return true;
	}
	
	private boolean saveFile(String fileName) {
		String output = "";
		
		ArrayList<String> text = cleaner.getText();
		for (int i = 0; i < text.size(); i++) {
			if (!Pattern.matches("^\\s*$", text.get(i))) {
				output += text.get(i) + "\n";
			}
		}
		
		/*
		output = output.replaceAll(" *(\r?\n)+ *", "\n"); // empty lines
		output = output.replaceAll(" *(\r?\n)+ *", "\n"); // some are leftover
		output = output.replaceAll("^\n", ""); // leading space
		output = output.replaceAll("\n$", ""); // trailing space
		*/
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
			return false;
        }
		
		return true;
	}
	
	class CleanListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			gui.setEnabledCleanButton(false);
			gui.setEnabledSaveButton(false);
			gui.setStatus("Reading File...");
			
			boolean success = readFile(gui.getInputTextField());
			
			if (success) {
				gui.setStatus("Cleaning text...");
			} else {
				gui.displayErrorMessage("Something went wrong when reading the file.");
				gui.setStatus("Something went wrong when reading the file.");
				gui.setEnabledCleanButton(true);
				return;
			}
			
			SwingUtilities.invokeLater(() -> {		
				boolean[] config = gui.getConfig();
				boolean filterModeEnabled = gui.getFilterModeEnabled();
				int filterConfig = gui.getFilterConfig();
				boolean csvMode = gui.getCsvModeEnabled();
				
				if (filterModeEnabled && filterConfig == -1) {
					gui.displayErrorMessage("No filter chat mode selected!");
				} else {					
					cleaner.setConfig(config, filterModeEnabled, filterConfig, csvMode);
					cleaner.bulkClean();
					
					gui.setStatus("Finished cleaning text, ready to save");
					gui.setEnabledSaveButton(true);
				}
				
				gui.setEnabledCleanButton(true);
			});
		}
	}
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			gui.setEnabledCleanButton(false);
			gui.setEnabledSaveButton(false);
			String outputFileName = gui.getOutputTextField();
			String ext = gui.getCsvModeEnabled() ? "csv" : "txt";
			gui.setStatus("Saving to " + outputFileName + "." + ext + "...");
			
			SwingUtilities.invokeLater(() -> {
				boolean success = saveFile(outputFileName + "." + ext);
			
				if (success) {
					gui.setStatus("File saved!");
				} else {
					gui.displayErrorMessage("Something went wrong when saving the file.");
					gui.setStatus("Something went wrong when saving the file.");
					gui.setEnabledSaveButton(true);
				}
				
				gui.setEnabledCleanButton(true);
			});
		}
	}
}