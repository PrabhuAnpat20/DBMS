package mongoCon;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.*;
import org.bson.*;

public class app {
    public static void main(String args[]) {
        System.out.println("hello");
        Scanner sc = new Scanner(System.in);
        int roll;
        String name;
        // Try-with-resources for automatic resource management
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("demo");
            MongoCollection <Document>  col = database.getCollection("sppu");

          
            System.out.println("Connected to the database successfully");
           int ch=sc.nextInt();
           if(ch==1) {
        	   System.out.println("enter roll");
        	   roll=sc.nextInt();
        	   sc.nextLine();
        	   System.out.println("enter name");
        	   name=sc.nextLine();
        	   Document d=new Document();
        	   d.append("roll", roll).append("name", name);
        	   col.insertOne(d);
        	   System.out.println("inserted successfully");
           }
           if (ch == 2) {
        	    System.out.println("Enter roll:");
        	    roll = sc.nextInt();
        	    
        	   
        	    Document filter = new Document("roll", roll);
        	    
        	   
        	    Document result =  col.find(filter).first();
        	    
        	    if (result != null) {
        	        System.out.println("Document found: " + result.toJson());
        	    } else {
        	        System.out.println("No document found with the given roll number.");
        	    }
        	}
           if (ch == 3) {
        	    System.out.println("Finding all documents in the collection:");
        	    for (Document doc : col.find()) {
        	        System.out.println("Document: " + doc.toJson());
        	    }
        	}
           if (ch == 4) {
        	    System.out.println("Enter roll to delete:");
        	    roll = sc.nextInt();

        	   
        	    Document filter = new Document("roll", roll);

        	   
        	    long deletedCount = col.deleteOne(filter).getDeletedCount();

        	    if (deletedCount > 0) {
        	        System.out.println("Document deleted successfully.");
        	    } else {
        	        System.out.println("No document found with the given roll number.");
        	    }
        	}

            
            
            

        } catch (Exception e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }
}
