package whatsender.application.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ALEXANDRE
 */
@Entity
@Table(name = Pacote.TABLE_NAME)
public class Pacote implements Serializable{
    public static final String TABLE_NAME= "tb_pacote";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String pacoteNome;
    private String pacoteDescricao;
    private int qtdeMensagens;
    private int qtdeMensagensMensais;
    private float valor;
    private String usuarios;
    

    public Pacote() {}

    public Pacote(Integer id, String pacoteNome, String pacoteDescricao, int qtdeMensagens, int qtdeMensagensMensais, String valor, String usuarios) {
        this.id = id;
        this.pacoteNome = pacoteNome;
        this.pacoteDescricao = pacoteDescricao;
        this.qtdeMensagens = qtdeMensagens;
        this.qtdeMensagensMensais = qtdeMensagensMensais;
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        decimalFormat.setDecimalFormatSymbols(symbols);

        Float ValorFormatado;
        try {
            ValorFormatado = decimalFormat.parse(valor).floatValue();
            this.valor = ValorFormatado;
        } catch (ParseException ex) {
            Logger.getLogger(Pacote.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.usuarios = usuarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPacoteNome() {
        return pacoteNome;
    }

    public void setPacoteNome(String pacoteNome) {
        this.pacoteNome = pacoteNome;
    }

    public String getPacoteDescricao() {
        return pacoteDescricao;
    }

    public void setPacoteDescricao(String pacoteDescricao) {
        this.pacoteDescricao = pacoteDescricao;
    }

    public int getQtdeMensagens() {
        return qtdeMensagens;
    }

    public void setQtdeMensagens(int qtdeMensagens) {
        this.qtdeMensagens = qtdeMensagens;
    }

    public int getQtdeMensagensMensais() {
        return qtdeMensagensMensais;
    }

    public void setQtdeMensagensMensais(int qtdeMensagensMensais) {
        this.qtdeMensagensMensais = qtdeMensagensMensais;
    }

    public float getValorPacote() {
        return valor;
    }

    public void setValorPacote(float valor) {
        this.valor = valor;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Pacote other = (Pacote) obj;
        if (this.qtdeMensagens != other.qtdeMensagens) {
            return false;
        }
        if (this.qtdeMensagensMensais != other.qtdeMensagensMensais) {
            return false;
        }
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.pacoteNome, other.pacoteNome)) {
            return false;
        }
        if (!Objects.equals(this.pacoteDescricao, other.pacoteDescricao)) {
            return false;
        }
        if (!Objects.equals(this.usuarios, other.usuarios)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Pacote{" + "id=" + id + ", pacoteNome=" + pacoteNome + ", pacoteDescricao=" + pacoteDescricao + ", qtdeMensagens=" + qtdeMensagens + ", qtdeMensagensMensais=" + qtdeMensagensMensais + ", valor=" + valor + ", usuarios=" + usuarios + '}';
    }

    
}
