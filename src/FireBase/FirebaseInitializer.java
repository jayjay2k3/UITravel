package FireBase;

import com.google.api.core.ApiFuture;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FirebaseInitializer {
    
        private static boolean initialized = false;

    public static void initialize() throws IOException{
          if (!initialized) {
            synchronized (FirebaseInitializer.class) {
                if (!initialized) {
                try {
                    FileInputStream serviceAccount = new FileInputStream("src\\FireBase\\uitravel-22edb-firebase-adminsdk-iz6t6-c31d6ea6fc.json");

                    FirebaseOptions options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();

                    FirebaseApp.initializeApp(options);
                    initialized = true;
                    System.out.println("Firebase Initialized");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
            }
          }
    }
    public void uploadImage(ImageIcon imageIcon ) {
        try {
            BufferedImage bufferedImage = new BufferedImage(
                            imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    imageIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imageData = baos.toByteArray();

            String imageName = UUID.randomUUID().toString(); // Generate a unique image name
            Firestore firestore = FirestoreClient.getFirestore();

            // Upload the image to Firestore
            Map<String, Object> data = new HashMap<>();
            data.put("imageData", imageData);
            firestore.collection("images").document(imageName).set(data);

            System.out.println("Image uploaded successfully: " + imageName);
        } catch (IOException e) {
        }
    }
        public void downloadImage(String imageName, String outputFilePath) {
        try {
            Firestore firestore = FirestoreClient.getFirestore();
            DocumentSnapshot documentSnapshot = firestore.collection("images").document(imageName).get().get();
            if (documentSnapshot.exists()) {
                // Get the image data as a byte array
                byte[] imageData = ((String) documentSnapshot.get("imageData")).getBytes();

                // Decode the byte array to obtain the image
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(imageData)));

                // Save the image to a file
                File outputFile = new File(outputFilePath);
                ImageIO.write(image, "jpg", outputFile);

                System.out.println("Image downloaded successfully: " + outputFilePath);
            } else {
                System.out.println("Document not found for image: " + imageName);
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
        }
    }
        public void uploadImages(Map<String, List<ImageIcon>> imagesMap) {
        try {
            for (Map.Entry<String, List<ImageIcon>> entry : imagesMap.entrySet()) {
                String documentId = entry.getKey();
                List<ImageIcon> imageIcons = entry.getValue();

                List<String> base64Images = new ArrayList<>();
                for (ImageIcon imageIcon : imageIcons) {
                    // Convert ImageIcon to BufferedImage
                    BufferedImage bufferedImage = new BufferedImage(
                            imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    imageIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

                    // Encode the BufferedImage to Base64 string
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
                    String base64Image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
                    base64Images.add(base64Image);
                }

                // Create a map to store the Base64 images
                Map<String, Object> imageDataMap = new HashMap<>();
                imageDataMap.put("images", base64Images);

                // Create a new document in Firestore for the images
                Firestore firestore = FirestoreClient.getFirestore();

                DocumentReference documentReference = firestore.collection("imageCollection").document(documentId);

                // Upload the image data to Firestore
                ApiFuture<WriteResult> uploadTask = documentReference.set(imageDataMap);
                uploadTask.get(); // Wait for the upload to complete

                System.out.println("Images uploaded successfully for document: " + documentId);
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
