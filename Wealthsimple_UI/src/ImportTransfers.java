import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * This class imorts the Transfers stored on TransferData.txt and adds them to an ArrayList.
 * Then, it is returned to PendingTransfers.
 */
class ImportTransfers {
  static ArrayList<Transfer> importSpreadsheet() {

    //String ImportTransfersPath = ImportTransfers.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    //String res_path = ImportTransfers.class.classLoader.getResource();
    // final String path = ImportTransfers.class.getClassLoader().getResource("TransferData.txt").getPath();
    //InputStream textStream = ImportTransfers.class.getClass().getClassLoader().getResourceAsStream();
    //System.out.println(textStream.toString());





    ArrayList<Transfer> pendingTransfers = new ArrayList<>();
    try {
      File textFile = new File("C:\\Users\\Ramis\\IdeaProjects\\Wealthsimple_UI\\TransferData.txt");
      BufferedReader input = new BufferedReader(new FileReader(textFile));

      String line = input.readLine();
      while (!(line == null)) {
        String[] strs = line.split(" ");

        String transfer_id = strs[0];
        String transfer_state = strs[1];
        String account_type = strs[2];
        String transfer_type = strs[3];

        Transfer transfer = new Transfer(transfer_id, transfer_state, account_type, transfer_type);
        pendingTransfers.add(transfer);
        line = input.readLine();
      }
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return pendingTransfers;
  }
}
