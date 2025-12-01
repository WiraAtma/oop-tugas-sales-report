import Model.Transaction;
import Service.SalesReader;
import Service.SalesReport;
import Service.TransactionProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainGUI {
    List<Transaction> transactions = new ArrayList<>();
    SalesReader reader = new SalesReader();
    TransactionProcessor processor = new TransactionProcessor();
    SalesReport report = new SalesReport();

    public void start() {
        JFrame frame = new JFrame("Sales Report");
        frame.setSize(700, 500);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());

        JButton loadButton = new JButton("Load Sales File");
        JButton generateButton = new JButton("Generate Report");
        topPanel.add(loadButton);
        topPanel.add(generateButton);

        String[] columns = new String[]{"ID", "Nama", "Qty", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JLabel label3 = new JLabel("Total : 0");

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(label3, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> {
            List<String> salesData = reader.readFromFile("sales.txt");
            transactions = processor.parseSalesData(salesData);

            for(Transaction t : transactions) {
                tableModel.addRow(new Object[] {t.getId(), t.getItem(), t.getQuantity(), t.getPrice()});
            }
            double revenue = transactions.stream().mapToInt(t -> (int) (t.quantity * t.price)).sum();

            label3.setText("Total : " + revenue);
        });

        generateButton.addActionListener(e -> {
            double total = processor.getTotalSales(transactions);
            report.writeReport("report.txt", transactions, total);

            JOptionPane.showMessageDialog(frame, "Laporan Berhasil");
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI().start();
    }
}
