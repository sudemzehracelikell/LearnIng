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

// Uygulamanın başlatıldığı ana sınıf
public class Main1 {
    public static void main(String[] args) {
        // Ana frame (pencere) başlatma
        JFrame frame = new JFrame("LearnIng - Dil Öğrenme Uygulaması");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Sol panel (Bordo arka plan)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(102, 0, 53)); // Renk seçimi
        leftPanel.setBounds(0, 0, 250, 400); // Panelin boyutları ve pozisyonu
        leftPanel.setLayout(null); // Layout ayarı
        frame.add(leftPanel);

        // Sol panelde başlık
        JLabel appTitle = new JLabel("LearnIng");
        appTitle.setFont(new Font("Arial", Font.BOLD, 28)); // Başlık fontu
        appTitle.setForeground(Color.WHITE); // Başlık rengi
        appTitle.setBounds(60, 50, 200, 30); // Başlık pozisyonu
        leftPanel.add(appTitle);

        // Sol panelde açıklama metni
        String descriptionText = "İngilizcenin Derinliklerini \nKeşfetmeye Hazır mısın?";
        JPanel descriptionPanel = createMultilineLabel(descriptionText, new Font("Arial", Font.PLAIN, 14), Color.WHITE);
        descriptionPanel.setBounds(40, 100, 170, 50); // Açıklama paneli pozisyonu
        leftPanel.add(descriptionPanel);

        // Sol panelde resim ekleme
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\szc\\Downloads\\WhatsApp Görsel 2025-01-08 saat 23.06.54_8a8297d2-Photoroom-Photoroom.png"); // Resim yolu
        Image img = imageIcon.getImage(); // Resmi al
        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Resmi boyutlandırma
        ImageIcon scaledIcon = new ImageIcon(scaledImg); // Boyutlandırılmış resmi ImageIcon'a çevirme
        imageLabel.setIcon(scaledIcon); // Etikete resmi ekle
        imageLabel.setBounds(50, 160, 150, 150); // Resmin pozisyonu ve boyutu
        leftPanel.add(imageLabel);

        // Sağ panel (Koyu arka plan)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(34, 45, 65)); // Sağ panelin arka plan rengi
        rightPanel.setBounds(250, 0, 350, 400); // Sağ panelin boyutları ve pozisyonu
        rightPanel.setLayout(null); // Layout ayarı
        frame.add(rightPanel);

        // Kullanıcı adı girişi
        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameLabel.setForeground(Color.WHITE); // Etiketin rengi
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Font ayarı
        usernameLabel.setBounds(30, 200, 100, 25); // Etiket pozisyonu
        rightPanel.add(usernameLabel);

        // Kullanıcı adı text alanı
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 230, 280, 30); // Alanın pozisyonu ve boyutu
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Kenarlık yok
        rightPanel.add(usernameField);

        // Giriş butonu
        JButton loginButton = new JButton("Giriş Yap");
        loginButton.setBackground(new Color(102, 0, 53)); // Butonun arka plan rengi
        loginButton.setForeground(Color.BLACK); // Buton yazı rengi
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); // Font ayarı
        loginButton.setBounds(30, 280, 280, 40); // Buton pozisyonu ve boyutu
        loginButton.setFocusPainted(false); // Odaklanıldığında butonun kenarını çizme
        rightPanel.add(loginButton);

        // Giriş butonuna aksiyon ekle
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // Kullanıcı adını al

                // Eğer kullanıcı adı girilmişse
                if (!username.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Giriş Başarılı!\nHoş geldiniz, " + username + "!");
                    frame.setVisible(false); // Giriş penceresini gizle

                    // Ana Menü penceresini oluştur
                    SwingUtilities.invokeLater(() -> {
                        AppFrame appFrame = new AppFrame();
                        appFrame.setVisible(true);
                    });
                } else {
                    // Eğer kullanıcı adı girilmemişse hata mesajı göster
                    JOptionPane.showMessageDialog(frame, "Lütfen kullanıcı adını girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Frame'i görünür yap
        frame.setVisible(true);
    }

    // Çok satırlı metin oluşturmak için yardımcı yöntem
    private static JPanel createMultilineLabel(String text, Font font, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Dikey düzen
        panel.setBackground(new Color(102, 0, 53)); // Sol panelin arka plan rengiyle uyumlu

        String[] lines = text.split("\n"); // Metni satırlara ayır
        for (String line : lines) {
            JLabel label = new JLabel(line);
            label.setFont(font); // Yazı fontu
            label.setForeground(color); // Yazı rengi
            panel.add(label); // Etiketi panele ekle
        }

        return panel; // Paneli geri döndür
    }
}

