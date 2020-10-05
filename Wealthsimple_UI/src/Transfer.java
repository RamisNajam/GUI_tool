/**
 * An object that represents one transfer and all of its attributes
 */
public class Transfer {
  private String transfer_id;
  private String transfer_state;
  String account_type;
  String transfer_type;

  Transfer(String transfer_id, String transfer_state, String account_type,
      String transfer_type) {
    this.transfer_id = transfer_id;
    this.transfer_state = transfer_state;
    this.account_type = account_type;
    this.transfer_type = transfer_type;
  }

  String getTransferID() { return this.transfer_id; }

  /**
   * This method reads the item's transfer state. Then, it appends the relevant
   * status details to the transfer state and returns the String.
   *
   * @return String The string with the relevant transfer status
   */
  String getTransferState() {
    String value = this.transfer_state;
    if(value.equals("prepared")) {
      return value + " - please sign the required forms.";
    } else if(value.equals("processing")) {
      return value + " - we are in the process of sending it to the financial institution.";
    } else if(value.equals("sent")) {
      return value + " - we have sent the request for transfer to the financial institution.";
    } else if(value.equals("accepted")) {
      return value + " - funds have arrived.";
    }
    return value;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(transfer_id + ' ');
    sb.append(transfer_state + ' ');
    sb.append(account_type + ' ');
    sb.append(transfer_type);
    return sb.toString();
  }
}
