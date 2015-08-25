package com.xyz.office.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 记事本设置
 */
public class NotesSet implements java.io.Serializable {

	private int recId;
	private String notesBgcolor;
	private String notesFontStyle;
	private String notesFontSize;
	private String notesFontColor;
	private Boolean notesFontBold;
	private Boolean notesFontItalic;
	private Boolean notesFontUnderline;
	private Boolean notesFontStrikeout;

	public NotesSet() {
	}

	public NotesSet(int recId) {
		this.recId = recId;
	}

	public NotesSet(int recId, String notesBgcolor, String notesFontStyle,
			String notesFontSize, String notesFontColor, Boolean notesFontBold,
			Boolean notesFontItalic, Boolean notesFontUnderline,
			Boolean notesFontStrikeout) {
		this.recId = recId;
		this.notesBgcolor = notesBgcolor;
		this.notesFontStyle = notesFontStyle;
		this.notesFontSize = notesFontSize;
		this.notesFontColor = notesFontColor;
		this.notesFontBold = notesFontBold;
		this.notesFontItalic = notesFontItalic;
		this.notesFontUnderline = notesFontUnderline;
		this.notesFontStrikeout = notesFontStrikeout;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getNotesBgcolor() {
		return this.notesBgcolor;
	}

	public void setNotesBgcolor(String notesBgcolor) {
		this.notesBgcolor = notesBgcolor;
	}

	public String getNotesFontStyle() {
		return this.notesFontStyle;
	}

	public void setNotesFontStyle(String notesFontStyle) {
		this.notesFontStyle = notesFontStyle;
	}

	public String getNotesFontSize() {
		return this.notesFontSize;
	}

	public void setNotesFontSize(String notesFontSize) {
		this.notesFontSize = notesFontSize;
	}

	public String getNotesFontColor() {
		return this.notesFontColor;
	}

	public void setNotesFontColor(String notesFontColor) {
		this.notesFontColor = notesFontColor;
	}

	public Boolean getNotesFontBold() {
		return this.notesFontBold;
	}

	public void setNotesFontBold(Boolean notesFontBold) {
		this.notesFontBold = notesFontBold;
	}

	public Boolean getNotesFontItalic() {
		return this.notesFontItalic;
	}

	public void setNotesFontItalic(Boolean notesFontItalic) {
		this.notesFontItalic = notesFontItalic;
	}

	public Boolean getNotesFontUnderline() {
		return this.notesFontUnderline;
	}

	public void setNotesFontUnderline(Boolean notesFontUnderline) {
		this.notesFontUnderline = notesFontUnderline;
	}

	public Boolean getNotesFontStrikeout() {
		return this.notesFontStrikeout;
	}

	public void setNotesFontStrikeout(Boolean notesFontStrikeout) {
		this.notesFontStrikeout = notesFontStrikeout;
	}

}
