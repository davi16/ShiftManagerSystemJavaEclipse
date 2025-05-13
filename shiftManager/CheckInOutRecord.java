package shiftManager;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckInOutRecord {

	private LocalDateTime inRecord;
	private LocalDateTime outRecord;

	public LocalDateTime getInRecord() {
		return inRecord;
	}

	public void setInRecord(LocalDateTime inRecord) {
		this.inRecord = inRecord;
	}

	public LocalDateTime getOutRecord() {
		return outRecord;
	}

	public void setOutRecord(LocalDateTime outRecord) {
		this.outRecord = outRecord;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String out = (outRecord != null) ? "" + outRecord.toLocalTime().format(formatter) : "null";
		return inRecord.toLocalDate() + " Start: " + inRecord.toLocalTime().format(formatter) + ", Finish:" + out;
	}
}
