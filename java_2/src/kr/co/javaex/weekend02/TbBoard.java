package kr.co.javaex.weekend02;

public class TbBoard {
    private int nbBoard;
    private String nmTitle;
    private String nmcontent;
    private String nmWriter;
    private String daWrite;
    private int cnHit;
    private String idFile;

    public int getNbBoard() {
        return nbBoard;
    }

    public void setNbBoard(int nbBoard) {
        this.nbBoard = nbBoard;
    }

    public String getNmTitle() {
        return nmTitle;
    }

    public void setNmTitle(String nmTitle) {
        this.nmTitle = nmTitle;
    }

    public String getNmcontent() {
        return nmcontent;
    }

    public void setNmcontent(String nmcontent) {
        this.nmcontent = nmcontent;
    }

    public String getNmWriter() {
        return nmWriter;
    }

    public void setNmWriter(String nmWriter) {
        this.nmWriter = nmWriter;
    }

    public String getDaWrite() {
        return daWrite;
    }

    public void setDaWrite(String daWrite) {
        this.daWrite = daWrite;
    }

    public int getCnHit() {
        return cnHit;
    }

    public void setCnHit(int cnHit) {
        this.cnHit = cnHit;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }
}
