package whatsender.application.entities;

import java.util.Objects;

/**
 *
 * @author ALEXANDRE
 */
public class Admin {
    private int adminId;
    private String admin_name;

    public Admin() {
    }

    public Admin(int adminId, String admin_name) {
        this.adminId = adminId;
        this.admin_name = admin_name;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.adminId;
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
        final Admin other = (Admin) obj;
        return Objects.equals(this.admin_name, other.admin_name);
    }

    @Override
    public String toString() {
        return "Admin{" + "adminId=" + adminId + ", admin_name=" + admin_name + '}';
    }
    
    
    
}
