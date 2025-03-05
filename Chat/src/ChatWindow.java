/* Grup Üyeleri
Sudem Zehra Çelikel-230609045
Hamdi Berat Köybaşı-23060035
Yusuf Deligöz- 230609020
İlknur Çelik – 230609058
Berat Serçe – 230609005
Berk Sözcü -230609040
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ChatWindow extends JPanel { // Ana sınıf ChatWindow

    // API anahtarı ve ChatGPT API URL'si
    private static final String API_KEY = "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";
    private static final String CHATGPT_API_URL = "https://api.openai.com/v1/chat/completions";
    
    // Arayüz bileşenleri
    private JTextArea chatArea; // Sohbet penceresi
    private JTextField inputField; // Kullanıcı girişi için text alanı
    private JButton sendButton; // Gönder butonu

    // Ana metod, GUI oluşturma
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatWindow::createAndShowGUI); // GUI'yi oluştur
    }

    // GUI'yi oluşturma
    private static void createAndShowGUI() {
        // Çerçeve oluşturma
        JFrame frame = new JFrame("Chatbot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // ChatWindow penceresini oluştur
        ChatWindow chatWindow = new ChatWindow();
        
        // ChatWindow'u çerçeveye ekle
        frame.add(chatWindow);
        frame.setVisible(true);
    }

    // ChatWindow constructor: Bileşenleri başlat
    public ChatWindow() {
        setLayout(new BorderLayout()); // BorderLayout düzenini kullanıyoruz

        // Sohbet alanı oluştur
        chatArea = new JTextArea();
        chatArea.setEditable(false); // Kullanıcının sohbeti düzenlemesini engelle
        JScrollPane scrollPane = new JScrollPane(chatArea); // Scroll ekle

        // Kullanıcı girişi için alan ve buton
        inputField = new JTextField();
        sendButton = new JButton("Send");

        // Input alanı ve buton için panel oluştur
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER); // Text alanını merkeze ekle
        inputPanel.add(sendButton, BorderLayout.EAST); // Gönder butonunu sağa ekle

        // Bileşenleri ana panele ekle
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Gönder butonuna aksiyon ekle
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userMessage = inputField.getText().trim(); // Kullanıcıdan gelen mesajı al
                if (!userMessage.isEmpty()) { // Eğer mesaj boş değilse
                    chatArea.append("You: " + userMessage + "\n"); // Kullanıcı mesajını göster
                    inputField.setText(""); // Text alanını temizle

                    // ChatGPT'den yanıt almak için yeni bir thread başlat
                    new Thread(() -> {
                        try {
                            String botResponse = getChatGptResponse(userMessage); // ChatGPT'den yanıt al
                            SwingUtilities.invokeLater(() -> chatArea.append("Bot: " + botResponse + "\n")); // Yanıtı chatArea'ya ekle
                        } catch (Exception ex) {
                            SwingUtilities.invokeLater(() -> chatArea.append("Error: " + ex.getMessage() + "\n")); // Hata mesajını ekle
                        }
                    }).start();
                }
            }
        });

        // Enter tuşuna basıldığında, send butonunu tıklat
        inputField.addActionListener(e -> sendButton.doClick());
    }

    // ChatGPT'ye mesaj gönderip yanıt almayı sağlayan metod
    private static String getChatGptResponse(String message) throws Exception {
        // API çağrısı için JSON nesneleri oluşturuyoruz
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("model", "gpt-3.5-turbo"); // Model seçimi

        // Kullanıcı mesajını JSON formatına dönüştür
        ObjectNode userMessage = mapper.createObjectNode();
        userMessage.put("role", "user");
        userMessage.put("content", "Check the following text for grammar and spelling errors. Provide the corrected version and explain the errors in English and Turkish:\n" + message);

        requestBody.putArray("messages").add(userMessage); // Mesajları ekle
        requestBody.put("max_tokens", 200); // Maksimum token sayısı

        // HTTP client oluştur
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CHATGPT_API_URL)) // API URL'si
                .header("Content-Type", "application/json") // İçerik tipi
                .header("Authorization", "Bearer " + API_KEY) // API Anahtarı
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestBody))) // POST isteği
                .build();

        // HTTP isteğini gönder ve yanıt al
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Eğer yanıt başarılı değilse hata mesajı döndür
        if (response.statusCode() != 200) {
            System.out.println("Response Body: " + response.body());
            throw new Exception("API request failed with status code: " + response.statusCode());
        }

        // Yanıt JSON'u işle
        JsonNode rootNode = mapper.readTree(response.body());
        JsonNode choices = rootNode.get("choices");

        // Yanıtı kontrol et ve mesajı al
        if (choices != null && choices.size() > 0) {
            JsonNode messageNode = choices.get(0).get("message");
            if (messageNode != null && messageNode.has("content")) {
                return messageNode.get("content").asText(); // Bot yanıtını döndür
            }
        }

        // Yanıt geçersizse hata 
        throw new Exception("Invalid response format from ChatGPT.");
    }
}
