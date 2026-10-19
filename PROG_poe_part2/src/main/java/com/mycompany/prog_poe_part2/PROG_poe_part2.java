/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog_poe_part2;

/**
 *
 * @author Student
 */

import java.util.Scanner;

public class PROG_poe_part2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to QuickChat.");
        boolean keepRunning = true;
        
        while (keepRunning) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            
            int menuChoice = Integer.parseInt(scanner.nextLine());
            
            if (menuChoice == 1) {
                System.out.print("How many messages do you wish to enter? ");
                int totalLoops = Integer.parseInt(scanner.nextLine());
                
                for (int i = 0; i < totalLoops; i++) {
                    int sequenceNum = i + 1;
                    System.out.println("\n--- Message #" + sequenceNum + " ---");
                    
                    System.out.print("Enter cell number: ");
                    String cellNum = scanner.nextLine();
                    
                    System.out.print("Enter text message: ");
                    String textBody = scanner.nextLine();
                    
                    Message messageObject = new Message(sequenceNum, cellNum, textBody);
                    
                    String validationTextResult = messageObject.checkMessageLength();
                    System.out.println(validationTextResult);
                    
                    if (validationTextResult.equals("Message ready to send.")) {
                        String cellFormatResult = messageObject.checkRecipientCell();
                        System.out.println(cellFormatResult);
                        
                        if (cellFormatResult.equals("Cell phone number successfully captured.")) {
                            String promptResult = messageObject.sentMessage();
                            System.out.println(promptResult);
                        }
                    }
                }
                
                System.out.println("\nTotal number of messages successfully accumulated: " + Message.returnTotalMessages());
                
            } else if (menuChoice == 2) {
                System.out.println("Coming Soon.");
                
            } else if (menuChoice == 3) {
                System.out.println("Exiting Application. Goodbye!");
                keepRunning = false;
            } else {
                System.out.println("Invalid choice selected.");
            }
        }
        scanner.close();
    }
}

