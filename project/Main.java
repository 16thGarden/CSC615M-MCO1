import java.io.*;
import java.lang.ModuleLayer.Controller;
import java.util.*;

public class Main {
    public static void main(String[] args) {		
		Gui gui = new Gui();
		Cleaner cleaner = new Cleaner();
		Manager manager = new Manager(gui, cleaner);
    }
}