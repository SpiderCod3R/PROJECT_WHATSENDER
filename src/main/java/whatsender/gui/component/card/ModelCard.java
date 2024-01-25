package whatsender.gui.component.card;

import java.util.Objects;
import javax.swing.Icon;

public class ModelCard {
    private String title;
    private String description;
    private Integer qtdeMessages;
    private double values;
    private int percentage;
    private Icon icon;

    public ModelCard() {
    }
    
    public ModelCard(String title, double values, int percentage, Icon icon) {
        this.title = title;
        this.values = values;
        this.percentage = percentage;
        this.icon = icon;
    }

    public ModelCard(String title, String description, Integer qtdeMessages, double values, Icon icon) {
        this.title = title;
        this.description = description;
        this.qtdeMessages = qtdeMessages;
        this.values = values;

        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQtdeMessages() {
        return qtdeMessages.toString();
    }

    public void setQtdeMessages(Integer qtdeMessages) {
        this.qtdeMessages = qtdeMessages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValues() {
        return values;
    }

    public void setValues(double values) {
        this.values = values;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.values) ^ (Double.doubleToLongBits(this.values) >>> 32));
        hash = 53 * hash + this.percentage;
        hash = 53 * hash + Objects.hashCode(this.icon);
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
        final ModelCard other = (ModelCard) obj;
        if (this.percentage != other.percentage) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }
    
    
}
