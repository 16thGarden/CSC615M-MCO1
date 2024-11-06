import java.io.*;
import java.util.*;

public class Cleaner {
	ArrayList<String> text = new ArrayList<String>();
	
	private boolean[] config;
	private boolean filterModeEnabled;
	private int filterModeConfig;
	private boolean csvMode;
	
	public void clearText() {
		text.clear();
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public void setText(ArrayList<String> text) {
		this.text = text;
	}
	
	public void setConfig(boolean[] config, boolean filterModeEnabled, int filterModeConfig, boolean csvMode) {
		this.config = config;
		this.filterModeEnabled = filterModeEnabled;
		this.filterModeConfig = filterModeConfig;
		this.csvMode = csvMode;
	}
	
	private String clean(String line) {
		line = line.replaceAll("^(?!\\[\\d{2}:\\d{2}:\\d{2}\\] \\[Client thread/INFO\\]: \\[CHAT\\]).*$", "");
		line = line.replaceAll("^\\[\\d{2}:\\d{2}:\\d{2}\\] \\[Client thread/INFO\\]: \\[CHAT\\] ?", "");
		line = line.replaceAll("[§][0-9a-fA-Fk-oK-OrR]", "");
		
		if (!filterModeEnabled) {				
			if (!config[0]) {
				line = line.replaceAll("^\\[WATCHDOG ANNOUNCEMENT\\]$", "");
				line = line.replaceAll("^Watchdog has banned [0-9,]+ players in the last 7 days\\.$", "");
				line = line.replaceAll("^Staff have banned an additional [0-9,]+ in the last 7 days\\.$", "");
				line = line.replaceAll("^Blacklisted modifications are a bannable offense\\!$", "");
			}
			
			if (!config[1]) {
				line = line.replaceAll("^Profile ID: [a-f0-9-]+( \\(\\d+\\))?$", "");
				String profilenames = "(Apple|Banana|Blueberry|Coconut|Cucumber|Grapes|Kiwi|Lemon|Lime|Mango|Orange|Papaya|Pear|Peach|Pineapple|Pomegranate|Raspberry|Strawberry|Tomato|Watermelon|Zucchini)";
				line = line.replaceAll("^You are playing on profile: " + profilenames + "( \\(Co-op\\))?( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Switching to profile " + profilenames + "...( \\(\\d+\\))?$", "");
				line = line.replaceAll("Your profile was changed to: " + profilenames + "( \\(Co-op\\))?( \\(\\d+\\))?$", "");
			}
			
			if (!config[2]) {
				line = line.replaceAll("^Warping\\.\\.\\.( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Warping you to your SkyBlock island\\.\\.\\.( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Sending to server (mega|mini).*\\.\\.\\.( \\(\\d+\\))?$", "");
			}
			
			if (!config[3]) {
				line = line.replaceAll("^\\[Important\\] This server will restart soon: (Scheduled Reboot|Game Update)$", "");
				line = line.replaceAll("^You have 60 seconds to warp out\\! CLICK to warp now\\!$", "");
				line = line.replaceAll("^Evacuating to Hub\\.\\.\\.$", "");
			}
			
			if (!config[4]) {
				line = line.replaceAll("^[-\\?]+( \\(\\d+\\))?$", "");
			}
			
			if (!config[5]) {
				line = line.replaceAll("^Setting up the auction\\.\\.\\.( \\(\\d+\\))?$", "");
				line = line.replaceAll("^[a-zA-Z0-9_]+ created a BIN auction for .* at [\\d,]+ coins\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^(BIN )?Auction started for .*\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^\\[Auction\\].*( \\(\\d+\\))?$", "");
				line = line.replaceAll("^[a-zA-Z0-9_]+ collected an auction for [\\d,]+ coins\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Putting coins in escrow\\.\\.\\.( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Processing purchase\\.\\.\\.( \\(\\d+\\))?$", "");
				line = line.replaceAll("^You purchased .* for [\\d,]+ coins\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Visit the Auction House to collect your item\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Claiming BIN auction\\.\\.\\.$( \\(\\d+\\))?$", "");
				line = line.replaceAll("^You claimed .+ from (\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+'s auction\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^You collected [\\d,]+ coins from selling .+ to (\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+ in an auction\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^There was an error with the auction house! \\(AUCTION_EXPIRED_OR_NOT_FOUND\\)( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Checking escrow for recent transaction\\.\\.\\.( \\(\\d+\\))?$", "");
				line = line.replaceAll("^Escrow refunded [\\d,]+ coins for (BIN )?Auction Buy\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^[a-zA-Z0-9-]+ collected an expired auction\\!( \\(\\d+\\))?$", "");
				line = line.replaceAll("^You claimed .+ back from your expired auction\\!( \\(\\d+\\))?$", "");
			}
			
			if (!config[6]) {
				line = line.replaceAll("^\\[Bazaar\\].*$", "");
			}
			
			if (!config[7]) {
				line = line.replaceAll("^\\[SkyHanni\\].*$", "");
			}
			
			if (!config[8]) {
				line = line.replaceAll("^\\[NEU\\].*$", "");
			}
			
			if (!config[9]) {
				line = line.replaceAll("^Party > .*$", "");
			}
			
			if (!config[10]) {
				line = line.replaceAll("^Guild > .*$", "");
			}
			
			if (!config[11]) {
				line = line.replaceAll("^Co-op > .*$", "");
			}
		} else {
			if (filterModeConfig == 0) {
				line = line.replaceAll("^(?!\\[\\d{1,3}\\]( \\?)?( \\[(\\?|.+)])?( \\[(VIP|MVP)\\+{0,2}\\])? [a-zA-Z0-9_]+:.*).*$", "");
				
				if (csvMode) {
					line = line.replaceAll("^(\\[\\d{1,3}\\]( \\?)?( \\[(\\?|.+)])?( \\[(VIP|MVP)\\+{0,2}\\])? [a-zA-Z0-9_]+): (.*)$", "$1\\,$7");
				}
			} else if (filterModeConfig == 1) {
				line = line.replaceAll("^(?!Party > (\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+: .*).*$", "");
				
				if (csvMode) {
					line = line.replaceAll("^Party > ((\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+): (.*)$", "$1\\,$4");
				}
			} else if (filterModeConfig == 2) {
				line = line.replaceAll("^(?!Guild > (\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+ (\\[.+\\])?:.*).*$", "");
				
				if (csvMode) {
					line = line.replaceAll("^Guild > ((\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+ \\[.+\\]): (.*)$", "$1\\,$4");
				}
			} else if (filterModeConfig == 3) {
				line = line.replaceAll("^(?!Co-op > (\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+: .*).*$", "");
				
				if (csvMode) {
					line = line.replaceAll("^Co-op > ((\\[(VIP|MVP)\\+{0,2}\\] )?[a-zA-Z0-9_]+): (.*)$", "$1\\,$4");
				}
			}
		}
		
		return line;
    }
	
	public void bulkClean() {
		for (int i = 0; i < text.size(); i++) {
			text.set(i, clean(text.get(i)));
		}
	}
}