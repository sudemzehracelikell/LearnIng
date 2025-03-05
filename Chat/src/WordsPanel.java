/* Grup Üyeleri
Sudem Zehra Çelikel-230609045
Hamdi Berat Köybaşı-23060035
Yusuf Deligöz- 230609020
İlknur Çelik – 230609058
Berat Serçe – 230609005
Berk Sözcü -230609040
*/
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class WordsPanel extends JPanel {
    private final Box wordsBox; // Kelimelerin dikey olarak yerleştirileceği Box

    public WordsPanel() {
        setLayout(new BorderLayout()); // BorderLayout kullanarak düzeni ayarlıyoruz
        wordsBox = Box.createVerticalBox(); // Kelimelerin sıralanacağı dikey kutu
        add(new JScrollPane(wordsBox), BorderLayout.CENTER); // Box'ı ScrollPane ile sarıp, ortada göstermek için ekliyoruz
    }

    public void addWord(String word) {
        JLabel wordLabel = new JLabel(word); // Kelimeyi bir JLabel'e dönüştürüyoruz
        wordsBox.add(wordLabel); // Kelimeyi wordsBox'a ekliyoruz
        wordsBox.revalidate(); // Box'ı güncelliyoruz ki yeni eklenen kelime görünür hale gelsin
    }
}
