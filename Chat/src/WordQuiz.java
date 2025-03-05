/* Grup Üyeleri
Sudem Zehra Çelikel-230609045
Hamdi Berat Köybaşı-23060035
Yusuf Deligöz- 230609020
İlknur Çelik – 230609058
Berat Serçe – 230609005
Berk Sözcü -230609040
*/
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class WordQuiz extends JPanel {
    private final JLabel questionLabel; // Soru etiketini tanımla
    private final JButton option1Button; // Birinci seçenek butonu
    private final JButton option2Button; // İkinci seçenek butonu
    private final JButton favoriteButton; // Favorilere ekle butonu
    private final WordsPanel wordsPanel; // Favorilere ekle panelini tutacak

    private final Map<String, String> wordQuizData; // Kelime ve anlamlarını tutan veri yapısı
    private String currentWord; // Şu anki soru kelimesi

    public WordQuiz(WordsPanel wordsPanel) {
        this.wordsPanel = wordsPanel;
        setLayout(new BorderLayout()); // Panelin düzenini BorderLayout olarak ayarla

        // Quiz verilerini başlat
        wordQuizData = new HashMap<>();
        wordQuizData.put("apple", "elma");
        wordQuizData.put("book", "kitap");
        wordQuizData.put("car", "araba");
        wordQuizData.put("dog", "köpek");
        wordQuizData.put("house", "ev");

        // Soru etiketini başlat
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Font ayarı
        add(questionLabel, BorderLayout.NORTH); // Etiketi üst kısma ekle

        // Seçenekler için butonları başlat
        JPanel optionsPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 satır 2 sütunlu grid düzeni
        option1Button = new JButton(); // Birinci buton
        option2Button = new JButton(); // İkinci buton
        optionsPanel.add(option1Button); // Birinci butonu ekle
        optionsPanel.add(option2Button); // İkinci butonu ekle
        add(optionsPanel, BorderLayout.CENTER); // Seçenekler panelini merkeze ekle

        // Favorilere ekleme butonunu başlat
        favoriteButton = new JButton("Favori Kelimelere Ekle");
        add(favoriteButton, BorderLayout.SOUTH); // Favori butonunu alt kısma ekle

        // Butonlara tıklama işlemleri ekle
        option1Button.addActionListener(e -> checkAnswer(option1Button.getText())); // Birinci buton tıklama
        option2Button.addActionListener(e -> checkAnswer(option2Button.getText())); // İkinci buton tıklama
        favoriteButton.addActionListener(e -> addToFavorites()); // Favori butonu tıklama

        // Quiz'i başlat
        nextQuestion();
    }

    private void nextQuestion() {
        // Rastgele bir kelime seç
        Object[] keys = wordQuizData.keySet().toArray();
        currentWord = (String) keys[(int) (Math.random() * keys.length)];
        String correctAnswer = wordQuizData.get(currentWord);

        // Rastgele yanlış bir seçenek oluştur
        Object[] values = wordQuizData.values().toArray();
        String wrongAnswer;
        do {
            wrongAnswer = (String) values[(int) (Math.random() * values.length)];
        } while (wrongAnswer.equals(correctAnswer)); // Doğru cevabı seçmeden yanlış cevabı oluştur

        // Seçenekleri karıştır
        if (Math.random() > 0.5) {
            option1Button.setText(correctAnswer); // Doğru cevabı birinci butona yerleştir
            option2Button.setText(wrongAnswer); // Yanlış cevabı ikinci butona yerleştir
        } else {
            option1Button.setText(wrongAnswer); // Yanlış cevabı birinci butona yerleştir
            option2Button.setText(correctAnswer); // Doğru cevabı ikinci butona yerleştir
        }

        // Soru etiketini güncelle
        questionLabel.setText("Kelimenin anlamını seçin: " + currentWord + "?");
    }

    private void checkAnswer(String selectedAnswer) {
        // Seçilen cevabın doğru olup olmadığını kontrol et
        if (wordQuizData.get(currentWord).equals(selectedAnswer)) {
            JOptionPane.showMessageDialog(this, "Doğru!", "Sonuç", JOptionPane.INFORMATION_MESSAGE); // Doğru cevabı bildiren mesaj
        } else {
            JOptionPane.showMessageDialog(this, "Yanlış!", "Sonuç", JOptionPane.ERROR_MESSAGE); // Yanlış cevabı bildiren mesaj
        }
        nextQuestion(); // Sonraki soruyu başlat
    }

    private void addToFavorites() {
        String translation = wordQuizData.get(currentWord); // Seçilen kelimenin çevirisini al
        if (translation != null) {
            wordsPanel.addWord(currentWord + " - " + translation); // Kelimeyi ve çevirisini favorilere ekle
            JOptionPane.showMessageDialog(this, currentWord + " Favorilere eklendi!", "Info", JOptionPane.INFORMATION_MESSAGE); // Favoriye ekleme mesajı
        }
    }
}
