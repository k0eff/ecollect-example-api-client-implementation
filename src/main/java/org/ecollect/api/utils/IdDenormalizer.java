package org.ecollect.api.utils;

public class IdDenormalizer {
    private static IdDenormalizer instance;

    public static IdDenormalizer buildSingleton(boolean active) {
        if (!(instance instanceof IdDenormalizer)) {
            instance = new IdDenormalizer(active);
        }
        return instance;
    }

    public static IdDenormalizer getInstance() throws Exception {
        if (instance instanceof IdDenormalizer) {
            return instance;
        }
        throw new Exception("IdNormalizer: no instance created. Method buildSingleton should be used before.");
    }





    public final String separator = "-";
    private boolean activeState = true;

    private IdDenormalizer(boolean activeState) {
        this.activeState = activeState;
    }

    private String defaultNormalizer(String oldId) {
        if (oldId == null) return null;
        if (this.activeState == false) return oldId;

        String[] parts = oldId.split(separator);
        String newId = "";
        for (int i=0; i<parts.length;i++) {
            if (i==0) {
                newId = parts[i] + "-";
                continue;
            }
            newId += parts[i];
        }
        return newId;
    }

    public boolean isActiveState() {
        return activeState;
    }












    public String customer(String oldId) {
        return defaultNormalizer(oldId);
    }
    public String claim(String oldId) {
        return defaultNormalizer(oldId);
    }
    public String file(String oldId) {
        return defaultNormalizer(oldId);
    }
    public String charge(String oldId) {
        return defaultNormalizer(oldId);
    }
    public String credit(String oldId) {
        return defaultNormalizer(oldId);
    }
    public String invoice(String oldId) {
        return defaultNormalizer(oldId);
    }
    public String payment(String oldId) {
        return defaultNormalizer(oldId);
    }






}
