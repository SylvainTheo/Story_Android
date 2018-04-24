package com.example.sylvain.storyloupio;

/**
 * Created by sylvain on 30/11/2017.
 */

public class ChapterData {
    private int idChapter;
    private String title;
    private String content;
    private String titleProp1;
    private String titleProp2;
    private String titleProp3;
    private String titleProp4;
    private int idChapterProp1;
    private int idChapterProp2;
    private int idChapterProp3;
    private int idChapterProp4;
    private int idGame;

    public ChapterData(int idChapter, String title, String content, String titleProp1, int idChapterProp1, String titleProp2, int idChapterProp2, String titleProp3, int idChapterProp3, String titleProp4, int idChapterProp4, int idGame) {
        this.idChapter = idChapter;
        this.title = title;
        this.content = content;
        this.titleProp1 = titleProp1;
        this.titleProp2 = titleProp2;
        this.titleProp3 = titleProp3;
        this.titleProp4 = titleProp4;
        this.idChapterProp1 = idChapterProp1;
        this.idChapterProp2 = idChapterProp2;
        this.idChapterProp3 = idChapterProp3;
        this.idChapterProp4 = idChapterProp4;
        this.idGame = idGame;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitleProp1(String titleProp1) {
        this.titleProp1 = titleProp1;
    }

    public void setTitleProp2(String titleProp2) {
        this.titleProp2 = titleProp2;
    }

    public void setTitleProp3(String titleProp3) {
        this.titleProp3 = titleProp3;
    }

    public void setTitleProp4(String titleProp4) {
        this.titleProp4 = titleProp4;
    }

    public void setIdChapterProp1(int idChapterProp1) {
        this.idChapterProp1 = idChapterProp1;
    }

    public void setIdChapterProp2(int idChapterProp2) {
        this.idChapterProp2 = idChapterProp2;
    }

    public void setIdChapterProp3(int idChapterProp3) {
        this.idChapterProp3 = idChapterProp3;
    }

    public void setIdChapterProp4(int idChapterProp4) {
        this.idChapterProp4 = idChapterProp4;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTitleProp1() {
        return titleProp1;
    }

    public String getTitleProp2() {
        return titleProp2;
    }

    public String getTitleProp3() {
        return titleProp3;
    }

    public String getTitleProp4() {
        return titleProp4;
    }

    public int getIdChapterProp1() {
        return idChapterProp1;
    }

    public int getIdChapterProp2() {
        return idChapterProp2;
    }

    public int getIdChapterProp3() {
        return idChapterProp3;
    }

    public int getIdChapterProp4() {
        return idChapterProp4;
    }

    public int getIdGame(){ return idGame; }

    @Override
    public String toString() {
        return "ChapterData{" +
                "idChapter=" + idChapter +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", titleProp1='" + titleProp1 + '\'' +
                ", titleProp2='" + titleProp2 + '\'' +
                ", titleProp3='" + titleProp3 + '\'' +
                ", titleProp4='" + titleProp4 + '\'' +
                ", idChapterProp1=" + idChapterProp1 +
                ", idChapterProp2=" + idChapterProp2 +
                ", idChapterProp3=" + idChapterProp3 +
                ", idChapterProp4=" + idChapterProp4 +
                ", idGame=" + idGame +
                '}';
    }
}
