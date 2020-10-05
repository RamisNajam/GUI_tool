import java.util.ArrayList;

/**
 * This class has an ArrayList that holds all of the pendingTransfers from the Excel file
 */
public class PendingTransfers {
  ArrayList<Transfer> pendingTransfers;

  PendingTransfers() { pendingTransfers = ImportTransfers.importSpreadsheet(); }

  Transfer getTransfer(String transfer_id) {
    for(Transfer transfer : pendingTransfers) {
      if(transfer.getTransferID().equals(transfer_id)) {
        return transfer;
      }
    }
    return null;
  }

}
