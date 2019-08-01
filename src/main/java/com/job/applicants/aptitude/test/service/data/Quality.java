package com.job.applicants.aptitude.test.service.data;

public enum Quality {
    BAD(0), GOOD(1), EXCELLENT(2);

    private final int index;

    Quality(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }


}
