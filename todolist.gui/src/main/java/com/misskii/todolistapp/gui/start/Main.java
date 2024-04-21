package com.misskii.todolistapp.gui.start;

import com.misskii.todolistapp.utils.SSLUtil;

public class Main {
    public static void main(String[] args) {
        SSLUtil.disableCertificateValidation();
        Application.main(args);
    }
}
