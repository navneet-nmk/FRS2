package com.teenvan.frs;

/**
 * Created by navneet on 26/10/15.
 */
public class Applicant {


    // Declaration of member variables
    protected String applicantName;
    protected String applicantEmail;
    protected String applicantInstitute;
    protected String applicantBtech;
    protected String applicantMtech;
    protected String applicantPhd;
    protected int applicantAge, applicantExperience , applicantResearch, applicantContact;
    protected String applicantDesignation;

    public String getApplicantMtech() {
        return applicantMtech;
    }

    public void setApplicantMtech(String applicantMtech) {
        this.applicantMtech = applicantMtech;
    }

    public String getApplicantBtech() {
        return applicantBtech;
    }

    public void setApplicantBtech(String applicantBtech) {
        this.applicantBtech = applicantBtech;
    }

    public String getApplicantPhd() {
        return applicantPhd;
    }

    public void setApplicantPhd(String applicantPhd) {
        this.applicantPhd = applicantPhd;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getApplicantDesignation(){
        return applicantDesignation;
    }

    public void setApplicantDesignation(String desig){
        applicantDesignation = desig;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantInstitute() {
        return applicantInstitute;
    }

    public void setApplicantInstitute(String applicantInstitute) {
        this.applicantInstitute = applicantInstitute;
    }

    public int getApplicantAge() {
        return applicantAge;
    }

    public void setApplicantAge(int applicantAge) {
        this.applicantAge = applicantAge;
    }

    public int getApplicantExperience() {
        return applicantExperience;
    }

    public void setApplicantExperience(int applicantExperience) {
        this.applicantExperience = applicantExperience;
    }

    public int getApplicantResearch() {
        return applicantResearch;
    }

    public void setApplicantResearch(int applicantResearch) {
        this.applicantResearch = applicantResearch;
    }

    public int getApplicantContact() {
        return applicantContact;
    }

    public void setApplicantContact(int applicantContact) {
        this.applicantContact = applicantContact;
    }



}
