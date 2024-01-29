package whatsender.application.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Admin.TABLE_NAME)
public class Admin implements Serializable {
    public static final String TABLE_NAME= "tb_admin_user";
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int adminId;
    
    private String admin_name;
    private String password;

    public Admin() {
        super();
    }

    public Admin(int adminId, String admin_name, String hashKey) {
        super();
        this.adminId = adminId;
        this.admin_name = admin_name;
        this.password = hashKey;
    }
    
    public Boolean isNull(){
        return (("".equals(getAdmin_name())) || ("".equals(getPassword())));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    public boolean check_login(){
        System.out.println(this.getAdmin_name());
        System.out.println(this.getPassword());
        if ( (this.getAdmin_name().equals("GlobalnetsisAdmin")) && (this.getPassword().equals("d68a34c7d9dc69b57fb93db459b1b4ac67b11aea7df4ff851124e0c2b2aa9e88"))){
            return true;
        }
        return false;
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
