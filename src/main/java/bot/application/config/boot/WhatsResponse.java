package bot.application.config.boot;

import java.util.Objects;


public class WhatsResponse {
    
    private String result;
    private String result_message;

    public WhatsResponse(String result, String result_message) {
        this.result = result;
        this.result_message = result_message;
    }

    public WhatsResponse() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.result);
        hash = 97 * hash + Objects.hashCode(this.result_message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WhatsResponse other = (WhatsResponse) obj;
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return Objects.equals(this.result_message, other.result_message);
    }
    
    
    
}
