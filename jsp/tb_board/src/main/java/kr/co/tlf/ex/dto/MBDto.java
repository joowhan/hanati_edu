package kr.co.tlf.ex.dto;

public class MBDto {
	private String nbBoard;
	private String nmTitle;
	private String nmContent;
	private String nmWriter;
	private String daWriter;
	private int cnHit;
	private int nbGroup;
	private int nbStep;
	private int nbIndent;

	public String getNbBoard() {
		return nbBoard;
	}

	public void setNbBoard(String nbBoard) {
		this.nbBoard = nbBoard;
	}

	public String getNmTitle() {
		return nmTitle;
	}

	public void setNmTitle(String nmTitle) {
		this.nmTitle = nmTitle;
	}

	public String getNmContent() {
		return nmContent;
	}

	public void setNmContent(String nmContent) {
		this.nmContent = nmContent;
	}

	public String getNmWriter() {
		return nmWriter;
	}

	public void setNmWriter(String nmWriter) {
		this.nmWriter = nmWriter;
	}

	public String getDaWriter() {
		return daWriter;
	}

	public void setDaWriter(String daWriter) {
		this.daWriter = daWriter;
	}

	public int getCnHit() {
		return cnHit;
	}

	public void setCnHit(int cnHit) {
		this.cnHit = cnHit;
	}

	public int getNbGroup() {
		return nbGroup;
	}

	public void setNbGroup(int nbGroup) {
		this.nbGroup = nbGroup;
	}

	public int getNbStep() {
		return nbStep;
	}

	public void setNbStep(int nbStep) {
		this.nbStep = nbStep;
	}

	public int getNbIndent() {
		return nbIndent;
	}

	public void setNbIndent(int nbIndent) {
		this.nbIndent = nbIndent;
	}
}