// Ana Menü Frame Sınıfı
class AppFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    public AppFrame() {
        setTitle("Dil Uygulaması");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Sol Menü Paneli
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(160, getHeight())); // Menü panelinin boyutları
        menuPanel.setLayout(new GridLayout(7, 1, 5, 5)); // Menü öğelerinin düzeni
        menuPanel.setBackground(new Color(102, 0, 53)); // Menü panelinin rengi

        // İçerik Paneli (CardLayout kullanılıyor)
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout); // CardLayout ile panel değişimi sağlanacak

        // Panelleri başlat
        WelcomePanel welcomePanel = new WelcomePanel();
        WordsPanel wordsPanel = new WordsPanel();
        EnglishLearningGame englishLearningGame = new EnglishLearningGame();
        WordMatch wordMatch = new WordMatch();
        HangmanWindow hangmanWindow = new HangmanWindow();
        ChatWindow chatWindow = new ChatWindow();
        WordQuiz wordQuiz = new WordQuiz(wordsPanel); // WordsPanel'i WordQuiz'e geçir

        // Panelleri CardLayout'a ekle
        contentPanel.add(welcomePanel, "Welcome");
        contentPanel.add(wordsPanel, "Words");
        contentPanel.add(englishLearningGame, "EnglishLearningGame");
        contentPanel.add(wordMatch, "WordMatch");
        contentPanel.add(hangmanWindow, "HangmanWindow");
        contentPanel.add(chatWindow, "ChatWindow");
        contentPanel.add(wordQuiz, "WordQuiz"); // WordQuiz'i ekle

        // Menü Butonları
        JButton chatWindowButton = new JButton("ChatWindow");
        JButton wordsButton = new JButton("Kelimeler");
        JButton englishLearningGameButton = new JButton("English Game");
        JButton wordMatchButton = new JButton("Word Match");
        JButton hangmanWindowButton = new JButton("Hangman");
        JButton wordQuizButton = new JButton("Word Quiz"); // WordQuiz butonunu ekle

        // Butonlara aksiyon dinleyicileri ekle
        wordsButton.addActionListener(e -> cardLayout.show(contentPanel, "Words"));
        englishLearningGameButton.addActionListener(e -> cardLayout.show(contentPanel, "EnglishLearningGame"));
        wordMatchButton.addActionListener(e -> cardLayout.show(contentPanel, "WordMatch"));
        hangmanWindowButton.addActionListener(e -> cardLayout.show(contentPanel, "HangmanWindow"));
        chatWindowButton.addActionListener(e -> cardLayout.show(contentPanel, "ChatWindow"));
        wordQuizButton.addActionListener(e -> cardLayout.show(contentPanel, "WordQuiz")); // WordQuiz butonunun aksiyonu

        // Butonları menü paneline ekle
        menuPanel.add(chatWindowButton);
        menuPanel.add(wordsButton);
        menuPanel.add(englishLearningGameButton);
        menuPanel.add(wordMatchButton);
        menuPanel.add(hangmanWindowButton);
        menuPanel.add(wordQuizButton); // WordQuiz butonunu menüye ekle

        // Panelleri frame'e ekle
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true); // Frame görünür yap
    }
}
