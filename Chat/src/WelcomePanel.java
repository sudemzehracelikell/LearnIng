/* Grup Üyeleri
Sudem Zehra Çelikel-230609045
Hamdi Berat Köybaşı-23060035
Yusuf Deligöz- 230609020
İlknur Çelik – 230609058
Berat Serçe – 230609005
Berk Sözcü -230609040
*/
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class WelcomePanel extends JPanel {
    public WelcomePanel() {
        // Arka plan rengini lacivert yap
        setBackground(new Color(36, 45, 63));

        // JLabel oluştur ve metin ayarlarını yap
        JLabel label = new JLabel("LearnIng", SwingConstants.CENTER);
        label.setForeground(Color.WHITE); // Metin rengini beyaz yap
        label.setFont(new Font("Arial", Font.BOLD, 40)); // Daha büyük ve kalın bir yazı tipi
        label.setHorizontalAlignment(SwingConstants.CENTER); // Metni yatayda ortala
        label.setVerticalAlignment(SwingConstants.CENTER); // Metni dikeyde ortala

        // Panelin layoutunu null yap, böylece tam kontrol sağlanır
        setLayout(null);

        // JLabel'in boyut ve konumunu ayarla
        label.setBounds(0, 0, 600, 600); // Genişlik ve yükseklik verilir (örnek boyutlar)
        add(label);
    }
}
