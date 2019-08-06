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

    public static Quality getQualityFromIndex(int i){
        switch (i) {
            case 0:
                return Quality.BAD;
            case 1:
                return Quality.GOOD;
            case 2:
                return Quality.EXCELLENT;
            default:
                throw new RuntimeException("Unknown index:" + i);
        }
    }
}
