package ses;

public class EmailResult {
    // create an empty constructor
    public EmailResult() {
    }

    // create variables message and timestamp
    private String message;
    private String timestamp;

    // create getters and setters for message and timestamp
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
