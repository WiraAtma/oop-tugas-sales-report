import Service.*;
import Model.Transaction;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> data = SalesReader.readFile("sales.txt");

        List<Transaction> trx = TransactionProcessor.parseData(data);

        double total = TransactionProcessor.getTotalSales(trx);

        SalesReport.writeReport("report.txt", total);

        System.out.println("Laporan berhasil dibuat!");
    }
}
