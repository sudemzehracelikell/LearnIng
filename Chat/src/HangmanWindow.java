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
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class HangmanWindow extends JPanel {
    private JTextField guessField; // Kullanıcının harf tahminini gireceği alan
    private JTextArea wordArea; // Oyun sırasında tahmin edilen kelimenin gösterileceği alan
    private JTextArea wrongGuessesArea; // Yanlış tahminlerin gösterileceği alan
    private JButton guessButton; // Tahmin butonu
    private JButton backButton; // Geri butonu (şu an kullanılmıyor)
    private JLabel imageLabel; // Hangman resminin gösterileceği alan
    private String wordToGuess; // Tahmin edilecek kelime
    private StringBuilder currentGuess; // Şu anki tahmin (boşluklarla doldurulmuş)
    private int attemptsLeft; // Kalan tahmin hakkı
    private ArrayList<Character> wrongGuesses; // Yanlış tahmin edilen harflerin listesi
    private String[] words = {"java", "programming", "hangman", "chatbot", "computer"}; // Kelimeler

    public HangmanWindow() {
        setLayout(new BorderLayout()); // BorderLayout düzenini kullanıyoruz

        // GUI bileşenlerini başlat
        guessField = new JTextField(20); // Tahmin için textfield
        guessButton = new JButton("Guess"); // Tahmin butonu
        wordArea = new JTextArea(); // Kelimeyi ve doğru/yanlış tahminleri göstermek için
        wrongGuessesArea = new JTextArea(); // Yanlış tahminleri göstermek için
        imageLabel = new JLabel(); // Hangman resmi

        

        wordArea.setEditable(false); // Kelimenin ve tahminlerin değiştirilmesini engelle
        wrongGuessesArea.setEditable(false); // Yanlış tahminlerin değiştirilmesini engelle

        // Layout ayarlamaları
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Dikey kutu düzeni

        JPanel topPanel = new JPanel(); // Üst panel (tahmin alanı ve butonları)
        topPanel.add(new JLabel("Enter your guess:")); // "Tahmininizi girin" etiketi
        topPanel.add(guessField); // Tahmin textfield'ı
        topPanel.add(guessButton); // Tahmin butonu

        panel.add(topPanel); // Üst paneli ekle
        panel.add(imageLabel); // Resim etiketini ekle
        panel.add(new JScrollPane(wordArea)); // Kelime alanını ekle
        panel.add(new JScrollPane(wrongGuessesArea)); // Yanlış tahminler alanını ekle
        
        add(panel, BorderLayout.CENTER); // Ana paneli merkeze ekle

        // Tahmin butonuna tıklama olayını işle
        guessButton.addActionListener(e -> processGuess());

        startGame(); // Oyunu başlat
    }

    // Yeni bir oyun başlatan metod
    private void startGame() {
        Random rand = new Random(); // Rastgele sayı üretici
        wordToGuess = words[rand.nextInt(words.length)]; // Rastgele kelime seç
        currentGuess = new StringBuilder("_".repeat(wordToGuess.length())); // Kelimenin uzunluğunda boşluklar
        attemptsLeft = 6; // Başlangıçta 6 tahmin hakkı
        wrongGuesses = new ArrayList<>(); // Yanlış tahminleri başlat

        wordArea.setText("Guess the word: " + currentGuess.toString()); // Kelimeyi göster
        wrongGuessesArea.setText("Wrong guesses: "); // Yanlış tahminleri sıfırla
       
    }

    // Kullanıcının tahminini işleyen metod
    private void processGuess() {
        String guess = guessField.getText().trim().toLowerCase(); // Tahmini al ve küçük harfe çevir
        if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) { // Geçerli bir harf mi?
            char guessedLetter = guess.charAt(0); // Tahmin edilen harfi al
            boolean correctGuess = false;

            // Kelimenin her harfi ile karşılaştırma yap
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guessedLetter) {
                    currentGuess.setCharAt(i, guessedLetter); // Eğer doğru tahminse, harfi yerleştir
                    correctGuess = true;
                }
            }

            // Eğer doğru tahmin yapılmadıysa, yanlış tahmin ekle
            if (!correctGuess) {
                if (!wrongGuesses.contains(guessedLetter)) {
                    wrongGuesses.add(guessedLetter);
                    attemptsLeft--; // Kalan tahmin hakkını azalt
                }
            }

            // Son durumu güncelle
            wordArea.setText("Guess the word: " + currentGuess.toString() + "\nAttempts left: " + attemptsLeft);
            wrongGuessesArea.setText("Wrong guesses: " + wrongGuesses);
            guessField.setText(""); // Textfield'ı temizle
           

            // Oyunun bitip bitmediğini kontrol et
            if (currentGuess.toString().equals(wordToGuess)) {
                wordArea.setText("You won! The word was: " + wordToGuess); // Kazandınız
            } else if (attemptsLeft <= 0) {
                wordArea.setText("Game Over! The word was: " + wordToGuess); // Oyunu kaybettiniz
            }
        } else {
            // Geçersiz giriş için uyarı göster
            JOptionPane.showMessageDialog(this, "Please enter a valid single letter.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

 
}
