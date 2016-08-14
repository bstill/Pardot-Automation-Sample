# Pardot Java Selenium WebDriver Automation Sample

This is a sample Java Selenium WebDriver test that completes the following steps:

- Log in to Pardot
- Create a list with a random name (Marketing > Segmentation > Lists)
- Attempt to create another list with that same name and ensure the system correctly gives a validation failure
- Rename the original list
- Ensure the system allows the creation of another list with the original name now that the original list is renamed
- Create a new prospect (Prospect > Prospect List)
- Add your new prospect to the newly created list
- Ensure the new prospect is successfully added to the list upon save
- Send a text only email to the list (Marketing > Emails)  *Please note, email is disabled in this account so you will not actually be able to send the email.  This is okay.
- Log out

## Development Environment

IntelliJ IDEA 2016.2.1
- Build #IC-162.1447.26, built on August 2, 2016
- JRE: 1.8.0_101-b13 amd64
- JVM: Java HotSpot(TM) 64-Bit Server VM by Oracle Corporation

Apache Maven v. 3.3.9
- selenium-java v. 2.53.1
- junit v. 4.12

Mozilla FireFox 46.0.1

## Framework Structure

The framework used is one I have developed over my years of automation to facilitate the creation of like test cases quickly and easily as well as for ease of maintenance.

This is a modular 3 tiered approach:

1. Test Case Layer
     example: 
          Fill Car with Gas()
2. Building Block Layer
     example: 
          Fill Car with Gas() {
               Remove Gas Cap from Gas Tank()
               Verify Gas Cap is Removed()
               Insert Nozzle into Gas Tank()
               Verify Nozzle is Inserted()
               Pump Gas()
               Verify Gas Tank Full()
               Remove Nozzle from Gas Tank()
               Verify Nozzle is Removed()
               Replace Gas Cap()
               Verify Gas Cap is Replaced()
          }
3. Action Layer
     example:
          Remove Gas Cap from Gas Tank() {
               Turn Gas Cap Counterclockwise
               Let Gas Cap Dangle
          }
               
          Verify Gas Cap is Removed() {
               if (Gas Cap is Not in Gas Tank) {
                    Pass
               } else {
                    Fail
               }  
          }

## Installation
TODO: Describe the installation process

## Usage
Open file file: .\src\test\java\com\pardot\sample\tests\PardotTest.java

Run the test: pardotSampleAutomationExercise

## Reporting
Test results will be generated and placed in the ./reports folder.

Open the "Automation_Report.html" file to see the current and historical results.

## Credits

By **Brian Still**
