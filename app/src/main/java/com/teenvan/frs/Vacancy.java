package com.teenvan.frs;

/**
 * Created by navneet on 27/09/15.
 */
public class Vacancy {
    // Declaration of member variables
    private String mVacancyTitle;
    private String mVacancyType;
    private String mApplicationDeadline;
    private String mDesignation;

    public String getVacancyTitle() {
        return mVacancyTitle;
    }

    public void setVacancyTitle(String vacancyTitle) {
        mVacancyTitle = vacancyTitle;
    }

    public String getVacancyType() {
        return mVacancyType;
    }

    public void setVacancyType(String vacancyType) {
        mVacancyType = vacancyType;
    }

    public String getApplicationDeadline() {
        return mApplicationDeadline;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        mApplicationDeadline = applicationDeadline;
    }

    public String getDesignation() {
        return mDesignation;
    }

    public void setDesignation(String designation) {
        mDesignation = designation;
    }
}
