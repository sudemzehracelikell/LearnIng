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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class EnglishLearningGame extends JPanel {
    // Kelimeler, anlamlar ve eş anlamlılar
    private final String[] words = {"happy", "sad", "fast", "slow"};
    private final String[] meanings = {"mutlu", "üzgün", "hızlı", "yavaş"};
    private final String[] synonyms = {"joyful", "unhappy", "quick", "lethargic"};

    // Skor ve mevcut soru indexi
    private int score = 0;
    private int currentQuestion = 0;

    // GUI bileşenleri
    private final JLabel questionLabel;
    private final JTextArea resultArea;
    private final JButton[] meaningButtons;
    private final JButton nextButton;
    private final JLabel scoreLabel;

    // Constructor, GUI bileşenlerini başlatma
    public EnglishLearningGame() {
        setLayout(new BorderLayout()); // BorderLayout düzenini kullanıyoruz

        // Soru etiketini oluştur
        questionLabel = new JLabel("Kelimenin anlamını seçin: " + words[currentQuestion], SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        // Sonuç alanı (kullanıcı geri bildirimleri)
        resultArea = new JTextArea();
        resultArea.setEditable(false); // Kullanıcı yazmasını engelliyoruz
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setLineWrap(true); // Metin satırlarını sarar
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Skor etiketini oluştur
        scoreLabel = new JLabel("Skor: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel, BorderLayout.SOUTH);

        // Cevap butonlarını oluştur
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2)); // 2 satır, 2 sütun düzeni
        meaningButtons = new JButton[4]; // 4 buton olacak

        for (int i = 0; i < 4; i++) {
            meaningButtons[i] = new JButton(); // Yeni buton oluştur
            meaningButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            meaningButtons[i].addActionListener(new MeaningButtonListener()); // Butona tıklama olayını bağla
            buttonPanel.add(meaningButtons[i]); // Butonu panele ekle
        }

        // "Sonraki" butonunu oluştur
        nextButton = new JButton("Sonraki");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(new NextButtonListener()); // Butona tıklama olayını bağla
        nextButton.setEnabled(false); // İlk başta "Sonraki" butonu pasif
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // İlk soruyu yükle
        loadQuestion();
    }

    // Soruyu yükleme ve seçenekleri karıştırma
    private void loadQuestion() {
        // Soruyu güncelle
        questionLabel.setText("Kelimenin anlamını seçin: " + words[currentQuestion]);
        resultArea.setText(""); // Sonuç alanını temizle
        nextButton.setEnabled(false); // "Sonraki" butonunu pasif yap

        // Şıklar için anlamları karıştır
        ArrayList<String> options = new ArrayList<>();
        options.add(meanings[currentQuestion]); // Doğru anlamı ilk seçeneğe ekle
        while (options.size() < 4) {
            String randomMeaning = meanings[(int) (Math.random() * meanings.length)]; // Rastgele bir anlam seç
            if (!options.contains(randomMeaning)) {
                options.add(randomMeaning); // Eğer zaten eklenmemişse ekle
            }
        }
        Collections.shuffle(options); // Şıkları karıştır

        // Şıkları butonlara yerleştir
        for (int i = 0; i < 4; i++) {
            meaningButtons[i].setText(options.get(i)); // Butonlara şıkları yerleştir
            meaningButtons[i].setEnabled(true); // Butonları aktif yap
        }
    }

    // Anlam butonlarına tıklama olayları
    class MeaningButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource(); // Tıklanan butonu al
            String selectedMeaning = source.getText(); // Seçilen anlamı al
            String correctMeaning = meanings[currentQuestion]; // Doğru anlamı al
            String correctSynonym = synonyms[currentQuestion]; // Doğru eş anlamlıyı al

            // Anlamı kontrol et
            if (selectedMeaning.equals(correctMeaning)) {
                resultArea.append("Doğru! Bu kelimenin anlamı: " + correctMeaning + "\n");
                score++; // Doğru cevaba skor ekle
            } else {
                resultArea.append("Yanlış, doğru cevap: " + correctMeaning + "\n");
            }

            // Eş anlamlıyı sor
            String selectedSynonym = JOptionPane.showInputDialog(null, "Bu kelimenin eş anlamlısı nedir?");
            if (selectedSynonym != null && selectedSynonym.equalsIgnoreCase(correctSynonym)) {
                resultArea.append("Eş anlamlı sorusuna da doğru cevap verdiniz!\n\n");
                score++; // Eş anlamlıya da doğru cevap verildiğinde skor ekle
            } else {
                resultArea.append("Yanlış, doğru cevap: " + correctSynonym + "\n\n");
            }

            // Butonları devre dışı bırak ve "Sonraki" butonunu aktif yap
            for (JButton button : meaningButtons) {
                button.setEnabled(false); // Butonları devre dışı bırak
            }
            nextButton.setEnabled(true); // "Sonraki" butonunu aktif yap
        }
    }

    // "Sonraki" butonuna tıklama olayları
    class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreLabel.setText("Skor: " + score); // Skoru güncelle

            // Sonraki soruya geç
            currentQuestion++;
            if (currentQuestion < words.length) {
                loadQuestion(); // Yeni soruyu yükle
            } else {
                resultArea.append("Oyun bitti! Final Skoru: " + score + "\n"); // Oyun bitti mesajı
                nextButton.setEnabled(false); // "Sonraki" butonunu devre dışı bırak
                for (JButton button : meaningButtons) {
                    button.setEnabled(false); // Butonları devre dışı bırak
                }
            }
        }
    }
}
