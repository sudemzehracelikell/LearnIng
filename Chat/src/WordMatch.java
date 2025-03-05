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
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class WordMatch extends JPanel {
    // Kelimeler ve anlamları
    private final String[] words = {"apple", "banana", "car", "dog", "house"};
    private final String[] meanings = {"elma", "muz", "araba", "köpek", "ev"};

    // GUI bileşenleri
    private JPanel wordPanel, meaningPanel;
    private JLabel[] wordLabels, meaningLabels;
    private int score = 0; // Skor değişkeni
    private JLabel scoreLabel; // Skor etiketi
    private JLabel selectedWordLabel = null; // Seçilen kelime etiketi

    private ArrayList<String> shuffledMeanings; // Karıştırılmış anlamlar listesi

    public WordMatch() {
        setLayout(new BorderLayout()); // BorderLayout düzeni

        // Skor etiketi
        scoreLabel = new JLabel("Skor: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel, BorderLayout.NORTH);

        // Kelimeler için panel
        wordPanel = new JPanel();
        wordPanel.setLayout(new GridLayout(words.length, 1)); // Kelimeler için grid düzeni
        wordLabels = new JLabel[words.length];
        for (int i = 0; i < words.length; i++) {
            wordLabels[i] = new JLabel(words[i], SwingConstants.CENTER);
            wordLabels[i].setFont(new Font("Arial", Font.PLAIN, 16));
            wordLabels[i].setOpaque(true); // Etiketi şeffaf yap
            wordLabels[i].setBackground(Color.LIGHT_GRAY); // Başlangıçta açık gri renk
            wordLabels[i].addMouseListener(new WordLabelListener(i)); // Kelime etiketine tıklama dinleyicisi ekle
            wordPanel.add(wordLabels[i]);
        }

        // Anlamları karıştır
        shuffledMeanings = new ArrayList<>();
        Collections.addAll(shuffledMeanings, meanings);
        Collections.shuffle(shuffledMeanings); // Anlamları karıştır

        // Anlamlar için panel
        meaningPanel = new JPanel();
        meaningPanel.setLayout(new GridLayout(meanings.length, 1)); // Anlamlar için grid düzeni
        meaningLabels = new JLabel[meanings.length];
        for (int i = 0; i < meanings.length; i++) {
            meaningLabels[i] = new JLabel(shuffledMeanings.get(i), SwingConstants.CENTER);
            meaningLabels[i].setFont(new Font("Arial", Font.PLAIN, 16));
            meaningLabels[i].setOpaque(true); // Etiketi şeffaf yap
            meaningLabels[i].setBackground(Color.LIGHT_GRAY); // Başlangıçta açık gri renk
            meaningLabels[i].addMouseListener(new MeaningLabelListener(i)); // Anlam etiketine tıklama dinleyicisi ekle
            meaningPanel.add(meaningLabels[i]);
        }

        // Ana panel (kelimeler ve anlamlar panelini yan yana yerleştir)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // İki sütunlu düzen
        mainPanel.add(wordPanel); // Kelimeler paneli
        mainPanel.add(meaningPanel); // Anlamlar paneli
        add(mainPanel, BorderLayout.CENTER); // Ana paneli merkeze ekle
    }

    // Kelime etiketine tıklama olayını işleyen sınıf
    class WordLabelListener extends MouseAdapter {
        int wordIndex;

        public WordLabelListener(int wordIndex) {
            this.wordIndex = wordIndex; // Kelime indeksini sakla
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Önceden seçilen kelimeyi sıfırla
            if (selectedWordLabel != null) {
                selectedWordLabel.setBackground(Color.LIGHT_GRAY);
            }
            // Yeni seçilen kelimeyi işaretle
            selectedWordLabel = wordLabels[wordIndex];
            selectedWordLabel.setBackground(Color.YELLOW); // Seçilen kelimeyi sarı ile işaretle
        }
    }

    // Anlam etiketine tıklama olayını işleyen sınıf
    class MeaningLabelListener extends MouseAdapter {
        int meaningIndex;

        public MeaningLabelListener(int meaningIndex) {
            this.meaningIndex = meaningIndex; // Anlam indeksini sakla
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Seçilen kelime varsa, anlamı ile karşılaştır
            if (selectedWordLabel != null) {
                String selectedWord = selectedWordLabel.getText(); // Seçilen kelimeyi al
                String selectedMeaning = meaningLabels[meaningIndex].getText(); // Seçilen anlamı al

                int wordIndex = -1;
                // Kelimenin doğru indeksiyle karşılaştır
                for (int i = 0; i < words.length; i++) {
                    if (selectedWord.equals(words[i])) {
                        wordIndex = i;
                        break;
                    }
                }

                // Eğer kelime ve anlam eşleşiyorsa
                if (wordIndex != -1 && meanings[wordIndex].equals(selectedMeaning)) {
                    selectedWordLabel.setBackground(Color.GREEN); // Yeşil ile işaretle
                    meaningLabels[meaningIndex].setBackground(Color.GREEN); // Anlam etiketini yeşil yap
                    score++; // Skoru artır
                    scoreLabel.setText("Skor: " + score); // Skoru güncelle

                    // Eğer tüm eşleştirmeler tamamlandıysa, kazandınız mesajı göster
                    if (score == words.length) {
                        showWinningMessage();
                    }
                } else {
                    // Yanlış eşleşme durumunda kırmızı ile işaretle
                    selectedWordLabel.setBackground(Color.RED);
                    meaningLabels[meaningIndex].setBackground(Color.RED);
                }
                selectedWordLabel = null; // Seçimi sıfırla
            }
        }
    }

    // Kazanma mesajını gösteren metod
    private void showWinningMessage() {
        JOptionPane.showMessageDialog(this, "Tebrikler! Tüm eşleştirmeleri doğru yaptınız!",
                "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE); // Bilgilendirme penceresi
    }
}
