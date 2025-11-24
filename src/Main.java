import Service.*;
import Model.Transaction;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> salesData = SalesReader.readFromFile("sales.txt");

        List<Transaction> transactionData = TransactionProcessor.parseSalesData(salesData);

        double total = TransactionProcessor.getTotalSales(transactionData);

        // Ubah dari:
        // SalesReport.writeReport("report.txt", total);
        // Menjadi:
        SalesReport.writeReport("report.txt", transactionData, total);

        System.out.println("Laporan berhasil dibuat, Cek report.txt");

        for(String data : salesData) {
            System.out.println(data);
        }
    }
}