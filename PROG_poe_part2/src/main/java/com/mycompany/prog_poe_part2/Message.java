/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog_poe_part2;

/**
 *
 * @author Student
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Message {

    private static int totalMessages = 0;
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int difference = message.length() - 250;
            return "Message exceeds 250 characters by " + difference + " characters. Please reduce the size.";
        }
    }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + (firstWord + lastWord).toUpperCase();
    }

    public String sentMessage() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nChoose an option:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message");
        System.out.print("Enter choice: ");
        
        int choice = Integer.parseInt(input.nextLine());
        
        switch (choice) {
            case 1:
                totalMessages++;
                sentMessages.add(this);
                messageIDs.add(messageID);
                messageHashes.add(messageHash);
                return "Message successfully sent.";
                
            case 2:
                disregardedMessages.add(this);
                return "Message disregarded.";
                
            case 3:
                storedMessages.add(this);
                messageIDs.add(messageID);
                messageHashes.add(messageHash);
                storeMessage(); 
                return "Message successfully stored.";
                
            default:
                return "No valid option selected.";
        }
    }

    public static String printMessages() {
        String allDetails = "";
        for (int i = 0; i < sentMessages.size(); i++) {
            Message current = sentMessages.get(i);
            allDetails += "Message ID: " + current.messageID + "\n" +
                          "Message Hash: " + current.messageHash + "\n" +
                          "Recipient: " + current.recipient + "\n" +
                          "Message: " + current.message + "\n" +
                          "-----------------------------------\n";
        }
        return allDetails;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public void storeMessage() {
        String simpleJson = "{\n" +
                "  \"MessageID\": \"" + this.messageID + "\",\n" +
                "  \"MessageNumber\": " + this.messageNumber + ",\n" +
                "  \"Recipient\": \"" + this.recipient + "\",\n" +
                "  \"Message\": \"" + this.message + "\",\n" +
                "  \"MessageHash\": \"" + this.messageHash + "\"\n" +
                "}";
        
        try {
            FileWriter myWriter = new FileWriter("stored_messages.json", true);
            myWriter.write(simpleJson + ",\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing to file.");
        }
    }
}

