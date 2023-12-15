package com.example.demo.entity;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 张旭康
 * @Date 2022/9/9
 */
public class TestDTO {

    private String phone;

    private String activityName;

    private Integer isOpen;

    private Integer experienceStrategy;

    private String selectedOneLevelSupplier;

    private String[] selectedTwoLevelSupplier;

    private boolean isNotice;

    @Override
    public String toString() {
        return "TestDTO{" +
                "phone='" + phone + '\'' +
                ", activityName='" + activityName + '\'' +
                ", isOpen=" + isOpen +
                ", experienceStrategy=" + experienceStrategy +
                ", selectedOneLevelSupplier='" + selectedOneLevelSupplier + '\'' +
                ", selectedTwoLevelSupplier=" + Arrays.toString(selectedTwoLevelSupplier) +
                ", isNotice=" + isNotice +
                '}';
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public void setExperienceStrategy(Integer experienceStrategy) {
        this.experienceStrategy = experienceStrategy;
    }

    public void setSelectedOneLevelSupplier(String selectedOneLevelSupplier) {
        this.selectedOneLevelSupplier = selectedOneLevelSupplier;
    }

    public void setSelectedTwoLevelSupplier(String[] selectedTwoLevelSupplier) {
        this.selectedTwoLevelSupplier = selectedTwoLevelSupplier;
    }

    public void setNotice(boolean notice) {
        isNotice = notice;
    }

    public String getPhone() {
        return phone;
    }

    public String getActivityName() {
        return activityName;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public Integer getExperienceStrategy() {
        return experienceStrategy;
    }

    public String getSelectedOneLevelSupplier() {
        return selectedOneLevelSupplier;
    }

    public String[] getSelectedTwoLevelSupplier() {
        return selectedTwoLevelSupplier;
    }

    public boolean isNotice() {
        return isNotice;
    }

    public TestDTO(String phone, String activityName, Integer isOpen, Integer experienceStrategy, String selectedOneLevelSupplier, String[] selectedTwoLevelSupplier, boolean isNotice) {
        this.phone = phone;
        this.activityName = activityName;
        this.isOpen = isOpen;
        this.experienceStrategy = experienceStrategy;
        this.selectedOneLevelSupplier = selectedOneLevelSupplier;
        this.selectedTwoLevelSupplier = selectedTwoLevelSupplier;
        this.isNotice = isNotice;
    }
}
