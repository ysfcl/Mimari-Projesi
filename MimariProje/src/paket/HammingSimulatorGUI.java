package paket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class HammingSimulatorGUI extends JFrame{

	private JTextField inputField, errorBitField;
    private JTextArea outputArea;
    private JButton calculateButton, injectErrorButton, correctErrorButton;
    private BitPanel bitPanel;

    private String encodedData;

    private DefaultListModel<String> memoryModel;
    private JList<String> memoryList;
    private int selectedIndex = -1;
    private java.util.List<String> encodedMemory;
    
    public HammingSimulatorGUI() {
        
    	setTitle("Hamming SEC-DED Simulatoru");
        setSize(650, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 1));

        
        //Veri girisi
        JPanel dataPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(32);
        dataPanel.add(new JLabel("Veri (8/16/32 bit):"));
        dataPanel.add(inputField);
        calculateButton = new JButton("Bellege Yaz (Hamming Kod)");
        dataPanel.add(calculateButton);
        
        
        //Hata bit pozisyonu
        JPanel errorPanel = new JPanel(new FlowLayout());
        errorBitField = new JTextField(5);
        errorPanel.add(new JLabel("Hata Eklenecek Bit Pozisyonu:"));
        errorPanel.add(errorBitField);
        injectErrorButton = new JButton("Hata Ekle");
        errorPanel.add(injectErrorButton);

        
        //Hata duzeltme
        JPanel correctionPanel = new JPanel(new FlowLayout());
        correctErrorButton = new JButton("Hata Tespit Et & Duzelt");
        correctionPanel.add(correctErrorButton);

        
        //Panele ekleme
        inputPanel.add(dataPanel);
        inputPanel.add(errorPanel);
        inputPanel.add(correctionPanel);
        
        
        //Cikti alani
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        
        //BitPanel'in GUI'ye entegrasyonu
        bitPanel = new BitPanel();
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bitPanel, BorderLayout.SOUTH);
        add(panel);

        
        // Event handlers
        calculateButton.addActionListener(e -> writeToMemory());
        injectErrorButton.addActionListener(e -> injectError());
        correctErrorButton.addActionListener(e -> correctError());

        encodedMemory = new ArrayList<>();

        
        //Bellek listesi
        memoryModel = new DefaultListModel<>();
     	memoryList = new JList<>(memoryModel);
     	memoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     	memoryList.addListSelectionListener(e -> {
        selectedIndex = memoryList.getSelectedIndex();

         if (selectedIndex >= 0) {
             encodedData = encodedMemory.get(selectedIndex);
             outputArea.setText("Secilen Veri:\n" + encodedData);
             bitPanel.showBits(encodedData, 0, 0);
         	}
     	});
     
     	panel.add(new JScrollPane(memoryList), BorderLayout.EAST);
       
        setVisible(true);
    }

    
    private void writeToMemory() {
    	String data = inputField.getText().trim();

        if (!(data.matches("[01]+") && (data.length() == 8 || data.length() == 16 || data.length() == 32))) {
            JOptionPane.showMessageDialog(this, "Lutfen yalnizca 8, 16 veya 32 bitlik 0 ve 1 iceren bir veri girin.", "Hatali Giris!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = HammingEncoder.encode(data);
        encodedMemory.add(result);
        memoryModel.addElement("Veri " + encodedMemory.size() + " (" + data.length() + " bit)");
        outputArea.setText("Bellege yazma islemi basarili!\nKod:\n" + result);
        inputField.setText("");
        
        int lastIndex = encodedMemory.size() - 1;
        memoryList.setSelectedIndex(lastIndex);
        encodedData = encodedMemory.get(lastIndex);
        bitPanel.showBits(encodedData, 0, 0);
    }

    
    private void injectError() {
    
    	String[] positions = errorBitField.getText().trim().split(",");
    	
    	if (selectedIndex < 0 || encodedData == null) {
    	    JOptionPane.showMessageDialog(this, "Once bellekteki bir veriyi secmelisiniz.", "Secim Yapilmadi", JOptionPane.WARNING_MESSAGE);
    	    return;
    	}
    	
    	
    	try {
    	    if (positions.length == 0 || positions.length > 2) {
    	        throw new NumberFormatException();
    	    }

    	    for (String posStr : positions) {
    	        int pos = Integer.parseInt(posStr.trim());
    	        if (pos < 1 || pos > encodedData.length()) {
    	            throw new NumberFormatException();
    	        }

    	        char[] bits = encodedData.toCharArray();
    	        bits[pos - 1] = (bits[pos - 1] == '0') ? '1' : '0';
    	        encodedData = new String(bits);
    	    }

    	    	//Bellegi guncelle
    	    if (selectedIndex >= 0) {
    	        encodedMemory.set(selectedIndex, encodedData);
    	    }

    	    outputArea.setText("Hata(lar) eklendi!\nYeni Veri:\n" + encodedData);
    	    bitPanel.showBits(encodedData,
    	        (positions.length > 0 ? Integer.parseInt(positions[0].trim()) : 0),0);

    	} catch (NumberFormatException ex) {
    	    JOptionPane.showMessageDialog(this, "Gecerli bir pozisyon giriniz. Ornek:3 veya 3,5", "Hatali Giris", JOptionPane.ERROR_MESSAGE);
    }
 }
    
    
    private void correctError() {
        
    	if (encodedData == null) {
            JOptionPane.showMessageDialog(this, "Once veri girip bellege yazmalisiniz.", "Bellek Bos", JOptionPane.WARNING_MESSAGE);
            return;
        }

    	
        String result = HammingEncoder.detectAndCorrect(encodedData);
        outputArea.setText(result);

        
        // Pozisyonu bulup yesille gosterme islemi
        if (result.contains("Pozisyon:")) {
            int pos = Integer.parseInt(result.split("Pozisyon: ")[1].split("\n")[0].trim());
            encodedData = result.split("Kod:\n")[1].trim();   
            bitPanel.showBits(encodedData, 0, pos);
        } else {
            bitPanel.showBits(encodedData, 0, 0);
        }
    }
}
