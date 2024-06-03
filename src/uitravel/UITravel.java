/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uitravel;

import FireBase.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Jay
 */
public class UITravel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collection = db.collection("admin");

        // Retrieve all documents in the collection
        ApiFuture<QuerySnapshot> future = collection.get();
        try {
            QuerySnapshot querySnapshot = future.get();
            for (QueryDocumentSnapshot document : querySnapshot) {
                // Print document id and data
                System.out.println("Document ID: " + document.getId());
                System.out.println("Data: " + document.getData());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    }
    

