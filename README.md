# CSC615M MCO1
## Text Cleaning Project

#### If the .jar is broken (skip this part if the file is working)
(in the *project* directory)
1. Run the batch file `RemakeJar.bat` 

**or**

1. Go to the file folder and make sure that there are 4 java files and 1 manifest.txt file
2. Open the command line (same directory)
3. Compile the app by typing `javac *.java`
4. Create the .jar file by typing ``jar cfm CSC615M_Text_Cleaning.jar manifest.txt *.class``
5. You should see the executable jar in the same file directory.

#### To use the app
1. After opening the executable .jar file, you will see an interface with two input fields for input and output filenames, customize as preferred.
2. Select your configuration for text cleaning, then click **Clean text**
3. Wait for the program to clean the text (this might take a while depending on the size of the input)
4. Save the result into a .txt or .csv file (depending selected configuration on step 2) - by clicking click on the **Save to file** button. (this might also take a while depending on the size of the input)