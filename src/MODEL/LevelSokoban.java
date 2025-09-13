/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author ASUS
 */
public class LevelSokoban {
    private String idLV;   
    private String tenLV;   
    private String tenFile; 
    private int cot;        
    private int dong;     

    // Constructor
    public LevelSokoban(String idLV, String tenLV, String tenFile, int cot, int dong) {
        this.idLV = idLV;
        this.tenLV = tenLV;
        this.tenFile = tenFile;
        this.cot = cot;
        this.dong = dong;
    }

    public String getIdLV() {
        return idLV;
    }

    public void setIdLV(String idLV) {
        this.idLV = idLV;
    }

    public String getTenLV() {
        return tenLV;
    }

    public void setTenLV(String tenLV) {
        this.tenLV = tenLV;
    }

    public String getTenFile() {
        return tenFile;
    }

    public void setTenFile(String tenFile) {
        this.tenFile = tenFile;
    }

    public int getCot() {
        return cot;
    }

    public void setCot(int cot) {
        this.cot = cot;
    }

    public int getDong() {
        return dong;
    }

    public void setDong(int dong) {
        this.dong = dong;
    }
    
}
