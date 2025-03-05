import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cark {
    private DefaultListModel<String> favoriteListModel;
    private JList<String> favoriteList;
    private JTextField movieInput;

    public Cark() {
        JFrame frame = new JFrame("Favoriler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        favoriteListModel = new DefaultListModel<>();
        favoriteList = new JList<>(favoriteListModel);
        JScrollPane scrollPane = new JScrollPane(favoriteList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        movieInput = new JTextField(20);
        JButton addButton = new JButton("Ekle");
        JButton removeButton = new JButton("Kaldır");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movie = movieInput.getText().trim();
                if (!movie.isEmpty() && !favoriteListModel.contains(movie)) {
                    favoriteListModel.addElement(movie);
                    movieInput.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = favoriteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    favoriteListModel.remove(selectedIndex);
                }
            }
        });

        inputPanel.add(movieInput);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cark::new);
    }
}
