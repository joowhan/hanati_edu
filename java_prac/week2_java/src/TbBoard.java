public class TbBoard {
    private int nbBoard;
    private String nmTitle;
    private String nmContent;
    private String nmWriter;
    private String daWrite;
    private int cnHit;
    private String idFile;
    TbBoard(){}

    TbBoard(String daWrite, String nmTitle, String nmContent, String nmWriter, int cnHit){
        this.daWrite = daWrite;
        this.nmTitle = nmTitle;
        if(nmContent.length()>4000){
            System.out.println(nmContent.length());
            this.nmContent = nmContent.substring(0,3999);
            System.out.println(nmContent.length());
        }
        else{
            this.nmContent = nmContent;
        }

        this.nmWriter = nmWriter;
        this.cnHit = cnHit;
    }
    public int getNbBoard() {
        return nbBoard;
    }

    public String getNmTitle() {
        return nmTitle;
    }

    public String getNmContent() {
        return nmContent;
    }

    public String getNmWriter() {
        return nmWriter;
    }

    public String getDaWrite() {
        return daWrite;
    }

    public int getCnHit() {
        return cnHit;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setNbBoard(int nbBoard) {
        this.nbBoard = nbBoard;
    }

    public void setNmTitle(String nmTitle) {
        this.nmTitle = nmTitle;
    }

    public void setNmContent(String nmContent) {
        this.nmContent = nmContent;
    }

    public void setNmWriter(String nmWriter) {
        this.nmWriter = nmWriter;
    }

    public void setDaWrite(String daWrite) {
        this.daWrite = daWrite;
    }

    public void setCnHit(int cnHit) {
        this.cnHit = cnHit;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }
}
