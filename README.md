# Guideline

### I/ Set up environment

  1/ Eclipse

	- Dowload and install Eclipse:
			https://www.eclipse.org/downloads/packages/		 
      
  2/ JAVA
  
     - Dowload and install Java JDK:
			https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
    
  	 - Set java home:
			https://javatutorial.net/set-java-home-windows-10 
      
  3/ Maven
   
     - Dowload Maven:
			https://maven.apache.org/download.cgi
      
	 - Set Maven Home, Path:
			https://www.tutorialspoint.com/maven/maven_environment_setup
      
  4/ TestNG
   
     - Dowload and install testNG:
			https://www.toolsqa.com/selenium-webdriver/install-testng/
			
  5/ Cucumber
   
     - Dowload and install Cucumber:
			https://www.toolsqa.com/cucumber/install-cucumber-eclipse-plugin/
			
  6/ Android
   
     - Dowload Android Sdk:
		  	https://developer.android.com/studio/
     - Set Android Home:
		  	https://www.gajotres.net/setting-up-your-windows-android-environment/	

  7/ NodeJS
            
     - Dowload NodeJS
			  https://nodejs.org/en/download/
			  
  8/ Appium
		  
     - Install: 
			  npm install -g appium@x.x.x
			  Ex: npm install -g appium@1.14.0
	 - Check version:
			  appium -v
			  
  9/ Devices Setting
		  
       ●	Go to Settings->Developer options
          - Enable USB debugging
       If you can't see Developer options, should go to Settings > About phone > Software info > Build number >Tap Build number seven times >Go back to Settings, where you’ll find a Developer options at the bottom
       ●	There's 1 devices connect to computer
       ●	Check connected devices by typing following command on command prompt:
                            adb devices
      
  10/ Git

	 -Dowload and install Git:
			https://git-scm.com/downloads
		
  11/ GitHub

	 - Register GitHub account.
  
	 - Link to clone code:
			 https://github.com/thuanvo191/leapxpert_automation
       
### II/ How to run

   1/Clone the repository
  
    ●	Create your workspace directory: example E:/leapxpert_automation/workspace
    ●	On command prompt, type the following command: cd E:/leapxpert_automation/workspace
    ●	 For cloning, type Git clone command: git clone git@github.com:thuanvo191/leapxpert_automation.git
 	Or download zip from https://github.com/thuanvo191/leapxpert_automation
    
   2/Import in Eclipse workspace
  
    ●	In the Eclipse menu, choose File => Import
    ●	In Maven section select Existing Maven Projects
    ●	Click on Next > button. The next screen Import projects appears
    ●	Click Browse... button to locate leapxpert_automation
    ●	Click on Finish button to import
    
   3/Connect devices
   
    ●	Plug the real device into the computer. If you use emulator, start virtual devices on Android Studio-> go to Tools->ADV Manager 
    (If there's not any device in popup, you can create new virtual devices to add new devices on list)
    ●	Check connected devices by typing following command on command prompt: adb devices. Then, get udid of devices in command prompt
    ●	On Eclipse, go to src/test/resources/config/Mobile-local-config.json and open this json file   
    ●	Replace udid which has just got in 'command prompt' for value of deviceName and udid in json file 
    Ex:  Replace old value
    	"deviceName": "ZX1G227NJC",
		"udid": "ZX1G227NJC",
	to new value
		"deviceName": "emulator-5554",
		"udid": "emulator-5554",
    ●	Save json file after changing
    
   4/Start appium
  
    ●	Open Git Bash
    ●	Type following command and enter to start appium server:
    		appium --no-reset
    
   5/Execution
  
    ●	On the top-bar menu of Eclipse, go to Run => Run Configurations...
    Or you can right click on on xml file (ex: regression.xml) in execution folder (leapxpert_automation/src/test/resources/execution)=>Select Run As-> Run Configurations
    ●	Select TestNG from the set of configurations and click on the New Configuration icon
    ●	On the configuration window, give a functional name to the configuration, example Test 
    ●	Go to Project section, click on Browse... and select your project on the project Window, example leapxpert_automation
    ●	Now go to Suite section and click on Browse... button. Select the regression.xml configuration
    leapxpert_automation/src/test/resources/execution/regression.xml
    ●	Click on Apply and Run buttons. This will run the selected testng XML configuration file
	Or you just right click on xml file (regression.xml) in execution folder (leapxpert_automation_test/src/test/resources/execution)->select Run As->1 TestNG Suite 


Thank you!
